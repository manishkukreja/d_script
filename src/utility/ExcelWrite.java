<<<<<<< HEAD
package utility;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import util.TestUtil;

import com.opera.core.systems.scope.services.Exec;

import datatable.Xls_Reader;




public class ExcelWrite {
	
	private static WebDriver driver = null;
	Xls_Reader reader = null;
    public void compareResult(String excpected,String actual,int i,String Use_Case) throws Exception {
    
	    
	    	// Open the Excel file
    		System.out.println("Expected value from excel write file=>"+excpected);
	        System.out.println("Actual from excel write file=>"+actual);
	     
            Date d1 = new Date();
            //output file column count
         System.out.println("row number   +   "+ExcelUtils.getRowCount(Use_Case));
         System.out.println("col number   +   "+ExcelUtils.getColumnCount(Use_Case));
         if(i==1)
         {
	       ExcelUtils.setCellData("Result_"+d1.toString(), 0,ExcelUtils.getColumnCount(Use_Case));
	      	if(actual.equals(excpected))
			     {
		     	    ExcelUtils.setCellData(Constant.KEYWORD_PASS, i, ExcelUtils.getColumnCount(Use_Case));
		     	    System.out.println("Test is pass"); 
		     	    ExcelUtils.setCellData(actual, i, 2);
		         }
		    	 else
		    	 {
		    		 ExcelUtils.setCellData(actual, i, 2);
		    		 ExcelUtils.setCellData(Constant.KEYWORD_FAIL, i,ExcelUtils.getColumnCount(Use_Case));
		    		 System.out.println("Test is fail");
		    		 //TestUtil.logout();   
		    	 }
         }
         else
         {
        	 int j=ExcelUtils.getColumnCount(Use_Case)-1;
        	// ExcelUtils.setCellData("Result_"+d1.toString(), 0,ExcelUtils.getColumnCount(Use_Case));
 	      	if(actual.equals(excpected))
 			     {
 		     	    ExcelUtils.setCellData(Constant.KEYWORD_PASS, i, j);
 		     	    System.out.println("Test is pass"); 
 		     	    ExcelUtils.setCellData(actual, i, 2);
 		         }
 		    	 else
 		    	 {
 		    		 ExcelUtils.setCellData(actual, i, 2);
 		    		 ExcelUtils.setCellData(Constant.KEYWORD_FAIL, i,j);
 		    		 System.out.println("Test is fail");
 		    		 //TestUtil.logout();   
 		    	 }
         }
   	}
	       
}


    


=======
package utility;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import util.TestUtil;

import com.opera.core.systems.scope.services.Exec;

import datatable.Xls_Reader;




public class ExcelWrite {
	
	private static WebDriver driver = null;
	static Xls_Reader y = new Xls_Reader("/home/manish/Desktop/bITs/workspace/selinium/DiasparkEnergy/src/testData/TestData.xlsx");
    static int z = y.getColumnCount("Login");
	
    public void loginTestResult(String excpected,String actual,int i) throws Exception {
    
	    
	    	// Open the Excel file
    		System.out.println("Expected value from excel write file=>"+excpected);
	        System.out.println("Actual from excel write file=>"+actual);
	     
//	        
	       // if(Use_Case.equalsIgnoreCase("PersonalInformation"))
		    	 if(actual.equals(excpected))
			     { 
		    		ExcelUtils.setCellData(actual, i, z);
		    		ExcelUtils.setCellData("Passss", i, z+1);
//		     	    ExcelUtils.setCellData("Passss", i, 4);
		     	    System.out.println("Test is pass"); 
//		     	    ExcelUtils.setCellData(actual, i, 3);
		     	    
		     	
		         }
		    	 else
		    	 {
		    		 ExcelUtils.setCellData(actual, i, z);
			    	 ExcelUtils.setCellData("Failsssss", i, z+1);
//		    		 ExcelUtils.setCellData(actual, i, 2);
//		    		 ExcelUtils.setCellData("Failssssss", i,4);
		    		 System.out.println("Test is fail");
		    		 TestUtil.logout();
		    		   
		    	 }
    }
    
}
    
  
    	
	    
>>>>>>> 414c2983c9de47ba534f12add8583362dbd8e871
