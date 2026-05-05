package pages;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class OrderPage extends AndroidActions {
	AndroidDriver driver;

	public OrderPage(AndroidDriver driver) {

		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Cash\"]")
	private WebElement clickCash;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Credit\"]")
	private WebElement clickCredit;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Cash Products\"]")
	private WebElement clickCashProducts;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Credit Products\"]")
	private WebElement clickCreditProducts;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Prepare Order\"]")
	private WebElement prepareOrderText;

	@AndroidFindBy(xpath = "//android.widget.EditText")
	private WebElement searchProductInput;

	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Details\"])[1]")
	private WebElement clickFirstMatchedProduct;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Search Product\"]")
	private WebElement clickSearchProduct;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Search\"]")
	private WebElement searchInputField;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Add\"])[1]")
	private WebElement clickAddProduct;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text=\"0\"]")
	private WebElement enterQuantity;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Confirm\"]")
	private WebElement clickConfirm;

	// Order Items Info
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Product Count\"]/following-sibling::android.widget.TextView[1]")
	private WebElement productCount;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Product Count\"]/following-sibling::android.widget.TextView[2]")
	private WebElement productCountAmount;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Gross Discount\"]/following-sibling::android.widget.TextView[1]")
	private WebElement grossDiscount;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Adjustment\"]/following-sibling::android.widget.TextView[1]")
	private WebElement adjustment;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Grand Total\"]/following-sibling::android.widget.TextView[1]")
	private WebElement grandTotal;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Submit\"]")
	private WebElement clickSubmit;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Commit Pay Date\"]")
	private WebElement clickCommitPay;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Continue\"]")
	private WebElement clickContinueBtn;

	public void selectCashAndCashProducts() {
		clickCash.click();
		clickCashProducts.click();
	}

	public void selectCashAndCreditProducts() {
		clickCash.click();
		clickCreditProducts.click();
	}

	public void selectCreditAndCreditProducts() {
		clickCredit.click();
		clickCreditProducts.click();
	}

	public String getPrepareOrderText() {
		return prepareOrderText.getText();
	}

	public void enterSearchRetailer(String productName) {
		searchProductInput.click();
		searchProductInput.clear();
		searchProductInput.sendKeys(productName);
	}

	public void clickMatchedRetailer() {
		clickFirstMatchedProduct.click();
	}

	public void clickSearchProduct(String productName) {
		clickSearchProduct.click();
		WebElement searchInput = driver.findElement(By.xpath("//android.widget.EditText"));
		searchInput.sendKeys(productName);
	}

	public void clickAddProduct() {
		clickAddProduct.click();
	}

	public void enterQuantity(String qty) {
		enterQuantity.click();
		enterQuantity.clear();
		enterQuantity.sendKeys(qty);
	}

	public void clickConfirm() {
		clickConfirm.click();
	}

	public void clickSubmit() {
		clickSubmit.click();
	}

	// Over Due List
	public void clickCommitPayDay() {
		clickCommitPay.click();
	}

	// Calendar date selection
	public void selectDateFromCalendar(String date) throws InterruptedException {
		Thread.sleep(1500);

		driver.findElement(By.xpath("//*[contains(@content-desc,'" + date + "')]")).click();
		Thread.sleep(500);

		driver.findElement(By.id("android:id/button1")).click();

		System.out.println("Selected date: " + date);
		Thread.sleep(500);
	}

	public void clickAddDateForAllInvoices(String date) throws InterruptedException {
		int processedCount = 0;
		int maxScrollAttempts = 2;
		int scrollAttempts = 0;

		while (scrollAttempts < maxScrollAttempts) {

			List<WebElement> addDateButtons = driver
					.findElements(By.xpath("//android.widget.TextView[@text='Add Date']"));

			if (!addDateButtons.isEmpty()) {
				scrollAttempts = 0;
				try {
					addDateButtons.get(0).click();
					processedCount++;
					System.out.println("Clicked Add Date #" + processedCount);
					selectDateFromCalendar(date);
					Thread.sleep(800);

				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}

			} else {
				System.out.println("No buttons visible, scrolling... attempt " + (scrollAttempts + 1));
				scrollDown();
				Thread.sleep(1200);
				scrollAttempts++;
			}
		}

		System.out.println("Done. Total Add Date clicked: " + processedCount);
	}

	public void clickContinue() {
		clickContinueBtn.click();
	}

}
