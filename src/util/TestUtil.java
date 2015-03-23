package util;
import static org.testng.AssertJUnit.assertTrue;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import junit.framework.Assert;
import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import tests.TestBase;
import utility.Constant;
import utility.ExcelUtils;
import utility.ExcelWrite;
import utility.Log;

public class TestUtil extends TestBase{

	public static WebElement ELEMENT;
	public static String ACTUAL;
	public static String records;
	private static Logger Log = Logger.getLogger(TestUtil.class.getName());
	public static int WRITE_EXCEL_ROW=1;public static int DBROWS=0;
	public static String PARENT=null;
	
	
	
	public static void dbConnect(String db_connect_string,String db_userid,String db_password,String queryString)throws Exception{
 		try {
	         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	         Connection conn = DriverManager.getConnection(db_connect_string,db_userid, db_password);
	         System.out.println("connected");
	         Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	         ResultSet rs = statement.executeQuery(queryString);
	         rs.last();
	         DBROWS = rs.getRow();
	         rs.beforeFirst();
	         System.out.println("+++++++++++Total no of rows in table is+++++++++  "+DBROWS);	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
				
	}
	public static void excelWrite(int WRITE_EXCEL_ROW,String ACTUAL,String Use_Case) throws Exception
	{
		ExcelWrite a=new ExcelWrite();
		if(ACTUAL.equalsIgnoreCase(ExcelUtils.getCellData(WRITE_EXCEL_ROW, 3)))
		   {
			    String excpected=ExcelUtils.getCellData(WRITE_EXCEL_ROW, 3);
		 	    a.compareResult(excpected,ACTUAL,WRITE_EXCEL_ROW,Use_Case);
		        ExcelUtils.WriteData(Constant.Path_TestData + Constant.File_TestData);
		   }
			else
			{
			    String excpected=ExcelUtils.getCellData(WRITE_EXCEL_ROW, 3);
		 	    a.compareResult(excpected,ACTUAL,WRITE_EXCEL_ROW,Use_Case);
		        ExcelUtils.WriteData(Constant.Path_TestData + Constant.File_TestData);
			}
	}

	
	public static void reportLinkWriteTest(String Use_Case)throws Exception{
		
	      TestUtil.report_moveToAnotherWindow();
	      ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,Use_Case);
	      excelWrite(WRITE_EXCEL_ROW, ACTUAL,Use_Case);  		
	      WRITE_EXCEL_ROW++;		
	}
	
	public static void login(String Username,String Password,String Use_Case) throws Exception{
		
			if(loggedIn){
			return ;
		}	
		    driver.get(config.getProperty("DiasparkEnergySignInURL"));	
			driver.manage().window().maximize();
			getObjectId("username_lbl").click();
		    getObject("username_text").sendKeys(Username);
	    	getObjectId("password_lbl").click();
	    	getObject("password_text").sendKeys(Password);
	    	getObjectId("login_btn").click();
	     	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,Use_Case);
	     	String ACTUAL;
			String title = driver.getTitle();
			
			if(Use_Case.equalsIgnoreCase("Login"))
			{	
			  if(title.equalsIgnoreCase("Diaspark Energy Login"))
			  {
	            ACTUAL=driver.findElement(By.xpath("//*[@id='lblErrorMessage']")) .getText();
	            excelWrite(WRITE_EXCEL_ROW, ACTUAL,Use_Case);
			  }
			  else
			  {	
				ACTUAL = title;
				excelWrite(WRITE_EXCEL_ROW, ACTUAL,Use_Case);
			  }
			   WRITE_EXCEL_ROW++;
			}
			driver.navigate().refresh();		
	}
	
	public static void PortfolioImageClick() throws Exception
	{
		getObject("image_click").click();
		Thread.sleep(1000L);
		System.out.println("PortfolioImageClick clicked");
		getObject("operational_view_click").click();
		Thread.sleep(1000L);
		
	}
	
    public static void logout() throws IOException, InterruptedException
    {		
			getObject("admin_click").click();
			getObject("signout").click();	
	}
	
	public static void startApplication() throws IOException, InterruptedException{
		driver.manage().window().maximize();
		Thread.sleep(2000L);
		Thread.sleep(2000L);
		getObject("start_application").click();
	}
	public static void download() throws Exception{
		
		
	}
public static void childBrowser_move() throws Exception{
	Set<String> windows = driver.getWindowHandles();
    Iterator<String> iter = windows.iterator();
    Constant.PARENT = iter.next();
// Switch to new window
    driver.switchTo().window(iter.next());
    }

