package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class HiarchyAttendancePage extends AndroidActions {

    AndroidDriver driver;

    public HiarchyAttendancePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

   

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Validate']")
    private WebElement validateTab;


    // ================= First modal (Workplace / Tour Type / Selfie / Time / Place) =================

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Validate Attendance']")
    private WebElement modalTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Validate']")
    private WebElement validateSubmitButton;

    // ---------- Time radio group ----------
    // STILL UNCONFIRMED - guess based on typical layout. Verify against a real
    // dump of this modal scrolled to the bottom before trusting in CI.
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='On time']/parent::android.view.View")
    private WebElement onTimeRadio;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Late']/parent::android.view.View")
    private WebElement lateRadio;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Planned']/parent::android.view.View")
    private WebElement plannedRadio;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Unplanned']/parent::android.view.View")
    private WebElement unplannedRadio;
    // ================= Confirmation dialog ("Validate Attendance?") =================
 
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Validate Attendance?']")
    private WebElement confirmDialogTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Validate Attendance?']/following-sibling::android.view.View[.//android.widget.TextView[@text='Cancel']]")
    private WebElement cancelButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Validate Attendance?']/following-sibling::android.view.View[.//android.widget.TextView[@text='Validate']]")
    private WebElement confirmValidateButton;

    // ================= Success message =================
  
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Validation Done') or contains(@text,'Validation done')]")
    private WebElement successMessage;

    // ================= Helpers / actions =================

    public void dumpPageSource(String fileName) {
        try {
            String source = driver.getPageSource();
            java.nio.file.Files.write(java.nio.file.Paths.get(fileName), source.getBytes());
            System.out.println("Page source dumped to: " + fileName);
        } catch (Exception e) {
            System.out.println("Failed to dump page source: " + e.getMessage());
        }
    }

    public void scrollModalToEnd() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"));
    }

    public void selectTime(String value) {
        onTimeRadio.click();
        // value: "On time" or "Late"
//        if (value.equalsIgnoreCase("On time")) {
//            onTimeRadio.click();
//        } else if (value.equalsIgnoreCase("Late")) {
//            lateRadio.click();
//        } else {
//            throw new IllegalArgumentException("Unknown time value: " + value);
//        }
    }

    public void selectPlace(String value) {
        // value: "Planned" or "Unplanned"
    	  plannedRadio.click();
//        if (value.equalsIgnoreCase("Planned")) {
//            plannedRadio.click();
//        } else if (value.equalsIgnoreCase("Unplanned")) {
//            unplannedRadio.click();
//        } else {
//            throw new IllegalArgumentException("Unknown place value: " + value);
//        }
    }

    public void clickValidate() {
        validateSubmitButton.click();
    }

    public void clickConfirmValidate() {
        confirmValidateButton.click();
    }

    public void clickCancel() {
        cancelButton.click();
    }

    public boolean isConfirmDialogShown() {
        return confirmDialogTitle.isDisplayed();
    }

    public boolean isValidationDoneShown() {
        return successMessage.isDisplayed();
    }
    
    public void clickValidateTab() {
        validateTab.click();
    }

   
//    public void clickValidateAttendanceForDate(String date) {
//        By cardContainer = By.xpath(
//            "//android.widget.TextView[@text='" + date + "']/ancestor::android.view.View[1]"
//        );
//        WebElement card = driver.findElement(cardContainer);
//
//        By validateBtnInCard = By.xpath(
//            ".//android.widget.TextView[@text='Validate Attendance'] | .//android.widget.Button[.//android.widget.TextView[@text='Validate Attendance']]"
//        );
//        card.findElement(validateBtnInCard).click();
//    }
    
    public void clickValidateAttendanceForDate(String date) {
        // Scrolls the nearest scrollable container until a TextView with this
        // exact text is found, or throws if it never appears.
        driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true))"
            + ".scrollIntoView(new UiSelector().text(\"" + date + "\"))"
        ));

        By cardContainer = By.xpath(
            "//android.widget.TextView[@text='" + date + "']/ancestor::android.view.View[1]"
        );
        WebElement card = driver.findElement(cardContainer);

        By validateBtnInCard = By.xpath(
            ".//android.widget.TextView[@text='Validate Attendance'] | .//android.widget.Button[.//android.widget.TextView[@text='Validate Attendance']]"
        );
        card.findElement(validateBtnInCard).click();
    }

    public void scrollToPageEnd() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollToEnd(10)"));
    }
}