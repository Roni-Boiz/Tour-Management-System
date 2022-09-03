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
public class HotelDTO {
    private String htlID;
    private String hName;
    private String address;
    private String telNo;
    private String email;
    private String accommodation;

    public HotelDTO() {
    }

    public HotelDTO(String htlID, String hName, String address, String telNo, String email, String accommodation) {
        this.htlID = htlID;
        this.hName = hName;
        this.address = address;
        this.telNo = telNo;
        this.email = email;
        this.accommodation = accommodation;
    }

    public String getHtlID() {
        return htlID;
    }

    public void setHtlID(String htlID) {
        this.htlID = htlID;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(String accommodation) {
        this.accommodation = accommodation;
    }
    
}
