/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.SuperBO;
import lk.pankajatravel.dto.VehicleDTO;
import lk.pankajatravel.bo.custom.VehicleBO;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class VehicleController implements Initializable {

    VehicleBO bo = (VehicleBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.VEHICLE);

    @FXML
    private JFXTextField txtVehicleID;
    @FXML
    private JFXTextField txtVehicleNum;
    @FXML
    private JFXTextField txtYOM;
    @FXML
    private JFXTextField txtPricePer1Km;
    @FXML
    private JFXButton btnAddVehicle;
    @FXML
    private JFXComboBox<String> cmbCategory;
    @FXML
    private JFXComboBox<String> cmbOwner;
    @FXML
    private JFXTextField txtOwnerName;
    @FXML
    private JFXTextField txtOwnerMobileNo;
    @FXML
    private JFXTextField txtVehicleName;
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
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtVehicleNum.requestFocus();
                setComboBoxes();
                tBtnEdit.setDisable(true);
                btnUpdate.setVisible(false);
                btnRemove.setVisible(false);
            }
        });
        loadVehicleID();
        txtOwnerName.setDisable(true);
        txtOwnerMobileNo.setDisable(true);
    }

    @FXML
    private void txtVehicleIDAction(ActionEvent event) throws SQLException, Exception {
        String searchVclID = txtVehicleID.getText();
        clearAll();
        txtVehicleID.setText(searchVclID);
        try {
            VehicleDTO searchVehical = bo.searchVehicle(searchVclID);
            txtVehicleID.setText(searchVehical.getVclID());
            txtVehicleNum.setText(searchVehical.getVclNum());
            cmbCategory.setValue(searchVehical.getCategory());
            txtYOM.setText(searchVehical.getyOM());
            txtVehicleName.setText(searchVehical.getName());
            txtPricePer1Km.setText(searchVehical.getPricePayperKm());
            cmbOwner.setValue(searchVehical.getOwner());
            txtOwnerName.setText(searchVehical.getOwnerName());
            txtOwnerMobileNo.setText(searchVehical.getOwnerMobileNum());
            tBtnEdit.setDisable(false);
            tBtnEditAction(event);
            tBtnEdit.requestFocus();
        } catch (NullPointerException ex) {
            txtVehicleNum.requestFocus();
            tBtnEdit.setSelected(false);
            tBtnEdit.setDisable(true);
            btnUpdate.setVisible(false);
            btnRemove.setVisible(false);
            setEditable();
        }

    }

    @FXML
    private void txtVehicleNumAction(ActionEvent event) throws SQLException, Exception {
        String searchVclNum = txtVehicleNum.getText();
        clearAll();
        txtVehicleNum.setText(searchVclNum);
        try {
            VehicleDTO searchVehical = bo.searchVehiclebyNumber(searchVclNum);
            txtVehicleID.setText(searchVehical.getVclID());
            txtVehicleNum.setText(searchVehical.getVclNum());
            cmbCategory.setValue(searchVehical.getCategory());
            txtYOM.setText(searchVehical.getyOM());
            txtVehicleName.setText(searchVehical.getName());
            txtPricePer1Km.setText(searchVehical.getPricePayperKm());
            cmbOwner.setValue(searchVehical.getOwner());
            txtOwnerName.setText(searchVehical.getOwnerName());
            txtOwnerMobileNo.setText(searchVehical.getOwnerMobileNum());
            tBtnEdit.setDisable(false);
            tBtnEditAction(event);
            tBtnEdit.requestFocus();
        } catch (NullPointerException ex) {
            cmbCategory.requestFocus();
            tBtnEdit.setSelected(false);
            tBtnEdit.setDisable(true);
            btnUpdate.setVisible(false);
            btnRemove.setVisible(false);
            setEditable();
        }

    }

    @FXML
    private void txtYOMAction(ActionEvent event) {
        txtVehicleName.requestFocus();
    }

    @FXML
    private void txtVehicleNameAction(ActionEvent event) {
        txtPricePer1Km.requestFocus();
    }

    @FXML
    private void txtPricePer1KmAction(ActionEvent event) {
        cmbOwner.requestFocus();
    }

    @FXML
    private void btnAddVehicleMouseClicked(MouseEvent event) throws ClassNotFoundException, Exception {
        if (Pattern.compile("^(vcl-00)[0-9]$").matcher(txtVehicleID.getText()).matches()) {
            if (Pattern.compile("^[A-Z]{2,3}(-)[0-9]{4}$").matcher(txtVehicleNum.getText()).matches()) {
                if (Pattern.compile("^(20)[0-9]{2}$").matcher(txtYOM.getText()).matches()) {
                    if (Pattern.compile("^([A-z,0-9]{1,}\\s?)+$").matcher(txtVehicleName.getText()).matches()) {
                        if (Pattern.compile("^(Rs\\.)[0-9]{1,2}((\\.)[0-9]{1,2})?$").matcher(txtPricePer1Km.getText()).matches()) {
                            if (cmbOwner.getValue().equals("Company")) {
                                boolean addVehical = bo.addVehicle(new VehicleDTO(txtVehicleID.getText(), txtVehicleNum.getText(), cmbCategory.getValue(), txtYOM.getText(), txtVehicleName.getText(), txtPricePer1Km.getText(), cmbOwner.getValue(), txtOwnerName.getText(), txtOwnerMobileNo.getText()));
                                if (addVehical) {
                                    new Alert(Alert.AlertType.INFORMATION, "Vehicle " + txtVehicleNum.getText() + " is Added Successfully", ButtonType.OK).showAndWait();
                                    clearAll();
                                    loadVehicleID();
                                    txtVehicleNum.requestFocus();
                                } else {
                                    Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
                                    al.setTitle("Error");
                                    al.showAndWait();
                                }
                            } else {
                                if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtOwnerName.getText()).matches()) {
                                    if (Pattern.compile("^((\\+94)[0-9]{9})$|^((0)[0-9]{9})$").matcher(txtOwnerMobileNo.getText()).matches()) {
                                        boolean addVehical = bo.addVehicle(new VehicleDTO(txtVehicleID.getText(), txtVehicleNum.getText(), cmbCategory.getValue(), txtYOM.getText(), txtVehicleName.getText(), txtPricePer1Km.getText(), cmbOwner.getValue(), txtOwnerName.getText(), txtOwnerMobileNo.getText()));
                                        if (addVehical) {
                                            new Alert(Alert.AlertType.INFORMATION, "Vehicle " + txtVehicleNum.getText() + " is Added Successfully", ButtonType.OK).showAndWait();
                                            clearAll();
                                            loadVehicleID();
                                            txtVehicleNum.requestFocus();
                                        } else {
                                            Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
                                            al.setTitle("Error");
                                            al.showAndWait();
                                        }
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Invalid mobile number. Please use correct mobile number correct format is (+94xxxxxxxxx) or (0xxxxxxxxx) where x=[0-9]", ButtonType.OK).show();
                                        txtOwnerMobileNo.requestFocus();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Invalid Owner Name. Please use letters only and 1st letter must be capital in each word", ButtonType.OK).show();
                                    txtOwnerName.requestFocus();
                                }
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid input for price pay per 1Km. Enter value as Rs.x, where x=1-100", ButtonType.OK).show();
                            txtPricePer1Km.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid year of manufacture. Please use numbers greater than 2000", ButtonType.OK).show();
                        txtYOM.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid vehicle name", ButtonType.OK).show();
                    txtVehicleName.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid vehicle number format. Please use following format (KA-1234 , CAK-8770)", ButtonType.OK).show();
                txtVehicleNum.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid vehicle ID format. Please use vcl-00x as vehicle ID where x={1,2,3,...}", ButtonType.OK).show();
            txtVehicleID.requestFocus();
        }

    }

    @FXML
    private void btnUpdateMouseClicked(MouseEvent event) throws ClassNotFoundException, Exception {
        if (Pattern.compile("^(vcl-00)[0-9]$").matcher(txtVehicleID.getText()).matches()) {
            if (Pattern.compile("^[A-Z]{2,3}(-)[0-9]{4}$").matcher(txtVehicleNum.getText()).matches()) {
                if (Pattern.compile("^(20)[0-9]{2}$").matcher(txtYOM.getText()).matches()) {
                    if (Pattern.compile("^(Rs\\.)[0-9]{1,2}((\\.)[0-9]{1,2})?$").matcher(txtPricePer1Km.getText()).matches()) {
                        if (cmbOwner.getValue().equals("Company")) {
                            boolean updateVehical = bo.updateVehicle(new VehicleDTO(txtVehicleID.getText(), txtVehicleNum.getText(), cmbCategory.getValue(), txtYOM.getText(), txtVehicleName.getText(), txtPricePer1Km.getText(), cmbOwner.getValue(), txtOwnerName.getText(), txtOwnerMobileNo.getText()));
                            if (updateVehical) {
                                new Alert(Alert.AlertType.INFORMATION, "Vehicle " + txtVehicleNum.getText() + " is Updated Successfully", ButtonType.OK).showAndWait();
                                clearAll();
                                loadVehicleID();
                                txtVehicleNum.requestFocus();
                            } else {
                                Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
                                al.setTitle("Error");
                                al.showAndWait();
                            }
                        } else {
                            if (Pattern.compile("^([A-Z]{1}[a-z]{1,})$|^([A-Z]{1}[a-z]{1,}\\s)([A-Z]{1}[a-z]{1,})$").matcher(txtOwnerName.getText()).matches()) {
                                if (Pattern.compile("^((\\+94)[0-9]{9})$|^((0)[0-9]{9})$").matcher(txtOwnerMobileNo.getText()).matches()) {
                                    boolean updateVehical = bo.updateVehicle(new VehicleDTO(txtVehicleID.getText(), txtVehicleNum.getText(), cmbCategory.getValue(), txtYOM.getText(), txtVehicleName.getText(), txtPricePer1Km.getText(), cmbOwner.getValue(), txtOwnerName.getText(), txtOwnerMobileNo.getText()));
                                    if (updateVehical) {
                                        new Alert(Alert.AlertType.INFORMATION, "Vehicle " + txtVehicleNum.getText() + " is Updated Successfully", ButtonType.OK).showAndWait();
                                        clearAll();
                                        loadVehicleID();
                                        txtVehicleNum.requestFocus();
                                    } else {
                                        Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
                                        al.setTitle("Error");
                                        al.showAndWait();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Invalid mobile number. Please use correct mobile number correct format is (+94xxxxxxxxx) or (0xxxxxxxxx) where x=[0-9]", ButtonType.OK).show();
                                    txtOwnerMobileNo.requestFocus();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Invalid Owner Name. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                                txtOwnerName.requestFocus();
                            }
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid input for price pay per 1Km. Enter value as Rs.x, where x=1-100", ButtonType.OK).show();
                        txtPricePer1Km.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid year of manufacture. Please use numbers greater than 2000", ButtonType.OK).show();
                    txtYOM.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid vehicle number format. Please use following format (KA-1234 , CAK-8770)", ButtonType.OK).show();
                txtVehicleNum.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid vehicle ID format. Please use vcl-00x as vehicle ID where x={1,2,3,...}", ButtonType.OK).show();
            txtVehicleID.requestFocus();
        }

    }

    @FXML
    private void btnRemoveMouseClicked(MouseEvent event) throws SQLException, Exception {
        boolean deleteVehical = bo.deleteVehicle(txtVehicleID.getText());
        if (deleteVehical) {
            new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK).showAndWait();
            clearAll();
            loadVehicleID();
            txtVehicleNum.requestFocus();
        }
    }

    @FXML
    private void btnViewAllMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/ViewAllVehicles.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    private void btnMoreDetailMouseClicked(MouseEvent event) {
    }

    @FXML
    private void txtOwnerNameAction(ActionEvent event) {
        txtOwnerMobileNo.requestFocus();
    }

    @FXML
    private void txtOwnerMobileNoAction(ActionEvent event) {
        btnAddVehicle.requestFocus();
    }

    public void setComboBoxes() {
        ObservableList<String> ol1 = FXCollections.observableArrayList("Car", "Van", "Bus", "SUV", "Luxury", "Other");
        cmbCategory.setItems(ol1);

        ObservableList<String> ol2 = FXCollections.observableArrayList("Company", "Guide", "Other");
        cmbOwner.setItems(ol2);
    }

    private void clearAll() {
        txtOwnerMobileNo.setText("");
        txtOwnerName.setText("");
        txtPricePer1Km.setText("Rs.");
        txtVehicleNum.setText("");
        txtVehicleName.setText("");
        txtYOM.setText("");
        cmbCategory.setValue("");
        cmbOwner.setValue("");
        txtOwnerName.setDisable(true);
        txtOwnerMobileNo.setDisable(true);
    }

    @FXML
    private void tBtnEditAction(ActionEvent event) {
        if (tBtnEdit.isSelected()) {
            setEditable();
            cmbCategory.requestFocus();
            btnRemove.setVisible(true);
            btnUpdate.setVisible(true);
        } else {
            setEditableOff();
            btnRemove.setVisible(false);
            btnUpdate.setVisible(false);
        }
    }

    private void setEditableOff() {
        txtYOM.setEditable(false);
        txtVehicleName.setEditable(false);
        txtPricePer1Km.setEditable(false);
        txtOwnerName.setEditable(false);
        txtOwnerMobileNo.setEditable(false);
        txtOwnerName.setDisable(true);
        txtOwnerMobileNo.setDisable(true);
    }

    private void setEditable() {
        txtYOM.setEditable(true);
        txtVehicleName.setEditable(true);
        txtPricePer1Km.setEditable(true);
        txtOwnerName.setEditable(true);
        txtOwnerMobileNo.setEditable(true);
        txtOwnerName.setDisable(false);
        txtOwnerMobileNo.setDisable(false);
    }

    @FXML
    private void cmbOwnerKeyPressed(KeyEvent event) {
//        System.out.println(event.getCode());
        if (event.getCode().equals(ENTER)) {
            switch (cmbOwner.getValue()) {
                case "Company":
                    txtOwnerMobileNo.setText("");
                    txtOwnerName.setText("");
                    txtOwnerMobileNo.setDisable(true);
                    txtOwnerName.setDisable(true);
                    btnAddVehicle.requestFocus();
                    break;
                case "Guide":
                    txtOwnerMobileNo.setDisable(false);
                    txtOwnerName.setDisable(false);
                    txtOwnerName.requestFocus();
                    break;
                case "Other":
                    txtOwnerMobileNo.setDisable(false);
                    txtOwnerName.setDisable(false);
                    txtOwnerName.requestFocus();
                    break;
            }
        }
    }

    @FXML
    private void cmbCategoryKeyPressed(KeyEvent event) {
        if (event.getCode().equals(ENTER)) {
            txtYOM.requestFocus();
        }
    }

    private void loadVehicleID() {
        String vehicleID = bo.getVehicleID();
        txtVehicleID.setText(vehicleID);
    }

}
