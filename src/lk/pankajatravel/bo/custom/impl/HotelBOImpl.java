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
import lk.pankajatravel.bo.custom.HotelBO;
import lk.pankajatravel.dao.DAOFactory;
import lk.pankajatravel.dao.custom.HotelDAO;
import lk.pankajatravel.dto.HotelDTO;
import lk.pankajatravel.entity.Hotel;

/**
 *
 * @author Ronila
 */
public class HotelBOImpl implements HotelBO{

        HotelDAO dao = (HotelDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.HOTEL);
    
    @Override
    public boolean addHotel(HotelDTO h) throws SQLException, ClassNotFoundException, Exception {
        return dao.add(new Hotel(h.getHtlID(), h.gethName(), h.getAddress(), h.getTelNo(), h.getEmail(), h.getAccommodation()));
    }

    @Override
    public boolean deleteHotel(String id) throws ClassNotFoundException, SQLException, Exception {
            return dao.delete(id);
    }

    @Override
    public boolean updateHotel(HotelDTO h) throws SQLException, ClassNotFoundException, Exception {
        return dao.update(new Hotel(h.getHtlID(), h.gethName(), h.getAddress(), h.getTelNo(), h.getEmail(), h.getAccommodation()));
    }

    @Override
    public HotelDTO searchHotel(String id) throws ClassNotFoundException, SQLException, Exception {
            Hotel h = dao.search(id);
            return new HotelDTO(h.getHtlID(), h.gethName(), h.getAddress(), h.getTelNo(), h.getEmail(), h.getAccommodation());
    }

    @Override
    public ArrayList<HotelDTO> getAllHotel() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<HotelDTO> all=new ArrayList<>();
        ArrayList<Hotel> allHotel = dao.getAll();
        for (Hotel h : allHotel) {
            all.add(new HotelDTO(h.getHtlID(), h.gethName(), h.getAddress(), h.getTelNo(), h.getEmail(), h.getAccommodation()));
        }
        return all;
    }

    @Override
    public ArrayList<HotelDTO> searchHotelbyName(String name) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<HotelDTO> all = new ArrayList<>();
        ArrayList<Hotel> allHotel = dao.getSearchName(name);
        if (allHotel.isEmpty()) {
            return null;
        }else{
            for (Hotel h : allHotel) {
                all.add(new HotelDTO(h.getHtlID(), h.gethName(), h.getAddress(), h.getTelNo(), h.getEmail(), h.getAccommodation()));
            }
            return all;
        }
    }

    @Override
    public ArrayList<HotelDTO> searchHotelbyAddress(String address) throws ClassNotFoundException, SQLException, Exception {
        ArrayList<HotelDTO> all = new ArrayList<>();
        ArrayList<Hotel> allHotel = dao.getSearchAddress(address);
        if (allHotel.isEmpty()) {
            return null;
        }else{
            for (Hotel h : allHotel) {
                all.add(new HotelDTO(h.getHtlID(), h.gethName(), h.getAddress(), h.getTelNo(), h.getEmail(), h.getAccommodation()));
            }
            return all;
        }
    }

    @Override
    public String getHotelID() {
            try {
                String lastHotelID = dao.getLastHotelID();
                int num =Integer.parseInt(lastHotelID.substring(6))+1;
                return "htl-00"+num;
            } catch (Exception ex) {
                return "htl-001";
            }
    }
    
}
