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
public class VehicleTM {
    private String Vehicle_ID;
    private String Vehicle_Number;
    private String Category;
    private String YOM;
    private String Name;
    private String Price_per_1Km;
    private String Owner;
    private String Owner_Name;
    private String Owner_Mobile_No;

    public VehicleTM() {
    }

    public VehicleTM(String Vehicle_ID, String Vehicle_Number, String Category, String YOM, String Name, String Price_per_1Km, String Owner, String Owner_Name, String Owner_Mobile_No) {
        this.Vehicle_ID = Vehicle_ID;
        this.Vehicle_Number = Vehicle_Number;
        this.Category = Category;
        this.YOM = YOM;
        this.Name = Name;
        this.Price_per_1Km = Price_per_1Km;
        this.Owner = Owner;
        this.Owner_Name = Owner_Name;
        this.Owner_Mobile_No = Owner_Mobile_No;
    }

    public String getOwner_Name() {
        return Owner_Name;
    }

    public void setOwner_Name(String Owner_Name) {
        this.Owner_Name = Owner_Name;
    }

    public String getOwner_Mobile_No() {
        return Owner_Mobile_No;
    }

    public void setOwner_Mobile_No(String Owner_Mobile_No) {
        this.Owner_Mobile_No = Owner_Mobile_No;
    }

   

    public String getVehicle_ID() {
        return Vehicle_ID;
    }

    public void setVehicle_ID(String Vehicle_ID) {
        this.Vehicle_ID = Vehicle_ID;
    }

    public String getVehicle_Number() {
        return Vehicle_Number;
    }

    public void setVehicle_Number(String Vehicle_Number) {
        this.Vehicle_Number = Vehicle_Number;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getYOM() {
        return YOM;
    }

    public void setYOM(String YOM) {
        this.YOM = YOM;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPrice_per_1Km() {
        return Price_per_1Km;
    }

    public void setPrice_per_1Km(String Price_per_1Km) {
        this.Price_per_1Km = Price_per_1Km;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String Owner) {
        this.Owner = Owner;
    }
    
    
}

