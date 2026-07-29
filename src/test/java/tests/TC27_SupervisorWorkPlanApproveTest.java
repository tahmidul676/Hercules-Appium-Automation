package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.AndroidBase;
import pages.HiarchyWorkPlanPage;
import pages.HomePage;
import pages.LoginPage;

public class TC27_SupervisorWorkPlanApproveTest extends AndroidBase {
	 @Test(dataProvider = "getData")
	    public void approveLeave(HashMap<String, String> input) throws InterruptedException {
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.setuserID(input.get("userID"));
	        loginPage.setuserPin(input.get("password"));
	        loginPage.clickLogin();

	        HomePage homePage = new HomePage(driver);
	        homePage.clickSyncNow();
	        homePage.clickSyncNow();
	        homePage.waitForHomePageToLoad();
	        homePage.clickWorkPlan();

	        HiarchyWorkPlanPage hiarchyWorkPlanPage = new HiarchyWorkPlanPage(driver);
	        String retailerName = input.get("retailerName");
	        String dayToSelect = input.get("dayToSelect");
	 
	        hiarchyWorkPlanPage.clickApproveTab();
	        Assert.assertTrue(hiarchyWorkPlanPage.isCardVisible(), "Weekly Work Plan card not visible on list screen");
	 
	        hiarchyWorkPlanPage.openWorkPlanCard(retailerName);
	        Assert.assertTrue(hiarchyWorkPlanPage.isLoaded(), "Work Plan Approve screen did not load");
	 
	        hiarchyWorkPlanPage.clickDayTab(dayToSelect);
	        hiarchyWorkPlanPage.clickSelectAll();
	        Thread.sleep(5000);
	        hiarchyWorkPlanPage.clickApprove();
	        hiarchyWorkPlanPage.clickApprove();
	        
	        boolean shown = hiarchyWorkPlanPage.isToastMessageShown("Plan Approved");
	        Assert.assertTrue(shown, "Expected success toast was not shown");
	        Thread.sleep(5000);
	    }

	    @DataProvider
	    public Object[][] getData() throws IOException {
	        String basePath = System.getProperty("user.dir") + "//src//test//java//testData//";
	        List<HashMap<String, String>> data = getMergedJsonData(basePath + "hiarchyLoginData.json",
	                basePath + "workPlanData.json");
	        Object[][] arr = new Object[data.size()][1];
	        for (int i = 0; i < data.size(); i++) {
	            arr[i][0] = data.get(i);
	        }
	        return arr;
	    }
}
