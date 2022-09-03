/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.bo.custom.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.pankajatravel.bo.custom.PlaceBO;
import lk.pankajatravel.dao.DAOFactory;
import lk.pankajatravel.dao.custom.PlaceDAO;
import lk.pankajatravel.dto.PlaceDTO;
import lk.pankajatravel.entity.Place;

/**
 *
 * @author Ronila
 */
public class PlaceBOImpl implements PlaceBO{

        PlaceDAO dao = (PlaceDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PLACE);
    
    @Override
    public boolean addPlace(PlaceDTO p) throws SQLException, ClassNotFoundException, Exception {
        return dao.add(new Place(p.getPlcID(), p.getPlcName(), p.getLocation(), Integer.parseInt(p.getDistanceFromColombo())));
    }

    @Override
    public boolean deletePlace(String id) throws ClassNotFoundException, SQLException, Exception {
        return dao.delete(id);
    }

    @Override
    public boolean updatePlace(PlaceDTO p) throws SQLException, ClassNotFoundException, Exception {
        return dao.update(new Place(p.getPlcID(), p.getPlcName(), p.getLocation(), Integer.parseInt(p.getDistanceFromColombo())));
    }

    @Override
    public PlaceDTO searchPlace(String id) throws ClassNotFoundException, SQLException, Exception {
            Place p = dao.search(id);
            return new PlaceDTO(p.getPlcID(), p.getPlcName(), p.getLocation(), p.getDistanceFromColombo()+"");
    }

    @Override
    public ArrayList<PlaceDTO> getAllPlace() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<PlaceDTO> all=new ArrayList<>();
        ArrayList<Place> allPlace = dao.getAll();
        for (Place p : allPlace) {
            all.add(new PlaceDTO(p.getPlcID(), p.getPlcName(), p.getLocation(), p.getDistanceFromColombo()+""));
        }
        return all;
    }

    @Override
    public String getPlaceID() {
            try {
                String LastPlaceID = dao.getLastPlaceID();
                int num =Integer.parseInt(LastPlaceID.substring(6))+1;
                return "plc-00"+num;
            } catch (Exception ex) {
               return "plc-001";
            }
    }
    
}
