/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
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


public class MainApp {
    /*
    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initFirstStep();
    }
    */

    /**
     * Initializes the root layout.
     */
/*
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
*/
    

    /*
    public Stage getPrimaryStage() {
        return primaryStage;
    }
*/
    /**
     * @param args the command line arguments
     * 
     * First spreadsheet
     * Then invitation file
     * then path to text to body of email
     * then path to text of subject of email
     * 
     * so app.jar spreadsheet.ods invitation.odt body.txt subject.txt
     */
    public static void main(String[] args) {
        InviteeSpreadsheet spreadsheet = null;
        try {
            //launch(args);
            System.out.println("Hello!");
            spreadsheet = new InviteeSpreadsheet(new File(args[0]));
            
        } catch (BadSpreadsheetException ex) {
            System.out.println("Not a valid spreadsheet. Check your spelling");
        }
        HashMap<String, String[]> headers = spreadsheet.getHeaders();
        HashMap<String, Integer> startingRow = new HashMap<String, Integer>();
        for(String sheetName : headers.keySet()){
            
            
            startingRow.put(sheetName, 1);
        }
        //now, for each of the 
    }
    
}
