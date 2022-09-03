/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.custom.TourBO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.dto.GuestDTO;
import lk.pankajatravel.dto.GuideDTO;
import lk.pankajatravel.dto.HotelDTO;
import lk.pankajatravel.dto.PlaceDTO;
import lk.pankajatravel.dto.TourDTO;
import lk.pankajatravel.dto.TourDetailsDTO;
import lk.pankajatravel.tableModel.TourDetailsTM;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class TourController implements Initializable {

    TourBO bo = (TourBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.TOUR);

    @FXML
    private JFXComboBox<String> cmbGuestID;
    @FXML
    private JFXButton btnBack;
    @FXML
    private TextField txtFlightNumber;
    @FXML
    private TextField txtAirport;
    @FXML
    private TextField txtAriveDate;
    @FXML
    private TextField txtArivetime;
    @FXML
    private TextField txtDepatureDate;
    @FXML
    private TextField txtDepatureTime;
    @FXML
    private TextField txtTourID;
    @FXML
    private JFXDatePicker txtStartDate;
    @FXML
    private TextField txtPickupOn;
    @FXML
    private TextField txtDropOn;
    @FXML
    private TextField txtChilds;
    @FXML
    private TextField txtMembers;
    @FXML
    private TextField txtAdults;
    @FXML
    private JFXDatePicker txtEndDate;
    @FXML
    private JFXDatePicker txtvisitDate;
    @FXML
    private JFXComboBox<String> cmbVisitPlace;
    @FXML
    private TextField txtNoNights;
    @FXML
    private JFXComboBox<String> cmbHotel;
    @FXML
    private JFXButton btnConfirm;
    @FXML
    private JFXButton btnCancle;
    @FXML
    private JFXButton btnPrint;
    @FXML
    private TableView<TourDetailsTM> tblTourDetails;
    @FXML
    private TextField txtGuestName;
    @FXML
    private JFXComboBox<String> cmbGuideID;
    @FXML
    private TextField txtGuideName;
    @FXML
    private AnchorPane paneTour;
    @FXML
    private TextField txtNoOfRooms;
    @FXML
    private JFXComboBox<String> cmbRoomType;
    @FXML
    private JFXComboBox<String> cmbMealType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cmbGuestID.requestFocus();

                try {
                    loadTourID();
                    setDateVariables();
                    setDateDisable();
                    setAllComboBoxes();
                } catch (Exception ex) {
                    Logger.getLogger(TourController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        tblTourDetails.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblTourDetails.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblTourDetails.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblTourDetails.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
//        --------------------------------------------------------------
        tblTourDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Date"));
        tblTourDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Place"));
        tblTourDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("No_of_Nights"));
        tblTourDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Hotel"));

    }

    @FXML
    private void cmbGuestIDKeyPressed(KeyEvent event) throws SQLException, Exception {
        if (event.getCode().equals(ENTER)) {
            if (cmbGuestID.getSelectionModel().isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Please Select a Guest First", ButtonType.OK).show();
            } else {
                String guestID = cmbGuestID.getValue();
                clearAll();
                loadTourID();
                cmbGuestID.setValue(guestID);
                try {
                    GuestDTO g = bo.getSearchGuest(guestID);
                    txtGuestName.setText(g.getFirstName() + " " + g.getLastName());
                    txtFlightNumber.setText(g.getFlightNum());
                    txtAirport.setText(g.getAirport());
                    txtAriveDate.setText(g.getArriveDate());
                    txtArivetime.setText(g.getArriveTime());
                    txtDepatureDate.setText(g.getDepartureDate());
                    txtDepatureTime.setText(g.getDepartureTime());
                    txtStartDate.setDisable(false);
                    txtStartDate.requestFocus();

                } catch (NullPointerException ex) {
                    new Alert(Alert.AlertType.ERROR, "No Such Guest", ButtonType.OK).showAndWait();
                }
            }
        }
    }

    @FXML
    private void txtGuestNameAction(ActionEvent event) {
//        txtTourID.requestFocus();
    }

    @FXML
    private void txtTourIDAction(ActionEvent event) throws SQLException, Exception {
        String tourID = txtTourID.getText();
        try {
//          ---------------Setting GuestDetail---------------------------
            TourDTO t = bo.searchTour(tourID);
            cmbGuestID.setValue(t.getGuestID());
            String guestID = cmbGuestID.getValue();
            GuestDTO g = bo.getSearchGuest(guestID);
            txtGuestName.setText(g.getFirstName() + " " + g.getLastName());
            txtFlightNumber.setText(g.getFlightNum());
            txtAirport.setText(g.getAirport());
            txtAriveDate.setText(g.getArriveDate());
            txtArivetime.setText(g.getArriveTime());
            txtDepatureDate.setText(g.getDepartureDate());
            txtDepatureTime.setText(g.getDepartureTime());
//          --------------------------setting TourDetails----------------------
            txtStartDate.setValue(LocalDate.parse(t.getTourStartDate()));
            txtEndDate.setValue(LocalDate.parse(t.getTourEndDate()));
            txtPickupOn.setText(t.getGuestPickupOn());
            txtDropOn.setText(t.getGuestDropOn());
            txtAdults.setText(t.getNoAdults());
            txtChilds.setText(t.getNoChildren());
            txtMembers.setText(Integer.parseInt(txtAdults.getText()) + Integer.parseInt(txtChilds.getText()) + "");
            txtNoOfRooms.setText(t.getRooms());
            cmbRoomType.setValue(t.getRoomType());
            cmbMealType.setValue(t.getMealType());
            cmbGuideID.setValue(t.getGuideID());
            String gID = cmbGuideID.getValue();
            GuideDTO searchGuide = bo.getSearchGuide(gID);
            txtGuideName.setText(searchGuide.getfName() + " " + searchGuide.getlName());
//            -------------------setting details to table-------------------------
            ArrayList<TourDetailsTM> all = new ArrayList<>();
            for (TourDetailsDTO td : t.getAllTourDetails()) {
                all.add(new TourDetailsTM(td.getDate(), td.getPlace(), td.getNoNights(), td.getHotel()));
            }
            tblTourDetails.setItems(FXCollections.observableArrayList(all));
            btnPrint.requestFocus();
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.INFORMATION, "No match found", ButtonType.OK).showAndWait();
            clearAll();
        }

    }

    @FXML
    private void txtStartDateAction(ActionEvent event) {
        txtEndDate.setDisable(false);
        txtvisitDate.setValue(txtStartDate.getValue());
        txtEndDate.requestFocus();
    }

    @FXML
    private void txtPickupOnAction(ActionEvent event) {
        txtDropOn.requestFocus();
    }

    @FXML
    private void txtDropOnAction(ActionEvent event) {
        txtAdults.requestFocus();
    }

    @FXML
    private void txtChildsAction(ActionEvent event) {
        if (Pattern.compile("^[0-9]{1,2}$").matcher(txtAdults.getText()).matches()) {
            txtMembers.setText(Integer.parseInt(txtAdults.getText()) + Integer.parseInt(txtChilds.getText()) + "");
            txtNoOfRooms.requestFocus();
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field No of Children. Please enter numbers between [0-99].", ButtonType.OK).show();
            txtAdults.requestFocus();
        }
    }

    @FXML
    private void txtAdultsAction(ActionEvent event) {
        if (Pattern.compile("^[0-9]{1,2}$").matcher(txtAdults.getText()).matches()) {
            txtChilds.requestFocus();
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field No of Adults. Please enter numbers between [0-99].", ButtonType.OK).show();
            txtAdults.requestFocus();
        }

    }

    @FXML
    private void txtNoOfRoomsAction(ActionEvent event) {
        cmbRoomType.requestFocus();
    }

    @FXML
    private void cmbRoomTypeKeyPressed(KeyEvent event) {
        if (event.getCode().equals(ENTER)) {
            cmbMealType.requestFocus();
        }
    }

    @FXML
    private void cmbMealTypeKeyPressed(KeyEvent event) {
        if (event.getCode().equals(ENTER)) {
            cmbGuideID.requestFocus();
        }
    }
