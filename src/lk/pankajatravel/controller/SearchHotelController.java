/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.pankajatravel.bo.BOFactory;
import lk.pankajatravel.bo.custom.HotelBO;
import lk.pankajatravel.dto.HotelDTO;
import lk.pankajatravel.tableModel.HotelTM;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class SearchHotelController implements Initializable {

    HotelBO bo = (HotelBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.HOTEL);

    @FXML
    private Label lblHeading1;
    @FXML
    private JFXComboBox<String> cmbSearchOption;
    @FXML
    private TextField txtSearchData;
    @FXML
    private Label lblHeading;
    @FXML
    private TextField txtSearchHtlID;
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
    private JFXRadioButton rBtnYes;
    @FXML
    private JFXRadioButton rBtnNo;
    @FXML
    private TableView<HotelTM> tblHotel;

    ToggleGroup tg = new ToggleGroup();
    @FXML
    private JFXToggleButton tBtnEdit;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private TabPane tabPaneHotel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblHotel.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblHotel.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblHotel.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblHotel.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        tblHotel.getColumns().get(4).setStyle("-fx-alignment: CENTER;");
        tblHotel.getColumns().get(5).setStyle("-fx-alignment: CENTER;");
//      -------------------------setting table columns--------------------------------------
        tblHotel.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Hotel_ID"));
        tblHotel.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Name"));
        tblHotel.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblHotel.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Telephone_Number"));
        tblHotel.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Email"));
        tblHotel.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Accommodation"));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cmbSearchOption.requestFocus();
//                txtSearchHtlID.requestFocus();
                rBtnYes.setUserData("Yes");
                rBtnYes.setToggleGroup(tg);
                rBtnNo.setUserData("No");
                rBtnNo.setToggleGroup(tg);
                setEditableOff();
                tBtnEdit.setDisable(true);
                btnUpdate.setVisible(false);
                btnRemove.setVisible(false);
                ObservableList<String> ol = FXCollections.observableArrayList("Name", "Address");
                cmbSearchOption.setItems(ol);
            }
        });
    }

    @FXML
    private void txtSearchDataAction(ActionEvent event) throws SQLException, Exception {
        ArrayList<HotelTM> allHotel = new ArrayList<>();
        try {
            switch (cmbSearchOption.getValue()) {
                case "Name":
                    try {
                        ArrayList<HotelDTO> all = bo.searchHotelbyName(txtSearchData.getText());
                        for (HotelDTO h : all) {
                            allHotel.add(new HotelTM(h.getHtlID(), h.gethName(), h.getAddress(), h.getTelNo(), h.getEmail(), h.getAccommodation()));
                        }
                        tblHotel.setItems(FXCollections.observableList(allHotel));
                    } catch (NullPointerException ex) {
                        new Alert(Alert.AlertType.ERROR, "No Match Found", ButtonType.OK).showAndWait();
                    }
                    break;
                case "Address":
                    try {
                        ArrayList<HotelDTO> all = bo.searchHotelbyAddress(txtSearchData.getText());
                        for (HotelDTO h : all) {
                            allHotel.add(new HotelTM(h.getHtlID(), h.gethName(), h.getAddress(), h.getTelNo(), h.getEmail(), h.getAccommodation()));
                        }
                        tblHotel.setItems(FXCollections.observableList(allHotel));
                    } catch (NullPointerException ex) {
                        new Alert(Alert.AlertType.ERROR, "No Match Found", ButtonType.OK).showAndWait();
                    }
                    break;
            }
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.ERROR, "Plese Select an Option", ButtonType.OK).showAndWait();
            cmbSearchOption.requestFocus();
        }
    }

    @FXML
    private void txtSearchHtlIDAction(ActionEvent event) throws SQLException, Exception {
        String SearchHtlID = txtSearchHtlID.getText();
        clearAll();
        try {
            HotelDTO searchHotel = bo.searchHotel(SearchHtlID);
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
            Alert al = new Alert(Alert.AlertType.INFORMATION, "No such Guide as " + SearchHtlID, ButtonType.OK);
            al.setTitle("Done");
            al.show();
            clearAll();
            tBtnEdit.setSelected(false);
            tBtnEdit.setDisable(true);
            btnUpdate.setVisible(false);
            btnRemove.setVisible(false);
            setEditableOff();
        }
    }

    @FXML
    private void txtHotelIDAction(ActionEvent event) {
    }

    @FXML
    private void txtNameAction(ActionEvent event) {
    }

    @FXML
    private void txtAddressAction(ActionEvent event) {
    }

    @FXML
    private void txtTelNoAction(ActionEvent event) {
    }

    @FXML
    private void txtEmailAddressAction(ActionEvent event) {
    }

    @FXML
    private void rBtnYesAction(ActionEvent event) {
    }

    @FXML
    private void rBtnNoAction(ActionEvent event) {
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
                            txtSearchHtlID.requestFocus();
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
            txtSearchHtlID.requestFocus();
        } else {
            Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
            al.setTitle("Error");
            al.showAndWait();
        }
    }

    private void clearAll() {
        txtAddress.setText("");
        txtEmailAddress.setText("");
        txtHotelID.setText("");
        txtName.setText("");
        txtTelNo.setText("(+94)");
        rBtnYes.setSelected(false);
        rBtnNo.setSelected(false);
    }

    private void setEditableOff() {
        txtHotelID.setEditable(false);
        txtName.setEditable(false);
        txtAddress.setEditable(false);
        txtTelNo.setEditable(false);
        txtEmailAddress.setEditable(false);
        rBtnYes.setDisable(true);
        rBtnNo.setDisable(true);
    }

    private void setEditable() {
        txtHotelID.setEditable(true);
        txtName.setEditable(true);
        txtAddress.setEditable(true);
        txtTelNo.setEditable(true);
        txtEmailAddress.setEditable(true);
        rBtnYes.setDisable(false);
        rBtnNo.setDisable(false);
    }

    @FXML
    private void tblHotelMouseClicked(MouseEvent event) {
        if (tblHotel.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "The Table is Empty", ButtonType.OK).show();
        } else {
            HotelTM searchHotel = tblHotel.getSelectionModel().getSelectedItem();
            txtSearchHtlID.setText(searchHotel.getHotel_ID());
            txtHotelID.setText(searchHotel.getHotel_ID());
            txtName.setText(searchHotel.getName());
            txtAddress.setText(searchHotel.getAddress());
            txtTelNo.setText(searchHotel.getTelephone_Number());
            txtEmailAddress.setText(searchHotel.getEmail());
            rBtnYes.setSelected(searchHotel.getAccommodation().equals("Yes"));
            rBtnNo.setSelected(searchHotel.getAccommodation().equals("No"));
            tBtnEdit.setDisable(false);
            tabPaneHotel.getSelectionModel().select(1);
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
    private void btnBackToHotelMouseClicked(MouseEvent event) {
        Stage stage = (Stage) this.tabPaneHotel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnBackMouseClicked(MouseEvent event) {
        tabPaneHotel.getSelectionModel().selectPrevious();
    }
}
