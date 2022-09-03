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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.custom.VehicleBO;
import lk.pankajatravel.dto.VehicleDTO;
import lk.pankajatravel.tableModel.VehicleTM;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class ViewAllVehiclesController implements Initializable {

        VehicleBO bo = (VehicleBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.VEHICLE);

    @FXML
    private TableView<VehicleTM> tblVehicle;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblVehicle.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblVehicle.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblVehicle.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblVehicle.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        tblVehicle.getColumns().get(4).setStyle("-fx-alignment: CENTER;");
        tblVehicle.getColumns().get(5).setStyle("-fx-alignment: CENTER;");
        tblVehicle.getColumns().get(6).setStyle("-fx-alignment: CENTER;");
        tblVehicle.getColumns().get(7).setStyle("-fx-alignment: CENTER;");
        tblVehicle.getColumns().get(8).setStyle("-fx-alignment: CENTER;");
//      -------------------------setting table columns--------------------------------------
        tblVehicle.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Vehicle_ID"));
        tblVehicle.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Vehicle_Number"));
        tblVehicle.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Category"));
        tblVehicle.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("YOM"));
        tblVehicle.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblVehicle.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Price_per_1Km"));
        tblVehicle.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Owner"));
        tblVehicle.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("Owner_Name"));
        tblVehicle.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("Owner_Mobile_No"));
            try {
                getAll();
            } catch (Exception ex) {
                Logger.getLogger(ViewAllVehiclesController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    private void getAll() throws SQLException, Exception{
            ArrayList<VehicleTM> allVehicle = new ArrayList<>();
            ArrayList<VehicleDTO> all = bo.getAllVehicle();
            for (VehicleDTO v : all) {
            allVehicle.add(new VehicleTM(v.getVclID(), v.getVclNum(), v.getCategory(), v.getyOM(), v.getName(), v.getPricePayperKm(), v.getOwner(),v.getOwnerName(),v.getOwnerMobileNum()));
        }
            tblVehicle.setItems(FXCollections.observableList(allVehicle));
    }
    
}
