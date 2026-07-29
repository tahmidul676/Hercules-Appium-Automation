package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HiarchyWorkPlanPage {

	private final AndroidDriver driver;

	public HiarchyWorkPlanPage(AndroidDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Approve\")")
	private WebElement approveTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"View\")")
	private WebElement viewTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Filter\")")
	private WebElement filterButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Submitted On\")")
	private WebElement submittedOnLabel;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"SAT\")")
	private WebElement satTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"SUN\")")
	private WebElement sunTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"MON\")")
	private WebElement monTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"TUE\")")
	private WebElement tueTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"WED\")")
	private WebElement wedTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"THU\")")
	private WebElement thuTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"FRI\")")
	private WebElement friTab;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[8]")
	private WebElement selectAllCheckbox;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Reject\")")
	private WebElement rejectButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Revert\")")
	private WebElement revertButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Approve\")")
	private WebElement approveButton;

	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Weekly Work Plan\")")
	private WebElement screenHeader;

	public boolean isLoaded() {
		return screenHeader.isDisplayed();
	}

	public void clickDayTab(String day) {
		switch (day.trim().toUpperCase()) {
		case "SAT":
			satTab.click();
			break;
		case "SUN":
			sunTab.click();
			break;
		case "MON":
			monTab.click();
			break;
		case "TUE":
			tueTab.click();
			break;
		case "WED":
			wedTab.click();
			break;
		case "THU":
			thuTab.click();
			break;
		case "FRI":
			friTab.click();
			break;
		default:
			throw new IllegalArgumentException("Invalid day tab: " + day);
		}

	}

	public void clickSelectAll() {
		selectAllCheckbox.click();
	}

	public void clickApprove() {
		approveButton.click();

	}

	public void clickReject() {
		rejectButton.click();

	}

	public void clickRevert() {
		revertButton.click();

	}

	public void clickApproveTab() {
		approveTab.click();

	}

	public void clickViewTab() {
		viewTab.click();

	}

	public boolean isCardVisible() {
		return submittedOnLabel.isDisplayed();
	}

	
	public void openWorkPlanCard(String retailerName) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + retailerName + "\")"))
				.click();

	}
	
	public boolean isToastMessageShown(String expectedPartialText) {
        try {
            WebElement toast = driver.findElement(
                AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"" + expectedPartialText + "\")"
                )
            );
            return toast.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}