/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.pankajatravel.db.DBConnection;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class SignInController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private AnchorPane paneSignIn;
    @FXML
    private JFXButton btnSignIn;
    @FXML
    private PasswordField txtConfirmPassword;
    @FXML
    private FontAwesomeIcon iconConfirm;

    double x = 0, y = 0;

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
    }

    @FXML
    private void txtUserNameAction(ActionEvent event) {
        txtPassword.requestFocus();
    }

    @FXML
    private void txtPasswordAction(ActionEvent event) {
        txtConfirmPassword.requestFocus();
    }

    @FXML
    private void txtConfirmPasswordKeyReleased(KeyEvent event) {
        if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
            iconConfirm.setVisible(true);
        } else {
            iconConfirm.setVisible(false);
        }
    }

    @FXML
    private void txtConfirmPasswordAction(ActionEvent event) {
        btnSignIn.requestFocus();
    }

    @FXML
    private void closeMouseClicked(MouseEvent event) {
        System.exit(0);
    }

    private void makeDragable() {
        paneSignIn.setOnMousePressed(((event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        }));

        paneSignIn.setOnMouseDragged((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
            stage.setOpacity(0.8f);
        });

        paneSignIn.setOnDragDone((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        });

        paneSignIn.setOnMouseReleased((event) -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setOpacity(1.0f);
        });
    }

    @FXML
    private void btnSignInAction(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        try {
            if (!txtUserName.getText().equals("") || !txtPassword.getText().equals("")) {
                if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
                    if (Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)$|^([a-z]{3,})$").matcher(txtUserName.getText()).matches()) {
                        if (Pattern.compile("^[A-z,0-9,~_+-.,!@#$%^&*();\\/|<>]{4,}$").matcher(txtPassword.getText()).matches()) {
                            Connection connection = DBConnection.getInstance().getConnection();
                            CallableStatement pstm = connection.prepareCall("INSERT INTO users (userName,password) VALUES(?,MD5(?))");
                            pstm.setObject(1, txtUserName.getText());
                            pstm.setObject(2, txtPassword.getText());
                            if (pstm.executeUpdate() > 0) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Account is created successfully", ButtonType.FINISH).showAndWait();
                                Stage stage = (Stage) this.paneSignIn.getScene().getWindow();
                                Parent prnt = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/LogIn.fxml"));
                                Scene scene = new Scene(prnt);
                                scene.setFill(Color.TRANSPARENT);
                                stage.setScene(scene);
                                stage.setResizable(false);
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Something Went Wrong !", ButtonType.OK).show();
                                txtUserName.requestFocus();
                                clearAll();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Enter correct password format !. You can use letters , numbers or any special charactors (~_+-.,!@#$%^&*();\\/|<>). Password must have at least 4 charactors.", ButtonType.OK).show();
                            txtPassword.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Enter correct user name format !. You can use any word with simple letters or Valid email address", ButtonType.OK).show();
                        txtUserName.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Confirm password do not match !. Re-Enter password again.", ButtonType.OK).show();
                    txtConfirmPassword.requestFocus();
                    txtConfirmPassword.setText("");
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "User Name and Password cannot be empty.", ButtonType.OK).show();
                txtUserName.requestFocus();
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Server is stoped. Please start server manually before SignIn !. (TaskManager -> Services -> MYSQL80 -> Start)", ButtonType.OK).showAndWait();
            txtUserName.requestFocus();
        }

    }

    @FXML
    private void btnLogInAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) this.paneSignIn.getScene().getWindow();
        Parent prnt = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/LogIn.fxml"));
        Scene scene = new Scene(prnt);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.setResizable(false);

        TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
        tt.setFromX(scene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    private void clearAll() {
        txtUserName.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
    }

}
