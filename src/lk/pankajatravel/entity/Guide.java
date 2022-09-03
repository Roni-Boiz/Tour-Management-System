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
public class Guide {
    private String gidID;
    private String fName;
    private String lName;
    private String NIC;
    private String Address;
    private int YOE;
    private String mobileNo;
    private String email;
    private String vehicle;
    private String guideLicence;

    public Guide() {
    }

    public Guide(String gidID, String fName, String lName, String NIC, String Address, int YOE, String mobileNo, String email, String vehicle, String guideLicence) {
        this.gidID = gidID;
        this.fName = fName;
        this.lName = lName;
        this.NIC = NIC;
        this.Address = Address;
        this.YOE = YOE;
        this.mobileNo = mobileNo;
        this.email = email;
        this.vehicle = vehicle;
        this.guideLicence = guideLicence;
    }

    public String getGidID() {
        return gidID;
    }

    public void setGidID(String gidID) {
        this.gidID = gidID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public int getYOE() {
        return YOE;
    }

    public void setYOE(int YOE) {
        this.YOE = YOE;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getGuideLicence() {
        return guideLicence;
    }

    public void setGuideLicence(String guideLicence) {
        this.guideLicence = guideLicence;
    }

    
    
}
