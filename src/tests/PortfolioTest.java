package tests;
import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.ErrorUtil;
import util.TestUtil;

public class PortfolioTest extends TestBase{
	
	@BeforeTest
	public void isSkipped(){
		if(TestUtil.isSkip("PortfolioLinkTest"))
			throw new SkipException("Runmode set to No");
	}
	
	@Test(dataProvider="getData")
	public void Portfolio(String SrNo,String Feature,String Use_Case,String Test_Case,String Provider,String Username, String Password, String testType ) throws Exception,InterruptedException, IOException{
		TestUtil.login(Username,Password,Use_Case);
		TestUtil.PortfolioLinkTest(SrNo,Feature,Use_Case,Test_Case,Provider,Username,Password,testType);
		}
		
	@DataProvider
	public Object[][] getData(){
		return TestUtil.getData("PortfolioLinkTest");
		
	}

}
