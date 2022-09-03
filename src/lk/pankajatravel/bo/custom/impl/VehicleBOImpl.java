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
import lk.pankajatravel.dao.DAOFactory;
import lk.pankajatravel.dto.VehicleDTO;
import lk.pankajatravel.entity.Vehicle;
import lk.pankajatravel.bo.custom.VehicleBO;
import lk.pankajatravel.dao.custom.VehicleDAO;

/**
 *
 * @author Ronila
 */
public class VehicleBOImpl implements VehicleBO {

    VehicleDAO dao = (VehicleDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.VEHICAL);

    @Override
    public boolean addVehicle(VehicleDTO v) throws SQLException, ClassNotFoundException, Exception {
        return dao.add(new Vehicle(v.getVclID(), v.getVclNum(), v.getCategory(), Integer.parseInt(v.getyOM()), v.getName(), Double.parseDouble(v.getPricePayperKm().substring(3)), v.getOwner(), v.getOwnerName(), v.getOwnerMobileNum()));
    }

    @Override
    public boolean deleteVehicle(String id) throws ClassNotFoundException, SQLException, Exception {
        return dao.delete(id);
    }

    @Override
    public boolean updateVehicle(VehicleDTO v) throws SQLException, ClassNotFoundException, Exception {
        return dao.update(new Vehicle(v.getVclID(), v.getVclNum(), v.getCategory(), Integer.parseInt(v.getyOM()), v.getName(), Double.parseDouble(v.getPricePayperKm().substring(3)), v.getOwner(), v.getOwnerName(), v.getOwnerMobileNum()));
    }

    @Override
    public VehicleDTO searchVehicle(String id) throws ClassNotFoundException, SQLException, Exception {
        Vehicle v = dao.search(id);
        return new VehicleDTO(v.getVclID(), v.getVclNum(), v.getCategory(), v.getyOM() + "", v.getName(), "Rs." + v.getPricePayperKm(), v.getOwner(), v.getOwnerName(), v.getOwnerMobileNum());
    }

    @Override
    public ArrayList<VehicleDTO> getAllVehicle() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<VehicleDTO> all = new ArrayList<>();
        ArrayList<Vehicle> allVehicle = dao.getAll();
        for (Vehicle v : allVehicle) {
            all.add(new VehicleDTO(v.getVclID(), v.getVclNum(), v.getCategory(), v.getyOM() + "", v.getName(), "Rs." + v.getPricePayperKm(), v.getOwner(), v.getOwnerName(), v.getOwnerMobileNum()));
        }
        return all;
    }

    @Override
    public VehicleDTO searchVehiclebyNumber(String number) throws ClassNotFoundException, SQLException, Exception {
        Vehicle v = dao.searchNumber(number);
        return new VehicleDTO(v.getVclID(), v.getVclNum(), v.getCategory(), v.getyOM() + "", v.getName(), "Rs." + v.getPricePayperKm(), v.getOwner(), v.getOwnerName(), v.getOwnerMobileNum());
    }

    @Override
    public String getVehicleID() {
        try {
            String lastVehicleID = dao.getLastVehicleID();
            int num =Integer.parseInt(lastVehicleID.substring(6))+1;
            return "vcl-00"+num;
        } catch (Exception ex) {
            return "vcl-001";
        }
    }

}