//
//    private void cmbGuideNameKeyPressed(KeyEvent event) {
//        if (event.getCode().equals(ENTER)) {
//            cmbVisitPlace.requestFocus();
//        }
//    }

    @FXML
    private void cmbGuideIDKeyPressed(KeyEvent event) throws SQLException, Exception {
        if (event.getCode().equals(ENTER)) {
            String gID = cmbGuideID.getValue();
            GuideDTO searchGuide = bo.getSearchGuide(gID);
            txtGuideName.setText(searchGuide.getfName() + " " + searchGuide.getlName());
            cmbVisitPlace.requestFocus();
        }
    }

    @FXML
    private void txtEndDateAction(ActionEvent event) {
        txtvisitDate.setDisable(false);
        txtPickupOn.requestFocus();
    }

    @FXML
    private void tblTourDetailsMouseClicked(MouseEvent event) {
        try {
            TourDetailsTM selectedItem = tblTourDetails.getSelectionModel().getSelectedItem();
            txtvisitDate.setValue(LocalDate.parse(selectedItem.getDate()));
            cmbVisitPlace.setValue(selectedItem.getPlace());
            txtNoNights.setText(selectedItem.getNo_of_Nights());
            cmbHotel.setValue(selectedItem.getHotel());
        } catch (NullPointerException ex) {
        }
    }

    @FXML
    private void txtvisitDateAction(ActionEvent event) {
        cmbVisitPlace.requestFocus();
    }

    @FXML
    private void cmbVisitPlaceKeyPressed(KeyEvent event) {
        if (event.getCode().equals(ENTER)) {
            txtNoNights.requestFocus();
        }
    }

    @FXML
    private void txtNoNightsAction(ActionEvent event) {
        cmbHotel.requestFocus();
    }

    @FXML
    private void cmbHotelKeyPressed(KeyEvent event) {
        if (Pattern.compile("^([A-z]{1}[a-z]{1,}\\s?)+$").matcher(cmbVisitPlace.getValue()).matches()) {
            if (Pattern.compile("^[0-9]{1,2}$").matcher(txtNoNights.getText()).matches()) {
                if (Pattern.compile("^([A-z]{1}[a-z]{1,}\\s?)+$").matcher(cmbHotel.getValue()).matches()) {
                    if (event.getCode().equals(ENTER)) {
                        ObservableList<TourDetailsTM> list = tblTourDetails.getItems();
                        list.add(new TourDetailsTM(txtvisitDate.getValue().toString(), cmbVisitPlace.getValue(), txtNoNights.getText(), cmbHotel.getValue()));
                        tblTourDetails.setItems(FXCollections.observableList(list));
                        clear();
                        txtvisitDate.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field Hotel. Please use letters and 1st letter must be capital in each word.", ButtonType.OK).show();
                    cmbHotel.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field No of Nights. Please enter numbers between [0-99].", ButtonType.OK).show();
                txtNoNights.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field Select Place. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
            cmbVisitPlace.requestFocus();
        }
//            //
//            LocalDate value = txtStartDate.getValue();
//            int dayOfMonth = value.getDayOfMonth();
//            int dayOfYear = value.getDayOfYear();
////            value.get
//            txtStartDate.setValue(LocalDate.of(2019,5,5));
    }

    @FXML
    private void btnConfirmMouseClicked(MouseEvent event) {
        if (Pattern.compile("^(gst-00)[0-9]$").matcher(cmbGuestID.getValue()).matches()) {
            if (Pattern.compile("^(tour-)[0-9]$").matcher(txtTourID.getText()).matches()) {
                if (Pattern.compile("^([A-z]{1}[a-z]{1,}\\s?)+$").matcher(txtPickupOn.getText()).matches()) {
                    if (Pattern.compile("^([A-z]{1}[a-z]{1,}\\s?)+$").matcher(txtDropOn.getText()).matches()) {
                        if (Pattern.compile("^[0-9]{1,2}$").matcher(txtAdults.getText()).matches()) {
                            if (Pattern.compile("^[0-9]{1,2}$").matcher(txtChilds.getText()).matches()) {
                                if (Pattern.compile("^[0-9]{1,2}$").matcher(txtMembers.getText()).matches()) {
                                    if (Pattern.compile("^[0-9]{1,2}$").matcher(txtNoOfRooms.getText()).matches()) {
                                        try {
                                            String tourID = txtTourID.getText();
                                            String GuestID = cmbGuestID.getValue();
                                            String tourStartDate = txtStartDate.getValue().toString();
                                            String tourEndDate = txtEndDate.getValue().toString();
                                            String guestPickupOn = txtPickupOn.getText();
                                            String guestDropOn = txtDropOn.getText();
                                            String noAdults = txtAdults.getText();
                                            String noChildren = txtChilds.getText();
                                            String rooms = txtNoOfRooms.getText();
                                            String roomType = cmbRoomType.getValue();
                                            String mealType = cmbMealType.getValue();
                                            String guideID = cmbGuideID.getValue();

                                            ArrayList<TourDetailsDTO> allTourDetails = new ArrayList<>();
                                            for (int i = 0; i < tblTourDetails.getItems().size(); i++) {
                                                TourDetailsTM t = tblTourDetails.getItems().get(i);
                                                allTourDetails.add(new TourDetailsDTO(t.getDate(), t.getPlace(), t.getNo_of_Nights(), t.getHotel()));
                                            }
                                            TourDTO tour = new TourDTO(tourID, GuestID, tourStartDate, tourEndDate, guestPickupOn, guestDropOn, noAdults, noChildren, rooms, roomType, mealType, guideID, allTourDetails);
                                            boolean confirmTour = bo.addTour(tour);
                                            if (confirmTour) {
                                                new Alert(Alert.AlertType.INFORMATION, "Tour is Comfirmed", ButtonType.OK).showAndWait();
                                                InputStream is = this.getClass().getResourceAsStream("/lk/pankajatravel/reports/Tour.jasper");
                                                Connection connection = DBConnection.getInstance().getConnection();
                                                HashMap map = new HashMap();
                                                map.put("tourID", tourID);
                                                map.put("gstName", txtGuestName.getText());
                                                map.put("arriveDate", txtAriveDate.getText());
                                                map.put("arriveAirport", txtAirport.getText());
                                                map.put("arriveTime", txtArivetime.getText());
                                                map.put("flightNo", txtFlightNumber.getText());
                                                map.put("departureDate", txtDepatureDate.getText());
                                                map.put("departureTime", txtDepatureTime.getText());

                                                GuideDTO searchGuide = bo.getSearchGuide(cmbGuideID.getValue());
                                                map.put("GuideName", txtGuideName.getText());
                                                map.put("GuideContactNo", searchGuide.getMobileNo());
                                                map.put("GuideVehicle", searchGuide.getVehicle());
                                                JasperPrint print = JasperFillManager.fillReport(is, map, connection);
                                                JasperViewer.viewReport(print, false);
                                                loadTourID();
                                                clearAll();
                                                cmbGuestID.requestFocus();
                                            }
                                        } catch (Exception ex) {
                                            new Alert(Alert.AlertType.ERROR, "Please Enter all Details First", ButtonType.OK).show();
                                        }
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field No of Rooms. Please enter numbers between [0-99].", ButtonType.OK).show();
                                        txtNoOfRooms.requestFocus();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field No of Members. Please enter numbers between [0-99].", ButtonType.OK).show();
                                    txtMembers.requestFocus();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field No of Childern. Please enter numbers between [0-99].", ButtonType.OK).show();
                                txtChilds.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field No of Adults. Please enter numbers between [0-99].", ButtonType.OK).show();
                            txtAdults.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field Guest drop on. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                        txtDropOn.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field Guest pickup on. Please use letters and 1st letter must be capital in each word", ButtonType.OK).show();
                    txtPickupOn.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Tour ID format. Please use tour-x as tour ID where x={1,2,3,...}", ButtonType.OK).show();
                txtTourID.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid Guest ID format. Please use gst-00x as guest ID where x={1,2,3,...}", ButtonType.OK).show();
            cmbGuestID.requestFocus();
        }

    }

    @FXML
    private void btnCancleMouseClicked(MouseEvent event) {
        clearAll();
    }

    @FXML
    private void btnPrintMouseClicked(MouseEvent event) throws SQLException, Exception {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/pankajatravel/reports/Tour.jasper");
            Connection connection = DBConnection.getInstance().getConnection();
            HashMap map = new HashMap();
            map.put("tourID", txtTourID.getText());
            map.put("gstName", txtGuestName.getText());
            map.put("arriveDate", txtAriveDate.getText());
            map.put("arriveAirport", txtAirport.getText());
            map.put("arriveTime", txtArivetime.getText());
            map.put("flightNo", txtFlightNumber.getText());
            map.put("departureDate", txtDepatureDate.getText());
            map.put("departureTime", txtDepatureTime.getText());

            GuideDTO searchGuide = bo.getSearchGuide(cmbGuideID.getValue());
            map.put("GuideName", txtGuideName.getText());
            map.put("GuideContactNo", searchGuide.getMobileNo());
            map.put("GuideVehicle", searchGuide.getVehicle());
            JasperPrint print = JasperFillManager.fillReport(is, map, connection);
            JasperViewer.viewReport(print, false);

        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Please Enter all Details First", ButtonType.OK).show();
        }
    }

    private void setDateVariables() {

        txtStartDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate arriveDate = LocalDate.parse(txtAriveDate.getText());
                LocalDate departureDate = LocalDate.parse(txtDepatureDate.getText());
                setDisable(empty || date.compareTo(arriveDate) < 0 || date.compareTo(departureDate) > 0);
            }
        });
        txtEndDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate startDate = txtStartDate.getValue();
                LocalDate departureDate = LocalDate.parse(txtDepatureDate.getText());
                setDisable(empty || date.compareTo(departureDate) > 0 || date.compareTo(startDate) < 0);
            }
        });
        txtvisitDate.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate startDate = txtStartDate.getValue();
                LocalDate EndDate = txtEndDate.getValue();
                setDisable(empty || date.compareTo(EndDate) > 0 || date.compareTo(startDate) < 0);
            }
        });
    }

    private void setAllComboBoxes() throws SQLException, Exception {
        ArrayList<GuestDTO> allGuest = bo.getAllGuest();
        ObservableList<String> ol1 = FXCollections.observableArrayList();
        for (GuestDTO g : allGuest) {
            ol1.add(g.getGuestID());
        }
        cmbGuestID.setItems(ol1);
//        --------------------------------------------------------------------
        ArrayList<GuideDTO> allGuide = bo.getAllGuide();
        ObservableList<String> ol2 = FXCollections.observableArrayList();
        for (GuideDTO g : allGuide) {
            ol2.add(g.getGidID());
        }
        cmbGuideID.setItems(ol2);
//        --------------------------------------------------------------------
        ArrayList<HotelDTO> allHotel = bo.getAllHotel();
        ObservableList<String> ol3 = FXCollections.observableArrayList();
        for (HotelDTO g : allHotel) {
            ol3.add(g.gethName());
        }
        cmbHotel.setItems(ol3);
//        --------------------------------------------------------------------
        ArrayList<PlaceDTO> all = bo.getAllPlace();
        ObservableList<String> ol4 = FXCollections.observableArrayList();
        for (PlaceDTO g : all) {
            ol4.add(g.getPlcName());
        }
        cmbVisitPlace.setItems(ol4);

        ObservableList<String> ol5 = FXCollections.observableArrayList();
        ol5.addAll("Single Room", "Double Room", "Triple Room", "2 Single Rooms", "Single & Double Room", "Single & Triple Room", "Single & 2 Double Rooms", "Single & 2 Triple Rooms", "2 Double Rooms", "Double & Triple Room", "Bungalow", "Guest House");
        cmbRoomType.setItems(ol5);

        ObservableList<String> ol6 = FXCollections.observableArrayList();
        ol6.addAll("BB", "Half Board", "Full Board");
        cmbMealType.setItems(ol6);
    }

    private void clear() {
        cmbVisitPlace.setValue("");
        txtNoNights.setText("");
        cmbHotel.setValue("");
    }

    private void clearAll() {
        clear();
        txtvisitDate.setValue(null);
        txtGuideName.setText("");
        cmbGuideID.setValue("");
        cmbMealType.setValue("");
        cmbRoomType.setValue("");
        txtNoOfRooms.setText("");
        txtMembers.setText("");
        txtChilds.setText("");
        txtAdults.setText("");
        txtDropOn.setText("");
        txtPickupOn.setText("");
        txtEndDate.setValue(null);
        txtStartDate.setValue(null);
        txtDepatureTime.setText("");
        txtDepatureDate.setText("");
        txtArivetime.setText("");
        txtAriveDate.setText("");
        txtAirport.setText("");
        txtFlightNumber.setText("");
        txtGuestName.setText("");
        cmbGuestID.setValue("");
        tblTourDetails.getItems().clear();
        setDateDisable();
        cmbGuestID.requestFocus();
    }

    @FXML
    private void cmbVisitPlaceKeyRelease(KeyEvent event) {
    }

    @FXML
    private void cmbHotelKeyRelease(KeyEvent event) {
    }

    private void loadTourID() throws SQLException, Exception {
        String tourID = bo.getTourID();
        txtTourID.setText(tourID);
    }

    private void setDateDisable() {
        txtStartDate.setDisable(true);
        txtEndDate.setDisable(true);
        txtvisitDate.setDisable(true);
    }

    @FXML
    private void btnBackMouseClicked(MouseEvent event) {
        Stage stage = (Stage) this.paneTour.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnClearAllMouseClicked(MouseEvent event) {
        if (tblTourDetails.getItems().isEmpty()) {
            new Alert(Alert.AlertType.INFORMATION, "Table is allready empty !", ButtonType.OK).show();
        } else {
            tblTourDetails.getItems().clear();
        }
    }

    @FXML
    private void btnRemoveRowMouseClicked(MouseEvent event) {
        int f = tblTourDetails.getSelectionModel().getSelectedIndex();
        if (f >= 0) {
            tblTourDetails.getItems().remove(f);
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Select a row to remove !", ButtonType.OK).show();
        }
    }

}
