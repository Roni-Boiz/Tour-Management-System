/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dto;

/**
 *
 * @author Ronila
 */
public class TourDetailsDTO {
    private String date;
    private String place;
    private String noNights;
    private String hotel;

    public TourDetailsDTO() {
    }

    public TourDetailsDTO(String date, String place, String noNights, String hotel) {
        this.date = date;
        this.place = place;
        this.noNights = noNights;
        this.hotel = hotel;
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

    public String getNoNights() {
        return noNights;
    }

    public void setNoNights(String noNights) {
        this.noNights = noNights;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    
    
}
