package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;



public class TourPlanPage extends AndroidActions{
	AndroidDriver driver;

	public TourPlanPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc='Next'] | //*[@text='>']")
	    private WebElement nextArrow;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Save']")
	    private WebElement saveButton;

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Submit']")
	    private WebElement submitButton;
	  

	    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Draft\"]")
	    private WebElement draftTab;
		
	    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Select All\"]")
	    private WebElement selectAllButton;
	    
	    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Ok']")
	    private WebElement confirmationOkButton;
	    
	    public void clickConfirmationOk() {
	        confirmationOkButton.click();
	    }

	    // ---------- Public actions used by tests ----------

	    public void clickTodayCard() {
	        String todayDay = String.valueOf(LocalDate.now().getDayOfMonth());
	        By todayLocator = By.xpath("//android.widget.TextView[@text='" + todayDay + "']");
	        driver.findElement(todayLocator).click();
	    }

	    public void clickNext() {
	        nextArrow.click();
	        try {
	            Thread.sleep(1000); // let the next day's screen finish rendering
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	        scrollToTop();
	    }

	    
	    public void clickSave() {
	        saveButton.click();
	    }

	    public void clickSubmit() {
	        submitButton.click();
	    }

	    // ---------- Helper for dropdowns, kept for later use ----------

	    private void selectDropdownOption(WebElement dropdown, String optionText) {
	        dropdown.click();
	        driver.findElement(By.xpath("//android.widget.TextView[@text='" + optionText + "']")).click();
	    }
	    
	    public void clickDraftTab() {
	    	draftTab.click();
	    }
	    
	    public void clickSelectAll() {
	    	selectAllButton.click();
	    }
}
