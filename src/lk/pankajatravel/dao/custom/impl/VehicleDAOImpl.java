/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.pankajatravel.dao.CrudUtil;
import lk.pankajatravel.entity.Vehicle;
import lk.pankajatravel.dao.custom.VehicleDAO;

/**
 *
 * @author Ronila
 */
public class VehicleDAOImpl implements VehicleDAO{

    @Override
    public boolean add(Vehicle v) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO vehicle VALUES(?,?,?,?,?,?,?,?,?)", v.getVclID(), v.getVclNum(), v.getCategory(), v.getyOM(), v.getName(), v.getPricePayperKm(), v.getOwner(),v.getOwnerName(),v.getOwnerMobileNum());
    }

    @Override
    public boolean update(Vehicle v) throws Exception {
        return CrudUtil.executeUpdate("UPDATE vehicle SET vclNum=?,category=?,YOM=?,name=?,pricePer1Km=?,owner=?,ownerName=?,ownerMobNum=? WHERE vclID=?", v.getVclNum(), v.getCategory(), v.getyOM(), v.getName(), v.getPricePayperKm(), v.getOwner(),v.getOwnerName(),v.getOwnerMobileNum(), v.getVclID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM vehicle WHERE vclID=?", id);
    }

    @Override
    public Vehicle search(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM vehicle WHERE vclID=?", id);
        Vehicle v=null;
        while(rst.next()){
        v=new Vehicle(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5), rst.getDouble(6), rst.getString(7),rst.getString(8),rst.getString(9));
        }
        return v;
    }

    @Override
    public ArrayList<Vehicle> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM vehicle");
        ArrayList<Vehicle> all =new ArrayList<Vehicle>();
        while(rst.next()){
        all.add(new Vehicle(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5), rst.getInt(6), rst.getString(7),rst.getString(8),rst.getString(9)));
        }
        return all;
    }
    
    @Override
    public Vehicle searchNumber(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM vehicle WHERE vclNum=?", id);
        Vehicle v=null;
        while(rst.next()){
        v=new Vehicle(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5), rst.getDouble(6), rst.getString(7),rst.getString(8),rst.getString(9));
        }
        return v;
    }

    @Override
    public String getLastVehicleID() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT vclID FROM vehicle ORDER BY vclID DESC LIMIT 1");
            String lastGuideID =null;
            while(rst.next()){
            lastGuideID=rst.getString(1);
            }
            return lastGuideID;
    }
    
}
