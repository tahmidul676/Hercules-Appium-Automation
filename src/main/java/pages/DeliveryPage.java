package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class DeliveryPage extends AndroidActions {
	AndroidDriver driver;

	public DeliveryPage(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Change Date\"]")
	private WebElement clickChangeDate;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Select All\"]")
	private WebElement clickSelectAll;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Upload\"]")
	private WebElement clickUploadBtn;

	public void clickChangeDate() {
		clickChangeDate.click();

	}

	public void selectDateFromCalendar(String date) throws InterruptedException {
		Thread.sleep(1500); // wait for calendar to open

		// "25 April 2026", "25 May 2026", any month/year automatically
		driver.findElement(By.xpath("//*[contains(@content-desc,'" + date + "')]")).click();
		Thread.sleep(500);

		// Click OK
		driver.findElement(By.id("android:id/button1")).click();

		System.out.println("Selected date: " + date);
		Thread.sleep(500);
	}

	public void clickSelectAll() {
		clickSelectAll.click();

	}
	
	public void clickUpload() {
		clickUploadBtn.click();

	}
	
}
