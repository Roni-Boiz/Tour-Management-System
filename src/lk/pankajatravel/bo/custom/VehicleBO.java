/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.bo.custom;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.pankajatravel.bo.SuperBO;
import lk.pankajatravel.dto.VehicleDTO;

/**
 *
 * @author Ronila
 */
public interface VehicleBO extends SuperBO{
    
    public boolean addVehicle(VehicleDTO v) throws SQLException, ClassNotFoundException, Exception;

    public boolean deleteVehicle(String id) throws ClassNotFoundException, SQLException, Exception;

    public boolean updateVehicle(VehicleDTO v) throws SQLException, ClassNotFoundException, Exception;

    public VehicleDTO searchVehicle(String id) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<VehicleDTO> getAllVehicle() throws ClassNotFoundException, SQLException, Exception;
    
    public VehicleDTO searchVehiclebyNumber(String id) throws ClassNotFoundException, SQLException, Exception;
    
    public String getVehicleID();
    
}
