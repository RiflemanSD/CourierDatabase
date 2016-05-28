package org.riflemansd.courierdb.gui.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/** <h1>﻿MyExcelDocument</h1>
 * 
 * <p></p>
 * 
 * <p>Last Update: 01/02/2016</p>
 * <p>Author: <a href=https://github.com/RiflemanSD>RiflemanSD</a></p>
 * 
 * <p>Copyright © 2016 Sotiris Doudis | All rights reserved</p>
 * 
 * @version 1.0.7
 * @author RiflemanSD
 */
public class MyExcelDocument {
    private FileInputStream infile;
    private FileOutputStream outfile;
    private org.apache.poi.ss.usermodel.Workbook workbook;
    private File file;
    
    public MyExcelDocument(String fileName) {
        file = new File(fileName);
        //createFile();
        if (!file.exists()) this.createNewXLSX(fileName);
        
        try {
            infile = new FileInputStream(file);
            //outfile = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            workbook = WorkbookFactory.create(infile);
        } catch (IOException ex) {
            Logger.getLogger(MyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(MyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(MyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public MyExcelDocument(String fileName, boolean rewrite) {
        file = new File(fileName);
        //createFile();
        if (!file.exists()) this.createNewXLSX(fileName);
        
        try {
            infile = new FileInputStream(file);
            //outfile = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            workbook = WorkbookFactory.create(infile);
            if (rewrite) workbook.removeSheetAt(0);
        } catch (IOException ex) {
            Logger.getLogger(MyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(MyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(MyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void save() {
        try {
            outfile = new FileOutputStream(file);
            workbook.write(outfile);
            outfile.flush();
            outfile.close();
        } catch (IOException ex) {
            Logger.getLogger(MyExcelDocument.class.getName()).log(Level.SEVERE, null, ex);
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
    public void setDouble(int nsheet, int nrow, int ncolumn, double number, String formulla, int size, boolean bold) {
        org.apache.poi.ss.usermodel.Cell cell = getCell(nsheet, nrow, ncolumn);
        cell.setCellValue(number);
        Font font = workbook.createFont();
        if (bold) font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        CellStyle style = workbook.createCellStyle();
        font.setFontHeightInPoints((short)size);
        style.setFont(font);
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat(formulla));
        
        cell.setCellStyle(style);
    }
    
    public void setDouble(int nsheet, int nrow, int ncolumn, double number, String formulla) {
        org.apache.poi.ss.usermodel.Cell cell = getCell(nsheet, nrow, ncolumn);
        cell.setCellValue(number);
        
        CellStyle style = workbook.createCellStyle();
        DataFormat format = workbook.createDataFormat();
        
        style.setDataFormat(format.getFormat(formulla));
        cell.setCellStyle(style);
    }
    
    public void mergeCells(int nsheet, int firstRow, int lastRow, int firstCol, int lastCol) {
        Sheet sheet = workbook.getSheetAt(nsheet);
        
        sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
    }
    
    public void setRowHeight(int nsheet, int nrow, int height) {
        Sheet sheet = workbook.getSheetAt(nsheet);
        
        Row row = sheet.getRow(nrow);
        if (row == null) row = sheet.createRow(nrow);
        
        row.setHeightInPoints(height);
    }
    
    public void addBorders(int nsheet, int nrow, int ncol) {
        Cell cell = getCell(nsheet, nrow, ncol);
                
        // Style the cell with borders all around.
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cell.setCellStyle(style);
    }
    
    public Date getDate(int nsheet, int nrow, int ncolumn) {
        Date value;
        
        org.apache.poi.ss.usermodel.Cell cell = getCell(nsheet, nrow, ncolumn);
        value = cell.getDateCellValue();
        
        return value;
    }

    public void setDate(int nsheet, int nrow, int ncolumn, Date value) {
        org.apache.poi.ss.usermodel.Cell cell = getCell(nsheet, nrow, ncolumn);
        CellStyle style = workbook.createCellStyle();
        CreationHelper helper = workbook.getCreationHelper();
        style.setDataFormat(helper.createDataFormat().getFormat("dd/mm/yyy"));
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
    
    public void setHeader(int nsheet, int nrow, int ncolumn, String value) {
        org.apache.poi.ss.usermodel.Cell cell = getCell(nsheet, nrow, ncolumn);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)12);
        style.setFont(font);
        cell.setCellValue(value);
        cell.setCellStyle(style);
    }
    public void setHeader(int nsheet, int nrow, int ncolumn, String value, int fontSize, boolean bold) {
        org.apache.poi.ss.usermodel.Cell cell = getCell(nsheet, nrow, ncolumn);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        if (bold) font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short)fontSize);
        style.setFont(font);
        cell.setCellValue(value);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
        cell.setCellStyle(style);
    }
    
    public void setFormula(int nsheet, int nrow, int ncolumn, String value) {
        org.apache.poi.ss.usermodel.Cell cell = getCell(nsheet, nrow, ncolumn);
        CellStyle style = workbook.createCellStyle();
    }
}
