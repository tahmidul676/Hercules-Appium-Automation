package pages;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class LeavePage extends AndroidActions {

	AndroidDriver driver;

	public LeavePage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Apply']")
	private WebElement applyTab;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
	private WebElement leaveDropdown;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Casual Leave\")")
	private WebElement leaveTypeDropdown;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"From\")")
	private WebElement fromDateField;

	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"To\")")
	private WebElement toDateField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Reason *']/parent::android.widget.EditText")
	private WebElement reasonField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Phone']/parent::android.widget.EditText")
	private WebElement phoneField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Address']/parent::android.widget.EditText")
	private WebElement addressField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Name']/parent::android.widget.EditText")
	private WebElement jobDelegatedNameField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Designation']/parent::android.widget.EditText")
	private WebElement designationField;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Department']/parent::android.widget.EditText")
	private WebElement departmentField;

	// ---------- Bottom Buttons ----------
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Save']")
	private WebElement saveButton;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Submit']")
	private WebElement submitButton;

	// ---------- Date Picker Dialog (Android Material calendar) ----------
	@AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
	private WebElement datePickerOkButton;

	@AndroidFindBy(xpath = "//android.widget.Button[@text='CANCEL']")
	private WebElement datePickerCancelButton;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Next month' or @content-desc='Change to next month']")
	private WebElement datePickerNextMonthButton;

	@AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc='Previous month' or @content-desc='Change to previous month']")
	private WebElement datePickerPrevMonthButton;

	public void dumpPageSource(String fileName) {
		try {
			String source = driver.getPageSource();
			java.nio.file.Files.write(java.nio.file.Paths.get(fileName), source.getBytes());
			System.out.println("Page source dumped to: " + fileName);
		} catch (Exception e) {
			System.out.println("Failed to dump page source: " + e.getMessage());
		}
	}

	public void scrollToPageEnd() {
		driver.findElement(
				AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"));
	}

	public void clickApplyTab() {
		applyTab.click();
	}

	public void clickLeaveTypeDropdown() {
		leaveTypeDropdown.click();
	}

	
	public void selectLeaveType(String leaveType) {
		clickLeaveTypeDropdown();
		By optionLocator = By.xpath("//android.widget.TextView[@text='" + leaveType + "']");
		driver.findElement(optionLocator).click();
	}

	public void selectDateFromPicker(int day, int monthsToNavigate) {
		for (int i = 0; i < monthsToNavigate; i++) {
			datePickerNextMonthButton.click();
		}
		for (int i = 0; i > monthsToNavigate; i--) {
			datePickerPrevMonthButton.click();
		}
		By dayLocator = By
				.xpath("//android.view.View[@text='" + day + "'] | //android.widget.TextView[@text='" + day + "']");
		driver.findElement(dayLocator).click();
	}

	public void setFromDate(int day, int monthsToNavigate) {
		clickFromDate();
		selectDateFromPicker(day, monthsToNavigate);
		clickDatePickerOk();
	}

	public void setToDate(int day, int monthsToNavigate) {
		clickToDate();
		selectDateFromPicker(day, monthsToNavigate);
		clickDatePickerOk();
	}

	// ---- Reason ----
	public void enterReason(String reason) {
		reasonField.clear();
		reasonField.sendKeys(reason);
	}

	// ---- Contact (During Leave) ----
	public void enterPhone(String phone) {
		phoneField.clear();
		phoneField.sendKeys(phone);
	}

	public void enterAddress(String address) {
		addressField.clear();
		addressField.sendKeys(address);
	}

	// ---- Job Delegated To ----
	public void enterJobDelegatedName(String name) {
		jobDelegatedNameField.clear();
		jobDelegatedNameField.sendKeys(name);
	}

	public void enterDesignation(String designation) {
		designationField.clear();
		designationField.sendKeys(designation);
	}

	public void enterDepartment(String department) {
		departmentField.clear();
		departmentField.sendKeys(department);
	}

	public void clickSave() {
		saveButton.click();
	}

	public void clickSubmit() {
		submitButton.click();
	}

	public void clickLeave() {
		leaveDropdown.click();
	}

	public void clickLeaveType() {
		leaveTypeDropdown.click();
	}

	public void clickFromDate() {
		fromDateField.click();
	}

	public void clickToDate() {
		toDateField.click();
	}

	public void clickDatePickerOk() {
		datePickerOkButton.click();
	}

	public void clickDatePickerCancel() {
		datePickerCancelButton.click();
	}

	private String buildDayContentDesc(int day, LocalDate monthContext) {
		String dayStr = String.format("%02d", day);
		String monthName = monthContext.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
		return dayStr + " " + monthName + " " + monthContext.getYear();
	}

	public void selectDateFromPicker(String day) {
		int dayInt = Integer.parseInt(day.trim());
		selectDateFromPicker(dayInt, LocalDate.now());
	}

	public void selectDateFromPicker(int day, LocalDate monthContext) {
		String contentDesc = buildDayContentDesc(day, monthContext);
		By dayLocator = By.xpath("//android.view.View[@content-desc='" + contentDesc + "']");
		driver.findElement(dayLocator).click();
	}

	public void setFromDate(String day) {
		selectDateFromPicker(day);
		clickDatePickerOk();
	}

	public void setToDate(String day) {
		selectDateFromPicker(day);
		clickDatePickerOk();
	}

}