package com.example.qabas18f17994;

public class data_tour_guid {

    private String Type;// type like: rent car/tour guide /tourist activities
    private String ID ;
    private String FULLName ;
    private String EMAIL;
    private String PHONE;
    private String GENDER;
    private String INFORMATION;
    private String PRICE;

    public data_tour_guid()
    {}

    public data_tour_guid(String ID, String FULLName, String EMAIL, String PHONE, String GENDER, String INFORMATION, String PRICE) {
        this.ID = ID;
        this.FULLName = FULLName;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
        this.GENDER = GENDER;
        this.INFORMATION = INFORMATION;
        this.PRICE = PRICE;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFULLName() {
        return FULLName;
    }

    public void setFULLName(String FULLName) {
        this.FULLName = FULLName;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getINFORMATION() {
        return INFORMATION;
    }

    public void setINFORMATION(String INFORMATION) {
        this.INFORMATION = INFORMATION;
    }

    public String getPRICE() {
        return PRICE;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }
}
