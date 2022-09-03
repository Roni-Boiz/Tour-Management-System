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
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.custom.GuestBO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.dto.GuestDTO;
import lk.pankajatravel.tableModel.GuestTM;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class SearchGuestController implements Initializable {

    GuestBO bo = (GuestBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.GUEST);

    @FXML
    private Label lblHeading1;
    @FXML
    private JFXComboBox<String> cmbSearchOption;
    @FXML
    private TextField txtSearchData;
    @FXML
    private TableView<GuestTM> tblGuest;
    @FXML
    private TextField txtSearchGuestID;
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
    private JFXDatePicker txtDepartureDate;
    @FXML
    private JFXTimePicker txtDepartureTime;
    @FXML
    private Label lblHeading;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXComboBox<String> cmbAirport;
    @FXML
    private JFXToggleButton tBtnEdit;
    @FXML
    private TabPane tabPaneGuest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //      --------------------Setting styles----------------------------------
        tblGuest.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblGuest.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblGuest.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblGuest.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        tblGuest.getColumns().get(4).setStyle("-fx-alignment: CENTER;");
        tblGuest.getColumns().get(5).setStyle("-fx-alignment: CENTER;");
        tblGuest.getColumns().get(6).setStyle("-fx-alignment: CENTER;");
        tblGuest.getColumns().get(7).setStyle("-fx-alignment: CENTER;");
        tblGuest.getColumns().get(8).setStyle("-fx-alignment: CENTER;");
        tblGuest.getColumns().get(9).setStyle("-fx-alignment: CENTER;");
        tblGuest.getColumns().get(10).setStyle("-fx-alignment: CENTER;");
        tblGuest.getColumns().get(11).setStyle("-fx-alignment: CENTER;");
//      -------------------------setting table columns--------------------------------------
        tblGuest.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("GstID"));
        tblGuest.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("F_Name"));
        tblGuest.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("L_Name"));
        tblGuest.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Country"));
        tblGuest.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Mobile_No"));
        tblGuest.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Email"));
        tblGuest.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Flight_No"));
        tblGuest.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("Airport"));
        tblGuest.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("Arrive_Date"));
        tblGuest.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("Arrive_Time"));
        tblGuest.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("Departure_Date"));
        tblGuest.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("Departure_Time"));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cmbSearchOption.requestFocus();
