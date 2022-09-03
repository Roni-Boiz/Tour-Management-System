/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXButton;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.pankajatravel.db.DBConnection;
import sun.security.provider.MD5;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class LogInController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private JFXButton btnLogIn;
    @FXML
    private AnchorPane paneLogIn;
    @FXML
    private ImageView imgCapcha;

    private double x = 0, y = 0;
    @FXML
    private TextField txtCapcha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtUserName.requestFocus();
                makeDragable();
            }
        });
        loadCapcha();
    }

    @FXML
    private void txtUserNameAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    private void txtPasswordAction(ActionEvent event) {
        txtCapcha.requestFocus();
    }

    @FXML
    private void txtCapchaAction(ActionEvent event) {
        btnLogIn.requestFocus();
    }

    @FXML
    private void btnLogInAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        try {
            if (checkCapcha()) {
                Connection connection = DBConnection.getInstance().getConnection();
                CallableStatement pstm = connection.prepareCall("SELECT * FROM users WHERE username=? && password=md5(?)");
                pstm.setObject(1, txtUserName.getText());
                pstm.setObject(2, txtPassword.getText());
                ResultSet rst = pstm.executeQuery();
                if (rst.next()) {
                    new Alert(Alert.AlertType.INFORMATION, "Welcome " + rst.getString(2), ButtonType.OK).show();
                    Stage stage = (Stage) this.paneLogIn.getScene().getWindow();
                    Parent prnt = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/Home.fxml"));
                    Scene scene = new Scene(prnt);
                    scene.setFill(Color.TRANSPARENT);
                    stage.setScene(scene);
                    stage.setResizable(false);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid User Name or Password !", ButtonType.OK).show();
                    txtUserName.requestFocus();
                    clearAll();
                    loadCapcha();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid Capcha !", ButtonType.OK).show();
                txtCapcha.requestFocus();
                loadCapcha();
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Server is stoped. Please start server manually before login !. (TaskManager -> Services -> MYSQL80 -> Start)", ButtonType.OK).showAndWait();
            txtUserName.requestFocus();
            clearAll();
        }
    }

    @FXML
    private void closeMouseClicked(MouseEvent event) {
        System.exit(0);
    }

    private void makeDragable() {
        paneLogIn.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));

        paneLogIn.setOnMouseDragged((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        });

        paneLogIn.setOnDragDone((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        });

        paneLogIn.setOnMouseReleased((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        });
    }

    private void loadCapcha() {
        int min = 1;
        int max = 20;
        Random r = new Random();
        int random = r.nextInt((max - min) + 1) + min;
        imgCapcha.setImage(new javafx.scene.image.Image("/lk/pankajatravel/img/capcha/Capcha" + random + ".PNG"));
    }

    private void btnCloseMouseClicked(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    private void refreshbtnMouseClicked(MouseEvent event) {
        loadCapcha();
    }

    @FXML
    private void btnSignUpAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.paneLogIn.getScene().getWindow();
        Parent prnt = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/SignIn.fxml"));
        Scene scene = new Scene(prnt);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
        tt.setFromX(-scene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    @FXML
    private void btnForgotPasswordAction(ActionEvent event) {
        new Alert(Alert.AlertType.INFORMATION, "Please Contact Administrator to get master key to reset your password or use Test Account", ButtonType.OK).show();

    }

    private boolean checkCapcha() {
        String[] capcha = {"ZKW4", "advses", "2pvcb", "quxg4h", "7d6bf", "slythygomi", "3nc9z", "2CCEX", "28ivw", "FH2DE", "e5hb", "XDHYN", "k4ez", "gwprp", "xmqki", "q98p", "jw62k", "4D7YS", "6ne3", "HRAI"};
        for (String s : capcha) {
            if (txtCapcha.getText().equals(s)) {
                return true;
            }
        }
        return false;
    }

    private void clearAll() {
        txtUserName.setText("");
        txtPassword.setText("");
        txtCapcha.setText("");
    }

}
