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
import lk.pankajatravel.dao.custom.GuideDAO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.entity.Guide;

/**
 *
 * @author Ronila
 */
public class GuideDAOImpl implements GuideDAO{

    @Override
    public boolean add(Guide g) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO guide VALUES(?,?,?,?,?,?,?,?,?,?)", g.getGidID(),g.getfName(),g.getlName(),g.getNIC(),g.getAddress(),g.getYOE(),g.getMobileNo(),g.getEmail(),g.getVehicle(),g.getGuideLicence());
    }

    @Override
    public boolean update(Guide g) throws Exception {
        return CrudUtil.executeUpdate("UPDATE guide SET fName=?, lName=?, nIC=?, address=?, YOE=?, mobileNo=?, email=?, vehicle=?, guideLicence=? WHERE gidID=?",g.getfName(),g.getlName(),g.getNIC(),g.getAddress(),g.getYOE(),g.getMobileNo(),g.getEmail(),g.getVehicle(),g.getGuideLicence(), g.getGidID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.executeUpdate("DELETE FROM guide WHERE gidID=?",id);
    }

    @Override
    public Guide search(String id) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM guide WHERE gidID=?", id);
        Guide searchGuide =null;
        while(rst.next()){
        searchGuide = new Guide(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getInt(6),rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10));
        }
        return searchGuide;
    }

    @Override
    public ArrayList<Guide> getAll() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM guide");
        ArrayList<Guide> all =new ArrayList<>();
        while(rst.next()){
        all.add(new Guide(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getInt(6),rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10)));
        }
        return all;
    }

    @Override
    public ArrayList<Guide> getSearchName(String name) throws Exception {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM guide WHERE fName LIKE ? || lName LIKE ?");
        pstm.setObject(1, "%"+name+"%");
        pstm.setObject(2, "%"+name+"%");
        ResultSet rst = pstm.executeQuery();
        ArrayList<Guide> allGuide = new ArrayList<>();
        while(rst.next()){
        allGuide.add(new Guide(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getInt(6),rst.getString(7),rst.getString(8),rst.getString(9),rst.getString(10)));
        }
        return allGuide;
    }

    @Override
    public String getlastGuideID() throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT gidID FROM guide ORDER BY gidID DESC LIMIT 1");
            String lastGuideID =null;
            while(rst.next()){
            lastGuideID=rst.getString(1);
            }
            return lastGuideID;
    }
    
}
