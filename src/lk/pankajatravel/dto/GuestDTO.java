/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dto;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author Ronila
 */
public class GuestDTO {

    private String guestID;
    private String country;
    private String firstName;
    private String lastName;
    private String mobileNum;
    private String emailAddress;
    private String flightNum;
    private String airport;
    private String arriveDate;
    private String arriveTime;
    private String departureDate;
    private String departureTime;

    public GuestDTO() {
    }

    public GuestDTO(String guestID, String country, String firstName, String lastName, String mobileNum, String emailAddress, String flightNum, String airport, String arriveDate, String arriveTime, String departureDate, String departureTime) {
        this.guestID = guestID;
        this.country = country;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNum = mobileNum;
        this.emailAddress = emailAddress;
        this.flightNum = flightNum;
        this.airport = airport;
        this.arriveDate = arriveDate;
        this.arriveTime = arriveTime;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getArriveDate() {
        return arriveDate;
    }

    public void setArriveDate(String arriveDate) {
        this.arriveDate = arriveDate;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    
}
