package util;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import tests.TestBase;
import utility.Constant;
import utility.ExcelUtils;

public class OperationalView_ProjectView extends TestBase{
	
	public static void OperationalView_ProjectView_dwnLinkTest(String SrNo,String Feature,String Use_Case,String Test_Case,String Provider,String Username,String Password,String testType)throws Exception, InterruptedException, IOException
	{
		System.out.println("SN NUMBER    =   "+SrNo);
		String parent1 = driver.getWindowHandle();
			
	        switch (SrNo) {
	        
	            case "1.0": 
	            	        TestUtil.PortfolioImageClick();
	    		            getObject("operational_view_projectView").click();
	    		            getObject("operational_project_downloadEquipData").click();
	    		            downloadLinkNextPage(Use_Case);
	                        break;
	            case "2.0": 
	            	        downloadLinkClick();
	            	        downloadLinkNotSelectData(Use_Case);
	            	        driver.switchTo().window(parent1);
	                        break;
	            case "3.0": 
	            	        downloadLinkClick();
	            	        downloadLinkSelectData(Use_Case);
	            	        driver.switchTo().window(parent1);
	            	        Thread.sleep(3000L);
	                        break;
	                        
	            case "4.0":
	            	System.out.println("In  4  th case");
	            	        getObject("image_click").click();
			                getObject("operational_view_projectView").click();
			                driver.findElement(By.xpath("html/body/form/div[4]/div[1]/div/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div/div[2]/div/div/div[1]/table/tbody/tr/td[2]/div/table/tbody/tr/td[2]/a")).click();
					        System.out.println("2nd link clicked");
					        downloadLinkNextPage(Use_Case);
					        break;
					        
	            case "5.0":
	            	System.out.println("In  5  th case");
	            	        getObject("image_click").click();
		                    getObject("operational_view_projectView").click();
		                    driver.findElement(By.xpath("html/body/form/div[4]/div[1]/div/div[2]/div[2]/div[3]/div[1]/div[1]/div[2]/div/div[2]/div/div/div[1]/table/tbody/tr/td[2]/div/table/tbody/tr/td[2]/a")).click();
				            System.out.println("2nd link clicked");
				            driver.findElement(By.xpath("html/body/form/div[4]/div[1]/div/div[2]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[3]/td/div/table/tbody/tr/td[2]/a")).click();
				            downloadLinkNextPage(Use_Case);
				            break;
	            	       
	            default:
           	                System.out.println("in default method");
                            break;
	                       }
	    
		}                        
	
	public static void downloadLinkClick() throws Exception
	  {
		getObject("image_click").click();
	    getObject("operational_view_projectView").click();
	    getObject("operational_project_downloadEquipData").click();
	  }
	

	public static void downloadLinkNextPage(String Use_Case) throws Exception
	  {
		
	    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,Use_Case);
		Constant.ACTUAL=driver.getTitle();
		TestUtil.excelWrite(Constant.WRITE_EXCEL_ROW,Constant.ACTUAL,Use_Case);
		Constant.WRITE_EXCEL_ROW++;
	  }
	public static void downloadLinkNotSelectData(String Use_Case) throws Exception
	  {
		
		 getObject("operational_project_componentData_viewBtn").click();
		 Thread.sleep(3000L);
		 ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,Use_Case);
		 Constant.ACTUAL = driver.switchTo().alert().getText();
		 driver.switchTo().alert().accept();
		 TestUtil.excelWrite(Constant.WRITE_EXCEL_ROW,Constant.ACTUAL,Use_Case);
		 Constant.WRITE_EXCEL_ROW++;	    
	  }
	

   public static void downloadLinkSelectData(String Use_Case) throws Exception
     {
	   
	   getObject("operational_project_componentData_chkImg1").click();
	   Thread.sleep(2000L);
	   getObject("operational_project_componentData_chkImg2").click();
	   Thread.sleep(2000L);
	   getObject("operational_project_componentData_chkImg3").click();
	   Thread.sleep(2000L);
	   getObject("operational_project_componentData_viewBtn").click();
	   TestUtil.childBrowser_move();
	   Constant.ACTUAL = driver.findElement(By.xpath("/html/body/form/div[3]/div/span")).getText();
	   getObject("operational_project_componentData_Report_DwnlodBtn").click();
	   getObject("operational_project_componentData_Report_FileBtn").click();
	   Thread.sleep(2000L);
	   //Execute Script To Download File.exe file to run AutoIt script. File location = C:\\Users\\sumit.kothari\\Desktop\\download.exe
	    Process pb=new ProcessBuilder("C:\\Users\\sumit.kothari\\Desktop\\ddd.exe","FireFox","C:\\Users\\sumit.kothari\\Desktop\\ddd.exe","10").start(); 
	    Thread.sleep(4000L);
	    // Runtime.getRuntime().exec("C:\\Users\\sumit.kothari\\Desktop\\ddd.exe");
	    //child browser closed    
	    driver.close();  
	    TestUtil.childBrowser_close();
	    ExcelUtils.setExcelFile(Constant.Path_TestData + Constant.File_TestData,Use_Case);
		TestUtil.excelWrite(Constant.WRITE_EXCEL_ROW,Constant.ACTUAL,Use_Case);
		Constant.WRITE_EXCEL_ROW++;
	// switch to parent
			//driver.switchTo().window(Constant.PARENT);
			
  }
	

}

