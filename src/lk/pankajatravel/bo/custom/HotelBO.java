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
import lk.pankajatravel.dto.HotelDTO;

/**
 *
 * @author Ronila
 */
public interface HotelBO extends SuperBO{
    
    public boolean addHotel(HotelDTO h) throws SQLException, ClassNotFoundException, Exception;

    public boolean deleteHotel(String id) throws ClassNotFoundException, SQLException, Exception;

    public boolean updateHotel(HotelDTO guide) throws SQLException, ClassNotFoundException, Exception;

    public HotelDTO searchHotel(String id) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<HotelDTO> getAllHotel() throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<HotelDTO> searchHotelbyName(String name) throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<HotelDTO> searchHotelbyAddress(String name) throws ClassNotFoundException, SQLException, Exception;
    
    public String getHotelID();
    
}
