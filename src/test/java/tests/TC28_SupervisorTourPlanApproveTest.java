package tests;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.AndroidBase;
import pages.HiarchyTourPlanPage;
import pages.HomePage;
import pages.LoginPage;
import pages.TourPlanPage;
import pages.WorkPlanPage;

public class TC28_SupervisorTourPlanApproveTest extends AndroidBase{
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
		
		
		HiarchyTourPlanPage hiarchyTourPlanPage = new HiarchyTourPlanPage(driver);
		hiarchyTourPlanPage.clickApproveTab();
	        Thread.sleep(2000);
	        hiarchyTourPlanPage.clickFirstMonthlyPlanCard();
	        Thread.sleep(2000);

	     //   TourPlanApprovePage tourPlanApprovePage = new TourPlanApprovePage(driver);
	        hiarchyTourPlanPage.clickSelectAll();
	        hiarchyTourPlanPage.clickApprove();

	        Assert.assertTrue(hiarchyTourPlanPage.isApprovalConfirmationVisible(),
	                "Approval confirmation dialog not visible!");
	        hiarchyTourPlanPage.clickConfirmationOk();

	        Thread.sleep(3000);
	        // Optional once you confirm the post-approve UI:
	        // Assert.assertTrue(tourPlanPage.isApprovedSuccessMessageVisible());
		
		
		}
		
		
	@DataProvider
	public Object[][] getData() throws IOException {

		String basePath = System.getProperty("user.dir") + "//src//test//java//testData//";

		List<HashMap<String, String>> data = getMergedJsonData(basePath + "hiarchyLoginData.json", basePath + "testData.json"

		);

		Object[][] arr = new Object[data.size()][1];
		for (int i = 0; i < data.size(); i++) {
			arr[i][0] = data.get(i);
		}

		return arr;
	}

}
