/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.tableModel;

/**
 *
 * @author Ronila
 */
public class TourDetailsTM {
    private String Date;
    private String Place;
    private String No_of_Nights;
    private String Hotel;

    public TourDetailsTM() {
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String Place) {
        this.Place = Place;
    }

    public String getNo_of_Nights() {
        return No_of_Nights;
    }

    public void setNo_of_Nights(String No_of_Nights) {
        this.No_of_Nights = No_of_Nights;
    }

    public String getHotel() {
        return Hotel;
    }

    public void setHotel(String Hotel) {
        this.Hotel = Hotel;
    }

    public TourDetailsTM(String Date, String Place, String No_of_Nights, String Hotel) {
        this.Date = Date;
        this.Place = Place;
        this.No_of_Nights = No_of_Nights;
        this.Hotel = Hotel;
    }

    
}
