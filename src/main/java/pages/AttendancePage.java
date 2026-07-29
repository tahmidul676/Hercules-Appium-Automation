package pages;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class AttendancePage extends AndroidActions {
	AndroidDriver driver;

	public AttendancePage(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
	private WebElement workplaceField;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(0)")
	private WebElement captureBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\")")
	private WebElement captureImageBtn;

	// Punch Button — instance(1)
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(1)")
	private WebElement punchBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Request successfully done\")")
	private WebElement txtOrderSuccessMessage;
	
	public boolean isOrderSuccessMessageDisplayed() {
		return txtOrderSuccessMessage.isDisplayed();
	}

	public void workplaceInput(String workplace) {
		workplaceField.click();
		workplaceField.sendKeys(workplace);
		driver.hideKeyboard();
	}

	public void selectTourType(String tourTypeCode) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement option = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='" + tourTypeCode + "']")));
		option.click();
	}

	public void clickCapture() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(captureBtn));
		captureBtn.click();
	}

	public void clickCaptureImage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(captureImageBtn));
		captureImageBtn.click();
	}

	public void clickPunch() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(punchBtn));
		punchBtn.click();
	}

	public boolean isToastMessageShown(String expectedPartialText, int timeoutSeconds) {
		long endTime = System.currentTimeMillis() + (timeoutSeconds * 1000L);
		boolean found = false;

		while (System.currentTimeMillis() < endTime && !found) {
			try {
				WebElement toast = driver.findElement(
						AppiumBy.androidUIAutomator("new UiSelector().textContains(\"" + expectedPartialText + "\")"));
				found = toast.isDisplayed();
			} catch (NoSuchElementException | StaleElementReferenceException e) {
				// not rendered yet, or gone already - keep polling, do NOT rethrow
			}

			if (!found) {
				try {
					Thread.sleep(200);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt();
					break;
				}
			}
		}
		return found;
	}

	public boolean isToastMessageShown(String expectedPartialText) {
		return isToastMessageShown(expectedPartialText, 5);
	}
}
