package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.AndroidBase;
import pages.DeliveryPage;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderPage;

public class TC07_DeliveryTest extends AndroidBase {

	@Test(dataProvider = "getData")

	public void Delivery(HashMap<String, String> input) throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setuserID(input.get("userID"));
		loginPage.setuserPin(input.get("password"));
		loginPage.clickLogin();

		HomePage homePage = new HomePage(driver);
		homePage.clickSyncNow();
		homePage.clickSyncNow();
		Thread.sleep(5000);
		homePage.clickDelivery();

		DeliveryPage deliveryPage = new DeliveryPage(driver);
		deliveryPage.clickChangeDate();
		deliveryPage.selectDateFromCalendar(input.get("changeDate"));
		deliveryPage.clickSelectAll();
		Thread.sleep(5000);
		deliveryPage.clickUpload();
		Thread.sleep(9000);

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		String basePath = System.getProperty("user.dir") + "//src//test//java//testData//";

		List<HashMap<String, String>> data = getMergedJsonData(basePath + "loginData.json", basePath + "delivery.json"

		);

		Object[][] arr = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			arr[i][0] = data.get(i);
		}

		return arr;
	}
}
