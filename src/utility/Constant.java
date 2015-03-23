package utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import util.TestUtil;

public class Constant {
	
     public static final String Path_TestData = "D:\\DiasparkEnergy\\DiasparkEnergy\\src\\testData\\";
     
     public static final String File_TestData = "TestData.xlsx";

	   	public static final String KEYWORD_FAIL = "FAIL";
	   	public static final String KEYWORD_PASS = "PASS";
	   	
	   	public static WebElement ELEMENT;
		public static String ACTUAL;
		
		public static String records;
		private static Logger Log = Logger.getLogger(TestUtil.class.getName());
		public static int WRITE_EXCEL_ROW=1;public static int DBROWS=0;
		public static String PARENT=null;
	
}
