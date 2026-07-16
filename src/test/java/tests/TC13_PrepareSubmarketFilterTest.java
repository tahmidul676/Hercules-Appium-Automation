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

public class TC13_PrepareSubmarketFilterTest extends AndroidBase {

	@Test(dataProvider = "getData")

	public void RetailerSubmarketSearch(HashMap<String, String> input) throws InterruptedException {

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
		orderPage.clickFilter();
		Thread.sleep(5000);
		orderPage.clickSubMarketDropdown();
		orderPage.selectSubMarket(input.get("submarket"));
		orderPage.clickApplyFilter();
		Thread.sleep(5000);

		// Scrape ALL retailers dynamically ----
		List<HashMap<String, String>> allRetailers = orderPage.scrapeAllRetailersOnPage();

		Assert.assertFalse(allRetailers.isEmpty(), "No retailers found on the page");

		for (HashMap<String, String> retailer : allRetailers) {
			System.out.println("Asserting: " + retailer.get("name"));

			Assert.assertNotNull(retailer.get("name"), "Name is null");
			Assert.assertNotNull(retailer.get("phone"), "Phone is null");
			Assert.assertNotNull(retailer.get("submarket"), "Submarket is null");
			Assert.assertNotNull(retailer.get("retailerCode"), "Retailer Code is null");
			Assert.assertNotNull(retailer.get("creditLimit"), "Credit Limit is null");
			Assert.assertNotNull(retailer.get("creditDays"), "Credit Days is null");
			Assert.assertNotNull(retailer.get("balance"), "Balance is null");
			Assert.assertNotNull(retailer.get("due"), "Due is null");
			Assert.assertNotNull(retailer.get("overdue"), "Overdue is null");
		}
		Thread.sleep(5000);

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		String basePath = System.getProperty("user.dir") + "//src//test//java//testData//";

		List<HashMap<String, String>> data = getMergedJsonData(basePath + "loginData.json",
				basePath + "orderTestData.json"

		);

		Object[][] arr = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			arr[i][0] = data.get(i);
		}

		return arr;
	}

}
