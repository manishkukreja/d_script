package tests;


import org.openqa.selenium.By;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import util.TestUtil;



public class PurchaseNewInventoryTest extends TestBase {
	
	@BeforeTest
	public void isSkipped(){
		if(TestUtil.isSkip("PurchaseNewInventoryTest"))
			throw new SkipException("Alert report subscrition test run mode set to NO");
	}	
	@Test(dataProvider="getData")
	public static void newInvertoryPurchase(String SrNo,String Feature,String Use_Case,String Scenario,String Test_Case,String Provider,String Username, String Password, String testType,String EquipmentType,String Manufacturer,String Model, String Quantity, String PurchaseDate) throws Exception  {
		TestUtil.login(Username, Password, Use_Case);
		TestUtil.clickSideBar(Use_Case);
		
		//if(Scenario.equalsIgnoreCase("inventory purchase"))
			//{
				TestUtil.newInventory(EquipmentType, Manufacturer, Model,  Quantity,  PurchaseDate);
		//	}
		
	}			
		
	@DataProvider
	public Object[][] getData(){
		return TestUtil.getData("PurchaseNewInventoryTest");
	}


}

