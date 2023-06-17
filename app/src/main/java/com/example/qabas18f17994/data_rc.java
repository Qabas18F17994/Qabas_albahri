package com.example.qabas18f17994;

public class data_rc {


    private String Type;// type like: rent car/tour guide /tourist activities
    private String Car_Image ;
    private String Car_ID ;
    private String Car_Name;
    private String Car_Company;
    private String Car_Informations;
    private String Car_Instructions;
    private String Car_Rent_price;
    private String keyq;



    public data_rc()
    {}

    public String getCar_Image() {
        return Car_Image;
    }

    public void setCar_Image(String car_Image) {
        Car_Image = car_Image;
    }

    public String getCar_ID() {
        return Car_ID;
    }

    public void setCar_ID(String car_ID) {
        Car_ID = car_ID;
    }

    public String getCar_Name() {
        return Car_Name;
    }

    public void setCar_Name(String car_Name) {
        Car_Name = car_Name;
    }

    public String getCar_Company() {
        return Car_Company;
    }

    public void setCar_Company(String car_Company) {
        Car_Company = car_Company;
    }

    public String getCar_Informations() {
        return Car_Informations;
    }

    public void setCar_Informations(String car_Informations) {
        Car_Informations = car_Informations;
    }

    public String getCar_Instructions() {
        return Car_Instructions;
    }

    public void setCar_Instructions(String car_Instructions) {
        Car_Instructions = car_Instructions;
    }

    public String getCar_Rent_price() {
        return Car_Rent_price;
    }

    public void setCar_Rent_price(String car_Rent_price) {
        Car_Rent_price = car_Rent_price;
    }

    public String getKeyq() {
        return keyq;
    }

    public void setKeyq(String keyq) {
        this.keyq = keyq;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public data_rc(String car_Image, String car_ID, String car_Name, String car_Company, String car_Informations, String car_Instructions, String car_Rent_price) {
        Car_Image = car_Image;
        Car_ID = car_ID;
        Car_Name = car_Name;
        Car_Company = car_Company;
        Car_Informations = car_Informations;
        Car_Instructions = car_Instructions;
        Car_Rent_price = car_Rent_price;
    }
}
