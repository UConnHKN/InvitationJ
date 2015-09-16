/* 
 * The MIT License
 *
 * Copyright 2015 Jordan Force.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.betaomega.finalmaveninvitationfx;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.navigation.InvalidNavigationException;
import org.odftoolkit.simple.common.navigation.Selection;
import org.odftoolkit.simple.common.navigation.TextNavigation;
import org.odftoolkit.simple.common.navigation.TextSelection;
import org.odftoolkit.odfdom.doc.OdfTextDocument;

/**
 *
 * @author jforce
 */
public class Invitation {
    private TextDocument invitationTemplate;
    private File tempOutputFile;
    public Invitation(File invitationTemplate) throws BadTemplateException{
        try {
            TextDocument template = TextDocument.loadDocument(invitationTemplate);
            setInvitationTemplate(template);
        } catch (Exception ex) {
            throw new BadTemplateException();
        }
        try {
            this.tempOutputFile = File.createTempFile("thing", ".odt");
        } catch (IOException ex) {
            Logger.getLogger(Invitation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void closeLibreOffice(){
        //I'm putting in both commands, just in case the process name of libreoffice changes in the future.
        String command = "pkill soffice";
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
        } catch (IOException ex) {
           System.out.println("Something wrong with closing libreoffice");
        } catch (InterruptedException ex) {
            System.out.println("Something wrong with closing libreoffice");
        }   
        String secondCommand = "pkill libreoffice";
        try {
            Process p = Runtime.getRuntime().exec(secondCommand);
            p.waitFor();
        } catch (IOException ex) {
           
        } catch (InterruptedException ex) {
        }
    }
    /*
    
    THIS DOES NOT CHANGE THE ACTUAL TEMPLATE FILE! IT ONLY SAVES TO A TEMPORARY SPOT!
    */
    public void replaceKeywords(HashMap<String, String> keyToValueMappings){
        /*
            Here we will us the simple API to create a new instance of the TextNavigation class
            for each keyword, which will be used to find and replace the keywords with their values
        */
        System.out.println("Going to replace keywords: " + keyToValueMappings);
        for(String key : keyToValueMappings.keySet()){
            System.out.println("Key: " + key);
            
            String value = keyToValueMappings.get(key);
            System.out.println("Value: " + value);
            TextNavigation navigation = new TextNavigation(key, this.invitationTemplate);
            boolean hasNext = true;
            while(hasNext){
               
                TextSelection selection = (TextSelection) navigation.nextSelection();
                System.out.println("Replacing selection: " + selection);
                try {
                    selection.replaceWith(value);
                } catch (InvalidNavigationException ex) {
                    System.out.println("Couldn't do replacemen");
                    Logger.getLogger(Invitation.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NullPointerException ex ){
                    /*
                        There is a mapping in the spreadsheet that isn't used in the invitation.
                    
                    This could be an error on the user's part, in which case we'd want to let them know.
                    
                    We should ask them whether or not to continue.
                    
                    */
                    Scanner in = new Scanner(System.in);
                    System.out.print("Apparently the replacement: " + value + " Isn't used in the invitation. Continue (y/N)? ");
                    String line = in.nextLine();
                    if(!line.equals("y")){
                        //then the user did not want to continue
                        System.out.println("Aborting program");
                        this.invitationTemplate.close();
                        System.exit(1);
                    }
                    
                }
                hasNext = navigation.hasNext();
            }
        }
        try {
            this.invitationTemplate.save(this.tempOutputFile);
        } catch (Exception ex) {
            System.out.println("Couldn't save the changed odt");
            Logger.getLogger(Invitation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    
    We pass in the conversion command template, which specifies how to call libreoffice/openoffice. Using the UNO bridge seems unecessary.
    
    Our application will assist the user in creating the proper command.
    
    Example: libreoffice --headless --convert-to pdf template.odt
    
    This returns the location of the PDF
    */
    public String convertToPDF(String conversionCommandTemplate){
        this.closeLibreOffice();
        //our modified, temporary ODT file.
        String odtPath = this.tempOutputFile.getAbsolutePath();
        String name = this.tempOutputFile.getName();
        System.out.println("odfpath: " + odtPath);
       
        String command = conversionCommandTemplate + " " + odtPath;
        System.out.println("Command: "+ command);
        try {
            Process p = Runtime.getRuntime().exec(command);
            p.waitFor();
        } catch (IOException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
            System.exit(-1);
        } catch (InterruptedException ex) {
            Logger.getLogger(Invitation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name.replace(".odt", ".pdf");
    }
    /**
     * @return the invitationTemplate
     */
    private TextDocument getInvitationTemplate() {
        return invitationTemplate;
    }

    /**
     * @param invitationTemplate the invitationTemplate to set
     */
    private void setInvitationTemplate(TextDocument invitationTemplate) {
        this.invitationTemplate = invitationTemplate;
    }

}
