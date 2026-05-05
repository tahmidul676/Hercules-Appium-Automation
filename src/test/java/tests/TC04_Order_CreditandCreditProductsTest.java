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

public class TC04_Order_CreditandCreditProductsTest extends AndroidBase {

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

		OrderPage orderPage = new OrderPage(driver);
		orderPage.enterSearchRetailer(input.get("retailerName"));
		orderPage.clickMatchedRetailer();
		Thread.sleep(5000);
		orderPage.selectCreditAndCreditProducts();
		// Assertion
		String actualText = orderPage.getPrepareOrderText();
		String expectedText = "Prepare Order";
		Assert.assertEquals(actualText, expectedText, "Prepare Order text mismatch!");

		// 1
				orderPage.clickSearchProduct(input.get("productName"));
				orderPage.clickAddProduct();
				orderPage.enterQuantity(input.get("productQty"));
				orderPage.clickConfirm();
				Thread.sleep(5000);
				// 2
				orderPage.clickSearchProduct(input.get("productName1"));
				orderPage.clickAddProduct();
				orderPage.enterQuantity(input.get("productQty1"));
				orderPage.clickConfirm();
				Thread.sleep(5000);
				// 3
				orderPage.clickSearchProduct(input.get("productName2"));
				orderPage.clickAddProduct();
				orderPage.enterQuantity(input.get("productQty2"));
				orderPage.clickConfirm();

				orderPage.clickSubmit();

				// Over Due List
				orderPage.clickCommitPayDay();
				orderPage.clickAddDateForAllInvoices(input.get("addDate"));
				orderPage.clickContinue();
				Thread.sleep(15000);
				orderPage.clickSubmit();
				Thread.sleep(5000);
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "//src//test//java//testData//testData.json");

		return new Object[][] { { data.get(0) } };
	}
}
