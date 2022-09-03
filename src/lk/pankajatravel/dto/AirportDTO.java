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
public class AirportDTO {
    private String airportID;
    private String name;
    private String location;

    public AirportDTO() {
    }

    public AirportDTO(String airportID, String name, String location) {
        this.airportID = airportID;
        this.name = name;
        this.location = location;
    }

    public String getAirportID() {
        return airportID;
    }

    public void setAirportID(String airportID) {
        this.airportID = airportID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    
}
