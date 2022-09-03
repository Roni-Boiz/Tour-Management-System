/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.bo.custom;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.pankajatravel.bo.SuperBO;
import lk.pankajatravel.dto.AirportDTO;
import lk.pankajatravel.dto.GuestDTO;

/**
 *
 * @author Ronila
 */
public interface GuestBO extends SuperBO{
    
    public boolean addGuest(GuestDTO guest) throws SQLException, ClassNotFoundException, Exception;

    public boolean deleteGuest(String id) throws ClassNotFoundException, SQLException, Exception;

    public boolean updateGuest(GuestDTO guest) throws SQLException, ClassNotFoundException, Exception;

    public GuestDTO searchGuest(String id) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<GuestDTO> getAllGuest() throws ClassNotFoundException, SQLException, Exception;
    
//    public ArrayList<AirportDTO> getAllAirports() throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<GuestDTO> searchGuestbyName(String id) throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<GuestDTO> searchGuestbyCountry(String id) throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<GuestDTO> searchGuestbyDate(String id) throws ClassNotFoundException, SQLException, Exception;
    
    public String getGuestID();
}
