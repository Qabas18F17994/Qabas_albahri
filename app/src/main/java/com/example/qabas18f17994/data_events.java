package com.example.qabas18f17994;

public class data_events {

    private String Event_Image ;
    private String Event_ID ;
    private String Event_Name;
    private String Event_location;
    private String Event_Informations;
    private String keyq;



    public data_events()
    {}

    public data_events(String event_Image, String event_ID, String event_Name, String event_location, String event_Informations) {
        Event_Image = event_Image;
        Event_ID = event_ID;
        Event_Name = event_Name;
        Event_location = event_location;
        Event_Informations = event_Informations;
    }

    public String getEvent_Image() {
        return Event_Image;
    }

    public void setEvent_Image(String event_Image) {
        Event_Image = event_Image;
    }

    public String getEvent_ID() {
        return Event_ID;
    }

    public void setEvent_ID(String event_ID) {
        Event_ID = event_ID;
    }

    public String getEvent_Name() {
        return Event_Name;
    }

    public void setEvent_Name(String event_Name) {
        Event_Name = event_Name;
    }

    public String getEvent_location() {
        return Event_location;
    }

    public void setEvent_location(String event_location) {
        Event_location = event_location;
    }

    public String getEvent_Informations() {
        return Event_Informations;
    }

    public void setEvent_Informations(String event_Informations) {
        Event_Informations = event_Informations;
    }

    public String getKeyq() {
        return keyq;
    }

    public void setKeyq(String keyq) {
        this.keyq = keyq;
    }
}
