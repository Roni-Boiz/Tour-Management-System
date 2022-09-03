/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.bo;

import lk.pankajatravel.bo.custom.impl.AirportBOImpl;
import lk.pankajatravel.bo.custom.impl.GuestBOImpl;
import lk.pankajatravel.bo.custom.impl.GuideBOImpl;
import lk.pankajatravel.bo.custom.impl.HotelBOImpl;
import lk.pankajatravel.bo.custom.impl.PlaceBOImpl;
import lk.pankajatravel.bo.custom.impl.TourBOImpl;
import lk.pankajatravel.bo.custom.impl.VehicleBOImpl;

/**
 *
 * @author Ronila
 */
public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstace() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public enum BOTypes {
        TOUR, GUEST, GUIDE, HOTEL, VEHICLE, AIRPORT, PLACE;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case TOUR:
                return new TourBOImpl();
            case GUEST:
                return new GuestBOImpl();
            case GUIDE:
                return new GuideBOImpl();
            case HOTEL:
                return new HotelBOImpl();
            case VEHICLE:
                return new VehicleBOImpl();
            case PLACE:
                return new PlaceBOImpl();
            case AIRPORT:
                return new AirportBOImpl();
            default:
                return null;
        }
    }

}
