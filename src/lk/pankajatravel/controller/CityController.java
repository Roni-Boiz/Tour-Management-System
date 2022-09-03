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
import lk.pankajatravel.bo.custom.PlaceBO;
import lk.pankajatravel.dto.PlaceDTO;
import lk.pankajatravel.tableModel.PlaceTM;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class CityController implements Initializable {

    PlaceBO bo = (PlaceBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.PLACE);

    @FXML
    private JFXTextField txtPlaceID;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXButton btnAddPlace;
    @FXML
    private JFXTextField txtDistanceFromColombo;
    @FXML
    private JFXTextField txtLocation;
    @FXML
    private TableView<PlaceTM> tblPlace;
    @FXML
    private JFXToggleButton tBtnEdit;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnRemove;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblPlace.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblPlace.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblPlace.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblPlace.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
//      -------------------------setting table columns--------------------------------------
        tblPlace.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Place_ID"));
        tblPlace.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblPlace.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Location"));
        tblPlace.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Distance_to_Colombo"));
        try {
            loadAll();
            loadPlaceID();
        } catch (Exception ex) {
            Logger.getLogger(CityController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtName.requestFocus();
                tBtnEdit.setDisable(true);
                btnUpdate.setVisible(false);
                btnRemove.setVisible(false);
            }
        });
    }

    @FXML
    private void txtPlaceIDAction(ActionEvent event) throws SQLException, Exception {
        String searchplcID = txtPlaceID.getText();
        clearAll();
        txtPlaceID.setText(searchplcID);
        try {
            PlaceDTO searchPlace = bo.searchPlace(searchplcID);
            txtPlaceID.setText(searchPlace.getPlcID());
            txtName.setText(searchPlace.getPlcName());
            txtLocation.setText(searchPlace.getLocation());
            txtDistanceFromColombo.setText(searchPlace.getDistanceFromColombo());
            tBtnEdit.setDisable(false);
            tBtnEditAction(event);
            tBtnEdit.requestFocus();
        } catch (NullPointerException ex) {
            txtName.requestFocus();
            tBtnEdit.setSelected(false);
            tBtnEdit.setDisable(true);
            btnUpdate.setVisible(false);
            btnRemove.setVisible(false);
            setEditable();
        }

    }

    @FXML
    private void txtNameAction(ActionEvent event) {
        txtLocation.requestFocus();
    }

    @FXML
    private void txtLocationAction(ActionEvent event) {
        txtDistanceFromColombo.requestFocus();
    }

    @FXML
    private void txtDistanceFromColomboAction(ActionEvent event) {
        btnAddPlace.requestFocus();
    }

    @FXML
    private void btnAddPlaceAction(ActionEvent event) throws ClassNotFoundException, Exception {
        if (Pattern.compile("^(plc-00)[0-9]$").matcher(txtPlaceID.getText()).matches()) {
            if (Pattern.compile("^([A-z]{1}[a-z]{1,}\\s?)+$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtLocation.getText()).matches()) {
                    if (Pattern.compile("^[0-9]+$").matcher(txtDistanceFromColombo.getText()).matches()) {
                        boolean addPlace = bo.addPlace(new PlaceDTO(txtPlaceID.getText(), txtName.getText(), txtLocation.getText(), txtDistanceFromColombo.getText()));
                        if (addPlace) {
                            loadAll();
                            new Alert(Alert.AlertType.INFORMATION, "Place " + txtName.getText() + " is Added Successfully", ButtonType.OK).showAndWait();
                            clearAll();
                            loadPlaceID();
                            txtName.requestFocus();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Something went Wrong", ButtonType.OK).showAndWait();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered into field distance from colombo. Please enter numbers for that text field", ButtonType.OK).show();
                        txtDistanceFromColombo.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid location. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                    txtLocation.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid place name. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                txtName.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid place ID format. Please use plc-00x as place ID where x={1,2,3,...}", ButtonType.OK).show();
            txtPlaceID.requestFocus();
        }

    }

    @FXML
    private void btnUpdateMouseClicked(MouseEvent event) throws ClassNotFoundException, Exception {
        if (Pattern.compile("^(plc-00)[0-9]$").matcher(txtPlaceID.getText()).matches()) {
            if (Pattern.compile("^([A-z]{1}[a-z]{1,}\\s?)+$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtLocation.getText()).matches()) {
                    if (Pattern.compile("^[0-9]+$").matcher(txtDistanceFromColombo.getText()).matches()) {
                        boolean updatePlace = bo.updatePlace(new PlaceDTO(txtPlaceID.getText(), txtName.getText(), txtLocation.getText(), txtDistanceFromColombo.getText()));
                        if (updatePlace) {
                            loadAll();
                            new Alert(Alert.AlertType.INFORMATION,"Place " + txtName.getText() + " is Updated Successfully", ButtonType.OK).showAndWait();
                            clearAll();
                            loadPlaceID();
                            txtName.requestFocus();
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Something went wrong", ButtonType.OK).showAndWait();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered into field distance from colombo. Please enter numbers for that text field", ButtonType.OK).show();
                        txtDistanceFromColombo.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid location. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                    txtLocation.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid place name. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                txtName.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid place ID format. Please use plc-00x as place ID where x={1,2,3,...}", ButtonType.OK).show();
            txtPlaceID.requestFocus();
        }

    }

    @FXML
    private void btnRemoveMouseClicked(MouseEvent event) throws SQLException, Exception {
        boolean deletePlace = bo.deletePlace(txtPlaceID.getText());
        if (deletePlace) {
            loadAll();
            clearAll();
            new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK).showAndWait();
            loadPlaceID();
            txtName.requestFocus();
        } else {
            new Alert(Alert.AlertType.ERROR, "not Done", ButtonType.OK).showAndWait();

        }
    }

    @FXML
    private void btnMoreDetailsMouseClicked(MouseEvent event) {
    }

    private void clearAll() {
        txtDistanceFromColombo.setText("");
        txtLocation.setText("");
        txtName.setText("");
        txtPlaceID.setText("");
    }

    private void loadAll() throws SQLException, Exception {
        ArrayList<PlaceTM> all = new ArrayList<>();
        ArrayList<PlaceDTO> allPlace = bo.getAllPlace();
        for (PlaceDTO p : allPlace) {
            all.add(new PlaceTM(p.getPlcID(), p.getPlcName(), p.getLocation(), p.getDistanceFromColombo()));
        }
        tblPlace.setItems(FXCollections.observableList(all));
    }

    @FXML
    private void tBtnEditAction(ActionEvent event) {
        if (tBtnEdit.isSelected()) {
            setEditable();
            txtName.requestFocus();
            btnRemove.setVisible(true);
            btnUpdate.setVisible(true);
//            System.out.println("on");
        } else {
            setEditableOff();
            btnRemove.setVisible(false);
            btnUpdate.setVisible(false);
//            System.out.println("off");
        }
    }

    private void setEditableOff() {
        txtName.setEditable(false);
        txtLocation.setEditable(false);
        txtDistanceFromColombo.setEditable(false);
    }

    private void setEditable() {
        txtName.setEditable(true);
        txtLocation.setEditable(true);
        txtDistanceFromColombo.setEditable(true);
    }

    private void loadPlaceID() {
        String placeID = bo.getPlaceID();
        txtPlaceID.setText(placeID);
    }

    @FXML
    private void tblPlaceMouseClicked(MouseEvent event) {
        try {
            PlaceTM selectedItem = tblPlace.getSelectionModel().getSelectedItem();
            txtPlaceID.setText(selectedItem.getPlace_ID());
            txtName.setText(selectedItem.getName());
            txtLocation.setText(selectedItem.getLocation());
            txtDistanceFromColombo.setText(selectedItem.getDistance_to_Colombo());
            tBtnEdit.setDisable(false);
            tBtnEdit.requestFocus();
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.ERROR, "The table is empty", ButtonType.OK).show();
        }
    }

}
