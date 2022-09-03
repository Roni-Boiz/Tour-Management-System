/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import lk.pankajatravel.dao.CrudUtil;
import lk.pankajatravel.dao.custom.GuestDAO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.entity.Airport;
import lk.pankajatravel.entity.Guest;

/**
 *
 * @author Ronila
 */
public class GuestDAOImpl implements GuestDAO {

    @Override
    public boolean add(Guest g) throws Exception {
        return CrudUtil.executeUpdate("Insert into guest values(?,?,?,?,?,?,?,?,?,?,?,?)", g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime());
    }

    @Override
    public boolean update(Guest g) throws Exception {
        return CrudUtil.executeUpdate("UPDATE guest SET country=?,fname=?,lname=?,mobileNo=?,email=?,flightNo=?,airport=?,arrivedDate=?,arrivedTime=?,departureDate=?,departureTime=? WHERE gstID=?", g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime(), g.getGuestID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("Delete from guest where gstID=?", id);
    }

    @Override
    public Guest search(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM guest WHERE gstID=?", id);
        Guest temp = null;
        while (rst.next()) {
            temp = new Guest(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12));
        }
        return temp;
    }

    @Override
    public ArrayList<Guest> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM guest");
        ArrayList<Guest> allGuest = new ArrayList<>();
        while (rst.next()) {
            allGuest.add(new Guest(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12)));
        }
        return allGuest;
    }

    @Override
    public ArrayList<Guest> getSearchName(String name) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM guest WHERE fName LIKE ? || lName LIKE ?");
        pstm.setObject(1, "%" + name + "%");
        pstm.setObject(2, "%" + name + "%");
        ResultSet rst = pstm.executeQuery();
        ArrayList<Guest> allGuest = new ArrayList<>();
        while (rst.next()) {
            allGuest.add(new Guest(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12)));
        }
        return allGuest;
    }

    @Override
    public ArrayList<Guest> getSearchCountry(String country) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM guest WHERE country=?", country);
        ArrayList<Guest> allGuest = new ArrayList<>();
        while (rst.next()) {
            allGuest.add(new Guest(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12)));
        }
        return allGuest;
    }

    @Override
    public ArrayList<Guest> getSearchDate(String date) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM guest WHERE ? BETWEEN arriveddate AND departuredate", date);
        ArrayList<Guest> allGuest = new ArrayList<>();
        while (rst.next()) {
            allGuest.add(new Guest(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12)));
        }
        return allGuest;
    }
//
//    @Override
//    public ArrayList<Airport> getAllAirpots() throws Exception {
//        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM airport");
//        ArrayList<Airport> all = new ArrayList<>();
//        while (rst.next()) {
//            all.add(new Airport(rst.getString(1), rst.getString(2), rst.getString(3)));
//        }
//        return all;
//
//    }

    @Override
    public String getLastGuestID() throws Exception {
       ResultSet rst = CrudUtil.executeQuery("SELECT gstID FROM guest ORDER BY gstID DESC LIMIT 1");
            String lastGuestID =null;
            while(rst.next()){
            lastGuestID=rst.getString(1);
            }
            return lastGuestID;
    }

}
