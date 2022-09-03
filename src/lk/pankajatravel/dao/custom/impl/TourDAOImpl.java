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
import lk.pankajatravel.dao.custom.TourDAO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.entity.Tour;

/**
 *
 * @author Ronila
 */
public class TourDAOImpl implements TourDAO{

    @Override
    public boolean add(Tour t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO tour VALUES(?,?,?,?,?,?,?,?,?,?,?,?)", t.getTourID(),t.getGuestID(),t.getGuideID(),t.getStartDate(),t.getEndDate(),t.getPickupOn(),t.getDropOn(),t.getAdults(),t.getChildren(),t.getRooms(),t.getRoomType(),t.getMealType());
    }

    @Override
    public boolean update(Tour t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tour search(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM tour WHERE tourID=?", id);
        Tour t =null;
        while(rst.next()){
        t=new Tour(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9),rst.getString(10),rst.getString(11),rst.getString(12));
        }
        return t;
    }

    @Override
    public ArrayList<Tour> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM tour");
        ArrayList<Tour> all = new ArrayList<>();
        while(rst.next()){
        all.add(new Tour(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12)));
        }
        return all;
    }

    @Override
    public String getLastTourID() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT tourID FROM tour ORDER BY tourID DESC LIMIT 1");
        String lTourID = null;
        while (rst.next()) {
            lTourID = rst.getString(1);
        }
        return lTourID;

    }

    @Override
    public ArrayList<Tour> getCurrentTours() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM tour WHERE endDate>=curDate()");
        ArrayList<Tour> t =new ArrayList<>();
        while(rst.next()){
        t.add(new Tour(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9),rst.getString(10),rst.getString(11),rst.getString(12)));
        }
        return t;
    }

    @Override
    public ArrayList<Tour> searchTourbyGuestName(ArrayList<String> allGuestID) throws Exception {
        ArrayList<Tour> all =new ArrayList<>();
        for (String guestID : allGuestID) {
            ResultSet rst = CrudUtil.executeQuery("SELECT * FROM tour WHERE gstID=?", guestID);
            while(rst.next()){
            all.add(new Tour(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12)));
            }
        }
        return all;
    }

    @Override
    public ArrayList<Tour> searchTourbyGuideName(ArrayList<String> allGuideID) throws Exception {
        ArrayList<Tour> all =new ArrayList<>();
        for (String guideID : allGuideID) {
            ResultSet rst = CrudUtil.executeQuery("SELECT * FROM tour WHERE gidID=?", guideID);
            while(rst.next()){
            all.add(new Tour(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12)));
            }
        }
        return all;
    }

    @Override
    public ArrayList<Tour> searchTourbySpecificDate(String date) throws Exception {
         ArrayList<Tour> all =new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM tour WHERE ? BETWEEN startDate AND endDate", date);
        while(rst.next()){
        all.add(new Tour(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(11), rst.getString(12)));
        }
        return all;
    }

    

    

    
}
