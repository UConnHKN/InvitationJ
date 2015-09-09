/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;

/**
 *
 * @author Jordan Force
 */
public class EmailInfo {
    private String conversionCommandTemplate;
    private String fromAddress;
    private String password;
    private String invitationMimeType;
    private String inviteName;
    private String fromName;
    public EmailInfo(String conversionCommandTemplate, String fromAddress, String fromName, String password, String invitationMimeType, String inviteName){
        this.setConversionCommandTemplate(conversionCommandTemplate);
        this.setFromAddress(fromAddress);
        this.setPassword(password);
        this.setInvitationMimeType(invitationMimeType);
        this.setInviteName(inviteName);
        this.setFromName(fromName);
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
}
