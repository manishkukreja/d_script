<<<<<<< HEAD
package tests;


import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.ErrorUtil;
import util.TestUtil;

public class LoginTest extends TestBase{
	@BeforeTest
	public void isSkipped(){
		if(TestUtil.isSkip("LoginTest"))
			throw new SkipException("Runmode set to No");
	}
	
	@Test(dataProvider="getData")
	public void login(String SrNo,String Feature,String Use_Case,String Test_Case,String Provider,String Username, String Password, String testType ) throws Exception,InterruptedException, IOException{
		
		TestUtil.login(Username,Password,Use_Case);
		
		TestUtil.logout();
			
		}
		
		
		
	
	@DataProvider
	public Object[][] getData(){
		return TestUtil.getData("LoginTest");
		
	}
}
=======
package tests;


import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.ErrorUtil;
import util.TestUtil;

public class LoginTest extends TestBase{
	
	public static String DEVKEY="8ef8a1f17390630e97188fde7bd0a4c4";
	public static String URL="http://10.20.20.18:81/testlink/login.php?";
	String testProject="Gmail";
	String testPlan="SampleTestPlan";
	String testCase="GmailLogin1";
	String build="SampleBuild";
	
	@BeforeTest
	public void isSkipped(){
		if(TestUtil.isSkip("LoginTest"))
			throw new SkipException("Runmode set to No");
	}
	
	@Test(dataProvider="getData")
	public void login(String SrNo,String Feature,String Use_Case,String Test_Case,String Provider,String Username, String Password,String testType ) throws Exception,InterruptedException, IOException{
		TestUtil.login(Username,Password,Use_Case);
		System.out.println(driver.getTitle()+ "this is the login method and page title --------------");
		TestUtil.logout();
			
		}
	
	@DataProvider
	public Object[][] getData(){
		return TestUtil.getData("LoginTest");
		
	}
}
>>>>>>> 414c2983c9de47ba534f12add8583362dbd8e871
