package tests;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.TestUtil;

public class ContractManagementPageTest extends TestBase {

	@BeforeTest
	public void isSkipped(){
		if(TestUtil.isSkip("ProjectOperationPlannedOutagePageTest"))
			throw new SkipException("Project operation page outage page run mode set to NO");
	}	
	@Test(dataProvider="getData")
	public static void contractManagementTest(String SrNo,String Feature,String Use_Case,String Test_Case,String Provider,String Username, String Password, String testType) throws Exception  {
				TestUtil.login(Username, Password, Use_Case);
				TestUtil.clickSideBar(Use_Case);
				//if(testType.equalsIgnoreCase("True"))
				//{	
					TestUtil.contractManagementPageElements();
				//}
				/*else
				{
					System.out.println("Please set the Test type to True");
				}*/
	}			
				
	@DataProvider
	public Object[][] getData(){
		return TestUtil.getData("ContractManagementPageTest");
	}
}
