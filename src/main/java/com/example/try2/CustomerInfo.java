package com.example.try2;

public class CustomerInfo {

    // string variable for
    // storing employee name.
    private String customerName;

    // string variable for storing
    // employee contact number
    private double customerLat;

    // string variable for storing
    // employee address.
    private double customerLong;

    // an empty constructor is
    // required when using
    // Firebase Realtime Database.
    public CustomerInfo() {

    }

    // created getter and setter methods
    // for all our variables.
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String custName) {
        this.customerName = custName;
    }

    public double getCustomerLat() {
        return customerLat;
    }

    public void setCustomerLat(double custLat) {
        this.customerLat = custLat;
    }

    public double getCustomerLong() {
        return customerLong;
    }

    public void setCustomerLong(double custLong) {
        this.customerLong = custLong;
    }
}

