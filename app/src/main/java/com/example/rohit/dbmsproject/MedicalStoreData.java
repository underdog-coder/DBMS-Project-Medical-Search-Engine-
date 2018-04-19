package com.example.rohit.dbmsproject;

public class MedicalStoreData {
    private String medicalStoreName;
    private String medicalStoreAddress;

    public MedicalStoreData(String medicalStoreName, String medicalStoreAddress) {
        this.medicalStoreName = medicalStoreName;
        this.medicalStoreAddress = medicalStoreAddress;
    }

    public String getMedicalStoreName() {
        return medicalStoreName;
    }

    public void setMedicalStoreName(String medicalStoreName) {
        this.medicalStoreName = medicalStoreName;
    }

    public String getMedicalStoreAddress() {
        return medicalStoreAddress;
    }

    public void setMedicalStoreAddress(String medicalStoreAddress) {
        this.medicalStoreAddress = medicalStoreAddress;
    }
}
