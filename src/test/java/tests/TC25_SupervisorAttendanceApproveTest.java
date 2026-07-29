package tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.AndroidBase;
import pages.HiarchyAttendancePage;
import pages.HomePage;
import pages.LoginPage;

public class TC25_SupervisorAttendanceApproveTest extends AndroidBase {

    @Test(dataProvider = "getData")
    public void validateAttendance(HashMap<String, String> input) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setuserID(input.get("userID"));
        loginPage.setuserPin(input.get("password"));
        loginPage.clickLogin();

        HomePage homePage = new HomePage(driver);
        homePage.clickSyncNow();
        homePage.clickSyncNow();
        homePage.waitForHomePageToLoad();

        // Adjust this method name to match whatever your HomePage actually exposes.
        homePage.clickAttendance();

        HiarchyAttendancePage hiarchyAttendancePage = new HiarchyAttendancePage(driver);
        Thread.sleep(2000);

        hiarchyAttendancePage.clickValidateTab();
        Thread.sleep(1500);

        hiarchyAttendancePage.clickValidateAttendanceForDate(input.get("date"));
        Thread.sleep(1500);

        hiarchyAttendancePage.scrollModalToEnd();
        Thread.sleep(1000);

        hiarchyAttendancePage.selectTime(input.get("time"));
        hiarchyAttendancePage.selectPlace(input.get("place"));

        hiarchyAttendancePage.clickValidate();
        Thread.sleep(1500);

        hiarchyAttendancePage.clickConfirmValidate();
        Thread.sleep(1500);

//        Assert.assertTrue(hiarchyAttendancePage.isValidationDoneShown(), "Expected 'Validation Done' success message was not shown");
//        Thread.sleep(5000);
    
    }

    @DataProvider
    public Object[][] getData() throws IOException {
        String basePath = System.getProperty("user.dir") + "//src//test//java//testData//";
        List<HashMap<String, String>> data = getMergedJsonData(basePath + "hiarchyLoginData.json",
                basePath + "attendanceData.json");
        Object[][] arr = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            arr[i][0] = data.get(i);
        }
        return arr;
    }
}