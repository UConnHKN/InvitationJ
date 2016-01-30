/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;

import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author Jordan Force
 */
public class GmailProvider implements EmailProvider{
    private String fromAddress;
    private String password;
    private String fromName;
    public GmailProvider(String fromAddress, String fromName, String password){
        this.fromAddress = fromAddress;
        this.password = password;
        this.fromName = fromName;
    }
     public void send(String address, String subject, String body, String invitationName, String invitationPath, String invitationMimeType) throws InvitationNotFoundException, UnsupportedEncodingException
    
  {
        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(invitationPath);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setName(invitationName);
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator(this.fromAddress, this.password));
        email.setStartTLSEnabled(true);
        


        email.setTLS(true);
        try {
            email.addTo(address);
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            email.setFrom(this.fromAddress, this.fromName);
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        email.setSubject(subject);
        try {
            email.setMsg(body);
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            email.attach(attachment);
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
