import io.appium.java_client.android.AndroidDriver;
import org.junit.Before;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import junit.framework.TestCase;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
public class HwTest {
    private AndroidDriver driver;
    private DriverFactory driverFactory;

    @Before
    public void setDriver() {
        driverFactory = new DriverFactory();
        driver = driverFactory.setUp();
    }

    @org.junit.Test
    @Test
    public void test() throws InterruptedException {
        Thread.sleep(Long.parseLong("5000"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}