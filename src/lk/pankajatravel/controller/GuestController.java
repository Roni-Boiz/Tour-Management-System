/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.SuperBO;
import lk.pankajatravel.bo.custom.AirportBO;
import lk.pankajatravel.bo.custom.GuestBO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.dto.AirportDTO;
import lk.pankajatravel.dto.GuestDTO;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class GuestController implements Initializable {

    GuestBO bo = (GuestBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.GUEST);

    AirportBO boAirport = (AirportBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.AIRPORT);

    @FXML
    private JFXTextField txtGstID;
    @FXML
    private JFXTextField txtCountry;
    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField txtMobileNo;
    @FXML
    private JFXTextField txtFlightNo;
    @FXML
    private JFXDatePicker txtArriveDate;
    @FXML
    private JFXTimePicker txtArriveTime;
    @FXML
    private JFXButton btnAddTour;
    @FXML
    private JFXDatePicker txtDepartureDate;
    @FXML
    private JFXTimePicker txtDepartureTime;
    @FXML
    private JFXButton btnSearchGuest;
    @FXML
    private JFXButton btnAddGuest;
    @FXML
    private JFXButton btnViewAllGuests;
    @FXML
    private AnchorPane paneGuest;
    @FXML
    private JFXComboBox<String> cmbAirport;
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
                txtCountry.requestFocus();
                try {
                    setComboBox();
                    loadGuestID();
                } catch (Exception ex) {
                    Logger.getLogger(GuestController.class.getName()).log(Level.SEVERE, null, ex);
                }
                setDateVariables();
                tBtnEdit.setDisable(true);
                btnUpdate.setVisible(false);
                btnRemove.setVisible(false);
            }
        });
    }

    @FXML
    private void txtGstIDActionPerformed(ActionEvent event) throws SQLException, Exception {
        try {
            String gstID = txtGstID.getText();
            clearAll();
            txtGstID.setText(gstID);
            GuestDTO searchGuest = bo.searchGuest(gstID);
//  --------------      Setting all text   ----------------------
            txtGstID.setText(searchGuest.getGuestID());
            txtCountry.setText(searchGuest.getCountry());
            txtFName.setText(searchGuest.getFirstName());
            txtLName.setText(searchGuest.getLastName());
            txtMobileNo.setText(searchGuest.getMobileNum());
            txtEmail.setText(searchGuest.getEmailAddress());
            txtFlightNo.setText(searchGuest.getFlightNum());
            cmbAirport.setValue(searchGuest.getAirport());
            txtArriveDate.setValue(LocalDate.parse(searchGuest.getArriveDate()));
            txtArriveTime.setValue(LocalTime.parse(searchGuest.getArriveTime()));
            txtDepartureDate.setValue(LocalDate.parse(searchGuest.getDepartureDate()));
            txtDepartureTime.setValue(LocalTime.parse(searchGuest.getDepartureTime()));
//        txtArriveDate.setValue(searchGuest.getArriveDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()); use to convert date to localdate
            tBtnEdit.setDisable(false);
            tBtnEditAction(event);
            tBtnEdit.requestFocus();
        } catch (NullPointerException ex) {
            txtCountry.requestFocus();
            tBtnEdit.setSelected(false);
            tBtnEdit.setDisable(true);
            btnUpdate.setVisible(false);
            btnRemove.setVisible(false);
            setEditable();
        }

    }

    @FXML
    private void txtCountryActionPerformed(ActionEvent event) {
        txtFName.requestFocus();
    }

    @FXML
    private void txtFNameActionPerformed(ActionEvent event) {
        txtLName.requestFocus();
    }

    @FXML
    private void txtEmailActionPerformed(ActionEvent event) {
        txtFlightNo.requestFocus();
    }

    @FXML
    private void txtLNameActionPerformed(ActionEvent event) {
        txtMobileNo.requestFocus();
    }

    @FXML
    private void txtMobileNoActionPerformed(ActionEvent event) {
        txtEmail.requestFocus();
    }

    @FXML
    private void txtFlightNoActionPerformed(ActionEvent event) {
        cmbAirport.requestFocus();
    }

    @FXML
    private void txtArriveDateActionPerformed(ActionEvent event) {
        txtArriveTime.requestFocus();
    }

    @FXML
    private void txtArriveTimeActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnAddTourMouseClicked(MouseEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/Tour.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    private void btnUpdateGuestMouseClicked(MouseEvent event) throws IOException, ClassNotFoundException, Exception {
        if (Pattern.compile("^(gst-00)[0-9]$").matcher(txtGstID.getText()).matches()) {
            if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$|^[A-Z]{1,}$").matcher(txtCountry.getText()).matches()) {
                if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtFName.getText()).matches()) {
                    if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtLName.getText()).matches()) {
                        if (Pattern.compile("^([\\+])?[0-9]{10,12}$").matcher(txtMobileNo.getText()).matches()) {
                            if (Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)$").matcher(txtLName.getText()).matches()) {
                                String guestID = txtGstID.getText();
                                String country = txtCountry.getText();
                                String firstName = txtFName.getText();
                                String lastName = txtLName.getText();
                                String mobileNum = txtMobileNo.getText();
                                String emailAddress = txtEmail.getText();
                                String flightNum = txtFlightNo.getText();
                                String airport = cmbAirport.getValue().toString();
                                String arriveDate = txtArriveDate.getValue().format(DateTimeFormatter.ISO_DATE);
                                String arriveTime = txtArriveTime.getValue().format(DateTimeFormatter.ISO_TIME);
                                String departureDate = txtDepartureDate.getValue().format(DateTimeFormatter.ISO_DATE);
                                String departureTime = txtDepartureTime.getValue().format(DateTimeFormatter.ISO_TIME);

                                GuestDTO guestDTO = new GuestDTO(guestID, country, firstName, lastName, mobileNum, emailAddress, flightNum, airport, arriveDate, arriveTime, departureDate, departureTime);
                                boolean updateGuest = bo.updateGuest(guestDTO);
                                if (updateGuest) {
                                    doneAlert("Guest "+firstName+" is Updated Successfully");
                                } else {
                                    ErrorAlert();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Invalid email address.", ButtonType.OK).show();
                                txtMobileNo.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field mobile number. Please use numbers and + sign  correct format is \"+441234567890\" or \"07xxxxxxxx\"", ButtonType.OK).show();
                            txtMobileNo.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field last name. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                        txtLName.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field first name. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                    txtFName.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field country. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                txtCountry.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid guest ID format. Please use gst-00x as guest ID where x={1,2,3,...}", ButtonType.OK).show();
            txtGstID.requestFocus();
        }

    }

    @FXML
    private void btnRemoveGuestMouseClicked(MouseEvent event) throws IOException, SQLException, Exception {
        try {
            String gid = txtGstID.getText();
            boolean deleteGuest = bo.deleteGuest(gid);
            if (deleteGuest) {
                doneAlert("Guest "+txtFName.getText()+" is removed successfully");
            } else {
                ErrorAlert();
            }
        } catch (Exception ex) {
            Alert al = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            al.setTitle("Error");
            al.showAndWait();
        }
    }

    @FXML
    private void txtDepartureDateActionPerformed(ActionEvent event) {
        txtDepartureTime.requestFocus();
    }

    @FXML
    private void txtDepartureTimeActionPerformed(ActionEvent event) {
    }

    @FXML
    private void cmbAirportKeyPressed(KeyEvent event) {
        if (event.getCode().equals(ENTER)) {
            txtArriveDate.requestFocus();
        }
    }

    @FXML
    private void btnSearchGuestMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/SearchGuest.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    private void btnAddGuestMouseClicked(MouseEvent event) throws ClassNotFoundException, Exception {
        if (Pattern.compile("^(gst-00)[0-9]$").matcher(txtGstID.getText()).matches()) {
            if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$|^[A-Z]{1,}$").matcher(txtCountry.getText()).matches()) {
                if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtFName.getText()).matches()) {
                    if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtLName.getText()).matches()) {
                        if (Pattern.compile("^([\\+])?[0-9]{10,12}$").matcher(txtMobileNo.getText()).matches()) {
                            if (Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)$").matcher(txtEmail.getText()).matches()) {
                                String guestID = txtGstID.getText();
                                String country = txtCountry.getText();
                                String firstName = txtFName.getText();
                                String lastName = txtLName.getText();
                                String mobileNum = txtMobileNo.getText();
                                String emailAddress = txtEmail.getText();
                                String flightNum = txtFlightNo.getText();
                                String airport = cmbAirport.getValue().toString();
                                String arriveDate = txtArriveDate.getValue().format(DateTimeFormatter.ISO_DATE);
                                String arriveTime = txtArriveTime.getValue().format(DateTimeFormatter.ISO_TIME);
                                String departureDate = txtDepartureDate.getValue().format(DateTimeFormatter.ISO_DATE);
                                String departureTime = txtDepartureTime.getValue().format(DateTimeFormatter.ISO_TIME);

                                GuestDTO guestDTO = new GuestDTO(guestID, country, firstName, lastName, mobileNum, emailAddress, flightNum, airport, arriveDate, arriveTime, departureDate, departureTime);
                                boolean addGuest = bo.addGuest(guestDTO);
                                if (addGuest) {
                                    doneAlert("Guest "+firstName+" is Added Successfully");
                                } else {
                                    ErrorAlert();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Invalid email address.", ButtonType.OK).show();
                                txtMobileNo.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field mobile number. Please use numbers and + sign  correct format is \"+441234567890\" or \"07xxxxxxxx\"", ButtonType.OK).show();
                            txtMobileNo.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field last name. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                        txtLName.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field first name. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                    txtFName.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field country. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                txtCountry.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid guest ID format. Please use gst-00x as guest ID where x={1,2,3,...}", ButtonType.OK).show();
            txtGstID.requestFocus();
        }

    }

    @FXML
    private void btnViewAllGuestsMouseClicked(MouseEvent event) throws IOException, Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/ViewAllGuests.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    private void btnBackMouseClicked(MouseEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/GuestManagement.fxml"));
        paneGuest.getChildren().setAll(pane);
    }

    public void setComboBox() throws ClassNotFoundException, SQLException, Exception {
        ArrayList<AirportDTO> allAirports = boAirport.getAllAirport();
        ObservableList<String> ol = FXCollections.observableArrayList();
        for (AirportDTO a : allAirports) {
            ol.add(a.getName());
        }
        cmbAirport.setItems(ol);
    }

    @FXML
    private void btnAddAirportMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/AirportManagement.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
    }

    @FXML
    private void cmbAirportMouseClicked(MouseEvent event) throws ClassNotFoundException, SQLException, Exception {
        setComboBox();
    }

    @FXML
    private void tBtnEditAction(ActionEvent event) {
        if (tBtnEdit.isSelected()) {
            setEditable();
            txtFName.requestFocus();
            btnRemove.setVisible(true);
            btnUpdate.setVisible(true);
        } else {
            setEditableOff();
            btnRemove.setVisible(false);
            btnUpdate.setVisible(false);
        }
    }

    private void clearAll() {
        txtGstID.setText("");
        txtCountry.setText("");
        txtFName.setText("");
        txtLName.setText("");
        txtMobileNo.setText("");
        txtEmail.setText("");
        txtFlightNo.setText("");
        cmbAirport.setValue("");
        txtDepartureDate.setValue(null);
        txtDepartureTime.setValue(null);
        txtArriveDate.setValue(null);
        txtArriveTime.setValue(null);
    }

    private void doneAlert(String s) {
        Alert al = new Alert(Alert.AlertType.INFORMATION, s, ButtonType.OK);
        al.setTitle("Done");
        al.setHeaderText("Information Message");
        al.showAndWait();
        clearAll();
        loadGuestID();
        txtCountry.requestFocus();
    }

    private void ErrorAlert() {
        Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
        al.setTitle("Error");
        al.setHeaderText("Error Message");
        al.showAndWait();
    }

    private void setDateVariables() {

        txtArriveDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });
        txtDepartureDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = txtArriveDate.getValue();
                setDisable(empty || date.compareTo(today) < 0);
            }
        });
    }

    private void setEditableOff() {
        txtCountry.setEditable(false);
        txtFName.setEditable(false);
        txtLName.setEditable(false);
        txtMobileNo.setEditable(false);
        txtEmail.setEditable(false);
        txtFlightNo.setEditable(false);
        txtArriveTime.setEditable(false);
        txtDepartureTime.setEditable(false);
    }

    private void setEditable() {
        txtCountry.setEditable(true);
        txtFName.setEditable(true);
        txtLName.setEditable(true);
        txtMobileNo.setEditable(true);
        txtEmail.setEditable(true);
        txtFlightNo.setEditable(true);
    }

    private void loadGuestID() {
        String guestID = bo.getGuestID();
        txtGstID.setText(guestID);
    }
}
