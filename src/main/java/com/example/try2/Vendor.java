package com.example.try2;

public class Vendor {

    private String name;
    private String category;
    private String email;
    private Double lati;
    private Double longi;

    public Vendor() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLati() {
        return lati;
    }

    public void setLati(Double lati) {
        this.lati = lati;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }

    public Vendor(String name, String category, String email, Double lati, Double longi) {
        this.name = name;
        this.category = category;
        this.email = email;
        this.lati = lati;
        this.longi = longi;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
