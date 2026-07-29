package pages;

import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
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

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Cash\"]")
	private WebElement clickCashProducts;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Credit\"]")
	private WebElement clickCreditProducts;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Both\"]")
	private WebElement clickBothProducts;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Prepare Order\"]")
	private WebElement prepareOrderText;
	
	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Search\"]")
	private WebElement searchProductInput;

	@AndroidFindBy(xpath = "(//android.view.View[@content-desc=\"Details\"])[1]")
	private WebElement clickFirstMatchedProduct;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Search Product\"]")
	private WebElement clickSearchProduct;

	@AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Search\"]")
	private WebElement searchInputField;

	@AndroidFindBy(xpath = "(//android.widget.ImageView[@content-desc=\"Add\"])[1]")
	private WebElement clickAddProduct;

	@AndroidFindBy(xpath = "//android.widget.EditText[@text=\"\"]")
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
	
	//----------------------------------------------------------
	
	// No Data Available text (shown when search returns empty)
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"No Data Available\")")
    private WebElement txtNoDataAvailable;
    
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Order saved to local storage successfully. Don't forgot to sync when you get online\")")
    private WebElement txtDraftSaveSuccessMessage;

 
    // Filter button 
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.View\").instance(12)")
    private WebElement btnFilter;
	

 // Filter button 
    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Filter\")")
    private WebElement listBtnFilter;
    // Name input field
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(0)")
    private WebElement inputName;
 
    // Sub Market dropdown 
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(2)")
    private WebElement dropdownSubMarket;
    
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(1)")
    private WebElement listDropdownSubMarket;
 
    // Rampura Submarket 1 option in dropdown
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Rampura Submarket 1\")")
    private WebElement optionRampuraSubmarket1;
 
    // Has Overdue toggle
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Has Overdue-\")")
    private WebElement toggleHasOverdue;
 
    // Apply Filter button
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Apply Filter\")")
    private WebElement btnApplyFilter;
    
    // Apply order save
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Save\")")
    private WebElement btnSave;
    
    // Draft Tab Click
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Draft\")")
    private WebElement draftTabClick;
   
    // Go Home
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Go Home\")")
    private WebElement goHomeClick;
    
    // List
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"List\")")
    private WebElement listOption;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Order Details\"]")
	private WebElement listOrderText;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Edit Order\"]")
	private WebElement editOrderText;
    
    // Edit Order
    
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Search Product\")")
    private WebElement searchProductField;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Attachment\")")
    private WebElement attachmentOption;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Update Order\")")
    private WebElement updateOrderButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Remarks\")")
    private WebElement remarksField;
    
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Yes\"]")
   	private WebElement yesOrderBtn;
    
    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Your Order Have been Updated Successfully\")")
    private WebElement txtUpdatedSuccessMessage;
    
    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").instance(2)")
    private WebElement orderIdField;
	
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Custom\")")
    private WebElement customDateOption;
    
    private static final DateTimeFormatter ISO_FMT = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-MM-dd
    private static final DateTimeFormatter DISPLAY_FMT =
            DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy", Locale.ENGLISH); // Wednesday, July 1, 2026
    private static final DateTimeFormatter MONTH_YEAR_FMT =
            DateTimeFormatter.ofPattern("MMMM yyyy", Locale.ENGLISH); // July 2026

    @FindBy(xpath = "//android.widget.TextView[@text='Ok']")
    private WebElement okButton;
    
    
