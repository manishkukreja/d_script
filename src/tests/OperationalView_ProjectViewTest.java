package tests;
import java.io.IOException;

import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import datatable.Xls_Reader;

import util.ErrorUtil;
import util.TestUtil;
import util.OperationalView_ProjectView;

public class OperationalView_ProjectViewTest extends TestBase{
	
	
		@BeforeTest
		public void isSkipped(){
			
			if(TestUtil.isSkip("OperationalView_ProjectViewTest"))
				throw new SkipException("Runmode set to No");
			
		}

		
		@Test(dataProvider="getData")
		 public void OperationalView_ProjectView(String SrNo,String Feature,String Use_Case,String Test_Case,String Provider,String Username, String Password, String testType ) throws Exception,InterruptedException, IOException
		 {
			    TestUtil.login(Username,Password,Use_Case);
	            OperationalView_ProjectView.OperationalView_ProjectView_dwnLinkTest(SrNo, Feature, Use_Case, Test_Case, Provider, Username, Password, testType);
	            TestUtil.logout();
	          
	           
		}
		
		@DataProvider
		public Object[][] getData(){
			return TestUtil.getData("OperationalView_ProjectViewTest");
			
		}

	}


