/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import lk.pankajatravel.dao.CrudUtil;
import lk.pankajatravel.dao.custom.TourDetailsDAO;
import lk.pankajatravel.entity.Tour;
import lk.pankajatravel.entity.TourDetails;

/**
 *
 * @author Ronila
 */
public class TourDetailsDAOImpl implements TourDetailsDAO {

    @Override
    public boolean add(TourDetails t) throws Exception {
        return CrudUtil.executeUpdate("INSERT INTO tourDetail VALUES(?,?,?,?,?)", t.getTourID(),t.getDate(),t.getPlace(),t.getNights(),t.getHotel());
    }

    @Override
    public boolean update(TourDetails t) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TourDetails search(String id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<TourDetails> getAll(String tourID) throws Exception {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM tourDetail WHERE tourID=?", tourID);
        ArrayList<TourDetails> t=new ArrayList<>();
        while(rst.next()){
        t.add(new TourDetails(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5)));
        }
        return t;
    }

    @Override
    public ArrayList<TourDetails> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
