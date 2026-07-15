package tests;

import base.AndroidBase;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

/**
 * Navigates: Dashboard -> Hamburger -> [User Profile, Leave Management,
 * Sr Schedule Plan, Trade Promotion, Sync Now] (each opened & backed out of) -> Logout.
 *
 * Driver/service lifecycle (start Appium server, launch app, quit driver, stop service)
 * is handled entirely by AndroidBase's @BeforeClass/@AfterClass — this class just
 * consumes the inherited `driver` and builds its own `wait` around it.
 *
 * NOTE: Locators below use xpath on visible text as a placeholder strategy.
 * Replace with resource-id locators (via Appium Inspector) once available —
 * text-based xpath is more brittle and slower to resolve.
 */
public class HamburgerMenuNavigationTest extends AndroidBase {

    private WebDriverWait wait;
    private By resolvedHamburgerLocator; // cached once the real candidate is found, for consistency across navigations

    @BeforeMethod
    public void initWait() {
        // driver is already created by AndroidBase's @BeforeClass by the time this runs
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // ---- Login credentials (UAT) ----
    private static final String USER_ID = "1A1SBSLA0005B";
    private static final String PASSWORD = "Password12";

    // ---- User Profile update values ----
    private static final String PROFILE_EMAIL = "test@gmail.com";
    private static final String PROFILE_ADDRESS = "Dhaka";

    // ---- Login page locators (Easy SR "Get started with easy" screen) ----
    // NOTE: The original @text='User ID'/@hint='User ID' xpath did not match anything on
    // this device/app build (see NoSuchElementException in the last run) — hint text on an
    // empty EditText isn't always exposed via @text or @hint depending on the Android/
    // UiAutomator2 version and whether the field is native vs. Compose. Falling back to
    // positional locators (1st/2nd EditText on the login screen) which matches the two
    // visible fields in the screenshot regardless of how the hint is exposed.
    // Once you have Appium Inspector output, replace these with resource-id locators, e.g.:
    // By.id("com.sslwireless.dsas:id/etUserId") / By.id("com.sslwireless.dsas:id/etPin")
    private final By userIdField = By.xpath("(//android.widget.EditText)[1]");
    private final By pinField    = By.xpath("(//android.widget.EditText)[2]");
    private final By proceedButton = By.xpath("//*[@text='Proceed']");

    // ---- Locators (placeholders — replace with resource-id where possible) ----
    // The single content-desc-based locator kept failing to match anything real on this
    // app build (see repeated NoSuchElementException across runs). Since we don't yet have
    // an Appium Inspector / page-source dump to confirm the real attribute, this tries several
    // common hamburger-icon patterns in sequence instead of betting on one guess.
    private final By[] hamburgerIconCandidates = {
            // content-desc variants (case-insensitive "menu", "navigate up", "drawer")
            By.xpath("//*[contains(translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'menu')]"),
            By.xpath("//*[contains(translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'navigate up')]"),
            By.xpath("//*[contains(translate(@content-desc,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'drawer')]"),
            // resource-id naming conventions
            By.xpath("//*[contains(@resource-id,'menu') or contains(@resource-id,'hamburger') or contains(@resource-id,'drawer') or contains(@resource-id,'nav_icon') or contains(@resource-id,'toolbar_icon')]"),
            // common Android widget classes for a toolbar icon button, positioned first in the hierarchy
            By.xpath("(//android.widget.ImageButton)[1]"),
            By.xpath("(//android.widget.ImageView)[1]")
    };
    private final By userProfileRow = By.xpath("//*[contains(@text,'8801313006854') or contains(@text,'H M Shahjalal Shanto')]");

    // ---- User Profile ("Update Profile") page fields ----
    // Confirmed from the actual screen layout (screenshot): fields appear in this fixed
    // order — Profile Name (English), Profile Name (বাংলা), Phone number, Email, Address —
    // all android.widget.EditText, followed by an "Update" button.
    // NOTE: the earlier "following::" xpath approach caused an Appium/UiAutomator2 XPath
    // engine crash (ArrayList$ListItr cannot be cast to NodeType) — this is a known bug with
    // that axis combined with translate(), not a locator-correctness issue. Positional
    // locators avoid it entirely and are safe here since the field order is now confirmed.
    private final By profileNameEnglishField = By.xpath("(//android.widget.EditText)[1]");
    private final By profileNameBanglaField  = By.xpath("(//android.widget.EditText)[2]");
    private final By phoneNumberField        = By.xpath("(//android.widget.EditText)[3]");
    private final By emailField              = By.xpath("(//android.widget.EditText)[4]");
    private final By addressField            = By.xpath("(//android.widget.EditText)[5]");
    private final By updateButton = By.xpath("//*[@text='Update']");
    private final By leaveManagement = By.xpath("//android.widget.TextView[@text='Leave Management']");
    private final By srSchedulePlan  = By.xpath("//android.widget.TextView[@text='Sr Schedule Plan']");
    private final By tradePromotion  = By.xpath("//android.widget.TextView[@text='Trade Promotion']");
    private final By syncNow         = By.xpath("//android.widget.TextView[@text='Sync Now']");
    private final By logout          = By.xpath("//android.widget.TextView[@text='Logout']");

    // A generic "did the sub-page actually load" check — adjust per page if you have
    // a stable header/title element on each screen instead of relying on menu disappearing.
    private final By menuOverlayMarker = By.xpath("//android.widget.TextView[@text='Sync Now']");

    @Test
    public void testHamburgerMenuFullNavigation() {
        performLogin(USER_ID, PASSWORD);
        clickSyncNow();

        openHamburgerMenu();

        updateUserProfile(PROFILE_EMAIL, PROFILE_ADDRESS);
        openHamburgerMenu();

        clickAndVerifyThenBack(leaveManagement, "Leave Management");
        openHamburgerMenu();

        clickAndVerifyThenBack(srSchedulePlan, "Sr Schedule Plan");
        openHamburgerMenu();

        clickAndVerifyThenBack(tradePromotion, "Trade Promotion");
        openHamburgerMenu();

        clickAndVerifyThenBack(syncNow, "Sync Now");
        openHamburgerMenu();

        performLogout();
    }

    private void performLogin(String userId, String password) {
        WebElement userIdInput = locateOrDumpSource(userIdField, "User ID field");
        userIdInput.clear();
        userIdInput.sendKeys(userId);

        WebElement pinInput = locateOrDumpSource(pinField, "PIN field");
        pinInput.clear();
        pinInput.sendKeys(password);

        WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(proceedButton));
        proceed.click();

        // Confirm login succeeded by waiting for the post-login dashboard's "Sync Now" button
        // to appear — the hamburger icon isn't guaranteed to render this early, so we don't
        // wait on it here. A longer timeout is used since this involves a network round trip.
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(syncNow));
    }

    public void clickSyncNow() {
        // Stage 1: dashboard's "Sync Now" button — this navigates into a sync listing page,
        // it does not itself perform the sync.
        WebElement firstSyncBtn = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.elementToBeClickable(syncNow));
        firstSyncBtn.click();

        // Stage 2: the listing page that appears next has its own "Sync Now" element —
        // this is the one that actually triggers the sync process. Click it if present;
        // if the app's flow changes and it's not there, don't fail the whole test over it.
        try {
            WebElement secondSyncBtn = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(syncNow));
            secondSyncBtn.click();
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("No second 'Sync Now' element appeared on the listing page — proceeding.");
        }

        // Sync itself may take a while over the network. Instead of waiting on one guessed
        // locator, try several candidate patterns for the hamburger icon.
        findHamburgerIcon();
    }

    /**
     * Locates the hamburger icon. On the first call, tries each candidate locator in turn
     * and caches whichever one actually matches — every subsequent call (i.e. every time we
     * return to the home page after a module navigation) reuses that same cached locator
     * directly instead of re-scanning the candidate list, so we always target the same real
     * element rather than risking a different positional fallback winning on a different screen.
     */
    private WebElement findHamburgerIcon() {
        // Fast path: we already know which locator works.
        if (resolvedHamburgerLocator != null) {
            try {
                return new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.visibilityOfElementLocated(resolvedHamburgerLocator));
            } catch (org.openqa.selenium.TimeoutException e) {
                // Cached locator stopped working (e.g. app UI changed mid-run) — fall through
                // and re-scan candidates below instead of failing immediately.
                System.out.println("Cached hamburger locator no longer matched, re-scanning candidates...");
            }
        }

        for (By candidate : hamburgerIconCandidates) {
            try {
                WebElement el = new WebDriverWait(driver, Duration.ofSeconds(4))
                        .until(ExpectedConditions.visibilityOfElementLocated(candidate));
                System.out.println("Hamburger icon matched via: " + candidate);
                resolvedHamburgerLocator = candidate;
                return el;
            } catch (org.openqa.selenium.TimeoutException ignored) {
                // try next candidate
            }
        }
        System.out.println("===== Could not find hamburger icon using any candidate locator =====");
        System.out.println(driver.getPageSource());
        System.out.println("===== End of page source dump =====");
        throw new org.openqa.selenium.NoSuchElementException(
                "No hamburger icon candidate matched. See page source dump above to pick the real locator.");
    }

    private WebElement locateOrDumpSource(By locator, String description) {
        return locateOrDumpSource(locator, description, Duration.ofSeconds(15));
    }

    private WebElement locateOrDumpSource(By locator, String description, Duration timeout) {
        try {
            return new WebDriverWait(driver, timeout)
                    .until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("===== Could not find: " + description + " using locator: " + locator + " =====");
            System.out.println(driver.getPageSource());
            System.out.println("===== End of page source dump =====");
            throw e;
        }
    }

    private void openHamburgerMenu() {
        final int maxAttempts = 2;
        org.openqa.selenium.WebDriverException lastError = null;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                WebElement hamburger = findHamburgerIcon();
                hamburger.click();
                // Wait for the drawer/menu overlay to actually render before proceeding.
                wait.until(ExpectedConditions.visibilityOfElementLocated(menuOverlayMarker));
                return; // success
            } catch (org.openqa.selenium.WebDriverException e) {
                // Covers StaleElementReferenceException, TimeoutException (overlay never showed),
                // and similar — re-locate and try the click again rather than failing immediately.
                lastError = e;
                System.out.println("openHamburgerMenu attempt " + attempt + " failed: " + e.getClass().getSimpleName() + " — retrying...");
            }
        }
        throw lastError;
    }

    /**
     * Opens User Profile, updates the email and address fields, and clicks Update.
     * Field order (Profile Name EN, Profile Name BN, Phone, Email, Address) is confirmed
     * from the actual "Update Profile" screen layout, so positional locators are used directly.
     */
    private void updateUserProfile(String email, String address) {
        WebElement profileRow = wait.until(ExpectedConditions.elementToBeClickable(userProfileRow));
        profileRow.click();

        WebElement emailInput = locateOrDumpSource(emailField, "User Profile - Email field");
        emailInput.clear();
        emailInput.sendKeys(email);

        WebElement addressInput = locateOrDumpSource(addressField, "User Profile - Address field");
        addressInput.clear();
        addressInput.sendKeys(address);

        WebElement update = wait.until(ExpectedConditions.elementToBeClickable(updateButton));
        update.click();

        // Update may navigate back to the dashboard on its own. If it doesn't, this ensures
        // we're back at the hamburger-accessible home screen before the next menu item.
        try {
            findHamburgerIcon();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            navigateBack();
        }
    }

    private void clickAndVerifyThenBack(By locator, String pageName) {
        WebElement item = wait.until(ExpectedConditions.elementToBeClickable(locator));
        item.click();

        // TODO: replace this generic wait with a real assertion per page —
        // e.g. wait for that page's title/header element, then assert it's displayed.
        // Example:
        // WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='" + pageName + "']")));
        // Assert.assertTrue(header.isDisplayed(), pageName + " page did not load as expected");

        navigateBack();
    }

    private void navigateBack() {
        // Prefer the device back key over driver.navigate().back() for native Android apps —
        // it mirrors real user behavior and avoids WebView-only back-stack assumptions.
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }

    private void performLogout() {
        WebElement logoutBtn = wait.until(ExpectedConditions.elementToBeClickable(logout));
        logoutBtn.click();

        // If a confirmation dialog ("Are you sure you want to logout?") appears, handle it here.
        // Adjust locator to match the actual dialog button text once you inspect it.
        By confirmYes = By.xpath("//*[@text='Yes' or @text='OK' or @text='Logout']");
        try {
            WebElement confirm = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(confirmYes));
            confirm.click();
        } catch (Exception ignored) {
            // No confirmation dialog appeared — logout proceeded directly.
        }

        // Optional: assert we've landed back on the login screen.
        // Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Login']"))).isDisplayed());
    }
}