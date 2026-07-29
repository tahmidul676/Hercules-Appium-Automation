package pages;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class HiarchyLeavePage extends AndroidActions {

    AndroidDriver driver;

    public HiarchyLeavePage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Approve']")
    private WebElement approveTab;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Approve Leave Request?']")
    private WebElement confirmDialogTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Approve Leave Request?']/following-sibling::android.view.View[.//android.widget.TextView[@text='Cancel']]")
    private WebElement cancelButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Approve Leave Request?']/following-sibling::android.view.View[.//android.widget.TextView[@text='Approve']]")
    private WebElement confirmApproveButton;
    
    public void clickApproveTab() {
        approveTab.click();
    }


    public void clickApproveForDateRange(String fromDate, String toDate) {
        String dateRangeText = fromDate + " to " + toDate;

        
        driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true).instance(1))"
            + ".scrollIntoView(new UiSelector().textContains(\"" + dateRangeText + "\"))"
        ));

        By cardContainer = By.xpath(
            "//android.widget.TextView[contains(@text,\"" + dateRangeText + "\")]/parent::android.view.View"
        );
        WebElement card = driver.findElement(cardContainer);

        By approveBtnInCard = By.xpath(
            ".//android.widget.TextView[@text='Approve']/parent::android.view.View"
        );
        card.findElement(approveBtnInCard).click();
    }


    public void clickConfirmApprove() {
        confirmApproveButton.click();
    }

    public void clickCancel() {
        cancelButton.click();
    }

    public boolean isConfirmDialogShown() {
        return confirmDialogTitle.isDisplayed();
    }
    
    public boolean isToastMessageShown(String expectedPartialText) {
        try {
            WebElement toast = driver.findElement(
                AppiumBy.androidUIAutomator(
                    "new UiSelector().textContains(\"" + expectedPartialText + "\")"
                )
            );
            return toast.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}