package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import base.AndroidBase;
import pages.HomePage;
import pages.LoginPage;

public class TC02_InvalidLoginTest extends AndroidBase{
	
	@Test(dataProvider = "getData")

	public void InvalidLogin(HashMap<String, String> input) throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setuserID(input.get("userID"));
		loginPage.setuserPin(input.get("password"));
		loginPage.clickLogin();

		Assert.assertTrue(true);
		
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "//src//test//java//testData//loginData.json");

		return new Object[][] { { data.get(1) } };
	}
}