//    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Order Successful!\")")
//    private WebElement txtOrderSuccessMessage;
    @AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Successful\")")
    private WebElement txtOrderSuccessMessage;

	//-----------------------------------------------------
    public boolean isOrderSuccessMessageDisplayed() {
        return txtOrderSuccessMessage.isDisplayed();
    }

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
	
	public void selectCashAndBothProducts() {
		clickCash.click();
		clickBothProducts.click();
	}
	
	public void selectCreditAndBothProducts() {
		clickCredit.click();
		clickBothProducts.click();
	}

	public String getPrepareOrderText() {
		return prepareOrderText.getText();
	}
	
	public String getOrderListText() {
		return listOrderText.getText();
	}


	
	public void enterSearchRetailer(String retailerName) {
	    searchProductInput.click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement actualSearchInput = wait.until(
	        ExpectedConditions.presenceOfElementLocated(
	            By.xpath("//android.widget.EditText[not(@text='Search')]")
	        )
	    );
	    actualSearchInput.click();
	    actualSearchInput.clear();
	    actualSearchInput.sendKeys(retailerName);
	}

	public void clickMatchedRetailer() {
		clickFirstMatchedProduct.click();
	}


	public void clickSearchProduct(String productName) {
	    clickSearchProduct.click();
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement searchBox = wait.until(
	        ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.EditText"))
	    );
	    searchBox.sendKeys(productName);
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

	// Scoll and click "Add Date" for all visible invoices
	public void clickAddDateForAllInvoices(String date) throws InterruptedException {
		int processedCount = 0;
		int maxScrollAttempts = 3;
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
	
	//------------------Filter----------------------
	
	public List<HashMap<String, String>> scrapeAllRetailersOnPage() throws InterruptedException {
	    List<HashMap<String, String>> allRetailers = new ArrayList<>();
	    List<String> seenCodes = new ArrayList<>();
	    int maxScrollAttempts = 3;
	    int scrollAttempts = 0;

	    while (scrollAttempts < maxScrollAttempts) {

	        boolean foundNew = false;
	        int cardIndex = 1;

	        while (true) {

	        	String cardXPath =
	        		    "//android.widget.FrameLayout[@resource-id='android:id/content']"
	        		    + "/r3.x0[2]/android.view.View/android.view.View[4]"
	        		    + "/android.view.View[" + cardIndex + "]";
	            List<WebElement> cards = driver.findElements(By.xpath(cardXPath));

	            if (cards.isEmpty()) {
	                System.out.println("No card at index " + cardIndex + ", stopping inner loop.");
	                break;
	            }

	            WebElement cardContainer = cards.get(0);

	            try {
	                // Retailer Code as unique key
	                List<WebElement> codeEls = cardContainer.findElements(
	                    By.xpath(".//android.widget.TextView[starts-with(@text, '2')]")
	                );

	                if (codeEls.isEmpty()) {
	                    cardIndex++;
	                    continue;
	                }

	                String retailerCode = codeEls.get(0).getText();

	                if (seenCodes.contains(retailerCode)) {
	                    cardIndex++;
	                    continue;
	                }

	                // ---- Check if financial fields are fully loaded ----
	                List<WebElement> overdueLabel = cardContainer.findElements(
	                    By.xpath(".//android.widget.TextView[@text='Overdue']")
	                );

	                if (overdueLabel.isEmpty()) {
	                    // Card not fully visible — scroll it into view and wait
	                    System.out.println("Card " + cardIndex + " not fully loaded, scrolling into view...");
	                    ((JavascriptExecutor) driver).executeScript(
	                        "arguments[0].scrollIntoView(true);", cardContainer
	                    );
	                    Thread.sleep(1500);

	                    // Re-fetch the card after scroll
	                    cards = driver.findElements(By.xpath(cardXPath));
	                    if (cards.isEmpty()) {
	                        cardIndex++;
	                        continue;
	                    }
	                    cardContainer = cards.get(0);
	                }

	                // ---- Extract all fields ----
	                String name        = getText(cardContainer, "(.//android.widget.TextView)[1]");
	                String phone       = getText(cardContainer, "(.//android.widget.TextView)[2]");
	                String submarket   = getText(cardContainer,
	                    ".//android.widget.TextView[contains(@text, 'Submarket')]");
	                String creditLimit = getText(cardContainer,
	                    ".//android.widget.TextView[@text='Credit Limit']"
	                    + "/following-sibling::android.widget.TextView[1]");
	                String creditDays  = getText(cardContainer,
	                    ".//android.widget.TextView[@text='Credit Days']"
	                    + "/following-sibling::android.widget.TextView[1]");
	                String balance     = getText(cardContainer,
	                    ".//android.widget.TextView[@text='Balance']"
	                    + "/following-sibling::android.widget.TextView[1]");
	                String due         = getText(cardContainer,
	                    ".//android.widget.TextView[@text='Due']"
	                    + "/following-sibling::android.widget.TextView[1]");
	                String overdue     = getText(cardContainer,
	                    ".//android.widget.TextView[@text='Overdue']"
	                    + "/following-sibling::android.widget.TextView[1]");

	                // ---- If still N/A, scroll down once more and retry ----
	                if (overdue.equals("N/A")) {
	                    System.out.println("Overdue still N/A for card " + cardIndex + ", retrying after scroll...");
	                    scrollDown();
	                    Thread.sleep(1500);

	                    cards = driver.findElements(By.xpath(cardXPath));
	                    if (!cards.isEmpty()) {
	                        cardContainer = cards.get(0);
	                        overdue     = getText(cardContainer,
	                            ".//android.widget.TextView[@text='Overdue']"
	                            + "/following-sibling::android.widget.TextView[1]");
	                        creditLimit = getText(cardContainer,
	                            ".//android.widget.TextView[@text='Credit Limit']"
	                            + "/following-sibling::android.widget.TextView[1]");
	                        creditDays  = getText(cardContainer,
	                            ".//android.widget.TextView[@text='Credit Days']"
	                            + "/following-sibling::android.widget.TextView[1]");
	                        balance     = getText(cardContainer,
	                            ".//android.widget.TextView[@text='Balance']"
	                            + "/following-sibling::android.widget.TextView[1]");
	                        due         = getText(cardContainer,
	                            ".//android.widget.TextView[@text='Due']"
	                            + "/following-sibling::android.widget.TextView[1]");
	                        submarket   = getText(cardContainer,
	                            ".//android.widget.TextView[contains(@text, 'Submarket')]");
	                    }
	                }

	                // ---- Store ----
	                HashMap<String, String> retailerData = new HashMap<>();
	                retailerData.put("name",         name);
	                retailerData.put("phone",        phone);
	                retailerData.put("submarket",    submarket);
	                retailerData.put("retailerCode", retailerCode);
	                retailerData.put("creditLimit",  creditLimit);
	                retailerData.put("creditDays",   creditDays);
	                retailerData.put("balance",      balance);
	                retailerData.put("due",          due);
	                retailerData.put("overdue",      overdue);

	                allRetailers.add(retailerData);
	                seenCodes.add(retailerCode);
	                foundNew = true;

	                System.out.println("=========================================");
	                System.out.println("Index        : " + cardIndex);
	                System.out.println("Name         : " + name);
	                System.out.println("Phone        : " + phone);
	                System.out.println("Submarket    : " + submarket);
	                System.out.println("Retailer Code: " + retailerCode);
	                System.out.println("Credit Limit : " + creditLimit);
	                System.out.println("Credit Days  : " + creditDays);
	                System.out.println("Balance      : " + balance);
	                System.out.println("Due          : " + due);
	                System.out.println("Overdue      : " + overdue);
	                System.out.println("=========================================");

	            } catch (Exception e) {
	                System.out.println("Skipped card index " + cardIndex + " | Reason: " + e.getMessage());
	            }

	            cardIndex++;
	        }

	        if (!foundNew) {
	            scrollAttempts++;
	            System.out.println("No new retailers, scrolling... attempt " + scrollAttempts);
	            scrollDown();
	            Thread.sleep(1200);
	        } else {
	            scrollAttempts = 0;
	            scrollDown();
	            Thread.sleep(1000);
	        }
	    }

	    System.out.println("Total retailers scraped: " + allRetailers.size());
	    return allRetailers;
	}

	private String getText(WebElement parent, String xpath) {
	    try {
	        return parent.findElement(By.xpath(xpath)).getText();
	    } catch (Exception e) {
	        return "N/A";
	    }
	}

	
	public boolean isNoDataAvailableDisplayed() {
        return txtNoDataAvailable.isDisplayed();
    }
 
    public void clickFilter() {
        btnFilter.click();
    }
 
    public void enterName(String name) {
        inputName.clear();
        inputName.sendKeys(name);
    }
 
    public void clickSubMarketDropdown() {
        dropdownSubMarket.click();
    }
    public void clickListSubMarketDropdown() {
    	listDropdownSubMarket.click();
    }

    public void selectSubMarket(String subMarketName) {
        driver.findElement(
            AppiumBy.androidUIAutomator("new UiSelector().text(\"" + subMarketName + "\")")
        ).click();
    }
 
    public void clickHasOverdue() {
        toggleHasOverdue.click();
    }
 
    public void clickApplyFilter() {
        btnApplyFilter.click();
    }

    public boolean enterSearchRetailerBoolean(String retailerName) {
        try {
            System.out.println("Searching for retailer: " + retailerName);

            WebElement searchBox = driver.findElement(By.xpath("//android.widget.EditText[not(@text='Search')]"));
            searchBox.clear();
            searchBox.sendKeys(retailerName);
            System.out.println("Typed retailer name in search box");

            // Wait for DOM to settle after typing
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//android.widget.TextView[contains(@text, '" + retailerName + "')]")
            ));

            // Re-find fresh list after wait
            List<WebElement> results = driver.findElements(
                By.xpath("//android.widget.TextView[contains(@text, '" + retailerName + "')]")
            );

            System.out.println("Matching results found: " + results.size());

            if (results.isEmpty()) {
                System.out.println("❌ No retailer found with name: " + retailerName);
                return false;
            }

            // Get text BEFORE click, then click
            String selectedRetailer = results.get(0).getText();
            results.get(0).click();
            System.out.println("✅ Clicked on retailer: " + selectedRetailer);
            return true;

        } catch (Exception e) {
            System.out.println("❌ Exception in enterSearchRetailerBoolean: " + e.getMessage());
            return false;
        }
    }
    
    public void clickSaveDraft() {
    	btnSave.click();
    }
    
    //Get draft save success message
    
    public boolean isDraftSuccessMessageDisplayed() {
        return txtDraftSaveSuccessMessage.isDisplayed();
    }
    
 // save
    public void clickDraftTab() {
    	draftTabClick.click();
    }
    
    // Go Home
    public void clickGoHome() {
    	goHomeClick.click();
    }
    
    //Order  List
    public void selectList() {
        listOption.click();
    }
    
    public void editOrder() {
    	editOrderText.click();
    }
    
    // Scoll and click order by orderId
    public void clickOrderByOrderId(String orderId) throws InterruptedException {
        int maxScrollAttempts = 5;
        int scrollAttempts = 0;
        boolean found = false;

        while (scrollAttempts < maxScrollAttempts && !found) {

            List<WebElement> orderElements = driver.findElements(
                    By.xpath("//android.widget.TextView[@text='" + orderId + "']"));

            if (!orderElements.isEmpty()) {
                try {
                    WebElement orderText = orderElements.get(0);
                    Point location = orderText.getLocation();
                    Dimension size = orderText.getSize();

                    int tapX = location.getX() + (size.getWidth() / 2);
                    int tapY = location.getY() + (size.getHeight() / 2);

                    PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
                    Sequence tap = new Sequence(finger, 1);
                    tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                            PointerInput.Origin.viewport(), tapX, tapY));
                    tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
                    tap.addAction(new Pause(finger, Duration.ofMillis(100)));
                    tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

                    driver.perform(Collections.singletonList(tap));

                    System.out.println("Tapped order row: " + orderId);
                    found = true;

                } catch (Exception e) {
                    System.out.println("Error tapping order " + orderId + ": " + e.getMessage());
                }
            } else {
                System.out.println("Order not visible, scrolling... attempt " + (scrollAttempts + 1));
                scrollDown();
                Thread.sleep(1200);
                scrollAttempts++;
            }
        }

        if (!found) {
            System.out.println("Order ID not found after scrolling: " + orderId);
        }
    }
    
    // Edit Order Page 
    
    public void yesOrder() {
    	yesOrderBtn.click();
    }
    

    /**
     * Scrolls down repeatedly until "Remarks" button is visible
     * (i.e., reached the end of the Order Items table)
     */
    public void scrollToEndOfOrderItems() throws InterruptedException {
        int maxScrollAttempts = 6;
        int attempts = 0;

        while (attempts < maxScrollAttempts) {
            List<WebElement> updateBtn = driver.findElements(
                    By.xpath("//android.widget.TextView[@text='Remarks']"));

            if (!updateBtn.isEmpty() && updateBtn.get(0).isDisplayed()) {
                System.out.println("Reached end of Order Items - Remarks visible");
                break;
            }

            scrollDown();
            Thread.sleep(800);
            attempts++;
        }
    }

    /**
     * Clicks the edit icon (pencil) in the Order Items row matching the given product name.
     * Dynamic - works regardless of row position.
     */
    public void clickEditIconByProductName(String productName) throws InterruptedException {
        scrollToEndOfOrderItems();

        List<WebElement> productElements = driver.findElements(
                By.xpath("//android.widget.TextView[@text='" + productName + "']"));

        if (productElements.isEmpty()) {
            System.out.println("Product not found in Order Items: " + productName);
            return;
        }

        try {
            WebElement editIcon = productElements.get(0).findElement(
                    By.xpath("./following::*[contains(@class,'ImageView') or contains(@class,'ImageButton')][1]"));

            editIcon.click();
            System.out.println("Clicked edit icon for: " + productName);

        } catch (Exception e) {
            System.out.println("Error clicking edit icon for " + productName + ": " + e.getMessage());

            // Fallback: tap by coordinates relative to the product name row
            try {
                WebElement productText = productElements.get(0);
                Point location = productText.getLocation();
                Dimension size = productText.getSize();

                int tapX = location.getX() + 430; // TODO: verify actual offset to edit icon
                int tapY = location.getY() + (size.getHeight() / 2);

                tapAtCoordinates(tapX, tapY);
                System.out.println("Tapped edit icon (fallback) for: " + productName);

            } catch (Exception e2) {
                System.out.println("Fallback tap also failed: " + e2.getMessage());
            }
        }
    }

    public void updateQuantity(String qty) {
        // TODO: Replace with actual locator for the qty input field that appears after tapping edit
        WebElement qtyInput = driver.findElement(By.xpath("//android.widget.EditText"));
        qtyInput.clear();
        qtyInput.sendKeys(qty);
    }
    
    public boolean isUpdatedSuccessMessageDisplayed() {
        return txtUpdatedSuccessMessage.isDisplayed();
    }

    // ---------- Shared helpers ----------

    private void tapAtCoordinates(int x, int y) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence tap = new Sequence(finger, 1);
        tap.addAction(finger.createPointerMove(Duration.ofMillis(0),
                PointerInput.Origin.viewport(), x, y));
        tap.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(finger, Duration.ofMillis(100)));
        tap.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(tap));
    }

    // Update Order
    public void clickUpdateOrder() {
        updateOrderButton.click();
    }

    public void clickListFilter() {
    	listBtnFilter.click();
    }
    
    public void setOrderID(String qty) {
    	orderIdField.click();
    	orderIdField.clear();
    	orderIdField.sendKeys(qty);
	}
    
    // Custom Date Filter
    
    public void clickCustomDateFilter(String fromDate, String toDate) {
        customDateOption.click();
        selectDate(fromDate);
        selectDate(toDate);
        okButton.click();
    }

    // ---------- Core logic ----------

    private void selectDate(String isoDate) {
        LocalDate target = LocalDate.parse(isoDate, ISO_FMT);
        navigateToMonth(target);

        String formatted = target.format(DISPLAY_FMT);
        By dateLocator = By.xpath(
            "//android.widget.TextView[@text='" + formatted + "' or @text='Today, " + formatted + "']"
        );
        driver.findElement(dateLocator).click();
    }

    private void navigateToMonth(LocalDate target) {
        YearMonth targetYearMonth = YearMonth.from(target);
        String targetMonthYear = target.format(MONTH_YEAR_FMT);
        int maxSwipes = 60;
        int attempts = 0;

        while (attempts < maxSwipes) {
            List<WebElement> headers = driver.findElements(
                By.xpath("//android.widget.TextView[@text='" + targetMonthYear + "']")
            );
            if (!headers.isEmpty()) {
                return;
            }

            YearMonth currentVisible = getCurrentVisibleMonth();

        
        }
        throw new RuntimeException("Could not scroll to month: " + targetMonthYear);
    }

    private YearMonth getCurrentVisibleMonth() {
        List<WebElement> possibleHeaders = driver.findElements(
            By.xpath("//android.widget.TextView[contains(@text, '20')]")
        );
        for (WebElement el : possibleHeaders) {
            String text = el.getAttribute("text");
            try {
                return YearMonth.parse(text, MONTH_YEAR_FMT);
            } catch (Exception ignored) {
                // not a month header (likely a day cell), skip
            }
        }
        throw new RuntimeException("Could not find a visible month header to orient from");
    }

    
}
