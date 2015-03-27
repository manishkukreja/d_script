package util;

import java.io.IOException;

import junit.framework.Test;

import org.openqa.selenium.By;

import tests.TestBase;
import utility.Constant;
import utility.ExcelUtils;

public class OperationalView_ReportView extends TestBase{
	public static void concateString(int firstElement,int lastElement,String Use_Case) throws Exception, InterruptedException, IOException
	{
		String s1 = new String("MainContent_reportList_reportTitle_");
		   String s2 = new String("_linkTitle_");
		   String s3 = s1+firstElement+s2+lastElement;
		   driver.findElement(By.id(s3)).click();
		   TestUtil.reportLinkWriteTest(Use_Case);
	}
	public static void OperationalView_ReportViewTest(String SrNo,String Feature,String Use_Case,String Test_Case,String Provider,String Username, String Password, String testType) throws Exception, InterruptedException, IOException
	{
		System.out.println("in my class+++++++++++++++++++++++++");
		TestUtil.PortfolioImageClick();
		//driver.findElement(By.xpath("html/body/form/div[4]/div[1]/div/div[1]/div/div/div/div[1]/a")).click();
		//driver.findElement(By.xpath("html/body/form/div[4]/div[1]/div/div[1]/div/div/div/div[3]/div/ul/li[1]/a/span/span[2]")).click();
		getObject("reports_view_click").click();
				//driver.findElement(By.xpath("html/body/form/div[4]/div[1]/div/div[1]/div/div/div/div[3]/div/ul/li[1]/div/ul/li[4]/a/span/span[2]"));
		Thread.sleep(1000L);
		 int exceptionReport_count_last=0;
		 int srecFinance_count_last=0;
		 int inventoryReport_count_last=0;
		 int project_count_last=1;
		 int alertReports_count_last=0;
		 //Integer.parseInt(config.getProperty("aletrListRows"));
		 
		 //driver.get(config.getProperty("DiasparkEnergySignInURL"));	
		while(alertReports_count_last < Integer.parseInt(config.getProperty("aletrListRows")))
		{
		   concateString(0, alertReports_count_last, Use_Case);
		   alertReports_count_last++;
		}
		///Exception Report
		while(exceptionReport_count_last < Integer.parseInt(config.getProperty("exceptionRows")))
		{
			concateString(1, exceptionReport_count_last, Use_Case);
			exceptionReport_count_last++;
		 }
		 ///////////INVENTORY REPORTS
		while(inventoryReport_count_last < Integer.parseInt(config.getProperty("inventoryRows")))
		{
			concateString(2, inventoryReport_count_last, Use_Case);
			inventoryReport_count_last++;
		 }
		/////SREC and Finance
        while(srecFinance_count_last < Integer.parseInt(config.getProperty("srecRows")))
        {                        
        	concateString(4, srecFinance_count_last, Use_Case);
        	srecFinance_count_last++;
		}
      /////////////// Project 
        while(project_count_last < Integer.parseInt(config.getProperty("projectRows")))
        {  
        	concateString(3, project_count_last, Use_Case);
        	project_count_last++;
        }
        /*
        	getObject("google_link").click();
        	TestUtil.childBrowser();
 		    Constant.ACTUAL = driver.getTitle();
 		    driver.close();           
 		   	driver.switchTo().window(Constant.PARENT);
 		    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,Use_Case);
 		    TestUtil.excelWrite(Constant.WRITE_EXCEL_ROW,Constant.ACTUAL,Use_Case);
 		  */ 					
 		}		
	}


