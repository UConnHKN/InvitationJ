/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;
import  microsoft.exchange.webservices.data.autodiscover.*;
/**
 *
 * @author Jordan Force
 */
public class ExchangeRedirection implements IAutodiscoverRedirectionUrl{
    
    public boolean autodiscoverRedirectionUrlValidationCallback(String redirection) {
        
            return redirection.toLowerCase().startsWith("https://");
            
    }
    
    
}
