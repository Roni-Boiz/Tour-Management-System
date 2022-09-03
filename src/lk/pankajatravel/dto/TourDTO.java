/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dto;

import java.util.ArrayList;

/**
 *
 * @author Ronila
 */
public class TourDTO {

    private String tourID;
    private String GuestID;
    private String tourStartDate;
    private String tourEndDate;
    private String guestPickupOn;
    private String guestDropOn;
    private String noAdults;
    private String noChildren;
    private String rooms;
    private String roomType;
    private String mealType;
    private String guideID;
    private ArrayList<TourDetailsDTO> allTourDetails;

    public TourDTO() {
    }

    public TourDTO(String tourID, String GuestID, String tourStartDate, String tourEndDate, String guestPickupOn, String guestDropOn, String noAdults, String noChildren, String rooms, String roomType, String mealType, String guideID, ArrayList<TourDetailsDTO> allTourDetails) {
        this.tourID = tourID;
        this.GuestID = GuestID;
        this.tourStartDate = tourStartDate;
        this.tourEndDate = tourEndDate;
        this.guestPickupOn = guestPickupOn;
        this.guestDropOn = guestDropOn;
        this.noAdults = noAdults;
        this.noChildren = noChildren;
        this.rooms = rooms;
        this.roomType = roomType;
        this.mealType = mealType;
        this.guideID = guideID;
        this.allTourDetails = allTourDetails;
    }
    
    public TourDTO(String tourID, String GuestID, String tourStartDate, String tourEndDate, String guestPickupOn, String guestDropOn, String noAdults, String noChildren, String rooms, String roomType, String mealType, String guideID) {
        this.tourID = tourID;
        this.GuestID = GuestID;
        this.tourStartDate = tourStartDate;
        this.tourEndDate = tourEndDate;
        this.guestPickupOn = guestPickupOn;
        this.guestDropOn = guestDropOn;
        this.noAdults = noAdults;
        this.noChildren = noChildren;
        this.rooms = rooms;
        this.roomType = roomType;
        this.mealType = mealType;
        this.guideID = guideID;
    }
    
     public TourDTO(String tourID, String GuestID, String tourStartDate, String tourEndDate, String guestPickupOn, String guestDropOn, String noAdults, String noChildren, String guideID) {
        this.tourID = tourID;
        this.GuestID = GuestID;
        this.tourStartDate = tourStartDate;
        this.tourEndDate = tourEndDate;
        this.guestPickupOn = guestPickupOn;
        this.guestDropOn = guestDropOn;
        this.noAdults = noAdults;
        this.noChildren = noChildren;
        this.guideID = guideID;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getGuestID() {
        return GuestID;
    }

    public void setGuestID(String GuestID) {
        this.GuestID = GuestID;
    }

    public String getTourStartDate() {
        return tourStartDate;
    }

    public void setTourStartDate(String tourStartDate) {
        this.tourStartDate = tourStartDate;
    }

    public String getTourEndDate() {
        return tourEndDate;
    }

    public void setTourEndDate(String tourEndDate) {
        this.tourEndDate = tourEndDate;
    }

    public String getGuestPickupOn() {
        return guestPickupOn;
    }

    public void setGuestPickupOn(String guestPickupOn) {
        this.guestPickupOn = guestPickupOn;
    }

    public String getGuestDropOn() {
        return guestDropOn;
    }

    public void setGuestDropOn(String guestDropOn) {
        this.guestDropOn = guestDropOn;
    }

    public String getNoAdults() {
        return noAdults;
    }

    public void setNoAdults(String noAdults) {
        this.noAdults = noAdults;
    }

    public String getNoChildren() {
        return noChildren;
    }

    public void setNoChildren(String noChildren) {
        this.noChildren = noChildren;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getGuideID() {
        return guideID;
    }

    public void setGuideID(String guideID) {
        this.guideID = guideID;
    }

    public ArrayList<TourDetailsDTO> getAllTourDetails() {
        return allTourDetails;
    }

    public void setAllTourDetails(ArrayList<TourDetailsDTO> allTourDetails) {
        this.allTourDetails = allTourDetails;
    }

    
}
