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
import lk.pankajatravel.bo.custom.GuideBO;
import lk.pankajatravel.dao.DAOFactory;
import lk.pankajatravel.dao.custom.GuideDAO;
import lk.pankajatravel.dto.GuideDTO;
import lk.pankajatravel.entity.Guide;

/**
 *
 * @author Ronila
 */
public class GuideBOImpl implements GuideBO {

    GuideDAO dao = (GuideDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.GUIDE);

    @Override
    public boolean addGuide(GuideDTO g) throws SQLException, ClassNotFoundException, Exception {
        return dao.add(new Guide(g.getGidID(), g.getfName(), g.getlName(), g.getNIC(), g.getAddress(), g.getYOE(), g.getMobileNo(), g.getEmail(), g.getVehicle(), g.getGuideLicence()));
    }

    @Override
    public boolean deleteGuide(String id) throws ClassNotFoundException, SQLException, Exception {
        return dao.delete(id);
    }

    @Override
    public boolean updateGuide(GuideDTO g) throws SQLException, ClassNotFoundException, Exception {
        return dao.update(new Guide(g.getGidID(), g.getfName(), g.getlName(), g.getNIC(), g.getAddress(), g.getYOE(), g.getMobileNo(), g.getEmail(), g.getVehicle(), g.getGuideLicence()));
    }

    @Override
    public GuideDTO searchGuide(String id) throws ClassNotFoundException, SQLException, Exception {
        Guide g = dao.search(id);
        return new GuideDTO(g.getGidID(), g.getfName(), g.getlName(), g.getNIC(), g.getAddress(), g.getYOE(), g.getMobileNo(), g.getEmail(), g.getVehicle(), g.getGuideLicence());
    }

    @Override
    public ArrayList<GuideDTO> getAllGuide() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<GuideDTO> all = new ArrayList<>();
        ArrayList<Guide> allGuide = dao.getAll();
        if (allGuide.isEmpty()) {
            return null;
        } else {
            for (Guide g : allGuide) {
                all.add(new GuideDTO(g.getGidID(), g.getfName(), g.getlName(), g.getNIC(), g.getAddress(), g.getYOE(), g.getMobileNo(), g.getEmail(), g.getVehicle(), g.getGuideLicence()));
            }
            return all;
        }
    }

    @Override
    public ArrayList<GuideDTO> searchGuidebyName(String name) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<GuideDTO> all = new ArrayList<>();
        ArrayList<Guide> allGuide = dao.getSearchName(name);
        if (allGuide.isEmpty()) {
            return null;
        }else{
            for (Guide g : allGuide) {
                all.add(new GuideDTO(g.getGidID(), g.getfName(), g.getlName(), g.getNIC(), g.getAddress(), g.getYOE(), g.getMobileNo(), g.getEmail(), g.getVehicle(), g.getGuideLicence()));
            }
            return all;
        }
    }

    @Override
    public String getGuideID() {
        try {
            String lastGuideID = dao.getlastGuideID();
            int num =Integer.parseInt(lastGuideID.substring(6))+1;
            return "gid-00"+num;
        } catch (Exception ex) {
            return "gid-001";
        }
    }

}
