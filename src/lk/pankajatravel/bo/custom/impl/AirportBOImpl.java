/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.bo.custom.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.pankajatravel.bo.custom.AirportBO;
import lk.pankajatravel.dao.DAOFactory;
import lk.pankajatravel.dao.custom.AirportDAO;
import lk.pankajatravel.dto.AirportDTO;
import lk.pankajatravel.entity.Airport;

/**
 *
 * @author Ronila
 */
public class AirportBOImpl implements AirportBO{

        AirportDAO dao = (AirportDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.AIRPORT);

    @Override
    public boolean addAirport(AirportDTO a) throws SQLException, ClassNotFoundException, Exception {
        return dao.add(new Airport(a.getAirportID(), a.getName(), a.getLocation()));
    }

    @Override
    public boolean deleteAirport(String id) throws ClassNotFoundException, SQLException, Exception {
       return dao.delete(id);
    }

    @Override
    public boolean updateAirport(AirportDTO a) throws SQLException, ClassNotFoundException, Exception {
       return dao.update(new Airport(a.getAirportID(), a.getName(), a.getLocation()));
    }

    @Override
    public AirportDTO searchAirport(String id) throws ClassNotFoundException, SQLException, Exception {
            Airport a = dao.search(id);
            return new AirportDTO(a.getAirportID(), a.getName(), a.getLocation());
    }

    @Override
    public ArrayList<AirportDTO> getAllAirport() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<AirportDTO> all=new ArrayList<>();
        ArrayList<Airport> allAirport = dao.getAll();
        for (Airport a : allAirport) {
            all.add(new AirportDTO(a.getAirportID(), a.getName(), a.getLocation()));
        }
        return all;
    }

    @Override
    public String getAirportID() {
            try {
                String LastAirportID = dao.getLastAirportID();
                int num =Integer.parseInt(LastAirportID.substring(6))+1;
                return "apt-00"+num;
            } catch (Exception ex) {
                return "apt-001";
            }
    }
    
}
