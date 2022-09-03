/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom;

import java.util.ArrayList;
import lk.pankajatravel.dao.CrudDAO;
import lk.pankajatravel.entity.Guest;
import lk.pankajatravel.entity.TourDetails;

/**
 *
 * @author Ronila
 */
public interface TourDetailsDAO extends CrudDAO<TourDetails, String>{
    
    public ArrayList<TourDetails> getAll(String tourID) throws Exception;
    
}
