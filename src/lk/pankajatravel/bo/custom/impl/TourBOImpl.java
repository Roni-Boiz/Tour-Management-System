/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.bo.custom.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import lk.pankajatravel.bo.custom.TourBO;
import lk.pankajatravel.dao.DAOFactory;
import lk.pankajatravel.dao.custom.GuestDAO;
import lk.pankajatravel.dao.custom.GuideDAO;
import lk.pankajatravel.dao.custom.HotelDAO;
import lk.pankajatravel.dao.custom.PlaceDAO;
import lk.pankajatravel.dao.custom.QueryDAO;
import lk.pankajatravel.dao.custom.TourDAO;
import lk.pankajatravel.dao.custom.TourDetailsDAO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.dto.GuestDTO;
import lk.pankajatravel.dto.GuideDTO;
import lk.pankajatravel.dto.HotelDTO;
import lk.pankajatravel.dto.PlaceDTO;
import lk.pankajatravel.dto.TourDTO;
import lk.pankajatravel.dto.TourDetailsDTO;
import lk.pankajatravel.entity.Guest;
import lk.pankajatravel.entity.Guide;
import lk.pankajatravel.entity.Hotel;
import lk.pankajatravel.entity.Place;
import lk.pankajatravel.entity.Tour;
import lk.pankajatravel.entity.TourDetails;

/**
 *
 * @author Ronila
 */
public class TourBOImpl implements TourBO {

    TourDAO tourDAO = (TourDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.TOUR);
    TourDetailsDAO tourDetailDAO = (TourDetailsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.TOURDETAILS);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    GuestDAO GuestDAO = (GuestDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GUEST);
    GuideDAO guideDAO = (GuideDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GUIDE);
    HotelDAO hotelDAO = (HotelDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.HOTEL);
    PlaceDAO placeDAO = (PlaceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PLACE);

    @Override
    public ArrayList<GuestDTO> getAllGuest() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<GuestDTO> all = new ArrayList<>();
        ArrayList<Guest> allGuests = GuestDAO.getAll();
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
    public GuestDTO getSearchGuest(String id) throws ClassNotFoundException, SQLException, Exception {
        Guest g = GuestDAO.search(id);
        return new GuestDTO(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime());
    }

