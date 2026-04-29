package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class MoneyCollectionPage extends AndroidActions {
	AndroidDriver driver;

	public MoneyCollectionPage(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

//	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"MPO 06 [MPO06]\"]")
//	private WebElement getUser;

//	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"No Route Found\"]")
//	private WebElement getRouteNo;
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"MPO\")")
	private WebElement getUser;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textContains(\"Route\")")
	private WebElement getRouteNo;

	public String getUserInfo() {
		return getUser.getText();
	}

	public String getRoute() {
		return getRouteNo.getText();
	}
}
