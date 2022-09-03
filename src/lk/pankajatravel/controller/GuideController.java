/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.custom.GuideBO;
import lk.pankajatravel.dto.GuideDTO;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class GuideController implements Initializable {

    GuideBO bo = (GuideBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.GUIDE);

    @FXML
    private AnchorPane paneGuide;
    @FXML
    private JFXTextField txtGidID;
    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField txtNICNumber;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtYearofExp;
    @FXML
    private JFXTextField txtMobileNo;
    @FXML
    private JFXTextField txtEmailAddress;
    @FXML
    private JFXButton btnAddGuide;
    @FXML
    private JFXRadioButton rBtnYes;
    @FXML
    private JFXRadioButton rBtnNo;
    @FXML
    private JFXTextField txtVehicle;

    ToggleGroup tgBtn = new ToggleGroup();
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXToggleButton tBtnEdit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtFName.requestFocus();
                rBtnYes.setToggleGroup(tgBtn);
                rBtnYes.setUserData("Yes");
                rBtnNo.setToggleGroup(tgBtn);
                rBtnNo.setUserData("No");
                tBtnEdit.setDisable(true);
                btnUpdate.setVisible(false);
                btnRemove.setVisible(false);
            }
        });
        loadGuideID();
    }

    @FXML
    private void btnBackMouseClicked(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(this.getClass().getResource("/lk/pankajatravel/view/GuideManagement.fxml"));
        paneGuide.getChildren().setAll(pane);
    }

    @FXML
    private void txtGidIDActionPerformed(ActionEvent event) throws SQLException, Exception {
        String gidID = txtGidID.getText();
        clearAll();
        txtGidID.setText(gidID);
        try {
            GuideDTO searchGuide = bo.searchGuide(gidID);
            txtGidID.setText(searchGuide.getGidID());
            txtFName.setText(searchGuide.getfName());
            txtLName.setText(searchGuide.getlName());
            txtNICNumber.setText(searchGuide.getNIC());
            txtAddress.setText(searchGuide.getAddress());
            txtYearofExp.setText(searchGuide.getYOE() + "");
            txtMobileNo.setText(searchGuide.getMobileNo());
            txtEmailAddress.setText(searchGuide.getEmail());
            txtVehicle.setText(searchGuide.getVehicle());
            rBtnYes.setSelected(searchGuide.getGuideLicence().equals("Yes"));
            rBtnNo.setSelected(searchGuide.getGuideLicence().equals("No"));

            tBtnEdit.setDisable(false);
            tBtnEditAction(event);
            tBtnEdit.requestFocus();
        } catch (NullPointerException ex) {
            txtFName.requestFocus();
            tBtnEdit.setSelected(false);
            tBtnEdit.setDisable(true);
            btnUpdate.setVisible(false);
            btnRemove.setVisible(false);
            setEditable();
        }

    }

    @FXML
    private void txtFNameActionPerformed(ActionEvent event) {
        txtLName.requestFocus();
    }

    @FXML
    private void txtLNameActionPerformed(ActionEvent event) {
        txtNICNumber.requestFocus();
    }

    @FXML
    private void txtNICNumberActionPerformed(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    private void txtAddressActionPerformed(ActionEvent event) {
        txtYearofExp.requestFocus();
    }

    @FXML
    private void txtYearofExpActionPerformed(ActionEvent event) {
        txtMobileNo.requestFocus();
    }

    @FXML
    private void txtMobileNoActionPerformed(ActionEvent event) {
        txtEmailAddress.requestFocus();
    }

    @FXML
    private void txtEmailAddressActionPerformed(ActionEvent event) {
        txtVehicle.requestFocus();
    }

    @FXML
    private void txtVahicleAction(ActionEvent event) {
        rBtnYes.requestFocus();
    }

    @FXML
    private void btnAddGuideMouseClicked(MouseEvent event) throws ClassNotFoundException, Exception {
        if (Pattern.compile("^(gid-00)[0-9]$").matcher(txtGidID.getText()).matches()) {
            if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtFName.getText()).matches()) {
                if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtLName.getText()).matches()) {
                    if (Pattern.compile("^([0-9]{9}(v|V))|(20)[0-9]{9}$").matcher(txtNICNumber.getText()).matches()) {
                        if (Pattern.compile("^[0-9]{1,2}$").matcher(txtYearofExp.getText()).matches()) {
                            if (Pattern.compile("^((\\+947)[0-9]{8})$|^((07)[0-9]{8})$").matcher(txtMobileNo.getText()).matches()) {
                                if (Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)$").matcher(txtEmailAddress.getText()).matches()) {
                                    if (Pattern.compile("^([A-z,0-9]{1,}\\s?)+$").matcher(txtVehicle.getText()).matches()) {
                                        String gidID = txtGidID.getText();
                                        String fName = txtFName.getText();
                                        String lName = txtLName.getText();
                                        String nIC = txtNICNumber.getText();
                                        String address = txtAddress.getText();
                                        int yOE = Integer.parseInt(txtYearofExp.getText());
                                        String mobileNo = txtMobileNo.getText();
                                        String email = txtEmailAddress.getText();
                                        String vehicle = txtVehicle.getText();
                                        String guideLicence = tgBtn.getSelectedToggle().getUserData().toString();
                                        GuideDTO guideDTO = new GuideDTO(gidID, fName, lName, nIC, address, yOE, mobileNo, email, vehicle, guideLicence);
                                        boolean addGuide = bo.addGuide(guideDTO);
                                        if (addGuide) {
                                            Alert al = new Alert(Alert.AlertType.INFORMATION, "Guide "+fName+" is Added Successfuly", ButtonType.OK);
                                            al.setTitle("Done");
                                            al.showAndWait();
                                            clearAll();
                                            loadGuideID();
                                            txtFName.requestFocus();
                                        } else {
                                            Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
                                            al.setTitle("Error");
                                            al.showAndWait();
                                        }
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field vehicle. Special charactors cannot entered to it.", ButtonType.OK).show();
                                        txtVehicle.requestFocus();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Invalid email address.", ButtonType.OK).show();
                                    txtEmailAddress.requestFocus();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field mobile number. Mobile number must have 10 digits starting from zero(0) or (+94xxxxxxxxx) where x=[0-9].", ButtonType.OK).show();
                                txtMobileNo.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field Year of experience. Please enter numbers between [0-99].", ButtonType.OK).show();
                            txtYearofExp.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field NIC Number. Old NIC must hava 10 digits have a format of ********V and New NIC must have 12 digits have format of 20***********.", ButtonType.OK).show();
                        txtNICNumber.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field last name. Please use letters and 1st letter must be capital in each word.", ButtonType.OK).show();
                    txtLName.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field first name. Please use letters and 1st letter must be capital in each word.", ButtonType.OK).show();
                txtFName.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid guide ID format. Please use gid-00x as guide ID where x={1,2,3,...}.", ButtonType.OK).show();
            txtGidID.requestFocus();
        }

    }

    @FXML
    private void btnUpdateMouseClicked(MouseEvent event) throws ClassNotFoundException, Exception {
        if (Pattern.compile("^(gid-00)[0-9]$").matcher(txtGidID.getText()).matches()) {
            if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtFName.getText()).matches()) {
                if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtLName.getText()).matches()) {
                    if (Pattern.compile("^([0-9]{9}(v|V))|(20)[0-9]{9}$").matcher(txtNICNumber.getText()).matches()) {
                        if (Pattern.compile("^[0-9]{1,2}$").matcher(txtYearofExp.getText()).matches()) {
                            if (Pattern.compile("^(([\\+947])[0-9]{11})$|^((07)[0-9]{8})$").matcher(txtMobileNo.getText()).matches()) {
                                if (Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)$").matcher(txtEmailAddress.getText()).matches()) {
                                    if (Pattern.compile("^([A-z,0-9]{1,}\\s?)+$").matcher(txtVehicle.getText()).matches()) {
                                        String gidID = txtGidID.getText();
                                        String fName = txtFName.getText();
                                        String lName = txtLName.getText();
                                        String nIC = txtNICNumber.getText();
                                        String address = txtAddress.getText();
                                        int yOE = Integer.parseInt(txtYearofExp.getText());
                                        String mobileNo = txtMobileNo.getText();
                                        String email = txtEmailAddress.getText();
                                        String vehicle = txtVehicle.getText();
                                        String guideLicence = tgBtn.getSelectedToggle().getUserData().toString();
                                        GuideDTO g = new GuideDTO(gidID, fName, lName, nIC, address, yOE, mobileNo, email, vehicle, guideLicence);
                                        boolean updateGuest = bo.updateGuide(g);
                                        if (updateGuest) {
                                            Alert al = new Alert(Alert.AlertType.INFORMATION, "Guide "+fName+" is Updated Successfuly", ButtonType.OK);
                                            al.setTitle("Done");
                                            al.showAndWait();
                                            clearAll();
                                            loadGuideID();
                                            txtFName.requestFocus();
                                        } else {
                                            Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
                                            al.setTitle("Error");
                                            al.showAndWait();
                                        }
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field vehicle. Special charactors cannot entered to it.", ButtonType.OK).show();
                                        txtVehicle.requestFocus();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Invalid email address.", ButtonType.OK).show();
                                    txtEmailAddress.requestFocus();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field mobile number. Mobile number must have 10 digits starting from zero(0) or (+94xxxxxxxxx) where x=[0-9].", ButtonType.OK).show();
                                txtMobileNo.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field Year of experience. Please enter numbers between [0-99].", ButtonType.OK).show();
                            txtYearofExp.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field NIC Number. Old NIC must hava 10 digits have a format of ********V and New NIC must have 12 digits have format of 20***********.", ButtonType.OK).show();
                        txtNICNumber.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field last name. Please use letters and 1st letter must be capital in each word.", ButtonType.OK).show();
                    txtLName.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field first name. Please use letters and 1st letter must be capital in each word.", ButtonType.OK).show();
                txtFName.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid guide ID format. Please use gid-00x as guide ID where x={1,2,3,...}.", ButtonType.OK).show();
            txtGidID.requestFocus();
        }

    }

    @FXML
    private void btnRemoveMouseClicked(MouseEvent event) throws SQLException, Exception {
        String gidID = txtGidID.getText();
        boolean deleteGuest = bo.deleteGuide(gidID);
        if (deleteGuest) {
            Alert al = new Alert(Alert.AlertType.INFORMATION, "Guide is Successfuly Removed", ButtonType.OK);
            al.setTitle("Done");
            al.showAndWait();
            clearAll();
            loadGuideID();
            txtFName.requestFocus();
        } else {
            Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
            al.setTitle("Error");
            al.showAndWait();
        }
    }

    @FXML
    private void btnViewAllMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/ViewAllGuides.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    private void btnMoreDetails(MouseEvent event) {
    }

    @FXML
    private void rBtnYesMouseClicked(MouseEvent event) {
        btnAddGuide.requestFocus();
    }

    @FXML
    private void rBtnNoMouseClicked(MouseEvent event) {
        btnAddGuide.requestFocus();
    }

    private void clearAll() {
        txtGidID.setText("");
        txtFName.setText("");
        txtLName.setText("");
        txtNICNumber.setText("");
        txtAddress.setText("");
        txtYearofExp.setText("");
        txtMobileNo.setText("");
        txtEmailAddress.setText("");
        txtVehicle.setText("");
        rBtnYes.setSelected(false);
        rBtnNo.setSelected(false);
    }

    private void doneAlert(String s) {
        Alert al = new Alert(Alert.AlertType.INFORMATION, "Guide is " + s + " Successfully", ButtonType.OK);
        al.setTitle("Done");
        al.setHeaderText("Information Message");
        al.showAndWait();
        clearAll();
    }

    private void ErrorAlert() {
        Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
        al.setTitle("Error");
        al.setHeaderText("Error Message");
        al.showAndWait();
    }

    @FXML
    private void tBtnEditAction(ActionEvent event) {
        if (tBtnEdit.isSelected()) {
            setEditable();
            txtFName.requestFocus();
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
        txtFName.setEditable(false);
        txtLName.setEditable(false);
        txtNICNumber.setEditable(false);
        txtAddress.setEditable(false);
        txtYearofExp.setEditable(false);
        txtMobileNo.setEditable(false);
        txtEmailAddress.setEditable(false);
        txtVehicle.setEditable(false);
        rBtnYes.setDisable(true);
        rBtnNo.setDisable(true);
    }

    private void setEditable() {
        txtFName.setEditable(true);
        txtLName.setEditable(true);
        txtNICNumber.setEditable(true);
        txtAddress.setEditable(true);
        txtYearofExp.setEditable(true);
        txtMobileNo.setEditable(true);
        txtEmailAddress.setEditable(true);
        txtVehicle.setEditable(true);
        rBtnYes.setDisable(false);
        rBtnNo.setDisable(false);
    }

    private void loadGuideID() {
        String guideID = bo.getGuideID();
        txtGidID.setText(guideID);
    }

}
