package pages;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class AttendancePage extends AndroidActions{
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
	
	// Capture Image Button — instance(1)
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(1)")
	private WebElement captureImageBtn;

	// Punch Button — instance(1)
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").instance(1)")
	private WebElement punchBtn;
	
    public void workplaceInput(String workplace) {
        workplaceField.click();
        workplaceField.sendKeys(workplace);
        driver.hideKeyboard();
    }
	

    public void selectTourType(String tourTypeCode) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//android.widget.TextView[@text='" + tourTypeCode + "']")
        ));
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
}
