/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;
import java.io.UnsupportedEncodingException; 


/**
 *
 * @author Jordan Force
 */
public interface EmailProvider {
    public void send(String address, String subject, String body, String invitationName, String invitationPath, String invitationMimeType) throws InvitationNotFoundException, UnsupportedEncodingException;
}
