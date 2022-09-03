/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.bo.custom;

import java.sql.SQLException;
import java.util.ArrayList;
import lk.pankajatravel.bo.SuperBO;
import lk.pankajatravel.dto.GuideDTO;

/**
 *
 * @author Ronila
 */
public interface GuideBO extends SuperBO{
    
    public boolean addGuide(GuideDTO guide) throws SQLException, ClassNotFoundException, Exception;

    public boolean deleteGuide(String id) throws ClassNotFoundException, SQLException, Exception;

    public boolean updateGuide(GuideDTO guide) throws SQLException, ClassNotFoundException, Exception;

    public GuideDTO searchGuide(String id) throws ClassNotFoundException, SQLException, Exception;

    public ArrayList<GuideDTO> getAllGuide() throws ClassNotFoundException, SQLException, Exception;
    
    public ArrayList<GuideDTO> searchGuidebyName(String name) throws ClassNotFoundException, SQLException, Exception;
    
    public String getGuideID();
    
}
