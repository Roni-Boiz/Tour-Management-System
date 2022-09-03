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

/**
 *
 * @author Ronila
 */
public interface AirportBO extends SuperBO{
    
    public boolean addAirport(AirportDTO a) throws SQLException, ClassNotFoundException, Exception;

    public boolean deleteAirport(String id) throws ClassNotFoundException, SQLException, Exception;

    public boolean updateAirport(AirportDTO a) throws SQLException, ClassNotFoundException, Exception;

    public AirportDTO searchAirport(String id) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<AirportDTO> getAllAirport() throws ClassNotFoundException, SQLException, Exception;

    public String getAirportID();
    
}
