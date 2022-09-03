/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom;

import lk.pankajatravel.dao.CrudDAO;
import lk.pankajatravel.entity.Airport;

/**
 *
 * @author Ronila
 */
public interface AirportDAO extends CrudDAO<Airport, String>{

    public String getLastAirportID() throws Exception;
    
}
