package tests;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
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
import pages.TourPlanPage;
import pages.WorkPlanPage;

public class TC16_TourPlan extends AndroidBase{

	
	@Test(dataProvider = "getData")

	public void TourPlan(HashMap<String, String> input)  throws InterruptedException{

		LoginPage loginPage = new LoginPage(driver);
		loginPage.setuserID(input.get("userID"));
		loginPage.setuserPin(input.get("password"));
		loginPage.clickLogin();

		HomePage homePage = new HomePage(driver);
		homePage.clickSyncNow();
		homePage.clickSyncNow();
		homePage.waitForHomePageToLoad(); 
		homePage.clickTourPlanBtn();
		WorkPlanPage workPlanPage = new WorkPlanPage(driver);
	    TourPlanPage tourPlanPage = new TourPlanPage(driver);

	    tourPlanPage.clickTodayCard(); 
		int daysRemainingInMonth = LocalDate.now().lengthOfMonth() - LocalDate.now().getDayOfMonth() + 1;

		for (int i = 1; i <= daysRemainingInMonth; i++) {
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

			workPlanPage.timePickerEndEvening(input.get("evening_endTime_Hour"), input.get("evening_endTime_Minute"));


		    if (i == daysRemainingInMonth) {
		    	tourPlanPage.clickSelectAll();
		        tourPlanPage.clickSubmit();
		        tourPlanPage.clickConfirmationOk();
		        Thread.sleep(5000);
		     //   Assert.assertTrue(workPlanPage.isSuccessMessageVisible(), "Success message not visible!");
			//	Assert.assertTrue(workPlanPage.isGoHomeButtonVisible(), "Go Home button not visible!");

		        break;
		    } else {
		    	tourPlanPage.clickNext();
		    }
		}
		
		
		}
		
		
	@DataProvider
	public Object[][] getData() throws IOException {

		String basePath = System.getProperty("user.dir") + "//src//test//java//testData//";

		List<HashMap<String, String>> data = getMergedJsonData(basePath + "loginData.json", basePath + "testData.json"

		);

		Object[][] arr = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			arr[i][0] = data.get(i);
		}

		return arr;
	}

}
