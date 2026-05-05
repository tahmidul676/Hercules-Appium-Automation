package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AndroidBase extends utils.AppiumUtils {

    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    public static Properties prop;

    @BeforeClass
    public void setUp() throws IOException {

        // Load properties
        prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "\\src\\main\\java\\properties\\data.properties");
        prop.load(fis);

        String ipAddress = System.getProperty("ipAddress") != null
                ? System.getProperty("ipAddress")
                : prop.getProperty("ipAddress");

        int port = Integer.parseInt(prop.getProperty("port"));

        // Start Appium Server
        service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\tahamidul.haque\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress)
                .usingPort(port)
                .withTimeout(Duration.ofSeconds(60))
                .build();

        service.start();

        // Get device info from ADB 
        String deviceName = prop.getProperty("AndroidDeviceName");

        // UiAutomator2 Options
        UiAutomator2Options options = new UiAutomator2Options();

        options.setDeviceName(deviceName);

        // Use UDID from adb devices
        options.setUdid(prop.getProperty("udid"));
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        
        options.setApp(System.getProperty("user.dir")
                + "\\src\\test\\java\\resources\\DSAS_UATRelease_v3_2_0_enamul_feature_money_collection_petty_cash.apk");

        /*
        options.setAppPackage("com.sslwireless.dsas");
        options.setAppActivity("com.sslwireless.dsas.ui.splash_screen.SplashScreen"); // 👈 Launcher activity
        options.setNoReset(true); // 👈 Keeps login session → auto skips to Dashboard
        */
        options.setCapability("autoGrantPermissions", true);
        options.setNewCommandTimeout(Duration.ofSeconds(300));

        // Driver init
        driver = new AndroidDriver(service.getUrl(), options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}