/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.custom.GuestBO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.dto.GuestDTO;
import lk.pankajatravel.tableModel.GuestTM;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class ViewAllGuestsController implements Initializable {

    GuestBO bo = (GuestBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.GUEST);

    @FXML
    private TableView<GuestTM> tblGuest;

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
        try {
            loadAll();
        } catch (Exception ex) {
            Logger.getLogger(ViewAllGuestsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void loadAll() throws SQLException, Exception {
        ArrayList<GuestTM> allGuest = new ArrayList<>();
        try{
        ArrayList<GuestDTO> all = bo.getAllGuest();
        for (GuestDTO g : all) {
            allGuest.add(new GuestTM(g.getGuestID(), g.getCountry(), g.getFirstName(), g.getLastName(), g.getMobileNum(), g.getEmailAddress(), g.getFlightNum(), g.getAirport(), g.getArriveDate(), g.getArriveTime(), g.getDepartureDate(), g.getDepartureTime()));
        }
        tblGuest.setItems(FXCollections.observableList(allGuest));
        }catch(NullPointerException ex){
        new Alert(Alert.AlertType.ERROR, "Customer Table is Empty", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void btnPrintMouseClicked(MouseEvent event) {
        try {
            InputStream is = this.getClass().getResourceAsStream("/lk/pankajatravel/reports/Guest.jasper");
            Connection connection = DBConnection.getInstance().getConnection();
            GuestTM selectedItem = tblGuest.getSelectionModel().getSelectedItem();
            HashMap map =new HashMap();
            map.put("guestID", selectedItem.getGstID());
            JasperPrint print = JasperFillManager.fillReport(is, map, connection);
            JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Select Guest from the table", ButtonType.OK).show();
        }
    }
}
