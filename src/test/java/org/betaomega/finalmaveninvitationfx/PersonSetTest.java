/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.betaomega.finalmaveninvitationfx;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jordan Force
 */
public class PersonSetTest {
    
    public PersonSetTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testEmailDifference() {
        String[] emails = new String[3];
        emails[0] = "hello@gmail.com";
        emails[1] = "jordan.force@uconn.edu";
        emails[2] = "clancy@uconn.edu";
        PersonSet existing = new PersonSet(emails);
        //there are two sheets, each with a couple email addresses, including the three in the emails array. I want to make sure it removes all of these properly.
        HashMap<String, Integer> subjectMap = new HashMap<String, Integer>();
        subjectMap.put("NAME", 1);
        HashMap<String, Integer> bodyMap = new HashMap<String, Integer>();
        bodyMap.put("NAME", 1);
        bodyMap.put("HELLO", 2);
        HashMap<String, Integer> invitationMap = new HashMap<String, Integer>();
        invitationMap.put("NAME", 1);
        invitationMap.put("BYE", 3);
        
        ColumnVariableMap cvpOne = new ColumnVariableMap(0, subjectMap, bodyMap, invitationMap);
        
        
        HashMap<String, Integer> subjectMapTwo = new HashMap<String, Integer>();
        subjectMapTwo.put("NAME", 2);
        HashMap<String, Integer> bodyMapTwo = new HashMap<String, Integer>();
        bodyMapTwo.put("NAME", 2);
        bodyMapTwo.put("HELLO", 3);
        HashMap<String, Integer> invitationMapTwo = new HashMap<String, Integer>();
        invitationMapTwo.put("NAME", 2);
        invitationMapTwo.put("BYE", 4);
        
        ColumnVariableMap cvpTwo = new ColumnVariableMap(1, subjectMapTwo, bodyMapTwo, invitationMapTwo);
        HashMap<String, ColumnVariableMap> sheetCVPMap = new HashMap<String, ColumnVariableMap>();
        sheetCVPMap.put("first", cvpOne);
        sheetCVPMap.put("second", cvpTwo);
        //now, we must construct the data for the two sheets.
        String[][] firstSheetTest = new String[4][2];
        firstSheetTest[0][0] = "a@gmail.com";
        firstSheetTest[0][1] = "1";
        firstSheetTest[1][0] = "b@gmail.com";
        firstSheetTest[1][1] = "2";
        firstSheetTest[2][0] = "jordan.force@uconn.edu";
        firstSheetTest[2][1] = "1";
        firstSheetTest[3][0] = "hello@gmail.com";
        firstSheetTest[3][1] = "3";
        
        String[][] secondSheetTest = new String[4][2];
        secondSheetTest[0][0] = "1";
        secondSheetTest[0][1] = "clancy@uconn.edu";
        secondSheetTest[1][0] = "2";
        secondSheetTest[1][1] = "d@gmail.com";
        secondSheetTest[2][0] = "3";
        secondSheetTest[2][1] = "e@uconn.edu";
        secondSheetTest[3][0] = "4";
        secondSheetTest[3][1] = "f@gmail.com";
        HashMap<String, String[][]> data = new HashMap<String, String[][]>();
        data.put("first", firstSheetTest);
        data.put("second", secondSheetTest);
        PersonSet pSet = new PersonSet(sheetCVPMap, data);
        pSet.removeAll(existing);
        HashSet<String> pSetEmails = pSet.getEmailAddresses();

        HashSet<String> answerEmails = new HashSet<String>();
        answerEmails.add("a@gmail.com");
        answerEmails.add("b@gmail.com");
        answerEmails.add("d@gmail.com");
        answerEmails.add("e@uconn.edu");
        answerEmails.add("f@gmail.com");
        System.out.println(String.valueOf(answerEmails.equals(pSetEmails)));
        assertTrue(answerEmails.size() == pSetEmails.size());
        
        Iterator<String> iter = pSetEmails.iterator();
        while(iter.hasNext()){
            String next = iter.next();
            
            assertTrue(answerEmails.contains(next));
            
        }
        //Make sure they are of same size.        
        // TODO review the generated test code and remove the default call to fail.
    } 
    
}
