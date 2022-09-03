/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom;

import java.util.ArrayList;
import lk.pankajatravel.dao.CrudDAO;
import lk.pankajatravel.entity.Airport;
import lk.pankajatravel.entity.Guest;

/**
 *
 * @author Ronila
 */
public interface GuestDAO extends CrudDAO<Guest,String>{
    public ArrayList<Guest> getSearchName(String name)throws Exception;
    
    public ArrayList<Guest> getSearchCountry(String country)throws Exception;
    
    public ArrayList<Guest> getSearchDate(String date)throws Exception;
    
//    public ArrayList<Airport> getAllAirpots()throws Exception;
    
    public String getLastGuestID() throws Exception;
    
}
