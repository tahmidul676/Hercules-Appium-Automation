package pages;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class HiarchyTourPlanPage extends AndroidActions{
	AndroidDriver driver;

	public HiarchyTourPlanPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text='Approve']")
	private WebElement approveTab;

	// Picks the FIRST card whose title starts with "Monthly Plan" (ignores Weekly Plan etc.)
	@AndroidFindBy(xpath = "(//android.widget.TextView[starts-with(@text,'Monthly Plan')])[1]")
	private WebElement firstMonthlyPlanCard;
	
	public void clickApproveTab() {
	    approveTab.click();
	}

	public void clickFirstMonthlyPlanCard() {
	    firstMonthlyPlanCard.click();
	}
	
	 @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select All']")
	    private WebElement selectAllCheckbox;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Approve']")
	    private WebElement approveButton;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Reject']")
	    private WebElement rejectButton;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Revert']")
	    private WebElement revertButton;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Approval Confirmation']")
	    private WebElement approvalConfirmationTitle;

	    // Native AlertDialog buttons are sometimes exposed via android:id/button1|button2
	    // rather than plain text — swap to that locator if the text-based one is flaky.
	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Ok']")
	    private WebElement confirmationOkButton;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cancel']")
	    private WebElement confirmationCancelButton;

	    public void clickSelectAll() {
	        selectAllCheckbox.click();
	    }

	    public void clickApprove() {
	        approveButton.click();
	    }

	    public void clickReject() {
	        rejectButton.click();
	    }

	    public void clickRevert() {
	        revertButton.click();
	    }

	    public boolean isApprovalConfirmationVisible() {
	        try {
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	            return wait.until(ExpectedConditions.visibilityOf(approvalConfirmationTitle)).isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }

	    public void clickConfirmationOk() {
	        confirmationOkButton.click();
	    }

	    public void clickConfirmationCancel() {
	        confirmationCancelButton.click();
	    }
}
