/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.bo.custom;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.pankajatravel.bo.SuperBO;
import lk.pankajatravel.dto.GuestDTO;
import lk.pankajatravel.dto.GuideDTO;
import lk.pankajatravel.dto.HotelDTO;
import lk.pankajatravel.dto.PlaceDTO;
import lk.pankajatravel.dto.TourDTO;

/**
 *
 * @author Ronila
 */
public interface TourBO extends SuperBO{
    
    public ArrayList<GuestDTO> getAllGuest() throws ClassNotFoundException, SQLException, Exception;
    
    public GuestDTO getSearchGuest(String id) throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<GuideDTO> getAllGuide() throws ClassNotFoundException, SQLException, Exception;
    
    public GuideDTO getSearchGuide(String id) throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<PlaceDTO> getAllPlace() throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<HotelDTO> getAllHotel() throws ClassNotFoundException, SQLException, Exception;

    public boolean addTour(TourDTO tour) throws ClassNotFoundException, SQLException, Exception;

    public String getTourID() throws ClassNotFoundException, SQLException, Exception;

    public TourDTO searchTour(String tourID) throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<TourDTO> getCurrentTours() throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<TourDTO> getAllTours() throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<TourDTO> searchTourbyGuestName(String name) throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<TourDTO> searchTourbyGuideName(String name) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<TourDTO> searchTourbySpecificDate(String date) throws ClassNotFoundException, SQLException, Exception;

}
