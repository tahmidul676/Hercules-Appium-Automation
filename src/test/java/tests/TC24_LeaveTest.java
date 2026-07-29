package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.AndroidBase;
import pages.HomePage;
import pages.LeavePage;
import pages.LoginPage;

public class TC24_LeaveTest extends AndroidBase {

	@Test(dataProvider = "getData")
	public void applyLeave(HashMap<String, String> input) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setuserID(input.get("userID"));
		loginPage.setuserPin(input.get("password"));
		loginPage.clickLogin();

		HomePage homePage = new HomePage(driver);
		homePage.clickSyncNow();
		homePage.clickSyncNow();
		homePage.waitForHomePageToLoad();
		homePage.clickLeaveBtn(); 

		LeavePage leavePage = new LeavePage(driver);
		Thread.sleep(2000);
		leavePage.clickLeave();
		leavePage.clickLeaveType();
		Thread.sleep(1000);
		leavePage.clickFromDate();
		leavePage.setFromDate(input.get("fromDay"));
		leavePage.clickToDate();
		Thread.sleep(3000);
		leavePage.setToDate(input.get("toDay"));
		Thread.sleep(3000);
		leavePage.enterReason(input.get("reason"));
		// Scroll down
		leavePage.scrollToPageEnd();
		Thread.sleep(1000);
		// Contact
		leavePage.enterPhone(input.get("phone"));
		leavePage.enterAddress(input.get("address"));
		// Job Delegated To
		leavePage.enterJobDelegatedName(input.get("delegateName"));
		leavePage.enterDesignation(input.get("delegateDesignation"));
		leavePage.enterDepartment(input.get("delegateDepartment"));

		Thread.sleep(5000);
		leavePage.clickSubmit();
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		String basePath = System.getProperty("user.dir") + "//src//test//java//testData//";
		List<HashMap<String, String>> data = getMergedJsonData(basePath + "loginData.json",
				basePath + "leave.json");
		Object[][] arr = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			arr[i][0] = data.get(i);
		}
		return arr;
	}
}