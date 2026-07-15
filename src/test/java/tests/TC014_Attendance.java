package tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.AndroidBase;
import pages.AttendancePage;
import pages.HomePage;
import pages.LoginPage;
import pages.WorkPlanPage;

public class TC09_Attendance extends AndroidBase {
	@Test(dataProvider = "getData")

	public void Attendance(HashMap<String, String> input) throws InterruptedException {

		//LoginPage loginPage = new LoginPage(driver);
        //loginPage.setuserID(input.get("userID"));
       //loginPage.setuserPin(input.get("password"));
       //loginPage.clickLogin();

		// HomePage homePage = new HomePage(driver);
		// homePage.clickSyncNow();
		// homePage.clickSyncNow();

		HomePage homePage = new HomePage(driver);
		homePage.waitForHomePageToLoad();   // For explicit wait
		homePage.clickAttendance();
		Thread.sleep(5000);

		AttendancePage attendancePage = new AttendancePage(driver);
		attendancePage.workplaceInput(input.get("workplace"));
		attendancePage.selectTourType(input.get("tourType"));
		attendancePage.clickCapture();
		attendancePage.clickCaptureImage();
		Thread.sleep(5000);
		attendancePage.clickPunch();

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "//src//test//java//testData//testData.json");

		return new Object[][] { { data.get(0) } };
	}

}
