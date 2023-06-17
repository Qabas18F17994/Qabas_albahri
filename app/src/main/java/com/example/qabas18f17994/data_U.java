package com.example.qabas18f17994;

public class data_U {

    private String ID ;
    private String FULLName ;
    private String EMAIL;
    private String PHONE;
    private String PASSWORD;
    private String GENDER;
    private String TYPE;


    public data_U()
    {}

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

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }



    public data_U(String ID, String FULLName, String EMAIL, String PHONE, String PASSWORD, String GENDER, String TYPE) {
        this.ID = ID;
        this.FULLName = FULLName;
        this.EMAIL = EMAIL;
        this.PHONE = PHONE;
        this.PASSWORD = PASSWORD;
        this.GENDER = GENDER;
        this.TYPE = TYPE;

    }
}
