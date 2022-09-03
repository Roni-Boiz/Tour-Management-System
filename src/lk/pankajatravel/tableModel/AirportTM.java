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
public class AirportTM {
    private String Airport_ID;    
    private String Name;
    private String Location;

    public AirportTM() {
    }

    public AirportTM(String Airport_ID, String Name, String Location) {
        this.Airport_ID = Airport_ID;
        this.Name = Name;
        this.Location = Location;
    }

    public String getAirport_ID() {
        return Airport_ID;
    }

    public void setAirport_ID(String Airport_ID) {
        this.Airport_ID = Airport_ID;
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

    
}
