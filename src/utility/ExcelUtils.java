package utility;
 
         import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
 
         import java.io.FileOutputStream;
 
         import org.apache.poi.xssf.usermodel.XSSFCell;
 
         import org.apache.poi.xssf.usermodel.XSSFRow;
 
         import org.apache.poi.xssf.usermodel.XSSFSheet;
 
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



 
    public class ExcelUtils {
 
         private static XSSFSheet ExcelWSheet;
 
         private static XSSFWorkbook ExcelWBook;
 
         private static XSSFCell Cell;
 
         private static XSSFRow Row;
 
     //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
 
     public static void setExcelFile(String Path,String SheetName) throws Exception {
 
       try {
 
                // Open the Excel file
 
				FileInputStream ExcelFile = new FileInputStream(Path);
				 
				// Access the required test data sheet
				 
				ExcelWBook = new XSSFWorkbook(ExcelFile);
				
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
			
	       } catch (Exception e){
	 
	    	   	throw (e);
 
}
 
}
 
     //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
 
         public static String getCellData(int RowNum, int ColNum) throws Exception{
 
       try{
 
           Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
 
           String CellData = Cell.getStringCellValue();
 
           return CellData;
 
           }catch (Exception e){
 
        	   	return"";
 
           }
 
    }
     	
       	public static int getRowCount(String SheetName){
    		int iNumber=0;
    		try {
    			ExcelWSheet = ExcelWBook.getSheet(SheetName);
    			iNumber=ExcelWSheet.getLastRowNum()+1;
    		} catch (Exception e){
    		
    			}
    		return iNumber;
    		}
 
       	public static int getColumnCount(String SheetName){
    		int iNumber=0;
    		try {
    			ExcelWSheet = ExcelWBook.getSheet(SheetName);
    			iNumber=ExcelWSheet.getRow(1).getLastCellNum();
    		
    		} catch (Exception e){
    		
    			}
    		return iNumber;
    		}
       	
     //This method is to write in the Excel cell, Row num and Col num are the parameters
  
       	
      public static void WriteData(String path) throws Exception{
   
	    FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream(path);
			ExcelWBook.write(fileOut);
			 fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
      }
       	
       	
     public static void setCellData(String Result,  int RowNum, int ColNum) throws Exception	{
 
       try{
 
            Row  = ExcelWSheet.getRow(RowNum);
			
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			 
			if (Cell == null) {
			 
	 		Cell = Row.createCell(ColNum);
			 
			Cell.setCellValue(Result);
			 
			} else {
			 
			Cell.setCellValue(Result);
			 System.out.println("In set method  ##################***");
			}
 
          // Constant variables Test Data path and Test Data file name
		
        
			 }catch(Exception e){
			 
			throw (e);
 
			}
 
     }
     
}