/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.custom.TourBO;
import lk.pankajatravel.dto.GuestDTO;
import lk.pankajatravel.dto.GuideDTO;
import lk.pankajatravel.dto.TourDTO;
import lk.pankajatravel.tableModel.CurrentToursTM;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class TourManagementController implements Initializable {

    TourBO bo = (TourBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.TOUR);

    @FXML
    private JFXButton btnNewTour;
    @FXML
    private TableView<CurrentToursTM> tblCurrentTours;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblCurrentTours.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblCurrentTours.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblCurrentTours.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblCurrentTours.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        tblCurrentTours.getColumns().get(4).setStyle("-fx-alignment: CENTER;");
        tblCurrentTours.getColumns().get(5).setStyle("-fx-alignment: CENTER;");

//        --------------------------------------------------------------
        tblCurrentTours.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Tour_ID"));
        tblCurrentTours.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Start_Date"));
        tblCurrentTours.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("End_Date"));
        tblCurrentTours.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Guest_Name"));
        tblCurrentTours.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Guide_Name"));
        tblCurrentTours.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Members"));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                btnNewTour.requestFocus();
            }
        });
        try {
            loadAll();
        } catch (Exception ex) {
            Logger.getLogger(TourManagementController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void newTourMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/Tour.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.centerOnScreen();
        stage.setResizable(false);
        stage.show();
        
    }

    @FXML
    private void btnViewTourDetailsMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/ViewTourDetails.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
        
    }

    private void loadAll() throws SQLException, Exception {
        ArrayList<CurrentToursTM> all = new ArrayList<>();
        ArrayList<TourDTO> currentTours = bo.getCurrentTours();
        int members;
        for (TourDTO t : currentTours) {
            GuestDTO searchGuest = bo.getSearchGuest(t.getGuestID());
            GuideDTO searchGuide = bo.getSearchGuide(t.getGuideID());
            members=Integer.parseInt(t.getNoAdults())+Integer.parseInt(t.getNoChildren());
            all.add(new CurrentToursTM(t.getTourID(), t.getTourStartDate(), t.getTourEndDate(), searchGuest.getFirstName(), searchGuide.getfName(), members+""));
        }
        tblCurrentTours.setItems(FXCollections.observableArrayList(all));
    }
}
