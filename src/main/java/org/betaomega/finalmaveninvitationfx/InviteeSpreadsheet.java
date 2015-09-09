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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.odfdom.pkg.*;
import org.odftoolkit.simple.table.Row;
import org.odftoolkit.simple.table.Table;

/**
 *
 * @author Jordan Force
 * 
 */
public class InviteeSpreadsheet {
    private SpreadsheetDocument spreadsheet;
    public InviteeSpreadsheet(File spreadsheet) throws BadSpreadsheetException{
        try {
            this.spreadsheet = SpreadsheetDocument.loadDocument(spreadsheet);
        } catch (Exception ex) {
            throw new BadSpreadsheetException();
        }
    }
    /*
        This should return the spreadsheet data from some of the sheets within the spreadsheets. 
    
        @param  mapSheetNamesToStartIndex Suppose the user wants to only extract data from some of the sheets, and only starting at a certain row within the sheet (indexed at 0, of course). 
        
        @return A HashMap mapping a sheet name to the data in it.
    */
    
    public HashMap<String, String[][]> getAllData(HashMap<String, Integer> mapSheetNamesToStartIndex){
        HashMap<String, String[][]> tempMap = new HashMap<String, String[][]>();
        System.out.println("HI!");
        for(String sheetName : mapSheetNamesToStartIndex.keySet()){
            System.out.println("Sheetname: " + sheetName);
            int startIndex = mapSheetNamesToStartIndex.get(sheetName);
            System.out.println("Startindex: " + String.valueOf(startIndex));
            Table sheet = this.spreadsheet.getSheetByName(sheetName);
            System.out.println("Past the sheet" + String.valueOf(sheet));
            int numColumns = sheet.getColumnCount();
            System.out.println("Num columns: " + String.valueOf(numColumns));
            List<Row> rows = sheet.getRowList();
            System.out.println("Rows: " + String.valueOf(rows));
            String[][] tableData = new String[rows.size() - startIndex][numColumns];
            //index in the array
            int arrayIterator = 0;
            //index in the table
            for(int i = startIndex; i < rows.size(); i++){
                System.out.println("i: " + String.valueOf(i));
                Row row = rows.get(i);
                System.out.println("Row: " + String.valueOf(row));
                //iterate across the row
                for(int cellIndex = 0; cellIndex < numColumns; cellIndex++){
                    tableData[arrayIterator][cellIndex] = row.getCellByIndex(cellIndex).getStringValue();
                }
                
                arrayIterator++;
            }
            System.out.println("Going to push to temp map");
            tempMap.put(sheetName, tableData);
            System.out.println("Pushed to temp map");
        }
        System.out.println("Goodbye!");
        return tempMap;
    }
    //gets first row of each sheet.
    public HashMap<String, String[]> getHeaders(){
        HashMap<String, String[]> headers = new HashMap<String, String[]>();
        Row header;
        String[] temp;
        for(String sheetName : this.getSheetNames()){
            Table sheet = this.spreadsheet.getSheetByName(sheetName);
            int numColumns = sheet.getColumnCount();
            List<Row> rows = sheet.getRowList();
            header = rows.get(0);
            temp = new String[numColumns];
            for(int i = 0; i < numColumns; i++){
                temp[i] = header.getCellByIndex(i).getStringValue();
            }
            headers.put(sheetName, temp);
        }
        return headers;
    }
    
    public String[] getSheetNames(){
        //first, get the number of sheets in the spreadsheet. We'll iterate from [0, 1, ..., n - 1], and get the name of each sheet.
        int numSheets = this.spreadsheet.getSheetCount();
        String[] sheetNames = new String[numSheets];
        for(int i = 0; i < numSheets; i++){
            sheetNames[i] = this.spreadsheet.getSheetByIndex(i).getTableName();
            
        }
        return sheetNames;
    } 
 
    public static ColumnVariableMap getHeaderInfo(String[] row){
        
    }
}
