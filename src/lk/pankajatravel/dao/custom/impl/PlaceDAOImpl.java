/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.pankajatravel.dao.CrudUtil;
import lk.pankajatravel.dao.custom.PlaceDAO;
import lk.pankajatravel.entity.Place;

/**
 *
 * @author Ronila
 */
public class PlaceDAOImpl implements PlaceDAO{

    @Override
    public boolean add(Place p) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO place VALUES(?,?,?,?)", p.getPlcID(),p.getPlcName(),p.getLocation(),p.getDistanceFromColombo());
    }

    @Override
    public boolean update(Place p) throws Exception {
        return CrudUtil.executeUpdate("UPDATE place SET plcName=?,location=?,distanceFromColombo=? WHERE plcID=?",p.getPlcName(),p.getLocation(),p.getDistanceFromColombo(), p.getPlcID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM place WHERE plcID=?", id);
    }

    @Override
    public Place search(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM place WHERE plcID=?", id);
        Place p=null;
        while(rst.next()){
        p=new Place(rst.getString(1),rst.getString(2),rst.getString(3),rst.getInt(4));
        }
        return p;
    }

    @Override
    public ArrayList<Place> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM place");
        ArrayList<Place> all = new ArrayList<>();
        while(rst.next()){
        all.add(new Place(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4)));
        }
        return all;
    }

    @Override
    public String getLastPlaceID() throws Exception{
            ResultSet rst = CrudUtil.executeQuery("SELECT plcID FROM place ORDER BY plcID DESC LIMIT 1");
            String lastPlaceID =null;
            while(rst.next()){
            lastPlaceID=rst.getString(1);
            }
            return lastPlaceID;
    }
    
}
