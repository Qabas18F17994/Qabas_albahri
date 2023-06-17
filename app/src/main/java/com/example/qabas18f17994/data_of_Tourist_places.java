package com.example.qabas18f17994;

//any thing we write here will be thw same titles in realtime database and firstore
public class data_of_Tourist_places {

    private String place_Image ;
    private String place_ID ;
    private String place_Name;
    private String place_location;
    private String place_Informations;
    private String place_Instructions;

    private String keyq;



    public data_of_Tourist_places()
    {}



    public String getPlace_Image() {
        return place_Image;
    }

    public String getPlace_ID() {
        return place_ID;
    }

    public String getPlace_Name() {
        return place_Name;
    }

    public String getPlace_location() {
        return place_location;
    }

    public String getPlace_Informations() {
        return place_Informations;
    }

    public String getPlace_Instructions() {
        return place_Instructions;
    }

    public String getKeyq() {
        return keyq;
    }

    public void setKeyq(String keyq) {
        this.keyq = keyq;
    }

    public data_of_Tourist_places(String place_Image, String place_ID, String place_Name, String place_location, String place_Informations, String place_Instructions) {
        this.place_Image = place_Image;
        this.place_ID = place_ID;
        this.place_Name = place_Name;
        this.place_location = place_location;
        this.place_Informations = place_Informations;
        this.place_Instructions = place_Instructions;
    }
}
