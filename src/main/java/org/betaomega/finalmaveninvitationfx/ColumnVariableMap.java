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

import java.util.HashMap;

/**
 *
 * @author jforce
 * 
 * For each application (Subject, body, and invitation), it should map the keyword to the corresponding column.
 */
public class ColumnVariableMap {
    
    private int emailIndex;
    private HashMap<String, Integer> subjectMap;
    private HashMap<String, Integer> bodyMap;
    private HashMap<String, Integer> invitationMap;
    public ColumnVariableMap(int emailIndex, HashMap<String, Integer> subjectMap, HashMap<String, Integer> bodyMap, HashMap<String, Integer> invitationMap){
        this.setEmailIndex(emailIndex);
        this.setBodyMap(bodyMap);
        this.setSubjectMap(subjectMap);
        this.setInvitationMap(invitationMap);
    }

    public String getEmail(String[] row){
        return row[this.getEmailIndex()];
    }
    @Override
    public String toString(){
        return "CVM with email index: " + String.valueOf(this.emailIndex) + ", subject map: " + String.valueOf(this.subjectMap) + ", body map: " + String.valueOf(this.bodyMap) + ", invitation map: " + String.valueOf(this.invitationMap);
    }
    public HashMap<String, String> getSubjectVariableValues(String[] row){
        HashMap<String, String> values = new HashMap<String, String>();
        for(String key : this.subjectMap.keySet()){
            int index = (int) this.subjectMap.get(key);
            values.put(key, row[index]);
        }
        return values;
    }
    public HashMap<String, String> getBodyVariableValues(String[] row){
        HashMap<String, String> values = new HashMap<String, String>();
        for(String key : this.bodyMap.keySet()){
            int index = (int) this.bodyMap.get(key);
            values.put(key, row[index]);
        }
        return values;
    }
    public HashMap<String, String> getInvitationValues(String[] row){
        HashMap<String, String> values = new HashMap<String, String>();
        System.out.println("keyset of invitation map: " + this.invitationMap.keySet());
        for(String key : this.invitationMap.keySet()){
            System.out.println("Key in cvm value getter: " + key);
            int index = (int) this.invitationMap.get(key);
            System.out.println("Index in cvm value getter: " + String.valueOf(index));
            values.put(key, row[index]);
        }
        return values;
    }
    /**
     * @return the emailIndex
     */
    public int getEmailIndex() {
        return emailIndex;
    }

    /**
     * @param emailIndex the emailIndex to set
     */
    public void setEmailIndex(int emailIndex) {
        this.emailIndex = emailIndex;
    }

    /**
     * @return the subjectMap
     */
    public HashMap<String, Integer> getSubjectMap() {
        return subjectMap;
    }

    /**
     * @param subjectMap the subjectMap to set
     */
    public void setSubjectMap(HashMap<String, Integer> subjectMap) {
        this.subjectMap = subjectMap;
    }

    /**
     * @return the bodyMap
     */
    public HashMap<String, Integer> getBodyMap() {
        return bodyMap;
    }

    /**
     * @param bodyMap the bodyMap to set
     */
    public void setBodyMap(HashMap<String, Integer> bodyMap) {
        this.bodyMap = bodyMap;
    }

    /**
     * @return the invitationMap
     */
    public HashMap<String, Integer> getInvitationMap() {
        return invitationMap;
    }

    /**
     * @param invitationMap the invitationMap to set
     */
    public void setInvitationMap(HashMap<String, Integer> invitationMap) {
        this.invitationMap = invitationMap;
    }
}
