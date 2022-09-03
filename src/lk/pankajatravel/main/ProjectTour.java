/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.pankajatravel.main;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Ronila
 */
public class ProjectTour extends Application {
    
    private double x=0;
    private double y=0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/lk/pankajatravel/view/LogIn.fxml"));
        Scene scene = new Scene(root,Color.TRANSPARENT);
        stage.setTitle("Pankaja Travels");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        FadeTransition trans = new FadeTransition(Duration.millis(2000), root);
        trans.setFromValue(0.1);
        trans.setToValue(1.0);
        trans.play();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
