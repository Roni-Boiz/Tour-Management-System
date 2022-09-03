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
public class GuestTM {
    private String GstID;
    private String Country;
    private String F_Name;
    private String L_Name;
    private String Mobile_No;
    private String Email;
    private String Flight_No;
    private String Airport;
    private String Arrive_Date;
    private String Arrive_Time;
    private String Departure_Date;
    private String Departure_Time;

    public GuestTM() {
    }

    public GuestTM(String GstID, String Country, String F_Name, String L_Name, String Mobile_No, String Email, String Flight_No, String Airport, String Arrive_Date, String Arrive_Time, String Departure_Date, String Departure_Time) {
        this.GstID = GstID;
        this.Country = Country;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.Mobile_No = Mobile_No;
        this.Email = Email;
        this.Flight_No = Flight_No;
        this.Airport = Airport;
        this.Arrive_Date = Arrive_Date;
        this.Arrive_Time = Arrive_Time;
        this.Departure_Date = Departure_Date;
        this.Departure_Time = Departure_Time;
    }

    public String getGstID() {
        return GstID;
    }

    public void setGstID(String GstID) {
        this.GstID = GstID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getF_Name() {
        return F_Name;
    }

    public void setF_Name(String F_Name) {
        this.F_Name = F_Name;
    }

    public String getL_Name() {
        return L_Name;
    }

    public void setL_Name(String L_Name) {
        this.L_Name = L_Name;
    }

    public String getMobile_No() {
        return Mobile_No;
    }

    public void setMobile_No(String Mobile_No) {
        this.Mobile_No = Mobile_No;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getFlight_No() {
        return Flight_No;
    }

    public void setFlight_No(String Flight_No) {
        this.Flight_No = Flight_No;
    }

    public String getAirport() {
        return Airport;
    }

    public void setAirport(String Airport) {
        this.Airport = Airport;
    }

    public String getArrive_Date() {
        return Arrive_Date;
    }

    public void setArrive_Date(String Arrive_Date) {
        this.Arrive_Date = Arrive_Date;
    }

    public String getArrive_Time() {
        return Arrive_Time;
    }

    public void setArrive_Time(String Arrive_Time) {
        this.Arrive_Time = Arrive_Time;
    }

    public String getDeparture_Date() {
        return Departure_Date;
    }

    public void setDeparture_Date(String Departure_Date) {
        this.Departure_Date = Departure_Date;
    }

    public String getDeparture_Time() {
        return Departure_Time;
    }

    public void setDeparture_Time(String Departure_Time) {
        this.Departure_Time = Departure_Time;
    }

    
    
}
