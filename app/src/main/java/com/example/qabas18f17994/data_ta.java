package com.example.qabas18f17994;

public class data_ta {

    private String Type;// type like: rent car/tour guide /tourist activities

    private String Activity_Image ;
    private String Activity_ID ;
    private String Activity_Name;
    private String Activity_location;
    private String Activity_Informations;
    private String Activity_Instructions;
    private String Activity_Price;
    private String Activity_day;
    private String keyq;



    public data_ta()
    {}

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getActivity_Image() {
        return Activity_Image;
    }

    public void setActivity_Image(String activity_Image) {
        Activity_Image = activity_Image;
    }

    public String getActivity_ID() {
        return Activity_ID;
    }

    public void setActivity_ID(String activity_ID) {
        Activity_ID = activity_ID;
    }

    public String getActivity_Name() {
        return Activity_Name;
    }

    public void setActivity_Name(String activity_Name) {
        Activity_Name = activity_Name;
    }

    public String getActivity_location() {
        return Activity_location;
    }

    public void setActivity_location(String activity_location) {
        Activity_location = activity_location;
    }

    public String getActivity_Informations() {
        return Activity_Informations;
    }

    public void setActivity_Informations(String activity_Informations) {
        Activity_Informations = activity_Informations;
    }

    public String getActivity_Instructions() {
        return Activity_Instructions;
    }

    public void setActivity_Instructions(String activity_Instructions) {
        Activity_Instructions = activity_Instructions;
    }

    public String getActivity_Price() {
        return Activity_Price;
    }

    public void setActivity_Price(String activity_Price) {
        Activity_Price = activity_Price;
    }

    public String getActivity_day() {
        return Activity_day;
    }

    public void setActivity_day(String activity_day) {
        Activity_day = activity_day;
    }

    public String getKeyq() {
        return keyq;
    }

    public void setKeyq(String keyq) {
        this.keyq = keyq;
    }



    public data_ta(String activity_Image, String activity_ID, String activity_Name, String activity_location, String activity_Informations, String activity_Instructions, String activity_Price, String activity_day) {
        Activity_Image = activity_Image;
        Activity_ID = activity_ID;
        Activity_Name = activity_Name;
        Activity_location = activity_location;
        Activity_Informations = activity_Informations;
        Activity_Instructions = activity_Instructions;
        Activity_Price = activity_Price;
        Activity_day = activity_day;
    }
}
