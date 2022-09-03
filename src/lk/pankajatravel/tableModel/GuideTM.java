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
public class GuideTM {
    
    private String GidID;
    private String F_Name;
    private String L_Name;
    private String NIC;
    private String Address;
    private int YOE;
    private String Mobile_No;
    private String Email;
    private String vehicle;
    private String Guide_Licence;

    public GuideTM() {
    }

    public GuideTM(String GidID, String F_Name, String L_Name, String NIC, String Address, int YOE, String Mobile_No, String Email, String vehicle, String Guide_Licence) {
        this.GidID = GidID;
        this.F_Name = F_Name;
        this.L_Name = L_Name;
        this.NIC = NIC;
        this.Address = Address;
        this.YOE = YOE;
        this.Mobile_No = Mobile_No;
        this.Email = Email;
        this.vehicle = vehicle;
        this.Guide_Licence = Guide_Licence;
    }

    public String getGidID() {
        return GidID;
    }

    public void setGidID(String GidID) {
        this.GidID = GidID;
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

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getGuide_Licence() {
        return Guide_Licence;
    }

    public void setGuide_Licence(String Guide_Licence) {
        this.Guide_Licence = Guide_Licence;
    }

    
}
