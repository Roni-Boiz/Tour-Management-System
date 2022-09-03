/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom;

import lk.pankajatravel.dao.CrudDAO;
import lk.pankajatravel.entity.Vehicle;

/**
 *
 * @author Ronila
 */
public interface VehicleDAO extends CrudDAO<Vehicle, String>{
    
    public Vehicle searchNumber(String id) throws Exception;
    
    public String getLastVehicleID() throws Exception;
    
}
