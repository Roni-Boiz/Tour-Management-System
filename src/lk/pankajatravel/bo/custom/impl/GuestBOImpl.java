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
import lk.pankajatravel.bo.custom.GuestBO;
import lk.pankajatravel.dao.DAOFactory;
import lk.pankajatravel.dao.SuperDAO;
import lk.pankajatravel.dao.custom.GuestDAO;
import lk.pankajatravel.dto.AirportDTO;
import lk.pankajatravel.dto.GuestDTO;
import lk.pankajatravel.entity.Airport;
import lk.pankajatravel.entity.Guest;

/**
 *
 * @author Ronila
 */
public class GuestBOImpl implements GuestBO {

    GuestDAO dao = (GuestDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GUEST);

    @Override
    public boolean addGuest(GuestDTO g) throws SQLException, ClassNotFoundException, Exception {
        return dao.add(new Guest(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime()));
    }

    @Override
    public boolean deleteGuest(String gid) throws ClassNotFoundException, SQLException, Exception {
        return dao.delete(gid);
    }

    @Override
    public boolean updateGuest(GuestDTO g) throws SQLException, ClassNotFoundException, Exception {
        return dao.update(new Guest(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime()));
    }

    @Override
    public GuestDTO searchGuest(String id) throws ClassNotFoundException, SQLException, Exception {
        Guest g = dao.search(id);
        return new GuestDTO(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime());
    }

    @Override
    public ArrayList<GuestDTO> getAllGuest() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<GuestDTO> all = new ArrayList<>();
        ArrayList<Guest> allGuests = dao.getAll();
        if (allGuests.isEmpty()) {
            return null;
        } else {
            for (Guest g : allGuests) {
                all.add(new GuestDTO(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime()));
            }
            return all;
        }
    }

    @Override
    public ArrayList<GuestDTO> searchGuestbyName(String name) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<GuestDTO> all = new ArrayList<>();
        ArrayList<Guest> allGuest = dao.getSearchName(name);
        if (allGuest.isEmpty()) {
            return null;
        } else {
            for (Guest g : allGuest) {
                all.add(new GuestDTO(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime()));
            }
            return all;
        }
    }

    @Override
    public ArrayList<GuestDTO> searchGuestbyCountry(String country) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<GuestDTO> all = new ArrayList<>();
        ArrayList<Guest> allGuest = dao.getSearchCountry(country);
        if (allGuest.isEmpty()) {
            return null;
        } else {
            for (Guest g : allGuest) {
                all.add(new GuestDTO(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime()));
            }
            return all;
        }
    }

    @Override
    public ArrayList<GuestDTO> searchGuestbyDate(String date) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<GuestDTO> all = new ArrayList<>();
        ArrayList<Guest> allGuest = dao.getSearchDate(date);
        if (allGuest.isEmpty()) {
            return null;
        } else {
            for (Guest g : allGuest) {
                all.add(new GuestDTO(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime()));
            }
            return all;
        }
    }

//    @Override
//    public ArrayList<AirportDTO> getAllAirports() throws ClassNotFoundException, SQLException, Exception {
//        ArrayList<AirportDTO> all = new ArrayList<>();
//        ArrayList<Airport> allAirpots = dao.getAllAirpots();
//        for (Airport a : allAirpots) {
//            all.add(new AirportDTO(a.getAirportID(), a.getName(), a.getLocation()));
//        }
//        return all;
//    }

    @Override
    public String getGuestID() {
        try {
            String lastGuestID = dao.getLastGuestID();
            int num=Integer.parseInt(lastGuestID.substring(6))+1;
            return "gst-00"+num;
        } catch (Exception ex) {
            return "gst-001";
        }
    }

}
