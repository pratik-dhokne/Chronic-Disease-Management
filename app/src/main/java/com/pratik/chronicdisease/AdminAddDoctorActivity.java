package com.pratik.chronicdisease;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminAddDoctorActivity extends AppCompatActivity {
    private EditText etDoctorName, etDoctorAddress, etDoctorEmail, etDoctorPassword, etDoctorQualification, etDoctorSpecialization;
    private Button btnAddDoctor;
    private DatabaseReference doctorsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add_doctor);

        etDoctorName = findViewById(R.id.etDoctorName);
        etDoctorAddress = findViewById(R.id.etDoctorAddress);
        etDoctorEmail = findViewById(R.id.etDoctorEmail);
        etDoctorPassword = findViewById(R.id.etDoctorPassword);
        etDoctorQualification = findViewById(R.id.etDoctorQualification);
        etDoctorSpecialization = findViewById(R.id.etDoctorSpecialization);
        btnAddDoctor = findViewById(R.id.btnAddDoctor);

        // Initialize Firebase Database reference
        doctorsDatabase = FirebaseDatabase.getInstance().getReference("Doctors");

        btnAddDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDoctor();
            }
        });

        // Apply edge-to-edge content
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addDoctor() {
        String doctorName = etDoctorName.getText().toString().trim();
        String doctorAddress = etDoctorAddress.getText().toString().trim();
        String doctorEmail = etDoctorEmail.getText().toString().trim();
        String doctorPassword = etDoctorPassword.getText().toString().trim();
        String doctorQualification = etDoctorQualification.getText().toString().trim();
        String doctorSpecialization = etDoctorSpecialization.getText().toString().trim();

        // Validation for required fields
        if (TextUtils.isEmpty(doctorName)) {
            etDoctorName.setError("Doctor name is required");
            return;
        }
        if (TextUtils.isEmpty(doctorAddress)) {
            etDoctorAddress.setError("Doctor address is required");
            return;
        }
        if (TextUtils.isEmpty(doctorEmail)) {
            etDoctorEmail.setError("Doctor email is required");
            return;
        }
        if (TextUtils.isEmpty(doctorPassword)) {
            etDoctorPassword.setError("Password is required");
            return;
        }
        if (TextUtils.isEmpty(doctorQualification)) {
            etDoctorQualification.setError("Qualification is required");
            return;
        }
        if (TextUtils.isEmpty(doctorSpecialization)) {
            etDoctorSpecialization.setError("Specialization is required");
            return;
        }

        // Generate a unique ID for the doctor
        String doctorId = doctorsDatabase.push().getKey();

        // Create a new doctor object
        DoctorDetails doctor = new DoctorDetails(doctorId, doctorName, doctorAddress, doctorEmail, doctorPassword, doctorQualification, doctorSpecialization);

        // Save the doctor object in the database
        if (doctorId != null) {
            doctorsDatabase.child(doctorId).setValue(doctor)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AdminAddDoctorActivity.this, "Doctor added successfully", Toast.LENGTH_SHORT).show();
                            clearFields();
                        } else {
                            Toast.makeText(AdminAddDoctorActivity.this, "Failed to add doctor", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void clearFields() {
        etDoctorName.setText("");
        etDoctorAddress.setText("");
        etDoctorEmail.setText("");
        etDoctorPassword.setText("");
        etDoctorQualification.setText("");
        etDoctorSpecialization.setText("");
    }
}
