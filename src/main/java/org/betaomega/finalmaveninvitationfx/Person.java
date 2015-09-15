/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jordan Force
 */
public class Person implements Comparable<Object>{
    private String email;
    private ColumnVariableMap columnMap;
    private String[] row;
    public Person(ColumnVariableMap columnMap, String[] row){
        this.setEmail(columnMap.getEmail(row));
        this.columnMap = columnMap;
        this.row = row;
    }
    public Person(String email){
        this.setEmail(email);
    }
    @Override
    public int compareTo(Object t) {
        if(!(t instanceof Person)){
            return 0;
        }else{
            Person p = (Person) t;
            return this.getEmail().compareTo(p.getEmail());
        }
    }
    
    @Override
    public boolean equals(Object t){
        if(!(t instanceof Person)){
            return false;
        }else{
            Person p = (Person) t;
            return this.getEmail().equals(p.getEmail());
        }
    }
    @Override
    public int hashCode(){
        return this.getEmail().hashCode();
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString(){
       return "email: " + this.getEmail(); 
    }
    
    public void sendEmail(String subjectTemplate, String bodyTemplate, String invitationPath, EmailInfo eInfo){
        System.out.println("Going to send email. Invitation path: " + invitationPath);
        HashMap<String, String> subjectVariables = this.columnMap.getSubjectVariableValues(this.row);
        
        for(String variableName : subjectVariables.keySet()){
            subjectTemplate = subjectTemplate.replaceAll(variableName, subjectVariables.get(variableName));
        }
        
        HashMap<String, String> bodyVariables = this.columnMap.getBodyVariableValues(this.row);
        
        for(String variableName : bodyVariables.keySet()){
            bodyTemplate = bodyTemplate.replaceAll(variableName, bodyVariables.get(variableName));
        }
        Invitation invite = null;
        try {
            invite = new Invitation(new File(invitationPath));
        } catch (BadTemplateException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Row in the person class: " + Arrays.toString(this.row));
        System.out.println("Invitation values: " + this.columnMap.getInvitationValues(this.row));
        invite.replaceKeywords(this.columnMap.getInvitationValues(this.row));
        String inviteLocation = invite.convertToPDF(eInfo.getConversionCommandTemplate());
        System.out.println("pdf location: " + inviteLocation);
        Email email = new Email(this.columnMap.getEmail(row), subjectTemplate, bodyTemplate, inviteLocation);
        System.out.println("From address: " + eInfo.getFromAddress());
        System.out.println("Password: " + eInfo.getPassword());
        try {
            email.send(eInfo.getFromAddress(), eInfo.getFromName(), eInfo.getPassword(), eInfo.getInviteName(), eInfo.getInvitationMimeType());
        } catch (InvitationNotFoundException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Person.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        
        
    }
}
