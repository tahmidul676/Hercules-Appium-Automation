package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class LoginPage extends AndroidActions {
	AndroidDriver driver;

	public LoginPage(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[1]")
	private WebElement userIdField;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.widget.EditText[2]")
	private WebElement userPinField;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Proceed\"]")
	private WebElement loginBtn;
	
	@AndroidFindBy(xpath = "//android.widget.Toast")
	private WebElement invalidErrorToast;
	
	public void setuserID(String userid) {

		userIdField.clear();
		userIdField.sendKeys(userid);
	}

	public void setuserPin(String pin) {

		userPinField.clear();
		userPinField.sendKeys(pin);
	}
	
	public void clickLogin() {
		loginBtn.click();
	}
	
	// ===== TOAST MESSAGE FUNCTION =====
	public String getErrorMessage() {

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

	    WebElement msg = wait.until(driver ->
	            driver.findElement(By.xpath(
	                    "//*[contains(@text,'invalid') or " +
	                    "contains(@text,'password') or " +
	                    "contains(@text,'user') or " +
	                    "contains(@text,'error') or " +
	                    "contains(@content-desc,'invalid') or " +
	                    "contains(@content-desc,'error')]"
	            ))
	    );

	    return msg.getText();
	}
}
