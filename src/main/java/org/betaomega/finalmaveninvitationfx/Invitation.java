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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.odftoolkit.simple.TextDocument;
import org.odftoolkit.simple.common.navigation.InvalidNavigationException;
import org.odftoolkit.simple.common.navigation.Selection;
import org.odftoolkit.simple.common.navigation.TextNavigation;
import org.odftoolkit.simple.common.navigation.TextSelection;
import org.odftoolkit.odfdom.converter.pdf.PdfConverter;
import org.odftoolkit.odfdom.converter.pdf.PdfOptions;
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
    
    /*
    
    THIS DOES NOT CHANGE THE ACTUAL TEMPLATE FILE! IT ONLY SAVES TO A TEMPORARY SPOT!
    */
    public void replaceKeywords(HashMap<String, String> keyToValueMappings){
        /*
            Here we will us the simple API to create a new instance of the TextNavigation class
            for each keyword, which will be used to find and replace the keywords with their values
        */
        for(String key : keyToValueMappings.keySet()){
            String value = keyToValueMappings.get(key);
            TextNavigation navigation = new TextNavigation(key, this.invitationTemplate);
            boolean hasNext = true;
            while(hasNext){
                TextSelection selection = (TextSelection) navigation.nextSelection();
                try {
                    selection.replaceWith(value);
                } catch (InvalidNavigationException ex) {
                    Logger.getLogger(Invitation.class.getName()).log(Level.SEVERE, null, ex);
                }
                hasNext = navigation.hasNext();
            }
        }
        try {
            this.invitationTemplate.save(this.tempOutputFile);
        } catch (Exception ex) {
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
        //our modified, temporary ODT file.
        String odtPath = this.tempOutputFile.getAbsolutePath();
        String command = conversionCommandTemplate + " " + odtPath;
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            System.out.println("Exception occurred:");
            ex.printStackTrace();
            System.exit(-1);
        }
        return odtPath.replace(".odt", ".pdf");
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
