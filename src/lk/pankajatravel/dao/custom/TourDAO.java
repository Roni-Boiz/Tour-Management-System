/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom;

import java.util.ArrayList;
import lk.pankajatravel.dao.CrudDAO;
import lk.pankajatravel.entity.Tour;

/**
 *
 * @author Ronila
 */
public interface TourDAO extends CrudDAO<Tour, String>{

    public String getLastTourID()throws Exception;

    public ArrayList<Tour> getCurrentTours()throws Exception;
    
    public ArrayList<Tour> searchTourbyGuestName(ArrayList<String> allGuestID)throws Exception;
    
    public ArrayList<Tour> searchTourbyGuideName(ArrayList<String> allGuideID)throws Exception;
    
    public ArrayList<Tour> searchTourbySpecificDate(String date)throws Exception;

}
