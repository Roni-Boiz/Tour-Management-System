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
public class Place {
    
    private String plcID;
    private String plcName;
    private String location;
    private int distanceFromColombo;

    public Place() {
    }

    public Place(String plcID, String plcName, String location, int distanceFromColombo) {
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

    public int getDistanceFromColombo() {
        return distanceFromColombo;
    }

    public void setDistanceFromColombo(int distanceFromColombo) {
        this.distanceFromColombo = distanceFromColombo;
    }
    
}
