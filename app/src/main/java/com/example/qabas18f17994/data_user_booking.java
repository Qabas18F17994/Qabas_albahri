package com.example.qabas18f17994;

public class data_user_booking {

  private   String FULLName;
     String PHONE;
     String Booking_Type;// type like: rent car/tour guide /tourist activities
     String Booking_name;//the name of rent car/tour guide /tourist activities
      String Booking_Type_id;
      String price;
     String Day_of_Booking;
       String Total_Price;
     String Quantity;

    //decomentID i used for delete the booking
    String decomentID;

    public data_user_booking() {
    }

    public data_user_booking(String FULLName, String PHONE, String booking_Type, String booking_name, String booking_Type_id, String price, String day_of_Booking, String total_Price, String quantity) {
        this.FULLName = FULLName;
        this.PHONE = PHONE;
        Booking_Type = booking_Type;
        Booking_name = booking_name;
        Booking_Type_id = booking_Type_id;
        this.price = price;
        Day_of_Booking = day_of_Booking;
        Total_Price = total_Price;
        Quantity = quantity;
    }

    public String getFULLName() {
        return FULLName;
    }

    public void setFULLName(String FULLName) {
        this.FULLName = FULLName;
    }

    public String getPHONE() {
        return PHONE;
    }

    public void setPHONE(String PHONE) {
        this.PHONE = PHONE;
    }

    public String getBooking_Type() {
        return Booking_Type;
    }

    public void setBooking_Type(String booking_Type) {
        Booking_Type = booking_Type;
    }

    public String getBooking_name() {
        return Booking_name;
    }

    public void setBooking_name(String booking_name) {
        Booking_name = booking_name;
    }

    public String getBooking_Type_id() {
        return Booking_Type_id;
    }

    public void setBooking_Type_id(String booking_Type_id) {
        Booking_Type_id = booking_Type_id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDay_of_Booking() {
        return Day_of_Booking;
    }

    public void setDay_of_Booking(String day_of_Booking) {
        Day_of_Booking = day_of_Booking;
    }

    public String getTotal_Price() {
        return Total_Price;
    }

    public void setTotal_Price(String total_Price) {
        Total_Price = total_Price;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getDecomentID() {
        return decomentID;
    }

    public void setDecomentID(String decomentID) {
        this.decomentID = decomentID;
    }
}
