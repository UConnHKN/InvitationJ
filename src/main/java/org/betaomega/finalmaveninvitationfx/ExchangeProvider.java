/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;

import java.io.UnsupportedEncodingException;
import microsoft.exchange.webservices.data.core.*;
import microsoft.exchange.webservices.data.credential.*;


/**
 * 
 *
 * @author Jordan Force
 */
public class ExchangeProvider implements EmailProvider{
    private ExchangeService service;
    public ExchangeProvider(String fromAddress, String fromName, String password){
        ExchangeService service = new ExchangeService();
        ExchangeCredentials credentials = new WebCredentials(fromAddress, password);
        service.setCredentials(credentials);
        try{
        service.autodiscoverUrl(fromAddress, new ExchangeRedirection());
        }catch(Exception e){
            System.out.println("Exception from the autodiscoverUrl method call. Exiting.");
            System.exit(1);
        }
        this.service = service;
    }
    
    public void send(String address, String subject, String body, String invitationName, String invitationPath, String invitationMimeType) throws InvitationNotFoundException, UnsupportedEncodingException{
      
    }

}
