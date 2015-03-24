<<<<<<< HEAD
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


=======
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import junit.framework.Assert;
//import jxl.Workbook;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import tests.TestBase;
import utility.Constant;
import utility.ExcelUtils;
import utility.ExcelWrite;


public class TestUtil extends TestBase{

	public static WebElement element;
	public static String actual;
	public static int i=1,COUNT_Business = 1;
	static String master = driver.getWindowHandle();
	
	public static void login(String Username,String Password,String Use_Case){
		// check if the user is logged in
		try{
		if(loggedIn){
			return ;
		}		driver.get(config.getProperty("DiasparkEnergySignInURL"));	
		
		System.out.println("login url click");		
		driver.manage().window().maximize();
	    driver.findElement(By.id("uidWaterMark")).click();
	    	if(driver.findElement(By.id("uidWaterMark")).isEnabled())
			{
	    		System.out.println("hi username");
				getObject("username_text").clear();
				getObject("username_text").sendKeys(Username);
				APPLICATION_LOGS.debug("SignUp:Entered First Name");
				
			}
			else
			{
				APPLICATION_LOGS.debug("Login:Unable to Enter Username");
			}
			
	    	
	    driver.findElement(By.id("txtWaterMark")).click();
	    	if(driver.findElement(By.id("txtWaterMark")).isEnabled())
	    	{
	    		System.out.println("hi password");
	    		getObject("password_text").clear();
				getObject("password_text").sendKeys(Password);    		
	    		System.out.println("put password");
	    		Thread.sleep(1000L);
	    	}
	    	else
	    	{
	    		System.out.println("SignUp:Unable to Enter Password");
	    	}
	    	
	    	driver.findElement(By.id("btnLogin")).click();
	    	
//	    	System.out.println("Page title is: " + driver.getTitle());
	    	
	    	ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"Login"); // file_path , file_name and sheet_name are passed 
			String actual;
		       //valid email address
				String title = driver.getTitle();
			if(Use_Case.equalsIgnoreCase("Login"))
				
			{	
				if(title.equalsIgnoreCase("Diaspark Energy Login"))
				{
					actual=driver.findElement(By.xpath("//*[@id='lblErrorMessage']")).getText();
					ExcelWrite a=new ExcelWrite();
		        	String expected=ExcelUtils.getCellData(i, 2);  // row_number and colum_number
		         	a.loginTestResult(expected,actual,i);
		            ExcelUtils.WriteData(Constant.Path_TestData + Constant.File_TestData);
				}
			    else
			    {
					System.out.println("***************** IN ELSE****************");					
					actual = title;
					ExcelWrite a=new ExcelWrite();				    
			        String excpected=ExcelUtils.getCellData(i, 2);
			        a.loginTestResult(excpected,actual,i);
			        ExcelUtils.WriteData(Constant.Path_TestData + Constant.File_TestData);
			     }
				i++;
			}
			if(driver.getTitle().equalsIgnoreCase("Diaspark Energy ErrorPage"))
			{
				System.out.println("Something is not right , please try after some time.@@@@@@@");
			}
			if(Use_Case.equalsIgnoreCase("Project_Overview_Page_test"))
			{
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
				
	}
	
	public static void PortfolioImageClick() throws Exception
	{
		
		driver.findElement(By.xpath("html/body/form/div[4]/div[1]/div/div[1]/div/div/div/div[3]/div/ul/li[1]/div/ul/li[5]/a/span/span[2]")).click();
		System.out.println("*******Reports View clicked");
		Thread.sleep(1000L);
	}
	
	public static void PortfolioLinkTest(String SrNo,String Feature,String Use_Case,String Test_Case,String Provider,String Username, String Password, String testType) throws Exception, InterruptedException, IOException{
		
	          TestUtil.PortfolioImageClick();
			  driver.findElement(By.xpath("html/body/form/div[4]/div[1]/div/div[2]/div[2]/div[2]/div/table/tbody/tr[1]/td[1]/div/div/div/div[2]/ul/li[1]/a[1]")).click();
		      TestUtil.moveToAnotherWindow();
		      ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,"PortfolioView");
		    	System.out.println("write file set");
				//String actual = driver.findElement(By.xpath("html/body/form/div[3]/div[1]/span")).getText();
				
			       
					//String title = driver.getTitle();
					System.out.println("********* USECASE****"+Use_Case);
					if(Use_Case.equalsIgnoreCase("Portfolio_OperationalViews_ReportView"))
					{	
					if(actual.equalsIgnoreCase("Report Title: Alerts List"))
					{
			       // actual=driver.findElement(By.xpath("//*[@id='viewMessagesPop']")) .getText();
			        ExcelWrite a=new ExcelWrite();
			    	
		        	String excpected=ExcelUtils.getCellData(i, 3);
		         	a.loginTestResult(excpected,actual,i);
		            ExcelUtils.WriteData(Constant.Path_TestData + Constant.File_TestData);
					}
					else
					{
						System.out.println("***************** IN ELSE****************");
						//actual = title;
						 ExcelWrite a=new ExcelWrite();
					    	
				        	String excpected=ExcelUtils.getCellData(i, 3);
				         	a.loginTestResult(excpected,actual,i);
				            ExcelUtils.WriteData(Constant.Path_TestData + Constant.File_TestData);
					}
			    	
					
					}
			
		
    	i++;
			
			System.out.println("value of i = "+i);
	}
				
