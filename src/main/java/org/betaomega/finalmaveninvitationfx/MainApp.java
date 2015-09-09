/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Jordan Force
 */
public class MainApp extends Application {
    
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initFirstStep();
    }

    /**
     * Initializes the root layout.
     */
    public void initFirstStep() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader().load(getClass().getResource("fxml/0.fxml"));
            SplitPane firstStep = (SplitPane) loader.load();

            Scene scene = new Scene(firstStep);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        System.out.println("Hello!");
    }
    
}
