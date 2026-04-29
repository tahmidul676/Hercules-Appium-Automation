package pages;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utils.AndroidActions;

public class WorkPlanPage extends AndroidActions {

	AndroidDriver driver;

	public WorkPlanPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.view.View[1]/android.widget.EditText")
	private WebElement tourTypeClickMorning;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View[1]/android.widget.EditText")
	private WebElement tourTypeClickEvening;

	// Tour Type -> EQ
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"EQ\"]")
	private WebElement selectTourType;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Work Type\"])[1]")
	private WebElement workTypeClickMorning;

	@AndroidFindBy(xpath = "(//android.widget.TextView[@text=\"Work Type\"])[2]")
	private WebElement workTypeClickEvening;

	// Tour Type -> EQ
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"AW\"]")
	private WebElement selectWorkType;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.widget.EditText")
	private WebElement deliveryDayMorning;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.widget.EditText")
	private WebElement deliveryDayEvening;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.view.View[3]")
	private WebElement clickTravelModeMorning;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View[3]")
	private WebElement clickTravelModeEvening;

	// Sub-Market set to -> Rampura SubMarket
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Rampura Submarket 1\"]")
	private WebElement clickStarAndEndPoint;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.view.View[5]")
	private WebElement clickStartTimeMorning;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View[5]")
	private WebElement clickStartTimeEvening;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.view.View[7]")
	private WebElement clickEndTimeMorning;

	@AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View[7]")
	private WebElement clickEndTimeEvening;

	By endPointFieldMorning = By
			.xpath("//android.widget.ScrollView/android.view.View[1]/android.view.View[8]/android.widget.EditText");
	By endPointFieldEvening = By
			.xpath("//android.widget.ScrollView/android.view.View[2]/android.view.View[8]/android.widget.EditText");

	By startPointFieldMorning = By
			.xpath("//android.widget.ScrollView/android.view.View[1]/android.view.View[6]/android.widget.EditText");

	By startPointFieldEvening = By
			.xpath("//android.widget.ScrollView/android.view.View[2]/android.view.View[6]/android.widget.EditText");

	// Time Picker Elements
	@AndroidFindBy(id = "android:id/hours")
	private WebElement hour;

	@AndroidFindBy(id = "android:id/minutes")
	private WebElement minute;

	@AndroidFindBy(id = "android:id/am_label")
	private WebElement am;

	@AndroidFindBy(id = "android:id/pm_label")
	private WebElement pm;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement okBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Next' or @text='NEXT' or @text='next']/following-sibling::android.widget.Button")
	private WebElement nextBtn;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Submit']/following-sibling::android.widget.Button")
	private WebElement submitBtn;

	@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(12).selected(true)")
	private WebElement friTabActive;

	@AndroidFindBy(xpath = "//android.view.View//android.widget.TextView[@text='Ok']")
	private WebElement okButton;

	@AndroidFindBy(xpath = "//android.view.View//android.widget.TextView[@text='request successfully done']")
	private WebElement successMessage;

	@AndroidFindBy(xpath = "//android.view.View//android.widget.TextView[@text='Go Home']")
	private WebElement goHomeButton;

	public void clickTourTypeMorning() {
		tourTypeClickMorning.click();
		selectTourType.click();
	}

	public void clickTourTypeEvening() {
		tourTypeClickEvening.click();
		selectTourType.click();
	}

	public void clickWorkTypeMorning() {
		workTypeClickMorning.click();
		selectWorkType.click();

	}

	public void clickWorkTypeEvening() {
		workTypeClickEvening.click();
		selectWorkType.click();
	}

	public void setDeliveryDayMorning(String day) {
		deliveryDayMorning.clear();
		deliveryDayMorning.sendKeys(day);
	}

	public void setDeliveryDayEvening(String day) {
		deliveryDayEvening.clear();
		deliveryDayEvening.sendKeys(day);
	}

	public void clickTravelModeMorning() {
		clickTravelModeMorning.click();
	}

	public void clickTravelModeEvening() {
		clickTravelModeEvening.click();
	}

	public void enterEndPointMorning() {
		scrollToEndPointByIndex();
		driver.findElement(endPointFieldMorning).click();
		clickStarAndEndPoint.click();
	}

	public void enterEndPointEvening() {
		scrollToEndPointByIndex();
		driver.findElement(endPointFieldEvening).click();
		clickStarAndEndPoint.click();
	}

	public void enterStartPointMorning() {
		scrollToEndPointByIndex();
		driver.findElement(startPointFieldMorning).click();
		clickStarAndEndPoint.click();
	}

	public void enterStartPointEvening() {
		scrollToEndPointByIndex();
		driver.findElement(startPointFieldEvening).click();
		clickStarAndEndPoint.click();
	}

	// Time Picker
	@AndroidFindBy(id = "android:id/toggle_mode")
	private WebElement timeKeyboardToggle;

	@AndroidFindBy(id = "android:id/input_hour")
	private WebElement inputHour;

	@AndroidFindBy(id = "android:id/input_minute")
	private WebElement inputMinute;

	@AndroidFindBy(id = "android:id/am_pm_spinner")
	private WebElement amPmSpinner;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"AM\"]")
	private WebElement selectAm;

	@AndroidFindBy(xpath = "//android.widget.CheckedTextView[@resource-id=\"android:id/text1\" and @text=\"PM\"]")
	private WebElement selectPm;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement timePickerOkBtn;

	public void timePickerStartMorning(String hour, String minute) {
		timeKeyboardToggle.click();
		inputHour.clear();
		inputHour.sendKeys(hour);
		inputMinute.clear();
		inputMinute.sendKeys(minute);
		amPmSpinner.click();
		selectAm.click();
		timePickerOkBtn.click();
	}
	
	public void timePickerEndMorning(String hour, String minute) {
		//timeKeyboardToggle.click();
		inputHour.clear();
		inputHour.sendKeys(hour);
		inputMinute.clear();
		inputMinute.sendKeys(minute);
		amPmSpinner.click();
		selectPm.click();
		timePickerOkBtn.click();
	}
	
	public void timePickerStartEvening(String hour, String minute) {
		//timeKeyboardToggle.click();
		inputHour.clear();
		inputHour.sendKeys(hour);
		inputMinute.clear();
		inputMinute.sendKeys(minute);
		amPmSpinner.click();
		selectPm.click();
		timePickerOkBtn.click();
	}
	
	public void timePickerEndEvening(String hour, String minute) {
		//timeKeyboardToggle.click();
		inputHour.clear();
		inputHour.sendKeys(hour);
		inputMinute.clear();
		inputMinute.sendKeys(minute);
		amPmSpinner.click();
		selectPm.click();
		timePickerOkBtn.click();
	}

	
	public void clickStartTimeMorning() {
		clickStartTimeMorning.click();
	}

	public void clickStartTimeEvening() {
		clickStartTimeEvening.click();

		timeKeyboardToggle.click();
	}

	public void clickEndTimeMorning() {
		clickEndTimeMorning.click();
		timeKeyboardToggle.click();
	}

	public void clickEndTimeEvening() {
		clickEndTimeEvening.click();
		timeKeyboardToggle.click();
	}

	public void clickNext() {
		nextBtn.click();
		scrollToTop();
	}

	public void scrollToTop() {

		int width = driver.manage().window().getSize().width;
		int height = driver.manage().window().getSize().height;

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence swipe = new Sequence(finger, 1);

		swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), width / 2, height / 3));

		swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));

		swipe.addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), width / 2,
				height - 200));

		swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(swipe));
	}

	public boolean isFridayTab() {
		try {
			return friTabActive.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void clickOkOnFirstPopup() {
		okButton.click();
	}

	public boolean isSuccessMessageVisible() {
		return successMessage.isDisplayed();
	}

	public boolean isGoHomeButtonVisible() {
		return goHomeButton.isDisplayed();
	}

	public void clickGoHome() {
		goHomeButton.click();
	}

	public void clickSubmit() {
		submitBtn.click();
	}

}