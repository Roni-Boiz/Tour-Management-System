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
public class PlaceDTO {
    
    private String plcID;
    private String plcName;
    private String location;
    private String distanceFromColombo;

    public PlaceDTO() {
    }

    public PlaceDTO(String plcID, String plcName, String location, String distanceFromColombo) {
        this.plcID = plcID;
        this.plcName = plcName;
        this.location = location;
        this.distanceFromColombo = distanceFromColombo;
    }


    public String getPlcID() {
        return plcID;
    }

    public void setPlcID(String plcID) {
        this.plcID = plcID;
    }

    public String getPlcName() {
        return plcName;
    }

    public void setPlcName(String plcName) {
        this.plcName = plcName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getDistanceFromColombo() {
        return distanceFromColombo;
    }

    public void setDistanceFromColombo(String distanceFromColombo) {
        this.distanceFromColombo = distanceFromColombo;
    }
    
    
}
