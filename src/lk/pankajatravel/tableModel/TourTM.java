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
public class TourTM {
    private String TourID;
                private String Guest_Name;
    private String Guide_Name;
    private String Start_Date;
    private String End_Date;
    private String Pickup_From;
    private String Drop_To;
    private String Adults;
    private String Children;
    private String Rooms;
    private String Room_Type;
    private String Meal_Type;

    public TourTM() {
    }

    public TourTM(String TourID, String Guest_Name, String Guide_Name, String Start_Date, String End_Date, String Pickup_From, String Drop_To, String Adults, String Children, String Rooms, String Room_Type, String Meal_Type) {
        this.TourID = TourID;
        this.Guest_Name = Guest_Name;
        this.Guide_Name = Guide_Name;
        this.Start_Date = Start_Date;
        this.End_Date = End_Date;
        this.Pickup_From = Pickup_From;
        this.Drop_To = Drop_To;
        this.Adults = Adults;
        this.Children = Children;
        this.Rooms = Rooms;
        this.Room_Type = Room_Type;
        this.Meal_Type = Meal_Type;
    }

    public String getTourID() {
        return TourID;
    }

    public void setTourID(String TourID) {
        this.TourID = TourID;
    }

    public String getGuest_Name() {
        return Guest_Name;
    }

    public void setGuest_Name(String Guest_Name) {
        this.Guest_Name = Guest_Name;
    }

    public String getGuide_Name() {
        return Guide_Name;
    }

    public void setGuide_Name(String Guide_Name) {
        this.Guide_Name = Guide_Name;
    }

    public String getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(String Start_Date) {
        this.Start_Date = Start_Date;
    }

    public String getEnd_Date() {
        return End_Date;
    }

    public void setEnd_Date(String End_Date) {
        this.End_Date = End_Date;
    }

    public String getPickup_From() {
        return Pickup_From;
    }

    public void setPickup_From(String Pickup_From) {
        this.Pickup_From = Pickup_From;
    }

    public String getDrop_To() {
        return Drop_To;
    }

    public void setDrop_To(String Drop_To) {
        this.Drop_To = Drop_To;
    }

    public String getAdults() {
        return Adults;
    }

    public void setAdults(String Adults) {
        this.Adults = Adults;
    }

    public String getChildren() {
        return Children;
    }

    public void setChildren(String Children) {
        this.Children = Children;
    }

    public String getRooms() {
        return Rooms;
    }

    public void setRooms(String Rooms) {
        this.Rooms = Rooms;
    }

    public String getRoom_Type() {
        return Room_Type;
    }

    public void setRoom_Type(String Room_Type) {
        this.Room_Type = Room_Type;
    }

    public String getMeal_Type() {
        return Meal_Type;
    }

    public void setMeal_Type(String Meal_Type) {
        this.Meal_Type = Meal_Type;
    }
    
    

    
}
