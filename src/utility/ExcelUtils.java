package utility;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import jxl.*;
 
import java.io.FileOutputStream;
import org.apache.poi.xssf.usermodel.XSSFCell; 
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import datatable.Xls_Reader;

 
    public class ExcelUtils 
    { 
    	 public  static Xls_Reader output_xls =null;
         private static XSSFSheet ExcelWSheet;
         private static XSSFWorkbook ExcelWBook;
         private static XSSFCell Cell;
         private static XSSFRow Row;
     //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheet name as Arguments to this method
 
	     public static void setExcelFile(String Path,String SheetName) throws Exception
	     {
	       try {
	                // Open the Excel file
	    	   		System.out.println(Path + "^^^^^^^^^^^file path");
					FileInputStream ExcelFile = new FileInputStream(Path);
					// Access the required test data sheet
					ExcelWBook = new XSSFWorkbook(ExcelFile);
					ExcelWSheet = ExcelWBook.getSheet(SheetName);
					System.out.println(SheetName + "^^^^^^^^^^^sheat name ....");
					Xls_Reader x = new Xls_Reader("/home/manish/Desktop/bITs/workspace/selinium/DiasparkEnergy/src/testData/TestData.xlsx");
					x.getRowCount("/home/manish/Desktop/bITs/workspace/selinium/DiasparkEnergy/src/testData/TestData.xlsx");
					System.out.println("****");
					System.out.println("sheetcount ,,,,,,,,,,"+ x.getColumnCount(SheetName));
					
					x.addColumn(SheetName, "Actual");
					
					x.addColumn(SheetName, "Results_On");
					
					System.out.println("added new column");
		       } 
	       catch (Exception e)
		       {
		    	   	throw (e);
	 
		       }
	     }
	 
	     //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
	 
	      public static String getCellData(int RowNum, int ColNum) throws Exception
		      {
		       try
		       {
		           Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		           System.out.println(Cell + "row and col value ********************");
		           String CellData = Cell.getStringCellValue();
		           return CellData;
		           }
		       catch (Exception e)
		           {
		        	   	return"";
		           }
		 
		       }
	     	
	       public static int getRowCount(String SheetName)
	       {
	    		int iNumber=0;
	    		try
		    		{
		    			ExcelWSheet = ExcelWBook.getSheet(SheetName);
		    			iNumber=ExcelWSheet.getLastRowNum()+1;
		    		} 
	    		catch (Exception e)
	    			{}
	    		return iNumber;
	    		}
	 
	     //This method is to write in the Excel cell, Row num and Col num are the parameters
	  
	       	
	      public static void WriteData(String path) throws Exception
	      {
		    FileOutputStream fileOut;
			try 
				{
					fileOut = new FileOutputStream(path);
					ExcelWBook.write(fileOut);
					 fileOut.close();
				}
			catch (FileNotFoundException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      }
	       	
	       	
	     public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception
	     {
	       try
	       	   {
	            Row  = ExcelWSheet.getRow(RowNum);
				Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
				if(Cell == null) 
					{
						Cell = Row.createCell(ColNum);
				 		Cell.setCellValue(Result);	 
					} 
				else
					{
						Cell.setCellValue(Result);
						 System.out.println("In set method  ##################***");
					}
	          // Constant variables Test Data path and Test Data file name
	       	 	}
	       	catch(Exception e)
	       		{
	       			throw (e);
				}
	     }

	     
	     
}