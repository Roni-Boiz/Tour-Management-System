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
import lk.pankajatravel.bo.custom.GuideBO;
import lk.pankajatravel.db.DBConnection;
import lk.pankajatravel.dto.GuideDTO;
import lk.pankajatravel.tableModel.GuideTM;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 * FXML Controller class
 *
 * @author Ronila
 */
public class ViewAllGuidesController implements Initializable {

    GuideBO bo = (GuideBO) BOFactory.getInstace().getBO(BOFactory.BOTypes.GUIDE);

    @FXML
    private TableView<GuideTM> tblGuide;

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

        try {
            loadAll();
        } catch (Exception ex) {
            Logger.getLogger(ViewAllGuidesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAll() throws SQLException, Exception {
        try {
            ArrayList<GuideTM> allGuide = new ArrayList<>();
            ArrayList<GuideDTO> all = bo.getAllGuide();
            for (GuideDTO g : all) {
                allGuide.add(new GuideTM(g.getGidID(), g.getfName(), g.getlName(), g.getNIC(), g.getAddress(), g.getYOE(), g.getMobileNo(), g.getEmail(), g.getVehicle(), g.getGuideLicence()));
            }
            tblGuide.setItems(FXCollections.observableList(allGuide));
        } catch (NullPointerException ex) {
            new Alert(Alert.AlertType.ERROR, "No Match Found", ButtonType.OK).showAndWait();
        }
    }

    @FXML
    private void btnPrintMouseClicked(MouseEvent event) {
        try {
            GuideTM selectedItem = tblGuide.getSelectionModel().getSelectedItem();
            InputStream is = this.getClass().getResourceAsStream("/lk/pankajatravel/reports/Guide.jasper");
             Connection connection = DBConnection.getInstance().getConnection();
                HashMap map = new HashMap();
                map.put("guideID", selectedItem.getGidID());
                JasperPrint print = JasperFillManager.fillReport(is, map, connection);
                JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Select Guide from the table", ButtonType.OK).show();
        }
    }
}
