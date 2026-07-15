package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.AndroidBase;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderPage;

public class TC010_RetailerSearchTest extends AndroidBase{
	@Test(dataProvider = "getData")

	public void Order(HashMap<String, String> input) throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setuserID(input.get("userID"));
		loginPage.setuserPin(input.get("password"));
		loginPage.clickLogin();

		HomePage homePage = new HomePage(driver);
		homePage.clickSyncNow();
		homePage.clickSyncNow();
		Thread.sleep(5000);
		homePage.clickOrder();
		Thread.sleep(5000);
		
		OrderPage orderPage = new OrderPage(driver);
		orderPage.enterSearchRetailer(input.get("retailerName"));
		Thread.sleep(5000);
		boolean result = orderPage.enterSearchRetailerBoolean(input.get("retailerName"));
		Assert.assertTrue(result, "Retailer selection failed for: " + input.get("retailerName"));
	}

	
	@DataProvider
	public Object[][] getData() throws IOException {

	    String basePath = System.getProperty("user.dir") + "//src//test//java//testData//";

	    List<HashMap<String, String>> data = getMergedJsonData(
	            basePath + "loginData.json",
	            basePath + "testData.json",
	            basePath + "cash&CashProduct.json"
	            
	    );

	    Object[][] arr = new Object[data.size()][1];
	    for (int i = 0; i < data.size(); i++) {
	        arr[i][0] = data.get(i);
	    }

	    return arr;
	}
	
	
}