    @Override
    public ArrayList<GuideDTO> getAllGuide() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<GuideDTO> all = new ArrayList<>();
        ArrayList<Guide> allGuide = guideDAO.getAll();
        if (allGuide.isEmpty()) {
            return null;
        } else {
            for (Guide g : allGuide) {
                all.add(new GuideDTO(g.getGidID(), g.getfName(), g.getlName(), g.getNIC(), g.getAddress(), g.getYOE(), g.getMobileNo(), g.getEmail(), g.getVehicle(), g.getGuideLicence()));
            }
            return all;
        }
    }

    @Override
    public ArrayList<PlaceDTO> getAllPlace() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<PlaceDTO> all = new ArrayList<>();
        ArrayList<Place> allPlace = placeDAO.getAll();
        for (Place p : allPlace) {
            all.add(new PlaceDTO(p.getPlcID(), p.getPlcName(), p.getLocation(), p.getDistanceFromColombo() + ""));
        }
        return all;
    }

    @Override
    public ArrayList<HotelDTO> getAllHotel() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<HotelDTO> all = new ArrayList<>();
        ArrayList<Hotel> allHotel = hotelDAO.getAll();
        for (Hotel h : allHotel) {
            all.add(new HotelDTO(h.getHtlID(), h.gethName(), h.getAddress(), h.getTelNo(), h.getEmail(), h.getAccommodation()));
        }
        return all;
    }

    @Override
    public boolean addTour(TourDTO t) throws ClassNotFoundException, SQLException, Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        boolean tourAdded = tourDAO.add(new Tour(t.getTourID(), t.getGuestID(), t.getGuideID(), t.getTourStartDate(), t.getTourEndDate(), t.getGuestPickupOn(), t.getGuestDropOn(), t.getNoAdults(), t.getNoChildren(), t.getRooms(), t.getRoomType(), t.getMealType()));
        if (tourAdded) {
            for (TourDetailsDTO td : t.getAllTourDetails()) {
                boolean ordeDetailAded = tourDetailDAO.add(new TourDetails(t.getTourID(), td.getDate(), td.getPlace(), td.getNoNights(), td.getHotel()));
                if (!ordeDetailAded) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } else {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }
    }

    @Override
    public String getTourID() throws ClassNotFoundException, SQLException, Exception {
        try {
            String lastTourID = tourDAO.getLastTourID();
            int num = Integer.parseInt(lastTourID.substring(5)) + 1;
            return "tour-" + num;
        } catch (NullPointerException ex) {
            return "tour-1";
        }
    }

    @Override
    public GuideDTO getSearchGuide(String id) throws ClassNotFoundException, SQLException, Exception {
        Guide g = guideDAO.search(id);
        return new GuideDTO(g.getGidID(), g.getfName(), g.getlName(), g.getNIC(), g.getAddress(), g.getYOE(), g.getMobileNo(), g.getEmail(), g.getVehicle(), g.getGuideLicence());
    }

    @Override
    public TourDTO searchTour(String tourID) throws ClassNotFoundException, SQLException, Exception {
        Tour s = tourDAO.search(tourID);
        ArrayList<TourDetailsDTO> searchTourDetail = new ArrayList<>();
        ArrayList<TourDetails> all = tourDetailDAO.getAll(tourID);
        if (all.isEmpty()) {
            return null;
        } else {
            for (TourDetails t : all) {
                searchTourDetail.add(new TourDetailsDTO(t.getDate(), t.getPlace(), t.getNights(), t.getHotel()));
            }
            return new TourDTO(s.getTourID(), s.getGuestID(), s.getStartDate(), s.getEndDate(), s.getPickupOn(), s.getDropOn(), s.getAdults(), s.getChildren(), s.getRooms(), s.getRoomType(), s.getMealType(), s.getGuideID(), searchTourDetail);
        }

    }

    @Override
    public ArrayList<TourDTO> getCurrentTours() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<TourDTO> all = new ArrayList<>();
        ArrayList<Tour> currentTours = tourDAO.getCurrentTours();
        for (Tour t : currentTours) {
            all.add(new TourDTO(t.getTourID(), t.getGuestID(), t.getStartDate(), t.getEndDate(), t.getPickupOn(), t.getDropOn(), t.getAdults(), t.getChildren(), t.getGuideID()));
        }
        return all;
    }

    @Override
    public ArrayList<TourDTO> getAllTours() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<TourDTO> all = new ArrayList<>();
        ArrayList<Tour> allTours = tourDAO.getAll();
        for (Tour t : allTours) {
            all.add(new TourDTO(t.getTourID(), t.getGuestID(), t.getStartDate(), t.getEndDate(), t.getPickupOn(), t.getDropOn(), t.getAdults(), t.getChildren(), t.getRooms(), t.getRoomType(), t.getMealType(), t.getGuideID()));
        }
        return all;

    }

    @Override
    public ArrayList<TourDTO> searchTourbyGuestName(String name) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<String> all = new ArrayList<>();
        ArrayList<Guest> searchGuest = GuestDAO.getSearchName(name);
        for (Guest g : searchGuest) {
            all.add(g.getGuestID());
        }
        ArrayList<TourDTO> allTours = new ArrayList<>();
        ArrayList<Tour> searchTours = tourDAO.searchTourbyGuestName(all);
        if (searchTours.isEmpty()) {
            return null;
        } else {
            for (Tour t : searchTours) {
                allTours.add(new TourDTO(t.getTourID(), t.getGuestID(), t.getStartDate(), t.getEndDate(), t.getPickupOn(), t.getDropOn(), t.getAdults(), t.getChildren(), t.getRooms(), t.getRoomType(), t.getMealType(), t.getGuideID()));
            }
            return allTours;
        }

    }

    @Override
    public ArrayList<TourDTO> searchTourbyGuideName(String name) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<String> all = new ArrayList<>();
        ArrayList<Guide> searchGuide = guideDAO.getSearchName(name);
        for (Guide g : searchGuide) {
            all.add(g.getGidID());
        }
        ArrayList<TourDTO> allTours = new ArrayList<>();
        ArrayList<Tour> searchTours = tourDAO.searchTourbyGuideName(all);
        if (searchTours.isEmpty()) {
            return null;
        } else {
            for (Tour t : searchTours) {
                allTours.add(new TourDTO(t.getTourID(), t.getGuestID(), t.getStartDate(), t.getEndDate(), t.getPickupOn(), t.getDropOn(), t.getAdults(), t.getChildren(), t.getRooms(), t.getRoomType(), t.getMealType(), t.getGuideID()));
            }
            return allTours;
        }
    }

    @Override
    public ArrayList<TourDTO> searchTourbySpecificDate(String date) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<TourDTO> allTours = new ArrayList<>();
        ArrayList<Tour> searchTours = tourDAO.searchTourbySpecificDate(date);
        if (searchTours.isEmpty()) {
            return null;
        } else {
            for (Tour t : searchTours) {
                allTours.add(new TourDTO(t.getTourID(), t.getGuestID(), t.getStartDate(), t.getEndDate(), t.getPickupOn(), t.getDropOn(), t.getAdults(), t.getChildren(), t.getRooms(), t.getRoomType(), t.getMealType(), t.getGuideID()));
            }
            return allTours;
        }

    }

}
