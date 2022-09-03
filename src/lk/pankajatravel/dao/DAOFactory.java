/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao;

import lk.pankajatravel.dao.custom.impl.AirportDAOImpl;
import lk.pankajatravel.dao.custom.impl.GuestDAOImpl;
import lk.pankajatravel.dao.custom.impl.GuideDAOImpl;
import lk.pankajatravel.dao.custom.impl.HotelDAOImpl;
import lk.pankajatravel.dao.custom.impl.PlaceDAOImpl;
import lk.pankajatravel.dao.custom.impl.QueryDAOImpl;
import lk.pankajatravel.dao.custom.impl.TourDAOImpl;
import lk.pankajatravel.dao.custom.impl.TourDetailsDAOImpl;
import lk.pankajatravel.dao.custom.impl.VehicleDAOImpl;

/**
 *
 * @author Ronila
 */
public class DAOFactory {

    private static DAOFactory dAOFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        if (dAOFactory == null) {
            dAOFactory = new DAOFactory();
        }
        return dAOFactory;
    }

    public enum DAOTypes {
        TOUR, GUEST, GUIDE, HOTEL, VEHICAL, AIRPORT, PLACE, TOURDETAILS, QUERY;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case TOUR:
                return new TourDAOImpl();
            case GUEST:
                return new GuestDAOImpl();
            case GUIDE:
                return new GuideDAOImpl();
            case HOTEL:
                return new HotelDAOImpl();
            case VEHICAL:
                return new VehicleDAOImpl();
            case PLACE:
                return new PlaceDAOImpl();
            case AIRPORT:
                return new AirportDAOImpl();
            case TOURDETAILS:
                return new TourDetailsDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
