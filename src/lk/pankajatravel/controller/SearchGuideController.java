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
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
import lk.pankajatravel.bo.custom.GuideBO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.dto.GuideDTO;
import lk.pankajatravel.tableModel.GuideTM;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class SearchGuideController implements Initializable {

    GuideBO bo = (GuideBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.GUIDE);

    @FXML
    private Label lblHeading1;
    @FXML
    private JFXComboBox<String> cmbSearchOption;
    @FXML
    private TextField txtSearchData;
    @FXML
    private TableView<GuideTM> tblGuide;
    @FXML
    private Label lblHeading;
    @FXML
    private TextField txtSearchGuideID;
    @FXML
    private JFXToggleButton tBtnEdit;
    @FXML
    private JFXTextField txtGidID;
    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtMobileNo;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtLName;
    @FXML
    private JFXTextField txtNICNumber;
    @FXML
    private JFXTextField txtYearofExp;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private JFXTextField txtEmailAddress;
    @FXML
    private JFXTextField txtVehicle;
    @FXML
    private JFXRadioButton rBtnYes;
    @FXML
    private JFXRadioButton rBtnNo;

    ToggleGroup tgBtn = new ToggleGroup();
    @FXML
    private TabPane tabPaneGuide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblGuide.getColumns().get(0).setStyle("-fx-alignment: CENTER;");
        tblGuide.getColumns().get(1).setStyle("-fx-alignment: CENTER;");
        tblGuide.getColumns().get(2).setStyle("-fx-alignment: CENTER;");
        tblGuide.getColumns().get(3).setStyle("-fx-alignment: CENTER;");
        tblGuide.getColumns().get(4).setStyle("-fx-alignment: CENTER;");
        tblGuide.getColumns().get(5).setStyle("-fx-alignment: CENTER;");
        tblGuide.getColumns().get(6).setStyle("-fx-alignment: CENTER;");
        tblGuide.getColumns().get(7).setStyle("-fx-alignment: CENTER;");
        tblGuide.getColumns().get(8).setStyle("-fx-alignment: CENTER;");
        tblGuide.getColumns().get(9).setStyle("-fx-alignment: CENTER;");
//      -------------------------setting table columns--------------------------------------
        tblGuide.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("GidID"));
        tblGuide.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("F_Name"));
        tblGuide.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("L_Name"));
        tblGuide.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("NIC"));
        tblGuide.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblGuide.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("YOE"));
        tblGuide.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("Mobile_No"));
        tblGuide.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("Email"));
        tblGuide.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("vehicle"));
        tblGuide.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("Guide_Licence"));
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                cmbSearchOption.requestFocus();
//                txtSearchGuideID.requestFocus();
                rBtnYes.setToggleGroup(tgBtn);
                rBtnYes.setUserData("Yes");
                rBtnNo.setToggleGroup(tgBtn);
                rBtnNo.setUserData("No");
                setEditableOff();
                tBtnEdit.setDisable(true);
                btnUpdate.setVisible(false);
                btnRemove.setVisible(false);
                ObservableList<String> ol = FXCollections.observableArrayList("Name");
                cmbSearchOption.setItems(ol);
            }
        });
    }

    @FXML
    private void txtSearchDataAction(ActionEvent event) throws SQLException, Exception {
        ArrayList<GuideTM> allGuide = new ArrayList<>();
        try {
            switch (cmbSearchOption.getValue()) {
                case "Name":
                    try {
                        ArrayList<GuideDTO> all = bo.searchGuidebyName(txtSearchData.getText());
                        for (GuideDTO g : all) {
                            allGuide.add(new GuideTM(g.getGidID(), g.getfName(), g.getlName(), g.getNIC(), g.getAddress(), g.getYOE(), g.getMobileNo(), g.getEmail(), g.getVehicle(), g.getGuideLicence()));
                        }
                        tblGuide.setItems(FXCollections.observableList(allGuide));
                    } catch (NullPointerException ex) {
                        new Alert(Alert.AlertType.ERROR, "No Match Found", ButtonType.OK).showAndWait();
                        tblGuide.getItems().clear();
                    }
                    break;
            }
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.ERROR, "Plese Select an Option", ButtonType.OK).showAndWait();
            cmbSearchOption.requestFocus();
        }
    }

    @FXML
    private void txtSearchGuideIDAction(ActionEvent event) throws SQLException, Exception {
        String searchGuidID = txtSearchGuideID.getText();
        clearAll();
        try {
            GuideDTO searchGuide = bo.searchGuide(searchGuidID);
            txtGidID.setText(searchGuide.getGidID());
            txtFName.setText(searchGuide.getfName());
            txtLName.setText(searchGuide.getlName());
            txtNICNumber.setText(searchGuide.getNIC());
            txtAddress.setText(searchGuide.getAddress());
            txtYearofExp.setText(searchGuide.getYOE() + "");
            txtMobileNo.setText(searchGuide.getMobileNo());
            txtEmailAddress.setText(searchGuide.getEmail());
            txtVehicle.setText(searchGuide.getVehicle());
            rBtnYes.setSelected(searchGuide.getGuideLicence().equals("Yes"));
            rBtnNo.setSelected(searchGuide.getGuideLicence().equals("No"));

            tBtnEdit.setDisable(false);
            tBtnEditAction(event);
            tBtnEdit.requestFocus();
        } catch (NullPointerException ex) {
            Alert al = new Alert(Alert.AlertType.INFORMATION, "No such Guide as " + searchGuidID, ButtonType.OK);
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
    private void btnUpdateMouseClicked(MouseEvent event) throws Exception {
        if (Pattern.compile("^(gid-00)[0-9]$").matcher(txtGidID.getText()).matches()) {
            if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtFName.getText()).matches()) {
                if (Pattern.compile("^([A-Z]{1}[a-z]{1,}\\s?)+$").matcher(txtLName.getText()).matches()) {
                    if (Pattern.compile("^([0-9]{9}(v|V))|(20)[0-9]{9}$").matcher(txtNICNumber.getText()).matches()) {
                        if (Pattern.compile("^[0-9]{1,2}$").matcher(txtYearofExp.getText()).matches()) {
                            if (Pattern.compile("^(([\\+947])[0-9]{11})$|^((07)[0-9]{8})$").matcher(txtMobileNo.getText()).matches()) {
                                if (Pattern.compile("^(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)$").matcher(txtEmailAddress.getText()).matches()) {
                                    if (Pattern.compile("^([A-z,0-9]{1,}\\s?)+$").matcher(txtVehicle.getText()).matches()) {
                                        String gidID = txtGidID.getText();
                                        String fName = txtFName.getText();
                                        String lName = txtLName.getText();
                                        String nIC = txtNICNumber.getText();
                                        String address = txtAddress.getText();
                                        int yOE = Integer.parseInt(txtYearofExp.getText());
                                        String mobileNo = txtMobileNo.getText();
                                        String email = txtEmailAddress.getText();
                                        String vehicle = txtVehicle.getText();
                                        String guideLicence = tgBtn.getSelectedToggle().getUserData().toString();
                                            GuideDTO g = new GuideDTO(gidID, fName, lName, nIC, address, yOE, mobileNo, email, vehicle, guideLicence);
                                            boolean updateGuest = bo.updateGuide(g);
                                            if (updateGuest) {
                                                Alert al = new Alert(Alert.AlertType.INFORMATION, "Guide "+fName+" is Updated Successfuly", ButtonType.OK);
                                                al.setTitle("Done");
                                                al.showAndWait();
                                                clearAll();
                                                txtSearchGuideID.requestFocus();
                                            } else {
                                                Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
                                                al.setTitle("Error");
                                                al.showAndWait();
                                            }
                                    } else {
                                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field vehicle. Special charactors cannot entered to it.", ButtonType.OK).show();
                                        txtVehicle.requestFocus();
                                    }
                                } else {
                                    new Alert(Alert.AlertType.ERROR, "Invalid email address.", ButtonType.OK).show();
                                    txtEmailAddress.requestFocus();
                                }
                            } else {
                                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field mobile number. Mobile number must have 10 digits starting from zero(0) or (+94xxxxxxxxx) where x=[0-9].", ButtonType.OK).show();
                                txtMobileNo.requestFocus();
                            }
                        } else {
                            new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field Year of experience. Please enter numbers between [0-99].", ButtonType.OK).show();
                            txtYearofExp.requestFocus();
                        }
                    } else {
                        new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field NIC Number. Old NIC must hava 10 digits have a format of ********V and New NIC must have 12 digits have format of 20***********.", ButtonType.OK).show();
                        txtNICNumber.requestFocus();
                    }
                } else {
                    new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field last name. Please use letters and 1st letter must be capital in each word.", ButtonType.OK).show();
                    txtLName.requestFocus();
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Invalid value is entered to text field first name. Please use letters and 1st letter must be capital in each word.", ButtonType.OK).show();
                txtFName.requestFocus();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid guide ID format. Please use gid-00x as guide ID where x={1,2,3,...}.", ButtonType.OK).show();
            txtGidID.requestFocus();
        }

    }

    @FXML
    private void btnRemoveMouseClicked(MouseEvent event) throws SQLException, Exception {
        String searchGidID = txtGidID.getText();
        try {
            boolean deleteGuest = bo.deleteGuide(searchGidID);
            if (deleteGuest) {
                Alert al = new Alert(Alert.AlertType.INFORMATION, "Guide is Successfuly Removed", ButtonType.OK);
                al.setTitle("Done");
                al.showAndWait();
                clearAll();
                txtSearchGuideID.requestFocus();
            } else {
                Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
                al.setTitle("Error");
                al.showAndWait();
            }
        } catch (SQLException ex) {
            Alert al = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
            al.setTitle("Error");
            al.showAndWait();
        }
    }

    private void clearAll() {
        txtGidID.setText("");
        txtFName.setText("");
        txtLName.setText("");
        txtNICNumber.setText("");
        txtAddress.setText("");
        txtYearofExp.setText("");
        txtMobileNo.setText("(+94)");
        txtEmailAddress.setText("");
        txtVehicle.setText("");
        rBtnYes.setSelected(false);
        rBtnNo.setSelected(false);
    }

    public void setComboBox() {
        ObservableList<String> ol = FXCollections.observableArrayList("Name");
        cmbSearchOption.setItems(ol);

    }

    private void setEditableOff() {
        txtGidID.setEditable(false);
        txtFName.setEditable(false);
        txtLName.setEditable(false);
        txtNICNumber.setEditable(false);
        txtAddress.setEditable(false);
        txtYearofExp.setEditable(false);
        txtMobileNo.setEditable(false);
        txtEmailAddress.setEditable(false);
        txtVehicle.setEditable(false);
        rBtnYes.setDisable(true);
        rBtnNo.setDisable(true);
    }

    private void setEditable() {
        txtGidID.setEditable(true);
        txtFName.setEditable(true);
        txtLName.setEditable(true);
        txtNICNumber.setEditable(true);
        txtAddress.setEditable(true);
        txtYearofExp.setEditable(true);
        txtMobileNo.setEditable(true);
        txtEmailAddress.setEditable(true);
        txtVehicle.setEditable(true);
        rBtnYes.setDisable(false);
        rBtnNo.setDisable(false);
    }

    //    private void doneAlert(String s){
//            Alert al = new Alert(Alert.AlertType.INFORMATION, "Guide is "+s+" Successfully", ButtonType.OK);
//            al.setTitle("Done");
//            al.setHeaderText("Information Message");
//            al.showAndWait();
//            clearAll();
//    }
//    
//    private void ErrorAlert(){
//            Alert al = new Alert(Alert.AlertType.ERROR, "Something Went Wrong", ButtonType.OK);
//            al.setTitle("Error");
//            al.setHeaderText("Error Message");
//            al.showAndWait();
//    }
    @FXML
    private void tblGuideMouseClicked(MouseEvent event) {
        if (tblGuide.getSelectionModel().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "The Table is Empty", ButtonType.OK).show();
        } else {
            GuideTM selectedGuide = tblGuide.getSelectionModel().getSelectedItem();
            txtSearchGuideID.setText(selectedGuide.getGidID());
            txtGidID.setText(selectedGuide.getGidID());
            txtFName.setText(selectedGuide.getF_Name());
            txtLName.setText(selectedGuide.getL_Name());
            txtNICNumber.setText(selectedGuide.getNIC());
            txtAddress.setText(selectedGuide.getAddress());
            txtYearofExp.setText(selectedGuide.getYOE() + "");
            txtMobileNo.setText(selectedGuide.getMobile_No());
            txtEmailAddress.setText(selectedGuide.getEmail());
            txtVehicle.setText(selectedGuide.getVehicle());
            rBtnYes.setSelected(selectedGuide.getGuide_Licence().equals("Yes"));
            rBtnNo.setSelected(selectedGuide.getGuide_Licence().equals("No"));
            tBtnEdit.setDisable(false);
            tabPaneGuide.getSelectionModel().select(1);
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
    private void btnBackToGuideMouseClicked(MouseEvent event) {
        Stage stage = (Stage) this.tabPaneGuide.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnBackMouseClicked(MouseEvent event) {
        tabPaneGuide.getSelectionModel().selectPrevious();
    }

    @FXML
    private void btnPrintMouseClicked(MouseEvent event) throws ClassNotFoundException, SQLException, JRException {
        if (txtSearchGuideID.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Search Guide First", ButtonType.OK).show();
        } else {
            InputStream is = this.getClass().getResourceAsStream("/lk/pankajatravel/reports/Guide.jasper");
            Connection connection = DBConnection.getInstance().getConnection();
            HashMap map = new HashMap();
            map.put("guideID", txtSearchGuideID.getText());
            JasperPrint print = JasperFillManager.fillReport(is, map, connection);
            JasperViewer.viewReport(print, false);
        }
    }

}
