// Doctor.java
package com.pratik.chronicdisease;

public class DoctorDetails {
    private String doctorId;
    private String name;
    private String address;
    private String email;
    private String password;
    private String qualification;
    private String specialization;

    public DoctorDetails() {
        // Default constructor required for calls to DataSnapshot.getValue(Doctor.class)
    }

    public DoctorDetails(String doctorId, String name, String address, String email, String password, String qualification, String specialization) {
        this.doctorId = doctorId;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.qualification = qualification;
        this.specialization = specialization;
    }

    // Getters and Setters
    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
