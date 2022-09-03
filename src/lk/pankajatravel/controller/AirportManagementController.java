/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.custom.AirportBO;
import lk.pankajatravel.dto.AirportDTO;
import lk.pankajatravel.tableModel.AirportTM;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class AirportManagementController implements Initializable {

    AirportBO bo = (AirportBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.AIRPORT);

    @FXML
    private JFXTextField txtAirportID;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtLocation;
    @FXML
    private JFXButton btnAddAirport;
    @FXML
    private TableView<AirportTM> tblAirport;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblAirport.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblAirport.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblAirport.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
//      -------------------------setting table columns--------------------------------------
        tblAirport.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Airport_ID"));
        tblAirport.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblAirport.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Location"));
        try {
            getAll();
            loadAirportID();
        } catch (Exception ex) {
            Logger.getLogger(AirportManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtName.requestFocus();
            }
        });
        try {
            getAll();
        } catch (Exception ex) {
            Logger.getLogger(AirportManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void txtAirportIDAction(ActionEvent event) throws SQLException, Exception {
        String searchAirportID = txtAirportID.getText();
        ClearAll();
        txtAirportID.setText(searchAirportID);
        try {
            AirportDTO searchAirport = bo.searchAirport(searchAirportID);
            txtAirportID.setText(searchAirport.getAirportID());
            txtLocation.setText(searchAirport.getLocation());
            txtName.setText(searchAirport.getName());
        } catch (NullPointerException ex) {

        }
        txtName.requestFocus();
    }

    @FXML
    private void txtNameAction(ActionEvent event) {
        txtLocation.requestFocus();
    }

    @FXML
    private void txtLocationAction(ActionEvent event) {
        btnAddAirport.requestFocus();
    }

    @FXML
    private void btnAddAirportMouseClicked(MouseEvent event) throws ClassNotFoundException, Exception {
        if (Pattern.compile("^(apt-00)[0-9]$").matcher(txtAirportID.getText()).matches()) {
            if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtLocation.getText()).matches()) {
                    boolean addAirport = bo.addAirport(new AirportDTO(txtAirportID.getText(), txtName.getText(), txtLocation.getText()));
                    if (addAirport) {
                        getAll();
                        new Alert(Alert.AlertType.INFORMATION, txtName.getText() + " is Added Successfully", ButtonType.OK).showAndWait();
                        ClearAll();
                        loadAirportID();
                        txtName.requestFocus();
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK).showAndWait();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid location format. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                    txtName.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid airport name format. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                txtName.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid airport ID format. Please use apt-00x as airport ID where x={1,2,3,...}", ButtonType.OK).show();
            txtAirportID.requestFocus();
        }

    }

    @FXML
    private void btnUpdateMouseClicked(MouseEvent event) throws ClassNotFoundException, Exception {
        if (Pattern.compile("^(apt-00)[0-9]$").matcher(txtAirportID.getText()).matches()) {
            if (Pattern.compile("^([A-Z]{1}[a-z]{1,})$|^([A-Z]{1}[a-z]{1,}\\s)([A-Z]{1}[a-z]{1,})$").matcher(txtName.getText()).matches()) {
                boolean updateAirport = bo.updateAirport(new AirportDTO(txtAirportID.getText(), txtName.getText(), txtLocation.getText()));
                if (updateAirport) {
                    getAll();
                    new Alert(Alert.AlertType.INFORMATION, txtName.getText() + " is Updated Successfully", ButtonType.OK).showAndWait();
                    ClearAll();
                    loadAirportID();
                    txtName.requestFocus();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK).showAndWait();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid airport name format. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                txtName.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid airport ID format. Please use apt-00x as airport ID where x={1,2,3,...}", ButtonType.OK).show();
            txtAirportID.requestFocus();
        }

    }

    @FXML
    private void btnRemoveMouseClicked(MouseEvent event) throws SQLException, Exception {
        boolean deleteAirport = bo.deleteAirport(txtAirportID.getText());
        if (deleteAirport) {
            getAll();
            new Alert(Alert.AlertType.INFORMATION, txtName.getText() + " is removed Successfully", ButtonType.OK).showAndWait();
            ClearAll();
            loadAirportID();
            txtName.requestFocus();
        } else {
            new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void btnMoreDetailsMouseClicked(MouseEvent event) {
    }

    private void ClearAll() {
        txtAirportID.setText("");
        txtLocation.setText("");
        txtName.setText("");
    }

    private void getAll() throws SQLException, Exception {
        ArrayList<AirportTM> allAirport = new ArrayList<AirportTM>();
        ArrayList<AirportDTO> all = bo.getAllAirport();
        for (AirportDTO a : all) {
            allAirport.add(new AirportTM(a.getAirportID(), a.getName(), a.getLocation()));
        }
        tblAirport.setItems(FXCollections.observableList(allAirport));
    }

    private void loadAirportID() {
        String AirportID = bo.getAirportID();
        txtAirportID.setText(AirportID);
    }

    @FXML
    private void tblAirportMouseClicked(MouseEvent event) {
        try {
            AirportTM selectedItem = tblAirport.getSelectionModel().getSelectedItem();
            txtAirportID.setText(selectedItem.getAirport_ID());
            txtName.setText(selectedItem.getName());
            txtLocation.setText(selectedItem.getLocation());
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.ERROR, "The table is empty", ButtonType.OK).show();
        }
    }

}
