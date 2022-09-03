/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXComboBox;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import lk.pankajatravel.bo.custom.TourBO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.dto.GuestDTO;
import lk.pankajatravel.dto.GuideDTO;
import lk.pankajatravel.dto.TourDTO;
import lk.pankajatravel.dto.TourDetailsDTO;
import lk.pankajatravel.tableModel.TourDetailsTM;
import lk.pankajatravel.tableModel.TourTM;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class ViewTourDetailsController implements Initializable {

    TourBO bo = (TourBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.TOUR);

    @FXML
    private Label lblHeading1;
    @FXML
    private JFXComboBox<String> cmbSearchOption;
    @FXML
    private TextField txtSearchData;
    @FXML
    private Label lblHeading;
    @FXML
    private TabPane tabPaneTour;
    @FXML
    private TableView<TourTM> tblTour;
    @FXML
    private TableView<TourDetailsTM> tblTourDetails;
    @FXML
    private TextField txtSearchTourID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cmbSearchOption.requestFocus();
            }
        });
//       ------------------------------ setting tabletour ---------------------------------------
        tblTour.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblTour.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblTour.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblTour.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        tblTour.getColumns().get(4).setStyle("-fx-alignment: CENTER;");
        tblTour.getColumns().get(5).setStyle("-fx-alignment: CENTER;");
        tblTour.getColumns().get(6).setStyle("-fx-alignment: CENTER;");
        tblTour.getColumns().get(7).setStyle("-fx-alignment: CENTER;");
        tblTour.getColumns().get(8).setStyle("-fx-alignment: CENTER;");
        tblTour.getColumns().get(9).setStyle("-fx-alignment: CENTER;");
        tblTour.getColumns().get(10).setStyle("-fx-alignment: CENTER;");
        tblTour.getColumns().get(11).setStyle("-fx-alignment: CENTER;");
//        --------------------------------------------------------------
        tblTour.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("TourID"));
        tblTour.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Guest_Name"));
        tblTour.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Guide_Name"));
        tblTour.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Start_Date"));
        tblTour.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("End_Date"));
        tblTour.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Pickup_From"));
        tblTour.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Drop_To"));
        tblTour.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("Adults"));
        tblTour.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("Children"));
        tblTour.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("Rooms"));
        tblTour.getColumns().get(10).setCellValueFactory(new PropertyValueFactory<>("Room_Type"));
        tblTour.getColumns().get(11).setCellValueFactory(new PropertyValueFactory<>("Meal_Type"));
//      -------------------------------  setting tabletourDetails  -----------------------------------------------
        tblTourDetails.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblTourDetails.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblTourDetails.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblTourDetails.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
