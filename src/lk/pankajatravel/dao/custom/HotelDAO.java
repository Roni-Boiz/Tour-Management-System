/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom;

import java.util.ArrayList;
import lk.pankajatravel.dao.CrudDAO;
import lk.pankajatravel.entity.Hotel;

/**
 *
 * @author Ronila
 */
public interface HotelDAO extends CrudDAO<Hotel, String>{

    public ArrayList<Hotel> getSearchName(String name) throws Exception;

    public ArrayList<Hotel> getSearchAddress(String address) throws Exception;
    
    public String getLastHotelID() throws Exception;
    
}
