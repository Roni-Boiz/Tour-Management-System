/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.entity;

/**
 *
 * @author Ronila
 */
public class TourDetails {
    private String tourID;
    private String date;
    private String place;
    private String nights;
    private String hotel;

    public TourDetails() {
    }

    public TourDetails(String tourID, String date, String place, String nights, String hotel) {
        this.tourID = tourID;
        this.date = date;
        this.place = place;
        this.nights = nights;
        this.hotel = hotel;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getNights() {
        return nights;
    }

    public void setNights(String nights) {
        this.nights = nights;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    
}
