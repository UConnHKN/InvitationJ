/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author Jordan Force
 */
public class PersonSet extends HashSet {
    /*
        This constructor will mainly be used for dealing with person sets from the spreadsheet.
    
    */
    public PersonSet(HashMap<String, ColumnVariableMap> sheetToCVPMap, HashMap<String, String[][]> spreadsheetData){
        for(String sheetName : spreadsheetData.keySet()){
            System.out.println("Sheetname: " + sheetName);
            
            ColumnVariableMap cvp = sheetToCVPMap.get(sheetName);
            String[][] sheetData = spreadsheetData.get(sheetName);
            for(String[] row : sheetData){
                Person p = new Person(cvp, row);
                System.out.println("Person to add: " + p.toString());
                this.add(p);
            }
        }
        
    }
    /*
    
        Mainly used for our own records.
    */
    public PersonSet(String[] emails){
        for(String email : emails){
            Person p = new Person(email);
            this.add(p);
        }
    }
    
    public HashSet<String> getEmailAddresses(){
        Iterator<Person> iter = this.iterator();
        HashSet<String> addresses = new HashSet<String>();
        while(iter.hasNext()){
            addresses.add(((Person) iter.next()).getEmail());
        }
        return addresses;
    }
}
