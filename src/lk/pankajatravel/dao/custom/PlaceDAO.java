/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom;

import lk.pankajatravel.dao.CrudDAO;
import lk.pankajatravel.entity.Place;

/**
 *
 * @author Ronila
 */
public interface PlaceDAO extends CrudDAO<Place, String>{
    
    public String getLastPlaceID() throws Exception;
    
}
