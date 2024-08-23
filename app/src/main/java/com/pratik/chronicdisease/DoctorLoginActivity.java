package com.pratik.chronicdisease;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class DoctorLoginActivity extends AppCompatActivity {
    private EditText etDoctorEmail, etDoctorPassword;
    private Button btnDoctorLogin;
    private DatabaseReference doctorsDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        etDoctorEmail = findViewById(R.id.etDoctorEmail);
        etDoctorPassword = findViewById(R.id.etDoctorPassword);
        btnDoctorLogin = findViewById(R.id.btnDoctorLogin);

        // Initialize Firebase Database reference
        doctorsDatabase = FirebaseDatabase.getInstance().getReference("Doctors");

        btnDoctorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDoctor();
            }
        });
    }

    private void loginDoctor() {
        String email = etDoctorEmail.getText().toString().trim();
        String password = etDoctorPassword.getText().toString().trim();

        // Validation for required fields
        if (TextUtils.isEmpty(email)) {
            etDoctorEmail.setError("Email is required");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            etDoctorPassword.setError("Password is required");
            return;
        }

        // Check if the doctor exists with the provided email and password
        doctorsDatabase.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        DoctorDetails doctor = snapshot.getValue(DoctorDetails.class);
                        if (doctor != null && doctor.getPassword().equals(password)) {
                            // Successful login
                            Intent intent = new Intent(DoctorLoginActivity.this, DoctorDashboardActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(DoctorLoginActivity.this, "Invalid password", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(DoctorLoginActivity.this, "Doctor not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DoctorLoginActivity.this, "Database error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
