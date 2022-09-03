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
public class Tour {
    private String tourID;
    private String guestID;
    private String guideID;
    private String startDate;
    private String endDate;
    private String pickupOn;
    private String dropOn;
    private String adults;
    private String children;
    private String rooms;
    private String roomType;
    private String mealType;

    public Tour() {
    }

    public Tour(String tourID, String guestID, String guideID, String startDate, String endDate, String pickupOn, String dropOn, String adults, String children, String rooms, String roomType, String mealType) {
        this.tourID = tourID;
        this.guestID = guestID;
        this.guideID = guideID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pickupOn = pickupOn;
        this.dropOn = dropOn;
        this.adults = adults;
        this.children = children;
        this.rooms = rooms;
        this.roomType = roomType;
        this.mealType = mealType;
    }

    public String getTourID() {
        return tourID;
    }

    public void setTourID(String tourID) {
        this.tourID = tourID;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public String getGuideID() {
        return guideID;
    }

    public void setGuideID(String guideID) {
        this.guideID = guideID;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPickupOn() {
        return pickupOn;
    }

    public void setPickupOn(String pickupOn) {
        this.pickupOn = pickupOn;
    }

    public String getDropOn() {
        return dropOn;
    }

    public void setDropOn(String dropOn) {
        this.dropOn = dropOn;
    }

    public String getAdults() {
        return adults;
    }

    public void setAdults(String adults) {
        this.adults = adults;
    }

    public String getChildren() {
        return children;
    }

    public void setChildren(String children) {
        this.children = children;
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

    
}
