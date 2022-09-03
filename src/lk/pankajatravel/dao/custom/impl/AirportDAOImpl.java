/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.pankajatravel.dao.CrudUtil;
import lk.pankajatravel.dao.custom.AirportDAO;
import lk.pankajatravel.entity.Airport;

/**
 *
 * @author Ronila
 */
public class AirportDAOImpl implements AirportDAO{

    @Override
    public boolean add(Airport a) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO airport VALUES(?,?,?)",a.getAirportID(),a.getName(),a.getLocation());
    }

    @Override
    public boolean update(Airport a) throws Exception {
        return CrudUtil.executeUpdate("UPDATE airport SET name=?,location=? WHERE aptID=?", a.getName(),a.getLocation(), a.getAirportID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM airport WHERE aptID=?", id);
    }

    @Override
    public Airport search(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM airport WHERE aptID=?", id);
        Airport a=null;
        while(rst.next()){
        a=new Airport(rst.getString(1), rst.getString(2), rst.getString(3));
        }
        return a;
    }

    @Override
    public ArrayList<Airport> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM airport");
        ArrayList<Airport> all =new ArrayList<>();
        while(rst.next()){
        all.add(new Airport(rst.getString(1), rst.getString(2), rst.getString(3)));
        }
        return all;
    }

    @Override
    public String getLastAirportID() throws Exception{
        ResultSet rst = CrudUtil.executeQuery("SELECT aptID FROM airport ORDER BY aptID DESC LIMIT 1");
        String aptID = null;
        while(rst.next()){
        aptID=rst.getString(1);
        }
        return aptID;
    }
    
}
