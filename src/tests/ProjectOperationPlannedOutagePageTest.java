package tests;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.TestUtil;

public class ProjectOperationPlannedOutagePageTest extends TestBase{

	@BeforeTest
	public void isSkipped(){
		if(TestUtil.isSkip("PlannedOutagePageTest"))
			throw new SkipException("Project operation page outage page run mode set to NO");
	}	
	@Test(dataProvider="getData")
	public static void plannedOutagePage(String SrNo,String Feature,String Use_Case,String Test_Case,String Provider,String Username, String Password, String testType, String St_date ,String End_date) throws InterruptedException,Exception  {
		/*if(testType.equalsIgnoreCase("True"))	
			{*/
				TestUtil.login(Username, Password, Use_Case);
				TestUtil.clickSideBar(Use_Case);
				TestUtil.plannedOutagePageElements(St_date,End_date,Use_Case);
				TestUtil.logout();
			//}
	}			
				
	@DataProvider
	public Object[][] getData(){
		return TestUtil.getData("PlannedOutagePageTest");
	}
				

	

}
