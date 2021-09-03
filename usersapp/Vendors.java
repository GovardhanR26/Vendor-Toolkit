package com.example.usersapp;
public class Vendors {

    // string variable for
    // storing employee name.
    private String vendorName;

    // string variable for storing
    // employee contact number
    private String vendorContactNumber;

    // string variable for storing
    // employee address.
    private String vendoriso;

    private String vendormail;


    private String latitude ;
    private String longitude ;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public Vendors() {

    }

    // created getter and setter methods
    // for all our variables.
    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorContactNumber() {
        return vendorContactNumber;
    }

    public void setVendorContactNumber(String vendorContactNumber) {
        this.vendorContactNumber = vendorContactNumber;
    }

    public String getVendorIso() {
        return vendoriso;
    }

    public void setVendorIso(String vendoriso) {
        this.vendoriso = vendoriso;
    }

    public String getVendorEmail() {
        return vendormail;
    }

    public void setVendorEmail(String vendormail) {
        this.vendormail = vendormail;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
