/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.SuperBO;
import lk.pankajatravel.bo.custom.HotelBO;
import lk.pankajatravel.dto.HotelDTO;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class HotelController implements Initializable {

    HotelBO bo = (HotelBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.HOTEL);

    @FXML
    private JFXTextField txtHotelID;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtTelNo;
    @FXML
    private JFXTextField txtEmailAddress;
    @FXML
    private JFXButton btnAddHotel;
    @FXML
    private JFXRadioButton rBtnYes;
    @FXML
    private JFXRadioButton rBtnNo;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXToggleButton tBtnEdit;

    ToggleGroup tg = new ToggleGroup();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                txtName.requestFocus();
                rBtnYes.setUserData("Yes");
                rBtnYes.setToggleGroup(tg);
                rBtnNo.setUserData("No");
                rBtnNo.setToggleGroup(tg);
                tBtnEdit.setDisable(true);
                btnUpdate.setVisible(false);
                btnRemove.setVisible(false);
            }
        });
        loadHotelID();
    }

    @FXML
    private void txtHotelIDAction(ActionEvent event) throws SQLException, Exception {
        String htlID = txtHotelID.getText();
        clearAll();
        txtHotelID.setText(htlID);
        try {
            HotelDTO searchHotel = bo.searchHotel(htlID);
//   ----------------     setting all text     --------------------
            txtHotelID.setText(searchHotel.getHtlID());
            txtName.setText(searchHotel.gethName());
            txtAddress.setText(searchHotel.getAddress());
            txtTelNo.setText(searchHotel.getTelNo());
            txtEmailAddress.setText(searchHotel.getEmail());
            rBtnYes.setSelected(searchHotel.getAccommodation().equals("Yes"));
            rBtnNo.setSelected(searchHotel.getAccommodation().equals("No"));

            tBtnEdit.setDisable(false);
            tBtnEditAction(event);
            tBtnEdit.requestFocus();
        } catch (NullPointerException ex) {
            txtName.requestFocus();
            tBtnEdit.setSelected(false);
            tBtnEdit.setDisable(true);
            btnUpdate.setVisible(false);
            btnRemove.setVisible(false);
            setEditable();
        }
    }

    @FXML
    private void txtNameAction(ActionEvent event) {
        txtAddress.requestFocus();
    }

    @FXML
    private void txtAddressAction(ActionEvent event) {
        txtTelNo.requestFocus();
    }

    @FXML
    private void txtTelNoAction(ActionEvent event) {
        txtEmailAddress.requestFocus();
    }

    @FXML
    private void txtEmailAddressAction(ActionEvent event) {
        rBtnYes.requestFocus();
    }

    @FXML
    private void btnSearchMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/SearchHotel.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    private void btnViewAllMouseClicked(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/ViewAllHotels.fxml"));
        Scene temp = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(temp);
        stage.show();
        stage.centerOnScreen();
        stage.setResizable(false);
    }

    @FXML
    private void rBtnYesAction(ActionEvent event) {
        btnAddHotel.requestFocus();
    }

    @FXML
    private void rBtnNoAction(ActionEvent event) {
        btnAddHotel.requestFocus();
    }

    @FXML
    private void btnAddHotelMouseClicked(MouseEvent event) throws ClassNotFoundException, Exception {
        if (Pattern.compile("^(htl-00)[0-9]$").matcher(txtHotelID.getText()).matches()) {
            if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^((\\+94)[0-9]{9})$|^((0)[0-9]{9})$").matcher(txtTelNo.getText()).matches()) {
                    if (Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)$").matcher(txtEmailAddress.getText()).matches()) {
                        boolean addHotel = bo.addHotel(new HotelDTO(txtHotelID.getText(), txtName.getText(), txtAddress.getText(), txtTelNo.getText(), txtEmailAddress.getText(), tg.getSelectedToggle().getUserData().toString()));
                        if (addHotel) {
                            new Alert(Alert.AlertType.INFORMATION, "Hotel " + txtName.getText() + " is Added Successfuly", ButtonType.OK).showAndWait();
                            clearAll();
                            loadHotelID();
                            txtName.requestFocus();
                        } else {
                            Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
                            al.setTitle("Error");
                            al.showAndWait();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid email address.", ButtonType.OK).show();
                        txtEmailAddress.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field telephone number. Telephone number must have 10 digits starting from zero(0) or (+94xxxxxxxxx) where x=[0-9].", ButtonType.OK).show();
                    txtTelNo.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field name. Please use letters and 1st letter must be capital in each word.", ButtonType.OK).show();
                txtName.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid hotel ID format. Please use htl-00x as hotel ID where x={1,2,3,...}.", ButtonType.OK).show();
            txtHotelID.requestFocus();
        }

    }

    @FXML
    private void btnUpdateMouseClicked(MouseEvent event) throws ClassNotFoundException, Exception {
        if (Pattern.compile("^(htl-00)[0-9]$").matcher(txtHotelID.getText()).matches()) {
            if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtName.getText()).matches()) {
                if (Pattern.compile("^((\\+94)[0-9]{9})$|^((0)[0-9]{9})$").matcher(txtTelNo.getText()).matches()) {
                    if (Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)$").matcher(txtEmailAddress.getText()).matches()) {
                        boolean updateHotel = bo.updateHotel(new HotelDTO(txtHotelID.getText(), txtName.getText(), txtAddress.getText(), txtTelNo.getText(), txtEmailAddress.getText(), tg.getSelectedToggle().getUserData().toString()));
                        if (updateHotel) {
                            new Alert(Alert.AlertType.INFORMATION, "Hotel " + txtName.getText() + " is Updated Successfuly", ButtonType.OK).showAndWait();
                            clearAll();
                            loadHotelID();
                            txtName.requestFocus();
                        } else {
                            Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
                            al.setTitle("Error");
                            al.showAndWait();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid email address.", ButtonType.OK).show();
                        txtEmailAddress.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field telephone number. Telephone number must have 10 digits starting from zero(0) or (+94xxxxxxxxx) where x=[0-9].", ButtonType.OK).show();
                    txtTelNo.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field name. Please use letters and 1st letter must be capital in each word.", ButtonType.OK).show();
                txtName.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid hotel ID format. Please use htl-00x as hotel ID where x={1,2,3,...}.", ButtonType.OK).show();
            txtHotelID.requestFocus();
        }

    }

    @FXML
    private void btnRemoveMouseClicked(MouseEvent event) throws SQLException, Exception {
        boolean deleteHotel = bo.deleteHotel(txtHotelID.getText());
        if (deleteHotel) {
            new Alert(Alert.AlertType.INFORMATION, "Done", ButtonType.OK).showAndWait();
            clearAll();
            loadHotelID();
            txtName.requestFocus();
        }
    }

    @FXML
    private void tBtnEditAction(ActionEvent event) {
        if (tBtnEdit.isSelected()) {
            setEditable();
            txtName.requestFocus();
            btnRemove.setVisible(true);
            btnUpdate.setVisible(true);
//            System.out.println("on");
        } else {
            setEditableOff();
            btnRemove.setVisible(false);
            btnUpdate.setVisible(false);
//            System.out.println("off");
        }
    }

    private void clearAll() {
        txtAddress.setText("");
        txtEmailAddress.setText("");
        txtHotelID.setText("");
        txtName.setText("");
        txtTelNo.setText("");
        rBtnYes.setSelected(false);
        rBtnNo.setSelected(false);
    }

    private void setEditableOff() {
        txtName.setEditable(false);
        txtAddress.setEditable(false);
        txtTelNo.setEditable(false);
        txtEmailAddress.setEditable(false);
        rBtnYes.setDisable(true);
        rBtnNo.setDisable(true);
    }

    private void setEditable() {
        txtName.setEditable(true);
        txtAddress.setEditable(true);
        txtTelNo.setEditable(true);
        txtEmailAddress.setEditable(true);
        rBtnYes.setDisable(false);
        rBtnNo.setDisable(false);
    }

    private void loadHotelID() {
        String hotelID = bo.getHotelID();
        txtHotelID.setText(hotelID);
    }
}
