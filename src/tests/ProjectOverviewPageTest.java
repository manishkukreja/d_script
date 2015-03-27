package tests;

import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.TestUtil;

public class ProjectOverviewPageTest extends TestBase{
	@BeforeTest
	public void isSkipped(){
		if(TestUtil.isSkip("ProjectOverviewPageTest"))
			throw new SkipException("Project overview page run mode set to NO");
		
	}
	
	@Test(dataProvider="getData")
	public static void projectOverviewPage(String SrNo,String Feature,String Use_Case,String Test_Case,String Provider,String Username, String Password, String testType
) throws Exception {
//		if(testType.equalsIgnoreCase("True"))
//		{
			TestUtil.login(Username, Password, Use_Case);
			TestUtil.clickSideBar(Use_Case);
			if(Test_Case.equalsIgnoreCase("Project overview link working"))
				{ 
					TestUtil.projectOverviewPageElements();
				}
			if(Test_Case.equalsIgnoreCase("Project management link working"))
				{
					TestUtil.projectManagementPageElements();
				}
			if(Test_Case.equalsIgnoreCase("Project finance link working"))
				{
					TestUtil.projectFinancePageElements();
				}
			if(Test_Case.equalsIgnoreCase("Project operation link working"))
				{
					TestUtil.projectOperationPageElements();
				}
//		}
		
	}
	
	@DataProvider
	public Object[][] getData(){
		return TestUtil.getData("ProjectOverviewPageTest");
	}
	

}
