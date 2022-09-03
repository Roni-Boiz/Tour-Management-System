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
public class HotelTM {
    private String Hotel_ID;
    private String Name;
    private String Address;
    private String Telephone_Number;
    private String Email;
    private String Accommodation;

    public HotelTM() {
    }

    public HotelTM(String Hotel_ID, String Name, String Address, String Telephone_Number, String Email, String Accommodation) {
        this.Hotel_ID = Hotel_ID;
        this.Name = Name;
        this.Address = Address;
        this.Telephone_Number = Telephone_Number;
        this.Email = Email;
        this.Accommodation = Accommodation;
    }

    public String getHotel_ID() {
        return Hotel_ID;
    }

    public void setHotel_ID(String Hotel_ID) {
        this.Hotel_ID = Hotel_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getTelephone_Number() {
        return Telephone_Number;
    }

    public void setTelephone_Number(String Telephone_Number) {
        this.Telephone_Number = Telephone_Number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAccommodation() {
        return Accommodation;
    }

    public void setAccommodation(String Accommodation) {
        this.Accommodation = Accommodation;
    }
    
    
}
