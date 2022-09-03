/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class HomeController implements Initializable {

    @FXML
    private FontAwesomeIcon logout;
    @FXML
    private AnchorPane paneHome;
    @FXML
    private Text lblTourManagement;
    @FXML
    private Text lblGuestManagement;
    @FXML
    private Text lblGuideManagement;
    @FXML
    private Text lblHotelManagement;
    @FXML
    private Text lblVehicalManagement;
    @FXML
    private Text lblCityManagement;
    @FXML
    private Text lblSelection;
    
    private double x=0,y=0;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeDragable();
    }    

    @FXML
    private void logoutClicked(MouseEvent event) throws IOException {
        Stage stage = (Stage) this.paneHome.getScene().getWindow();
        Parent prnt= FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/LogIn.fxml"));
        Scene scene= new Scene(prnt);
        stage.setScene(scene);
    }

    @FXML
    private void guestManagementClicked(MouseEvent event) throws IOException {
        lblSelection.setText("Guest Management");
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/GuestManagement.fxml"));
        paneHome.getChildren().setAll(pane);
    }

    @FXML
    private void guideManagementClicked(MouseEvent event) throws IOException {
        lblSelection.setText("Guide Management");
         AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/GuideManagement.fxml"));
         paneHome.getChildren().setAll(pane);
    }

    @FXML
    private void hotelManagementClicked(MouseEvent event) throws IOException {
        lblSelection.setText("Hotel Management");
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/Hotel.fxml"));
         paneHome.getChildren().setAll(pane);
    }

    @FXML
    private void vehicalManagementClicked(MouseEvent event) throws IOException {
        lblSelection.setText("Vehical Management");
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/Vehicle.fxml"));
         paneHome.getChildren().setAll(pane);
    }

    @FXML
    private void cityManagementClicked(MouseEvent event) throws IOException {
        lblSelection.setText("City Management");
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/City.fxml"));
         paneHome.getChildren().setAll(pane);
    }

    @FXML
    private void tourManagementMouseClicked(MouseEvent event) throws IOException {
        lblSelection.setText("Tour Management");
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/TourManagement.fxml"));
         paneHome.getChildren().setAll(pane);
    }
    
    private void makeDragable(){
    paneHome.setOnMousePressed(((event) -> {
        x=event.getSceneX();
        y=event.getSceneY();
    }));
    
    paneHome.setOnMouseDragged((event) -> {
        Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
        stage.setOpacity(0.8f);
    });
    
    paneHome.setOnDragDone((event) -> {
        Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setOpacity(1.0f);
    });
    
    paneHome.setOnMouseReleased((event) -> {
        Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setOpacity(1.0f);
    });
    }
}