//        --------------------------------------------------------------
        tblTourDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Date"));
        tblTourDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Place"));
        tblTourDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("No_of_Nights"));
        tblTourDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Hotel"));
        try {
            loadAllTours();
            setComboBoxes();
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Table is Empty", ButtonType.OK).show();
        }
    }

    @FXML
    private void btnBackToTourManagementMouseClicked(MouseEvent event) {
        Stage stage = (Stage) this.tabPaneTour.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void cmbSearchOptionKeyPressed(KeyEvent event) {
        if (event.getCode().equals(ENTER)) {
            txtSearchData.requestFocus();
        }
    }

    @FXML
    private void txtSearchDataAction(ActionEvent event) throws Exception {
        ArrayList<TourTM> all = new ArrayList<>();
        try {
            switch (cmbSearchOption.getValue()) {
                case "Tour_ID":
                    try {
                        TourDTO t = bo.searchTour(txtSearchData.getText());
                            GuestDTO searchGuest = bo.getSearchGuest(t.getGuestID());
                            GuideDTO searchGuide = bo.getSearchGuide(t.getGuideID());
                            all.add(new TourTM(t.getTourID(), searchGuest.getFirstName() + " " + searchGuest.getLastName(), searchGuide.getfName(), t.getTourStartDate(), t.getTourEndDate(), t.getGuestPickupOn(), t.getGuestDropOn(), t.getNoAdults(), t.getNoChildren(), t.getRooms(), t.getRoomType(), t.getMealType()));
                        tblTour.setItems(FXCollections.observableArrayList(all));
                    } catch (NullPointerException ex) {
                        new Alert(Alert.AlertType.ERROR, "No Match Found", ButtonType.OK).show();
                        tblTour.getItems().clear();
                    }
                    break;
                case "Guest_Name":
                    try {
                        ArrayList<TourDTO> allTours = bo.searchTourbyGuestName(txtSearchData.getText());
                        for (TourDTO t : allTours) {
                            GuestDTO searchGuest = bo.getSearchGuest(t.getGuestID());
                            GuideDTO searchGuide = bo.getSearchGuide(t.getGuideID());
                            all.add(new TourTM(t.getTourID(), searchGuest.getFirstName() + " " + searchGuest.getLastName(), searchGuide.getfName(), t.getTourStartDate(), t.getTourEndDate(), t.getGuestPickupOn(), t.getGuestDropOn(), t.getNoAdults(), t.getNoChildren(), t.getRooms(), t.getRoomType(), t.getMealType()));
                        }
                        tblTour.setItems(FXCollections.observableArrayList(all));
                    } catch (NullPointerException ex) {
                        new Alert(Alert.AlertType.ERROR, "No Match Found", ButtonType.OK).show();
                        tblTour.getItems().clear();
                    }
                    break;
                case "Guide_Name":
                    try {
                        ArrayList<TourDTO> allTours = bo.searchTourbyGuideName(txtSearchData.getText());
                        for (TourDTO t : allTours) {
                            GuestDTO searchGuest = bo.getSearchGuest(t.getGuestID());
                            GuideDTO searchGuide = bo.getSearchGuide(t.getGuideID());
                            all.add(new TourTM(t.getTourID(), searchGuest.getFirstName() + " " + searchGuest.getLastName(), searchGuide.getfName(), t.getTourStartDate(), t.getTourEndDate(), t.getGuestPickupOn(), t.getGuestDropOn(), t.getNoAdults(), t.getNoChildren(), t.getRooms(), t.getRoomType(), t.getMealType()));
                        }
                        tblTour.setItems(FXCollections.observableArrayList(all));
                    } catch (NullPointerException ex) {
                        new Alert(Alert.AlertType.ERROR, "No Match Found", ButtonType.OK).show();
                        tblTour.getItems().clear();
                    }
                    break;
                case "Specific_Date":
                    try {
                        ArrayList<TourDTO> allTours = bo.searchTourbySpecificDate(txtSearchData.getText());
                        for (TourDTO t : allTours) {
                            GuestDTO searchGuest = bo.getSearchGuest(t.getGuestID());
                            GuideDTO searchGuide = bo.getSearchGuide(t.getGuideID());
                            all.add(new TourTM(t.getTourID(), searchGuest.getFirstName() + " " + searchGuest.getLastName(), searchGuide.getfName(), t.getTourStartDate(), t.getTourEndDate(), t.getGuestPickupOn(), t.getGuestDropOn(), t.getNoAdults(), t.getNoChildren(), t.getRooms(), t.getRoomType(), t.getMealType()));
                        }
                        tblTour.setItems(FXCollections.observableArrayList(all));
                    } catch (NullPointerException ex) {
                        new Alert(Alert.AlertType.ERROR, "No Match Found. Please enter date in following format YYYY-MM-DD", ButtonType.OK).show();
                        tblTour.getItems().clear();
                    }
                    break;
            }
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.ERROR, "Plese Select an Option", ButtonType.OK).showAndWait();
            cmbSearchOption.requestFocus();
        }
    }

    @FXML
    private void btnBackMouseClicked(MouseEvent event) {
        tabPaneTour.getSelectionModel().selectPrevious();
    }

    private void loadAllTours() throws SQLException, Exception {
        ArrayList<TourTM> all = new ArrayList<>();
        ArrayList<TourDTO> allTours = bo.getAllTours();

        for (TourDTO t : allTours) {
            GuestDTO searchGuest = bo.getSearchGuest(t.getGuestID());
            GuideDTO searchGuide = bo.getSearchGuide(t.getGuideID());
            all.add(new TourTM(t.getTourID(), searchGuest.getFirstName() + " " + searchGuest.getLastName(), searchGuide.getfName(), t.getTourStartDate(), t.getTourEndDate(), t.getGuestPickupOn(), t.getGuestDropOn(), t.getNoAdults(), t.getNoChildren(), t.getRooms(), t.getRoomType(), t.getMealType()));
        }
        tblTour.setItems(FXCollections.observableArrayList(all));
    }

    @FXML
    private void txtSearchTourIDAction(ActionEvent event) throws SQLException, Exception {
        try {
            TourDTO searchTour = bo.searchTour(txtSearchTourID.getText());
            ArrayList<TourDetailsTM> all = new ArrayList<>();
            ArrayList<TourDetailsDTO> allTourDetails = searchTour.getAllTourDetails();
            for (TourDetailsDTO td : allTourDetails) {
                all.add(new TourDetailsTM(td.getDate(), td.getPlace(), td.getNoNights(), td.getHotel()));
            }
            tblTourDetails.setItems(FXCollections.observableArrayList(all));
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.ERROR, "No Match Found", ButtonType.OK).show();
            tblTourDetails.getItems().clear();
        }
    }

    @FXML
    private void tblTourMouseClicked(MouseEvent event) throws SQLException, Exception {
        if (tblTour.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "The Table is Empty", ButtonType.OK).show();
        } else {
            TourTM selectedItem = tblTour.getSelectionModel().getSelectedItem();
            ArrayList<TourDetailsTM> all = new ArrayList<>();
            TourDTO searchTour = bo.searchTour(selectedItem.getTourID());
            ArrayList<TourDetailsDTO> allTourDetails = searchTour.getAllTourDetails();
            for (TourDetailsDTO td : allTourDetails) {
                all.add(new TourDetailsTM(td.getDate(), td.getPlace(), td.getNoNights(), td.getHotel()));
            }
            txtSearchTourID.setText(selectedItem.getTourID());
            tblTourDetails.setItems(FXCollections.observableArrayList(all));
            tabPaneTour.getSelectionModel().selectNext();
        }
    }

    @FXML
    private void btnPrintMouseClicked(MouseEvent event) throws ClassNotFoundException, SQLException, Exception {
        if (txtSearchTourID.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Search Tour First", ButtonType.OK).show();
        } else {
            InputStream is = this.getClass().getResourceAsStream("/lk/pankajatravel/reports/Tour.jasper");
            Connection connection = DBConnection.getInstance().getConnection();
            TourDTO searchTour = bo.searchTour(txtSearchTourID.getText());
            GuestDTO searchGuest = bo.getSearchGuest(searchTour.getGuestID());
            HashMap map = new HashMap();
            map.put("tourID", searchTour.getTourID());
            map.put("gstName", searchGuest.getFirstName() + " " + searchGuest.getLastName());
            map.put("arriveDate", searchGuest.getArriveDate());
            map.put("arriveAirport", searchGuest.getAirport());
            map.put("arriveTime", searchGuest.getArriveTime());
            map.put("flightNo", searchGuest.getFlightNum());
            map.put("departureDate", searchGuest.getDepartureDate());
            map.put("departureTime", searchGuest.getDepartureTime());

            GuideDTO searchGuide = bo.getSearchGuide(searchTour.getGuideID());
            map.put("GuideName", searchGuide.getfName() + " " + searchGuide.getlName());
            map.put("GuideContactNo", searchGuide.getMobileNo());
            map.put("GuideVehicle", searchGuide.getVehicle());
            JasperPrint print = JasperFillManager.fillReport(is, map, connection);
            JasperViewer.viewReport(print, false);
        }
    }

    private void setComboBoxes() {
        ObservableList<String> ol = FXCollections.observableArrayList();
        ol.addAll("Tour_ID", "Guest_Name", "Guide_Name", "Specific_Date");
        cmbSearchOption.setItems(ol);
    }

    @FXML
    private void btnRefreshMouseClicked(MouseEvent event) throws Exception {
        loadAllTours();
        tblTourDetails.getItems().clear();
        txtSearchTourID.setText("");
    }

}
