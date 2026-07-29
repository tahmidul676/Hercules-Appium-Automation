package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import base.AndroidBase;
import pages.HiarchyLeavePage;
import pages.HomePage;
import pages.LoginPage;

public class TC26_SupervisorLeaveApproveTest extends AndroidBase {

    @Test(dataProvider = "getData")
    public void SupervisorLeaveApprove(HashMap<String, String> input) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setuserID(input.get("userID"));
        loginPage.setuserPin(input.get("password"));
        loginPage.clickLogin();

        HomePage homePage = new HomePage(driver);
        homePage.clickSyncNow();
        homePage.clickSyncNow();
        homePage.waitForHomePageToLoad();
        homePage.clickLeaveBtn();

        HiarchyLeavePage leaveApprovePage = new HiarchyLeavePage(driver);
        Thread.sleep(2000);
        leaveApprovePage.clickApproveTab();
        Thread.sleep(1500);
        leaveApprovePage.clickApproveForDateRange(input.get("fromDate"), input.get("toDate"));
        leaveApprovePage.clickConfirmApprove();
//        boolean shown = leaveApprovePage.isToastMessageShown("Leave application approved successfully.");
//        Assert.assertTrue(shown, "Expected success toast was not shown");
//        Thread.sleep(5000);
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        String basePath = System.getProperty("user.dir") + "//src//test//java//testData//";
        List<HashMap<String, String>> data = getMergedJsonData(basePath + "hiarchyLoginData.json",
                basePath + "leave.json");
        Object[][] arr = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            arr[i][0] = data.get(i);
        }
        return arr;
    }
}