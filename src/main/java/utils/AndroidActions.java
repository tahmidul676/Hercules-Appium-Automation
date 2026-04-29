package utils;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;

public class AndroidActions extends AppiumUtils {
	AndroidDriver driver;
	WebDriverWait wait;

	public AndroidActions(AndroidDriver driver) {
		this.driver = driver;
	}
	// Scroll Down

	public void scrollDown() {
		Dimension size = driver.manage().window().getSize();

		int startX = size.width / 2;
		int startY = (int) (size.height * 0.80); // start from 80% of screen
		int endY = (int) (size.height * 0.20); // end at 20% of screen

		PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
		Sequence scroll = new Sequence(finger, 0);

		scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
		scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		scroll.addAction(
				finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), startX, endY));
		scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		driver.perform(Collections.singletonList(scroll));
	}

	// Scroll by index using JavascriptExecutor

	public void scrollToEndPointByIndex() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		int maxScroll = 10;

		By locator = By
				.xpath("//android.widget.ScrollView/android.view.View[1]/android.view.View[8]/android.widget.EditText");

		for (int i = 0; i < maxScroll; i++) {

			if (driver.findElements(locator).size() > 0) {
				return;
			}

			js.executeScript("mobile: scrollGesture", ImmutableMap.of("left", 100, "top", 300, "width", 800, "height",
					1200, "direction", "down", "percent", 0.75));
		}

		throw new RuntimeException("Element not found after scrolling");
	}

}
