/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class GuestManagementController implements Initializable {

    @FXML
    private AnchorPane paneGuestManagement;
    @FXML
    private JFXButton btnNewGuest;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                btnNewGuest.requestFocus();
            }
        });
    }    

    @FXML
    private void btnNewGuestMouseClicked(MouseEvent event) throws IOException {
        AnchorPane pane=FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/Guest.fxml"));
        paneGuestManagement.getChildren().setAll(pane);
    }

    @FXML
    private void btnSearchGuestMouseClicked(MouseEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/SearchGuest.fxml"));
        Scene temp=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    private void btnUpdateRemoveGuestMouseClicked(MouseEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/SearchGuest.fxml"));
        Scene temp=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    private void btnViewAllMouseClicked(MouseEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/ViewAllGuests.fxml"));
        Scene temp=new Scene(root);
        Stage stage=new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    
}
