package org.riflemansd.courierdb.gui.excel;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;


/** <h1>﻿ExcelExamplePOI</h1>
 * 
 * <p></p>
 * 
 * <p>Last Update: 29/01/2016</p>
 * <p>Author: <a href=https://github.com/RiflemanSD>RiflemanSD</a></p>
 * 
 * <p>Copyright © 2016 Sotiris Doudis | All rights reserved</p>
 * 
 * @version 1.0.7
 * @author RiflemanSD
 */
public class ExcelExamplePOI {
    

    public static void main(String[] args) throws Throwable {
        SXSSFWorkbook wb = new SXSSFWorkbook(1000); // keep 100 rows in memory, exceeding rows will be flushed to disk
        
        if (wb.getNumberOfSheets() == 0) {
            wb.createSheet("MySheet");
        }
        Sheet sh = wb.getSheetAt(0);
        Row  row = sh.createRow(3);
        
        for (int i = 0; i < 10; i++) {
            Cell cell = row.createCell(i);
            //String address = new CellReference(cell).formatAsString();
            cell.setCellValue("Καλημέρα " + i);
            //row.setHeightInPoints(50);
            //sh.setColumnWidth(5, 1200); //4, 33 pixels
            wb.getSheetAt(0).autoSizeColumn(i);
        }
        
        FileOutputStream out = new FileOutputStream("test.xlsx");
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
        
        Desktop.getDesktop().open(new File("test.xlsx"));
    }
}
