/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.custom.HotelBO;
import lk.pankajatravel.dto.HotelDTO;
import lk.pankajatravel.tableModel.HotelTM;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class ViewAllHotelsController implements Initializable {
    
    HotelBO bo = (HotelBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.HOTEL);

    @FXML
    private TableView<HotelTM> tblHotel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblHotel.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblHotel.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblHotel.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblHotel.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        tblHotel.getColumns().get(4).setStyle("-fx-alignment: CENTER;");
        tblHotel.getColumns().get(5).setStyle("-fx-alignment: CENTER;");
//      -------------------------setting table columns--------------------------------------
        tblHotel.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Hotel_ID"));
        tblHotel.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblHotel.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblHotel.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Telephone_Number"));
        tblHotel.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Email"));
        tblHotel.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Accommodation"));
        try {
            loadAll();
        } catch (Exception ex) {
            Logger.getLogger(ViewAllHotelsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    private void loadAll() throws SQLException, Exception{
        ArrayList<HotelTM> all=new ArrayList<>();
        ArrayList<HotelDTO> allHotel = bo.getAllHotel();
        for (HotelDTO h : allHotel) {
           all.add(new HotelTM(h.getHtlID(),h.gethName(),h.getAddress(),h.getTelNo(),h.getEmail(),h.getAccommodation()));
        }
        tblHotel.setItems(FXCollections.observableList(all));
    }
    
}
