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
import lk.pankajatravel.dao.custom.HotelDAO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.entity.Guide;
import lk.pankajatravel.entity.Hotel;

/**
 *
 * @author Ronila
 */
public class HotelDAOImpl implements HotelDAO{

    @Override
    public boolean add(Hotel h) throws Exception {
       return CrudUtil.executeUpdate("INSERT INTO hotel VALUES(?,?,?,?,?,?)", h.getHtlID(), h.gethName(), h.getAddress(), h.getTelNo(), h.getEmail(), h.getAccommodation());
    }

    @Override
    public boolean update(Hotel h) throws Exception {
       return CrudUtil.executeUpdate("UPDATE hotel SET hName=?,address=?,telNo=?,email=?,accommodation=? WHERE htlID=?", h.gethName(), h.getAddress(), h.getTelNo(), h.getEmail(), h.getAccommodation(), h.getHtlID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM hotel WHERE htlID=?", id);
    }

    @Override
    public Hotel search(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM hotel WHERE htlID=?", id);
        Hotel searchHotel=null;
        while(rst.next()){
        searchHotel=new Hotel(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6));
        }
        return searchHotel;
    }

    @Override
    public ArrayList<Hotel> getAll() throws Exception {
        ArrayList<Hotel> all =new ArrayList<>();
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM hotel");
        while(rst.next()){
        all.add(new Hotel(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6)));
        }
        return all;
    }

    @Override
    public ArrayList<Hotel> getSearchName(String name) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM hotel WHERE fName LIKE ? || lName LIKE ?");
        pstm.setObject(1, "%"+name+"%");
        pstm.setObject(2, "%"+name+"%");
        ResultSet rst = pstm.executeQuery();
        ArrayList<Hotel> allHotel = new ArrayList<>();
        while(rst.next()){
        allHotel.add(new Hotel(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
        }
        return allHotel;
    }

    @Override
    public ArrayList<Hotel> getSearchAddress(String address) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM hotel WHERE address LIKE ?");
        pstm.setObject(1, "%"+address+"%");
        ResultSet rst = pstm.executeQuery();
        ArrayList<Hotel> allHotel = new ArrayList<>();
        while(rst.next()){
        allHotel.add(new Hotel(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
        }
        return allHotel;
    }

    @Override
    public String getLastHotelID() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT htlID FROM hotel ORDER BY htlID DESC LIMIT 1");
            String lastGuideID =null;
            while(rst.next()){
            lastGuideID=rst.getString(1);
            }
            return lastGuideID;
    }
    
}
