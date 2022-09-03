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
public class PlaceTM {
    private String Place_ID;
    private String Name;
    private String Location;
    private String Distance_to_Colombo;

    public PlaceTM() {
    }

    public PlaceTM(String Place_ID, String Name, String Location, String Distance_to_Colombo) {
        this.Place_ID = Place_ID;
        this.Name = Name;
        this.Location = Location;
        this.Distance_to_Colombo = Distance_to_Colombo;
    }

    public String getPlace_ID() {
        return Place_ID;
    }

    public void setPlace_ID(String Place_ID) {
        this.Place_ID = Place_ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getDistance_to_Colombo() {
        return Distance_to_Colombo;
    }

    public void setDistance_to_Colombo(String Distance_to_Colombo) {
        this.Distance_to_Colombo = Distance_to_Colombo;
    }
    
    
}
