package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.AndroidBase;
import io.appium.java_client.AppiumBy;
import pages.HomePage;
import pages.LoginPage;
import pages.OrderPage;
import pages.WorkPlanPage;

public class TC08_WorkPlan extends AndroidBase {

	@Test(dataProvider = "getData")

	public void WorkPlan(HashMap<String, String> input) throws InterruptedException {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setuserID(input.get("userID"));
		loginPage.setuserPin(input.get("password"));
		loginPage.clickLogin();

		HomePage homePage = new HomePage(driver);
		homePage.clickSyncNow();
		homePage.clickSyncNow();
		Thread.sleep(2000);
		homePage.clickWorkPlan();

		WorkPlanPage workPlanPage = new WorkPlanPage(driver);

		for (int i = 1; i <= 7; i++) {
			Thread.sleep(3000);

			workPlanPage.clickTourTypeMorning();
			workPlanPage.clickTourTypeEvening();
			workPlanPage.clickWorkTypeMorning();
			workPlanPage.clickWorkTypeEvening();
			workPlanPage.setDeliveryDayMorning(input.get("morning_orderDeliveryDay"));
			workPlanPage.setDeliveryDayEvening(input.get("evening_orderDeliveryDay"));
			workPlanPage.clickTravelModeMorning();
			workPlanPage.clickTravelModeEvening();
			workPlanPage.enterEndPointMorning();
			workPlanPage.enterEndPointEvening();
			workPlanPage.enterStartPointMorning();
			workPlanPage.enterStartPointEvening();
			workPlanPage.clickStartTimeMorning();
			Thread.sleep(1000);
			workPlanPage.timePickerStartMorning(input.get("morning_startTime_Hour"),
					input.get("morning_startTime_Minute"));

			workPlanPage.clickStartTimeEvening();
			Thread.sleep(1000);
			workPlanPage.timePickerStartEvening(input.get("evening_startTime_Hour"),
					input.get("evening_startTime_Minute"));

			workPlanPage.clickEndTimeMorning();
			Thread.sleep(1000);
			workPlanPage.timePickerEndMorning(input.get("morning_endTime_Hour"), input.get("morning_endTime_Minute"));
			Thread.sleep(1000);
			workPlanPage.clickEndTimeEvening();

			workPlanPage.timePickerEndEvening(input.get("morning_endTime_Hour"), input.get("morning_endTime_Minute"));
			
			if (i == 7) {
				workPlanPage.clickSubmit();
				workPlanPage.clickOkOnFirstPopup();

				Assert.assertTrue(workPlanPage.isSuccessMessageVisible(), "Success message not visible!");
				Assert.assertTrue(workPlanPage.isGoHomeButtonVisible(), "Go Home button not visible!");

				workPlanPage.clickGoHome();
				break;
			} else {
				workPlanPage.clickNext();
				Thread.sleep(5000);
			}
		}

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "//src//test//java//testData//testData.json");

		return new Object[][] { { data.get(0) } };
	}

}
