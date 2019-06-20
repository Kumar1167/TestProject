package Test.Project;

import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Readexcel {

public static void main(String []args){

try {

// Specify the file path which you want to create or write

File src=new File(System.getProperty("user.dir") + "\\testData.xls");

// Load the file

FileInputStream fis=new FileInputStream(src);

// load the workbook

HSSFWorkbook wb=new HSSFWorkbook(fis);

// get the sheet which you want to modify or create

HSSFSheet sh1= wb.getSheetAt(0);

// getRow specify which row we want to read and getCell which column

/*System.out.println(sh1.getRow(0).getCell(0).getStringCellValue());

System.out.println(sh1.getRow(0).getCell(1).getStringCellValue());

System.out.println(sh1.getRow(1).getCell(0).getStringCellValue());

System.out.println(sh1.getRow(1).getCell(1).getStringCellValue());

System.out.println(sh1.getRow(2).getCell(0).getStringCellValue());

System.out.println(sh1.getRow(2).getCell(1).getStringCellValue());
*/

// here createCell will create column

// and setCellvalue will set the value

sh1.getRow(0).createCell(0).setCellValue("2.41.0");

sh1.getRow(0).createCell(1).setCellValue("2.5");

sh1.getRow(0).createCell(2).setCellValue("2.39");


// here we need to specify where you want to save file

FileOutputStream fout=new FileOutputStream(new File(System.getProperty("user.dir") + "\\filename.xls"));


// finally write content 

wb.write(fout);

// close the file

fout.close();


} catch (Exception e) {

System.out.println(e.getMessage());

 }

}

}
