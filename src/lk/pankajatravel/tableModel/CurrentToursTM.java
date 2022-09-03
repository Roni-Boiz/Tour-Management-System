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
public class CurrentToursTM {

    private String Tour_ID;
    private String Start_Date;
    private String End_Date;
    private String Guest_Name;
    private String Guide_Name;
    private String Members;

    public CurrentToursTM() {
    }

    public CurrentToursTM(String Tour_ID, String Start_Date, String End_Date, String Guest_Name, String Guide_Name, String Members) {
        this.Tour_ID = Tour_ID;
        this.Start_Date = Start_Date;
        this.End_Date = End_Date;
        this.Guest_Name = Guest_Name;
        this.Guide_Name = Guide_Name;
        this.Members = Members;
    }

    public String getTour_ID() {
        return Tour_ID;
    }

    public void setTour_ID(String Tour_ID) {
        this.Tour_ID = Tour_ID;
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

    public String getMembers() {
        return Members;
    }

    public void setMembers(String Members) {
        this.Members = Members;
    }
    

    
}
