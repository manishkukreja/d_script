package tests;


import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.TestUtil;


public class SubscribeAlertReportTest extends TestBase {

	@BeforeTest
	public void isSkipped(){
		if(TestUtil.isSkip("SubscribeAlertReportTest"))
			throw new SkipException("Alert report subscrition test run mode set to NO");
	}	
	@Test(dataProvider="getData")
	public static void alertSubscriptionTest(String SrNo,String Feature,String Use_Case,String Scenario,String Test_Case,String Provider,String Username, String Password, String testType, String expectedOutput) throws Exception  
	{
		TestUtil.login(Username, Password, Use_Case);
		if(testType.equalsIgnoreCase("True"))
			{										
				TestUtil.alertReportSubscription(Scenario , Test_Case, expectedOutput);
			}
		else
			{
				System.out.println("Please change the Test Type to start the execution");
			}
	}			
				
	@DataProvider
	public Object[][] getData(){
		return TestUtil.getData("SubscribeAlertReportTest");
	}

}
