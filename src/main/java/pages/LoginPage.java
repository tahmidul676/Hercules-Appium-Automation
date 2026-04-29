package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

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
}
