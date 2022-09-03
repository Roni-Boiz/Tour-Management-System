/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.bo.custom;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.pankajatravel.bo.SuperBO;
import lk.pankajatravel.dto.GuideDTO;
import lk.pankajatravel.dto.PlaceDTO;

/**
 *
 * @author Ronila
 */
public interface PlaceBO extends SuperBO{
   
    public boolean addPlace(PlaceDTO p) throws SQLException, ClassNotFoundException, Exception;

    public boolean deletePlace(String id) throws ClassNotFoundException, SQLException, Exception;

    public boolean updatePlace(PlaceDTO p) throws SQLException, ClassNotFoundException, Exception;

    public PlaceDTO searchPlace(String id) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<PlaceDTO> getAllPlace() throws ClassNotFoundException, SQLException, Exception;
    
    public String getPlaceID();
    
}