//                txtSearchGuestID.requestFocus();
                tBtnEdit.setDisable(true);
                btnRemove.setVisible(false);
                btnUpdate.setVisible(false);
                try {
                    setComboBox();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SearchGuestController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(SearchGuestController.class.getName()).log(Level.SEVERE, null, ex);
                }
                setEditableOff();
                ObservableList<String> ol = FXCollections.observableArrayList("Name", "Country", "Specific Date");
                cmbSearchOption.setItems(ol);
            }
        });
    }

    @FXML
    private void txtSearchDataAction(ActionEvent event) throws SQLException, Exception {
        ArrayList<GuestTM> allGuest = new ArrayList<>();
        try {
            switch (cmbSearchOption.getValue()) {
                case "Name":
                    try {
                        ArrayList<GuestDTO> all = bo.searchGuestbyName(txtSearchData.getText());
                        for (GuestDTO g : all) {
                            allGuest.add(new GuestTM(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime()));
                        }
                        tblGuest.setItems(FXCollections.observableList(allGuest));
                    } catch (NullPointerException ex) {
                        new Alert(Alert.AlertType.ERROR, "No Match Found", ButtonType.OK).showAndWait();
                        tblGuest.getItems().clear();
                    }
                    break;
                case "Country":
                    try {
                        ArrayList<GuestDTO> all = bo.searchGuestbyCountry(txtSearchData.getText());
                        for (GuestDTO g : all) {
                            allGuest.add(new GuestTM(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime()));
                        }
                        tblGuest.setItems(FXCollections.observableList(allGuest));
                    } catch (NullPointerException ex) {
                        new Alert(Alert.AlertType.ERROR, "No Match Found", ButtonType.OK).showAndWait();
                        tblGuest.getItems().clear();
                    }
                    break;
                case "Specific Date":
                    try {
                        ArrayList<GuestDTO> all = bo.searchGuestbyDate(txtSearchData.getText());
                        for (GuestDTO g : all) {
                            allGuest.add(new GuestTM(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime()));
                        }
                        tblGuest.setItems(FXCollections.observableList(allGuest));
                    } catch (NullPointerException ex) {
                        new Alert(Alert.AlertType.ERROR, "No Match Found. Please enter date in following format YYYY-MM-DD", ButtonType.OK).showAndWait();
                        tblGuest.getItems().clear();
                    }
                    break;
            }
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.ERROR, "Plese Select an Option", ButtonType.OK).showAndWait();
            cmbSearchOption.requestFocus();
        }

    }

    @FXML
    private void txtSearchGuestIDAction(ActionEvent event) throws SQLException, Exception {
        try {
            String searchGID = txtSearchGuestID.getText();
            clearAll();
            GuestDTO searchGuest = bo.searchGuest(searchGID);
            setComboBox();
//        ------------------------Setting all text--------------------------------------
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
            new Alert(Alert.AlertType.ERROR, "No Such Customer as \"" + txtSearchGuestID.getText() + "\"", ButtonType.OK).showAndWait();
            tBtnEdit.setSelected(false);
            tBtnEdit.setDisable(true);
            btnUpdate.setVisible(false);
            btnRemove.setVisible(false);
            setEditableOff();
        }
    }

    @FXML
    private void txtGstIDActionPerformed(ActionEvent event) {
    }

    @FXML
    private void txtCountryActionPerformed(ActionEvent event) {
    }

    @FXML
    private void txtFNameActionPerformed(ActionEvent event) {
    }

    @FXML
    private void txtEmailActionPerformed(ActionEvent event) {
    }

    @FXML
    private void txtLNameActionPerformed(ActionEvent event) {
    }

    @FXML
    private void txtMobileNoActionPerformed(ActionEvent event) {
    }

    @FXML
    private void txtFlightNoActionPerformed(ActionEvent event) {
    }

    @FXML
    private void txtArriveDateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void txtArriveTimeActionPerformed(ActionEvent event) {
    }

    @FXML
    private void txtDepartureDateActionPerformed(ActionEvent event) {
    }

    @FXML
    private void txtDepartureTimeActionPerformed(ActionEvent event) {
    }

    @FXML
    private void btnUpdateMouseClicked(MouseEvent event) throws ClassNotFoundException, Exception {
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
                                    new Alert(Alert.AlertType.INFORMATION, "Guest "+firstName+" is Updated Successfully", ButtonType.OK).show();
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
    private void btnRemoveMouseClicked(MouseEvent event) throws SQLException, Exception {
        String gstID = txtGstID.getText();
        boolean deleteGuest = bo.deleteGuest(gstID);
        if (deleteGuest) {
            new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK).show();
        }
    }

    @FXML
    private void tBtnEditAction(ActionEvent event) {
        if (tBtnEdit.isSelected()) {
//            System.out.println("on");
            setEditable();
            btnRemove.setVisible(true);
            btnUpdate.setVisible(true);
        } else {
//            System.out.println("off");
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
        cmbAirport.getItems().clear();
        txtArriveDate.setValue(null);
        txtArriveTime.setValue(null);
        txtDepartureDate.setValue(null);
        txtDepartureTime.setValue(null);
    }

    public void setComboBox() throws ClassNotFoundException, SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT name FROM airport;");
        ResultSet rst = pstm.executeQuery();
        ObservableList<String> ol = FXCollections.observableArrayList();
        while (rst.next()) {
            ol.add(rst.getString(1));
        }
        cmbAirport.setItems(ol);
    }

    private void setEditableOff() {
        txtGstID.setEditable(false);
        txtCountry.setEditable(false);
        txtFName.setEditable(false);
        txtLName.setEditable(false);
        txtMobileNo.setEditable(false);
        txtEmail.setEditable(false);
        txtFlightNo.setEditable(false);
    }

    private void setEditable() {
        txtGstID.setEditable(true);
        txtCountry.setEditable(true);
        txtFName.setEditable(true);
        txtLName.setEditable(true);
        txtMobileNo.setEditable(true);
        txtEmail.setEditable(true);
        txtFlightNo.setEditable(true);
    }

    @FXML
    private void tblGuestMouseClicked(MouseEvent event) {
        if (tblGuest.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "The table row is empty", ButtonType.OK).show();
        } else {
            GuestTM searchGuest = tblGuest.getSelectionModel().getSelectedItem();

            txtSearchGuestID.setText(searchGuest.getGstID());
            txtGstID.setText(searchGuest.getGstID());
            txtCountry.setText(searchGuest.getCountry());
            txtFName.setText(searchGuest.getF_Name());
            txtLName.setText(searchGuest.getL_Name());
            txtMobileNo.setText(searchGuest.getMobile_No());
            txtEmail.setText(searchGuest.getEmail());
            txtFlightNo.setText(searchGuest.getFlight_No());
            cmbAirport.setValue(searchGuest.getAirport());
            txtArriveDate.setValue(LocalDate.parse(searchGuest.getArrive_Date()));
            txtArriveTime.setValue(LocalTime.parse(searchGuest.getArrive_Time()));
            txtDepartureDate.setValue(LocalDate.parse(searchGuest.getDeparture_Date()));
            txtDepartureTime.setValue(LocalTime.parse(searchGuest.getDeparture_Time()));
            tBtnEdit.setDisable(false);
            tabPaneGuest.getSelectionModel().selectNext();
        }
    }

    @FXML
    private void cmbSearchOptionKeyPressed(KeyEvent event) {
        if (event.getCode().equals(ENTER)) {
            if (cmbSearchOption.getSelectionModel().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Select an Search Option First", ButtonType.OK).show();
                cmbSearchOption.requestFocus();
            } else {
                txtSearchData.requestFocus();
            }
        }
    }

    @FXML
    private void btnBackToGuestMouseClicked(MouseEvent event) {
        Stage stage = (Stage) this.tabPaneGuest.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnBackMouseClicked(MouseEvent event) {
        tabPaneGuest.getSelectionModel().selectPrevious();
    }

    @FXML
    private void btnPrintMouseClicked(MouseEvent event) throws SQLException, ClassNotFoundException, JRException {
        if (txtSearchGuestID.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Search Guest First", ButtonType.OK).show();
        } else {
            InputStream is = this.getClass().getResourceAsStream("/lk/pankajatravel/reports/Guest.jasper");
            Connection connection = DBConnection.getInstance().getConnection();
            HashMap map = new HashMap();
            map.put("guestID", txtSearchGuestID.getText());
            JasperPrint print = JasperFillManager.fillReport(is, map, connection);
            JasperViewer.viewReport(print, false);
        }

    }

}
