/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;
import java.util.Scanner;

/**
 *
 * @author Jordan Force
 */
public class EmailInfo {
    private String conversionCommandTemplate;
    private String fromAddress;
    private String invitationMimeType;
    private String inviteName;
    private String fromName;
    private EmailProvider provider;
    public EmailInfo(String conversionCommandTemplate, int option, String invitationMimeType, String inviteName){
        this.setConversionCommandTemplate(conversionCommandTemplate);
        
        if(option == 1){
            //Then exchange
            System.out.println("Exchange is not currently supported. It will be in the future, though.");
            System.exit(1);
        }else if(option == 2){
            System.out.println("Enter in email address: ");
            Scanner scan = new Scanner(System.in);
            String email = scan.next();
            this.setFromAddress(email);
            System.out.println("Enter in password: ");
            String password = scan.next();
            System.out.println("Enter in name: ");
            String fromName = scan.next();
            this.setFromName(fromName);
            EmailProvider gmail = new GmailProvider(email, fromName, password);
       }else{
            System.out.println("Other email systems are not supported at this time.");
            System.exit(1);
        }
        
        this.setInvitationMimeType(invitationMimeType);
        this.setInviteName(inviteName);
    }
    /**
     * @return the conversionCommandTemplate
     */
    public String getConversionCommandTemplate() {
        return conversionCommandTemplate;
    }

    /**
     * @param conversionCommandTemplate the conversionCommandTemplate to set
     */
    public void setConversionCommandTemplate(String conversionCommandTemplate) {
        this.conversionCommandTemplate = conversionCommandTemplate;
    }

    /**
     * @return the fromAddress
     */
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * @param fromAddress the fromAddress to set
     */
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    
  
    /**
     * @return the invitationMimeType
     */
    public String getInvitationMimeType() {
        return invitationMimeType;
    }

    /**
     * @param invitationMimeType the invitationMimeType to set
     */
    public void setInvitationMimeType(String invitationMimeType) {
        this.invitationMimeType = invitationMimeType;
    }

    /**
     * @return the inviteName
     */
    public String getInviteName() {
        return inviteName;
    }

    /**
     * @param inviteName the inviteName to set
     */
    public void setInviteName(String inviteName) {
        this.inviteName = inviteName;
    }

    /**
     * @return the fromName
     */
    public String getFromName() {
        return fromName;
    }

    /**
     * @param fromName the fromName to set
     */
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    /**
     * @return the provider
     */
    public EmailProvider getProvider() {
        return provider;
    }

    /**
     * @param provider the provider to set
     */
    public void setProvider(EmailProvider provider) {
        this.provider = provider;
    }
}
