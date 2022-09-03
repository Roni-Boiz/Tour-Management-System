/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dto;

/**
 *
 * @author Ronila
 */
public class VehicleDTO {
    private String vclID;
    private String vclNum;
    private String Category;
    private String yOM;
    private String name;
    private String pricePayperKm;
    private String owner;
    private String ownerName;
    private String OwnerMobileNum;

    public VehicleDTO() {
    }

    public VehicleDTO(String vclID, String vclNum, String Category, String yOM, String name, String pricePayperKm, String owner, String ownerName, String OwnerMobileNum) {
        this.vclID = vclID;
        this.vclNum = vclNum;
        this.Category = Category;
        this.yOM = yOM;
        this.name = name;
        this.pricePayperKm = pricePayperKm;
        this.owner = owner;
        this.ownerName = ownerName;
        this.OwnerMobileNum = OwnerMobileNum;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerMobileNum() {
        return OwnerMobileNum;
    }

    public void setOwnerMobileNum(String OwnerMobileNum) {
        this.OwnerMobileNum = OwnerMobileNum;
    }

    

    public String getVclID() {
        return vclID;
    }

    public void setVclID(String vclID) {
        this.vclID = vclID;
    }

    public String getVclNum() {
        return vclNum;
    }

    public void setVclNum(String vclNum) {
        this.vclNum = vclNum;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public String getyOM() {
        return yOM;
    }

    public void setyOM(String yOM) {
        this.yOM = yOM;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPricePayperKm() {
        return pricePayperKm;
    }

    public void setPricePayperKm(String pricePayperKm) {
        this.pricePayperKm = pricePayperKm;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

   
    
}

