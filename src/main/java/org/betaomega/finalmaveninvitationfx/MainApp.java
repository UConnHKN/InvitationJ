/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
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
     * 
     * so app.jar spreadsheet.ods invitation.odt body.txt subject.txt existingEmails.csv
     */
    public static void main(String[] args) {
        if(args.length != 5){
            System.out.println("Usage: app.jar spreadsheet.ods invitation.odt body.txt subject.txt existingEmails.csv. \n Don't forget, that if you have a name with a space in it, you need to escape the space (such as 'Jordan\\ Force'");
        }else{
            
            Scanner scan = new Scanner(System.in);
            System.out.println("Exchange (1), Gmail (2) or other email provider(3)?");
            int option = 0;
            try{
                option = scan.nextInt();
            }catch(java.util.InputMismatchException m){
               System.out.println("Invalid input. Enter in 1, 2, or 3.");
               main(args);
            }
            
            if(option != 1 && option != 2 && option != 3){
                System.out.println("Invalid option. Enter in 1, 2 or 3.");
                main(args);
            }else{
            
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
                HashMap<String, ColumnVariableMap> columnVarMaps = spreadsheet.getHeaderSpecifiedCVMs();
                System.out.println("Column var maps: " + columnVarMaps);
                HashMap<String, String[][]> data = spreadsheet.getAllData(startingRow);
                PersonSet persons = new PersonSet(columnVarMaps, data);
                System.out.println("Persons: " + persons);
                String subjectText = "";
                try {
                    subjectText = new String(Files.readAllBytes(Paths.get(args[3])), StandardCharsets.UTF_8);
                } catch (IOException ex) {
                    System.out.println("Could not read text from subject file. Check your spelling.");
                }
                String bodyText = "";
                try {
                    bodyText = new String(Files.readAllBytes(Paths.get(args[2])), StandardCharsets.UTF_8);
                } catch (IOException ex) {
                    System.out.println("Could not read text from body file. Check your spelling.");
                }
                if(args.length == 8){


                    String existingMembersText = null;
                     try {
                        existingMembersText = new String(Files.readAllBytes(Paths.get(args[7])), StandardCharsets.UTF_8);
                    } catch (IOException ex) {
                        System.out.println("Could not read text from existing members file. Check your spelling.");
                    }
                    String[] emails = existingMembersText.split(",");
                    PersonSet existingPersons = new PersonSet(emails);
                    persons.removeAll(existingPersons);

                }
                System.out.println("arg 4: " + args[4]);
                System.out.println("Arg 5: " + args[5]);


                EmailInfo eInfo = new EmailInfo("libreoffice --headless --convert-to pdf ", option, "application/pdf", "invitation.pdf");
                persons.sendEmails(subjectText, bodyText, args[1], eInfo);
            }
        }
    }
    
}
