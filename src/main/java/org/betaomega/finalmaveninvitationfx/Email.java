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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.InputStream;
import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author jforce
 * 
 * Takes in the address to send to, the subject, body, and the invitation
 */
public class Email {
    private String address;
    private String subject;
    private String body;
    private String invitationPath;
    public Email(String address, String subject, String body, String invitationPath){
        this.address = address;
        this.subject = subject;
        this.body = body;
        this.invitationPath = invitationPath;
        
    }
    public void send(String fromAddress, String password, String invitationName, String invitationMimeType) throws InvitationNotFoundException
    
  {
        
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromAddress));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.address));
            message.setSubject(this.getSubject());
            System.out.println("Invitation path: " + this.invitationPath);
            this.invitationPath = "thing1524829128976613409.pdf";
            File invitation = new File(this.invitationPath);
            System.out.println("Invitation file: " + invitation.getAbsolutePath());
            System.out.println("Current dir: " + System.getProperty("user.dir"));
            System.out.println("Can read: " + invitation.canRead());
            System.out.println("Exists: " + invitation.exists());
            MimeBodyPart invitationMime = new MimeBodyPart();
            InputStream invitationDS = new FileInputStream(invitation);
            invitationMime.setFileName(invitationName);
            invitationMime.setContent(invitationDS, invitationMimeType);
            System.out.print("Set the content: " + invitationDS) ;
            Multipart messageMultiPart = new MimeMultipart();
            //now create the body
            MimeBodyPart mBody = new MimeBodyPart();
            mBody.setText(this.getBody());
            messageMultiPart.addBodyPart(mBody);
            messageMultiPart.addBodyPart(invitationMime);
            message.setContent(messageMultiPart);
            //Transport.send(message, fromAddress, password);
        }   catch (MessagingException ex) {
            System.out.println("messsaging exception");
            Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            System.out.println("message: " + ex.getMessage());
            //throw new InvitationNotFoundException();
        }
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

   
}
