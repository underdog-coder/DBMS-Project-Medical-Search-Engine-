package com.example.rohit.dbmsproject;

class MedicineData {
    private String med_name;
    private String unit;
    private String price;
    private String category;
    private String manufacturer;

    public MedicineData(String med_name, String unit, String price, String category, String manufacturer) {
        this.med_name = med_name;
        this.unit = unit;
        this.price = price;
        this.category = category;
        this.manufacturer = manufacturer;
    }

    public String getMed_name() {
        return med_name;
    }

    public void setMed_name(String med_name) {
        this.med_name = med_name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