public static void childBrowser_close() throws Exception{
    driver.close();           
// switch to parent
    driver.switchTo().window(Constant.PARENT);
}

	public static void report_moveToAnotherWindow() throws Exception
	{	
		Set<String> windows = driver.getWindowHandles();
	    Iterator<String> iter = windows.iterator();
	    String parent = iter.next();
	// Switch to new window
	    driver.switchTo().window(iter.next());
	    ACTUAL = getObject("report_title_text").getText();
	    driver.close();           
	// switch to parent
	    driver.switchTo().window(parent);
	}

	public static void moveToAnotherFrame(String abc) throws Exception{
		 // Store your parent window
		driver.switchTo().frame("FrameContent");  
		 driver.findElement(By.xpath("/html/body/form/div[3]/panel/div/div/table/tbody/tr[3]/td/fieldset/input[2]")).click();
		 Thread.sleep(1000L);
		 driver.switchTo().window(abc);
	}
	
	public static void quitBrowser() throws IOException{
		driver.manage().window().maximize();
		driver.quit();
	}
	public static void scrollDown()
	{
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250);");
	}
	
	private boolean isElementPresent(WebDriver driver, By by){
		try{
		driver.findElement(by);
		return true;
		}catch(NoSuchElementException e){
		return false;
		}
		}
	public static boolean isSkip(String testCase){
		for(int WRITE_EXCEL_ROW=2; WRITE_EXCEL_ROW<=datatable.getRowCount("Test Cases");WRITE_EXCEL_ROW++ ){
			System.out.println("in isSkip");
	    	  if(datatable.getCellData("Test Cases", "TCID", WRITE_EXCEL_ROW).equals(testCase)){
	    		  if(datatable.getCellData("Test Cases", "Runmode", WRITE_EXCEL_ROW).equals("Y"))
	    			  return false;
	    		  else
	    			  return true;
	    	  }
	    	  
	      }
		
		return false;
	}
	public static boolean isSkipSheetRow(String testType){
		System.out.println("in isSkipSheetRow method");
		for(int WRITE_EXCEL_ROW_Sheet=2; WRITE_EXCEL_ROW_Sheet<=datatable.getRowCount("OperationalView_ProjectViewTest");WRITE_EXCEL_ROW_Sheet++ ){
			System.out.println("in isSkipSheetRow");
	    	  if(datatable.getCellData("OperationalView_ProjectViewTest", "Test_Case", WRITE_EXCEL_ROW_Sheet).equals(testType)){
	    		  if(datatable.getCellData("OperationalView_ProjectViewTest", "testType", WRITE_EXCEL_ROW_Sheet).equals("TRUE"))
	    			  return false;
	    		  else
	    			  return true;
	    	  }
	    	  
	      }
		
		return false;
	}
	
	public static Object[][] getData(String sheetName){
		// return test data;
		// read test data from xls
		
		int rows=datatable.getRowCount(sheetName)-1;
		if(rows <=0){
			Object[][] testData =new Object[1][0];
			return testData;
			
		}
	    rows = datatable.getRowCount(sheetName);  // 3
		int cols = datatable.getColumnCount(sheetName);
		System.out.println("total rows -- "+ rows);
		System.out.println("total cols -- "+cols);
		Object data[][] = new Object[rows-1][cols];
		
		for( int rowNum = 2 ; rowNum <= rows ; rowNum++){
			
			for(int colNum=0 ; colNum< cols; colNum++){
				data[rowNum-2][colNum]=datatable.getCellData(sheetName, colNum, rowNum);
			}
		}
		
		return data;
		
		
	}
	
	// screenshots
	public static void takeScreenShot(String fileName) throws IOException{
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(scrFile, new File(config.getProperty("screenShotsPath")+"\\"+fileName+".jpg"));	   
	    
	}
	
	// Robot 
	public static void setClipboardData(String string) {
		//StringSelection is a class that can be used for copy and paste operations.
		   StringSelection stringSelection = new StringSelection(string);
		   Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
		}
	
	public static void uploadFile(String fileLocation) {
        try {
        	//Setting clipboard with file location
            setClipboardData(fileLocation);
            //native key strokes for CTRL, V and ENTER keys
            Robot robot = new Robot();
	
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception exp) {
        	exp.printStackTrace();
        }
    }
	
	
	// make zip of reports
	public static void zip(String filepath){
	 	try
	 	{
	 		File inFolder=new File(filepath);
	 		File outFolder=new File("Reports.zip");
	 		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
	 		BufferedInputStream in = null;
	 		byte[] data  = new byte[1000];
	 		String files[] = inFolder.list();
	 		for (int WRITE_EXCEL_ROW=0; WRITE_EXCEL_ROW<files.length; WRITE_EXCEL_ROW++)
	 		{
	 			in = new BufferedInputStream(new FileInputStream
	 			(inFolder.getPath() + "/" + files[WRITE_EXCEL_ROW]), 1000);  
	 			out.putNextEntry(new ZipEntry(files[WRITE_EXCEL_ROW])); 
	 			int count;
	 			while((count = in.read(data,0,1000)) != -1)
	 			{
	 				out.write(data, 0, count);
	 			}
	 			out.closeEntry();
  }
  out.flush();
  out.close();
	 	
}
  catch(Exception e)
  {
	  e.printStackTrace();
  } 
	 	
 }

}