	public static void logout() throws IOException, InterruptedException{
		
			System.out.println("in logout method");
			
			        
			        driver.findElement(By.xpath("html/body/form/div[3]/div/div[2]/ul/li[3]/a/span")).click();  
			        System.out.println("clicked on admin");
			        driver.findElement(By.xpath("html/body/form/div[3]/div/div[2]/ul/li[3]/div/div/div[3]/div[2]/a")).click();
			        System.out.println("clicked on signout");
		//driver.manage().window().maximize();
		//Thread.sleep(2000L);
		//TestUtil.takeScreenShot("logout");
		//Thread.sleep(2000L);
		//getObject("log_out").click();
			        
		
	}
	
	public static void startApplication() throws IOException, InterruptedException{
		driver.manage().window().maximize();
		Thread.sleep(2000L);
		Thread.sleep(2000L);
		getObject("start_application").click();
	}
	
	public static void moveToAnotherWindow() throws IOException, InterruptedException{
		Set<String> windows = driver.getWindowHandles();
	      Iterator<String> iter = windows.iterator();
	      System.out.println(driver.getWindowHandles().size());
	       String parent = iter.next();

	// Switch to new window
	      driver.switchTo().window(iter.next());
	      actual = driver.findElement(By.xpath("html/body/form/div[3]/div[1]/span")).getText();
	     System.out.println(actual);
	
	     System.out.println("*************************************");
	
	
	     driver.close();
	// switch to parent
			//driver.switchTo().window(parent);
			//driver.findElement(By.xpath("html/body/form/div[3]/div/div[2]/ul/li[3]/a/span")).click();
			//System.out.println("admin clicked");
			Thread.sleep(2000L);
			
			//driver.findElement(By.xpath("html/body/form/div[3]/div/div[2]/ul/li[3]/div/div/div[3]/div[2]/a")).click();
			//System.out.println("signout clicked");
			//Thread.sleep(2000L);
	
	
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
		System.out.println(testCase + "$&^#&^$&#^&$^#&^$");
		for(int i=2; i<=datatable.getRowCount("Test Cases");i++ ){
			System.out.println("in isSkip 1");
	    	  if(datatable.getCellData("Test Cases", "TCID", i).equals(testCase)){
	    		  if(datatable.getCellData("Test Cases", "Runmode", i).equals("Y"))
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
		System.out.println(sheetName + "============");
		int rows=datatable.getRowCount(sheetName)-1;
		System.out.println(rows+ "============");
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
	 		for (int i=0; i<files.length; i++)
	 		{
	 			in = new BufferedInputStream(new FileInputStream
	 			(inFolder.getPath() + "/" + files[i]), 1000);  
	 			out.putNextEntry(new ZipEntry(files[i])); 
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
	
	
//	method to click navigation sidebar followed by selecting project 
	public static void clickSideBar(String Use_Case)
	{
		driver.findElement(By.xpath(OR.getProperty("main_menu_icon"))).click();
		System.out.println("clicked left top menu");
//		String[] project_use_cases = new String[]{"Project_Overview_Page_test" , "Project_Management_Page_test","Project_Finance_Page_test","Project_Operation_Page_test"};	
		
		if(Use_Case.equalsIgnoreCase("inventory"))
			{
				driver.findElement(By.xpath(OR.getProperty("inventory_link"))).click(); // Inventory link call
				System.out.println(" Inventory clicked");
			}
		if(Use_Case.equalsIgnoreCase("Contract_Management_Page_Test"))
			{
				driver.findElement(By.xpath(OR.getProperty("o_and_m_link"))).click(); // O & M link call
				System.out.println(" O & M clicked");
			}
		
		if(Use_Case.equalsIgnoreCase("Project_Operation_Planned_Outage_Page_test") || (Use_Case.equalsIgnoreCase("Project_sub_menus")))
			{	
				driver.findElement(By.xpath(OR.getProperty("projects_link"))).click(); // Project link click call 
				System.out.println("clicked project");
			}
	}
	
	public static void projectOverviewPageElements()
	{
		driver.findElement(By.xpath(OR.getProperty("overview_link"))).click();
		System.out.println("Overview link clicked");
		
		WebElement page_heading = driver.findElement(By.xpath(OR.getProperty("overview_page_heading")));
		if(page_heading.getText().equalsIgnoreCase("Project overview"))
			{
				System.out.println("### Project Overview Test case passed ###");
			}
		else
			{
				System.out.println("#### Project Overview test Failed ####");
			}
			driver.findElement(By.xpath(OR.getProperty("projects_link"))).click();
			driver.findElement(By.xpath(OR.getProperty("main_menu_icon"))).click();
			driver.findElement(By.xpath(OR.getProperty("denergy_logo"))).click();
	}
	
	public static void projectManagementPageElements(){
		driver.findElement(By.xpath(OR.getProperty("project_management_link"))).click();
		System.out.println("Project Management link clicked");
		
		System.out.println("### Project Management Test case passed ###");
		driver.findElement(By.xpath(OR.getProperty("projects_link"))).click();
		driver.findElement(By.xpath(OR.getProperty("main_menu_icon"))).click();
		driver.findElement(By.xpath(OR.getProperty("denergy_logo"))).click();
	}
	
	public static void projectFinancePageElements(){
		driver.findElement(By.xpath(OR.getProperty("project_finance_link"))).click();
		System.out.println("Project Finance lick clicked");
	}
	
	public static void projectOperationPageElements()
	{
		driver.findElement(By.xpath(OR.getProperty("project_operations_link"))).click();
		System.out.println("Project Operation link clicked");
		
		WebElement page_heading = driver.findElement(By.xpath(OR.getProperty("project_operations_page_heading")));															
		if(page_heading.getText().equalsIgnoreCase("Planned outage detail"))
			{
				System.out.println("#### Project Operation test passed ####");
			}
		else
			{
				System.out.println("#### Project Operation test Failed ####");
			}
		driver.findElement(By.xpath(OR.getProperty("projects_link"))).click();
		driver.findElement(By.xpath(OR.getProperty("main_menu_icon"))).click();
		driver.findElement(By.xpath(OR.getProperty("denergy_logo"))).click();
	}
	
	public static void plannedOutagePageElements(String st_date, String end_date) throws InterruptedException{
		driver.findElement(By.xpath(OR.getProperty("project_operations_link"))).click();
		System.out.println("Project Operation link clicked");
		driver.findElement(By.xpath(OR.getProperty("st_date_select"))).click();									 
		WebElement s_date = driver.findElement(By.xpath(OR.getProperty("st_date_click")));
		s_date.click();
		s_date.sendKeys(st_date);
		WebElement s_time = driver.findElement(By.xpath(OR.getProperty("st_time_click")));
		s_time.click();
		s_time.sendKeys("12:00 AM");
		
		WebElement e_date = driver.findElement(By.xpath(OR.getProperty("end_date_click")));
		e_date.click();
		e_date.sendKeys(end_date);
		WebElement e_time = driver.findElement(By.xpath(OR.getProperty("end_time_click")));
		e_time.click();
		e_time.sendKeys("12:00 AM");
		
		WebElement reason_txt = driver.findElement(By.id(OR.getProperty("reason_txt")));
		reason_txt.click();
		
		int row = driver.findElements(By.xpath(OR.getProperty("table_rows_count"))).size();
		reason_txt.sendKeys("test_"+row+"");
		
	
		
		driver.findElement(By.id(OR.getProperty("save_btn"))).click();
		Thread.sleep(2000);
		
		if(driver.findElement(By.id("lblMessage")).getText().equalsIgnoreCase("Record Inserted Successfully"))
		{
			int row_count = driver.findElements(By.xpath(OR.getProperty("table_rows_count"))).size();
			System.out.println( "Row count ......." + row_count );
	//		edit flow
			driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div/div[3]/div/table/tbody/tr["+row_count+"]/td[5]/div/a")).click();
			WebElement updated_reason_txt = driver.findElement(By.id(OR.getProperty("reason_txt")));
			updated_reason_txt.click();
			updated_reason_txt.clear();
			updated_reason_txt.sendKeys("test_"+row+"_updated");
			driver.findElement(By.id(OR.getProperty("save_btn"))).click();
			WebElement text = driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div/div[3]/div/table/tbody/tr["+row_count+"]/td[4]"));
			if(text.getText().equalsIgnoreCase("test_"+row+"_updated"))
				{
					System.out.println("Planned outage detail request test case is passing");
					driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[2]/div[2]/div/div[3]/div/div/div/div/div[3]/div/table/tbody/tr["+row_count+"]/td[5]/div/a[2]")).click();
					System.out.println("###Clicked on Delete button$$$$$");
					TestUtil.isAlertPresent();
				}
			else
				{
					System.out.println("Something is not right........");
				}
		}
		
	}
	
	public static void isAlertPresent() throws InterruptedException
	{
	    try
	    {
	        Alert alert = driver.switchTo ().alert ();
	        Thread.sleep(2000);
	        //alert is present
	        System.out.println(alert.getText());
	        alert.accept(); 
	    }
	    catch ( NoAlertPresentException n)
	    {
	        //Alert isn't present
	        return; 
	    }
	}
	
	public static void moniteringPageElements(){

		driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div/div/div/div/div[3]/div/ul/li[3]/a/span/span[2]")).click();
		
		
	}
	
	public static void contractManagementPageElements() throws InterruptedException{
		driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div/div/div/div/div[3]/div/ul/li[6]/div/ul/li/a/span/span[2]")).click();
		WebElement contract_heading = driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[2]/div/div/div/h3"));
		if(contract_heading.getText().equals("Contract Master List"))
		{
			driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[2]/div/div[3]/div/div/div/div/div[2]/div/table/thead/tr/td/table/tbody/tr/td/a/span")).click();
			WebElement contract_ref_no = driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[2]/div[2]/div/div/div[2]/table/tbody/tr/td[2]/input"));
			contract_ref_no.click();
			int i = 50;
			contract_ref_no.sendKeys("10000"+i+"");
			
			Select customer_dropdown = new Select(driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_ddlCustomer")));
			customer_dropdown.selectByVisibleText("Advance Energy Inc.");
			WebElement contract_title = driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_UCContractGeneralInfo_txtContractName"));
			contract_title.click();
			contract_title.sendKeys("test_"+i+"");
			WebElement description = driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_UCContractGeneralInfo_txtDescription"));
			description.click();
			description.sendKeys("we have created new contract title test_"+i+"");
			WebElement btn = driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_UCContractGeneralInfo_btnSaveGenInfo"));
			btn.click();
			Thread.sleep(2000);
			
			// Project Page
			
//			driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_UCContractProjects_btnAddProject")).click();
//			TestUtil.switchFrame("RadGridProject_ctl00_ctl04_lnkSelect");
//			driver.switchTo().window(master);
//			driver.navigate().refresh();
//			Thread.sleep(2000);
//			
//			Contract tasks page
			driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_UCContractProjects_btnSaveGenInfo")).click(); // Projects
			Thread.sleep(2000);
			Select service_dropdown = new Select(driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_UCContractService_ddlService")));
			service_dropdown.selectByVisibleText("Panel Cleaning");
			Thread.sleep(4000);
			WebElement no_of_times = driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_UCContractService_txtNoOfTimes"));
			no_of_times.click();
			Thread.sleep(1000);
			no_of_times.sendKeys("10");
			Thread.sleep(2000);
			driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_UCContractService_btnAddService")).click();
			Thread.sleep(2000);
			TestUtil.skipDocClauseNotes();
//			ON main page
			WebElement ref_no = driver.findElement(By.id("MainContent_ContentPlaceHolderMainList_txtContractRefNo"));
			ref_no.click();
			ref_no.sendKeys("10000"+i+"");
			driver.findElement(By.id("MainContent_ContentPlaceHolderMainList_btnSearch")).click();
			Thread.sleep(2000);
			int row_count = driver.findElements(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[2]/div/div[3]/div/div/div/div/div[2]/div/table/tbody/tr")).size();
			
			for( int x = 0 ; x < row_count ; x=x+1 )
				{
					WebElement c_ref_no = driver.findElement(By.id("ctl00_ctl00_MainContent_ContentPlaceHolderMainList_RadGridContractMaster_ctl00__"+x+""));
					System.out.println("we are doing foo.......@@@@@@");
					System.out.println(row_count);
				}
			driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[2]/div/div[3]/div/div/div/div/div[2]/div/table/tbody/tr/td/a")).click();
			Thread.sleep(1000);
			WebElement new_contract_title = driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_UCContractGeneralInfo_txtContractName"));
			
			Thread.sleep(1500);
			new_contract_title.click();
			new_contract_title.sendKeys("updated_");
			driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_UCContractGeneralInfo_btnSaveGenInfo")).click();
			driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_UCContractProjects_btnSaveGenInfo")).click(); 
			Thread.sleep(1000);
			TestUtil.skipDocClauseNotes();
			WebElement name = driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[2]/div/div[3]/div/div/div/div/div[2]/div/table/tbody/tr/td[2]"));
			System.out.println(name.getText());
				
					driver.findElement(By.xpath("/html/body/form/div[4]/div/div/div[2]/div[2]/div/div[3]/div/div/div/div/div[2]/div/table/tbody/tr/td[9]/div/img[2]")).click();
					
					TestUtil.switchFrame("DPAsOn_dateInput");
					driver.switchTo().window(master);
					driver.findElement(By.id("MainContent_ContentPlaceHolderMainList_btnSearch")).click();
					System.out.println("Contract CRUD pass");
				
		}
		
	}
	
	
	public static void skipDocClauseNotes() throws InterruptedException
		{
			driver.findElement(By.id(OR.getProperty("btn_nxt_contract_task"))).click(); // Contracts tasks
			Thread.sleep(1500);
			driver.findElement(By.id(OR.getProperty("btn_nxt_document_upload"))).click(); // Documents
			Thread.sleep(1500);
			driver.findElement(By.id(OR.getProperty("btn_save_and_nxt_special_clause"))).click(); // Special Clause
			Thread.sleep(1500);
			driver.findElement(By.id(OR.getProperty("btn_save_and_close_special_note"))).click(); // Save and Close
		}
	
	
	public static void switchFrame(String abc) throws InterruptedException
		{
			driver.switchTo().frame("FrameContent");
			WebElement page_title = driver.findElement(By.className("page-title"));
			WebElement as_on = driver.findElement(By.id(abc));
			as_on.click();
			if(page_title.getText().equalsIgnoreCase("Add Project"))
			{
				Thread.sleep(1500);
				driver.findElement(By.id("btnDone")).click();
			}
			if(page_title.getText().equalsIgnoreCase("Delete Contract"))
			{
				as_on.sendKeys(todayDate());
				driver.findElement(By.id("btnDoneTermination")).click();
				Thread.sleep(2000);
			}
						
		}
	
	public static String todayDate()
		{
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			System.out.println(sdf.format(date));
			return sdf.format(date);
		}

	public static void alertReportSubscription(String test_scenario,String test_case, String expectedOutput) throws InterruptedException
	{
		System.out.println(test_scenario+ "----------------");
		int rep_size = 0;
		int alert_rep = 0; 
		int exception_rep = 1;
		int inventory_rep = 2;
		int project_rep = 3;
		int finance_rep = 4;
		
		TestUtil.getToReportView();
	
		System.out.println(test_scenario + ">>>>>>>>>>>>>>>>>>" );
		String master = driver.getWindowHandle();
		if(test_scenario.equalsIgnoreCase("alert reports"))
			{
				rep_size = driver.findElements(By.xpath(OR.getProperty("alert_reports_section"))).size();
	 			System.out.println(rep_size); //report block size
	 			TestUtil.subscriptionWindow(rep_size, alert_rep);
					
			}
		if(test_scenario.equalsIgnoreCase("exception reports"))
			{
				rep_size = driver.findElements(By.xpath(OR.getProperty("exception_reports_section"))).size();
				System.out.println("exception reports size ............" + rep_size);
				System.out.println(rep_size); //report block size
	 			TestUtil.subscriptionWindow(rep_size, exception_rep);
			}
		if(test_scenario.equalsIgnoreCase("Inventory reports"))
			{
				rep_size = driver.findElements(By.xpath(OR.getProperty("inventory_reports_section"))).size();
				System.out.println("inventory reports size ............" + rep_size);
	 			TestUtil.subscriptionWindow(rep_size, inventory_rep); 
			}
		if(test_scenario.equalsIgnoreCase("Delete alert reports"))
			{
				rep_size = driver.findElements(By.xpath(OR.getProperty("alert_reports_section"))).size();
				TestUtil.deleteSubscription(rep_size, alert_rep,expectedOutput);
			}
		
		driver.switchTo().window(master);
		
	}
	
	public static void getToReportView(){
		driver.findElement(By.xpath(OR.getProperty("main_menu_icon"))).click();
		driver.findElement(By.xpath(OR.getProperty("operational_view_link"))).click();
		driver.findElement(By.xpath(OR.getProperty("reports_view_link"))).click();
	
	}

	public static void subscriptionWindow(int rep_size , int num){
		for(int i=0;i < rep_size;i=i+1)
				{
					Boolean del_icon_isPresent = driver.findElements(By.id("MainContent_reportList_reportTitle_"+num+"_btnDeleteSubscribe_"+i+"")).size() > 0;
					System.out.println(del_icon_isPresent + "..........");
					
					if(del_icon_isPresent)
						{
							driver.findElement(By.id("MainContent_reportList_reportTitle_"+num+"_btnEditSubscribe_"+i+"")).click();
							driver.switchTo().frame("FrameContent");
							if(driver.findElement(By.id("optDaily")).isSelected())
								{
									driver.findElement(By.id("optWeekly")).click();
								}
							else if(driver.findElement(By.id("optWeekly")).isSelected())
								{
									driver.findElement(By.id("optMonthly")).click();
								}
							else
								{
									driver.findElement(By.id("optDaily")).click();
								}	
						}
					else
						{
							driver.findElement(By.id("MainContent_reportList_reportTitle_"+num+"_btnAddSubscribe_"+i+"")).click();
							driver.switchTo().frame("FrameContent");
							
						}
					driver.findElement(By.id("FormOperationBottom_btnSave")).click();
					
				}
		}

	public static void deleteSubscription(int rep_size , int num, String expectedOutput) throws InterruptedException{
		System.out.println(rep_size + "*************" + num);
		for(int i=0;i < rep_size;i=i+1)
		{
			Boolean del_icon_isPresent = driver.findElement(By.id("MainContent_reportList_reportTitle_"+num+"_btnDeleteSubscribe_"+i+"")).isDisplayed();
			
			if(del_icon_isPresent)
				{
					driver.findElement(By.id("MainContent_reportList_reportTitle_"+num+"_btnDeleteSubscribe_"+i+"")).click();
					TestUtil.isAlertPresent();
					if(driver.findElement(By.id("lblMessage")).getText().equalsIgnoreCase(expectedOutput))
						{
							System.out.println("Delete test case passed successfully");
						}
					else
						{
							System.out.println("Something is wrong");
						}
						
				}
			
		}
		
	}
	
	public static void newInventory(String equipmentType, String manufacturer,
			String model, String quantity, String purchaseDate) throws InterruptedException{
		driver.findElement(By.xpath(OR.getProperty("details_link"))).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath(OR.getProperty("purchase_new_link"))).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_ddlEquipmentType")).click();
		driver.findElement(By.xpath(OR.getProperty("equip_type_dropdown"))).click();
		driver.findElement(By.xpath(OR.getProperty("select_equip_type"))).click();
		Thread.sleep(1000);
		
		driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_ddlManufacturer")).click();
		driver.findElement(By.xpath(OR.getProperty("manufacturer_dropdown"))).click();
		driver.findElement(By.xpath(OR.getProperty("select_manufacturer"))).click();
		Thread.sleep(1000);

		driver.findElement(By.xpath(OR.getProperty("model_dropdown"))).click();
		driver.findElement(By.xpath(OR.getProperty("select_model"))).click();

		driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_txtQuantity")).click();
		driver.findElement(By.id("MainContent_ContentPlaceHolderMainDetail_txtQuantity")).sendKeys("5");
		driver.findElement(By.id("ctl00_ctl00_MainContent_ContentPlaceHolderMainDetail_dtPurchaseDate_dateInput")).click();
		driver.findElement(By.id("ctl00_ctl00_MainContent_ContentPlaceHolderMainDetail_dtPurchaseDate_dateInput")).sendKeys(purchaseDate);
		
		driver.findElement(By.id(OR.getProperty("btn_save"))).click();
		WebElement success_txt = driver.findElement(By.id("lblMessage"));
		if(success_txt.getText().equalsIgnoreCase("record inserted successfully"))
			{
				System.out.println("badhai hoo ....");
			}
		
		
		
		
	}
	
}


>>>>>>> 414c2983c9de47ba534f12add8583362dbd8e871
