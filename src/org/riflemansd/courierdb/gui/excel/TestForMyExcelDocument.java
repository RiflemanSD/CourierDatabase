
package org.riflemansd.courierdb.gui.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/** <h1>﻿TestForMyExcelDocument</h1>
 * 
 * <p></p>
 * 
 * <p>Last Update: 30/01/2016</p>
 * <p>Author: <a href=https://github.com/RiflemanSD>RiflemanSD</a></p>
 * 
 * <p>Copyright © 2016 Sotiris Doudis | All rights reserved</p>
 * 
 * @version 1.0.7
 * @author RiflemanSD
 */
public class TestForMyExcelDocument {
    private FileInputStream infile;
    private FileOutputStream outfile;
    private org.apache.poi.ss.usermodel.Workbook workbook;
    private File file;
    
    public TestForMyExcelDocument(String fileName) {
        file = new File(fileName);
        //createFile();
        if (!file.exists()) this.createNewXLSX(fileName);
        
        try {
            infile = new FileInputStream(file);
            //outfile = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestForMyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            workbook = WorkbookFactory.create(infile);
        } catch (IOException ex) {
            Logger.getLogger(TestForMyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(TestForMyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(TestForMyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
    // XSSFWorkbook, File
  OPCPackage pkg = OPCPackage.open(new File("file.xlsx"));
  XSSFWorkbook wb = new XSSFWorkbook(pkg);
  ....
  pkg.close();
    
    Workbook wb = WorkbookFactory.create(new File("MyExcel.xls"));
    */
    public void createNewXLSX(String fileName) {
        Workbook wb = new XSSFWorkbook();
        FileOutputStream fileOut;
        try {
            fileOut = new FileOutputStream(fileName);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestForMyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestForMyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void save() {
        try {
            outfile = new FileOutputStream(file);
            workbook.write(outfile);
            outfile.close();
        } catch (IOException ex) {
            Logger.getLogger(TestForMyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void autoFillColumhWidth(int nsheet, int ncolumn) {
        Sheet sheet = workbook.getSheetAt(nsheet);
        sheet.autoSizeColumn(ncolumn); 
    }
    
    /**
    row.createCell(1).setCellValue(1.2);
    row.createCell(2).setCellValue(
         createHelper.createRichTextString("This is a string"));
    row.createCell(3).setCellValue(true);
    */
    public Cell getCell(int nsheet, int nrow, int ncolumn) {
        Sheet sheet;
        if (workbook.getNumberOfSheets() == 0) {
            sheet = workbook.createSheet();
        }
        else {
            sheet = workbook.getSheetAt(nsheet);
            if (sheet == null) sheet = workbook.createSheet();
        }
        
        Row row = sheet.getRow(nrow);
        if (row == null) row = sheet.createRow(nrow);
        
        Cell cell = row.getCell(ncolumn);
        if (cell == null) cell = row.createCell(ncolumn);
        
        return cell;
        /*
        // Create a new font and alter it.
        Font font = wb.createFont();
        font.setFontHeightInPoints((short)24);
        font.setFontName("Courier New");
        font.setItalic(true);
        font.setStrikeout(true);

        // Fonts are set into a style so create a new one to use.
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        */
    }
    
    /*
    Sheet sheet = wb.getSheetAt(0);
    for (Row row : sheet) {
      for (Cell cell : row) {
        // Do something here
      }
    }
    
    
    */
    public String getString(int nsheet, int nrow, int ncolumn) {
        String value = "";
        
        org.apache.poi.ss.usermodel.Cell cell = getCell(nsheet, nrow, ncolumn);
        value = cell.getRichStringCellValue().getString();
        
        return value;
    }
    public double getDouble(int nsheet, int nrow, int ncolumn) {
        double value = 0;
        
        org.apache.poi.ss.usermodel.Cell cell = getCell(nsheet, nrow, ncolumn);
        value = cell.getNumericCellValue();
        
        return value;
    }

    public void setString(int nsheet, int nrow, int ncolumn, String value) {
        org.apache.poi.ss.usermodel.Cell cell = getCell(nsheet, nrow, ncolumn);
        cell.setCellValue(value);
    }
    public void setDouble(int nsheet, int nrow, int ncolumn, double number) {
        org.apache.poi.ss.usermodel.Cell cell = getCell(nsheet, nrow, ncolumn);
        cell.setCellValue(number);
    }
}
