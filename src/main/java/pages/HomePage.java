package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class HomePage extends AndroidActions {
	AndroidDriver driver;

	public HomePage(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sync Now\"]")
	private WebElement syncNowBtn;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View[1]/android.view.View")
	private WebElement orderBtn;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View[3]/android.view.View")
	private WebElement moneyCollectionBtn;
	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View[2]/android.view.View")
	private WebElement clickDeliveryBtn;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View[5]/android.view.View")
	private WebElement clickWorkPlanBtn;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(14)")
	private WebElement clickAttendanceBtn;
	

	public void clickSyncNow() {
		syncNowBtn.click();
	}

	public void clickOrder() {
		orderBtn.click();
	}

	public void clickMoneyCollection() {
		moneyCollectionBtn.click();
	}

	public void clickDelivery() {
		clickDeliveryBtn.click();
	}

	public void clickWorkPlan() {
		clickWorkPlanBtn.click();
	}
	
//	public void clickAttendance() {
//		clickAttendanceBtn.click();
//	}
	// Wait until Attendance button is visible
    public void waitForHomePageToLoad() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(clickAttendanceBtn));
    }

    public void clickAttendance() {
        waitForHomePageToLoad(); // Wait inside the method itself
        clickAttendanceBtn.click();
    }
}
