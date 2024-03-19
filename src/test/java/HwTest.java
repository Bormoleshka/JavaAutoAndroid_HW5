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
    private final DriverFactory driverFactory = DriverFactory();
    private AndroidDriver<?> driver;
    
    @AndroidFindBy(accessibility = "Каталог")
    MobileElement catalogElement;
    
    @AndroidFindBy(id = "block_search_widget")
    MobileElement searchWidget;
    
    @AndroidFindBy(id = "search_src_text")
    MobileElement searchText;
    
    @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*id/title\").textContains(\"товаров\")")
    MobileElement foundTitle;
    
    @AndroidFindBy(id = "filter_label")
    MobileElement filterLabel;
    
    @AndroidFindBy(id = "filter_switch")
    MobileElement filterSwitch;
    
    @AndroidFindBy(id = "apply_filters")
    MobileElement applyFiltersButton;
    
    @AndroidFindBy(id = "specify_category_pager")
    MobileElement specifyCategoryPager;

    @Before
    @Step("Создание драйвера")
    public void setDriver() {
    
        driver = driverFactory.setUp();
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

   
    @Test
    @Step("Тест Мвидео")
    public void test() {
        Assert.assertFalse(catalogElement.isSelected());
        catalogElement.click();
        swipe(Direction.UP);
        swipe(Direction.DOWN);
        searchWidget.click();
        String television = "Телевизор";
        searchText.sendKeys(television);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        String foundTitleText = foundTitle.getText();
        filterLabel.click();
        filterSwitch.click();
        applyFiltersButton.click();
        String foundSaleTitleText = foundTitle.getText();
        Assert.assertNotEquals(foundTitleText, foundSaleTitleText);
        driver.lockDevice(Duration.ofSeconds(3));
        Assert.assertEquals(television, searchText.getText());
        swipe(Direction.LEFT, specifyCategoryPager);
        
        
    }

    @After
    @Step("Закрытие драйвера")
    public void tearDown() {
        driver.quit();
    }
    
    Private enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
        }
        
    @Step("Свайп {direction}")
    private void swipe(Direction direction){
        int border = 5;
        int press = 100;
        Dimension dims = driver.manage().window().getSize();
        PointOption<?> pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
        PointOption<?> pointOptionEnd;
        switch (direction) {
            case RIGHT:
                pointOptionEnd = PointOption.point(dims.width - border, dims.height / 2);
                break;
            case LEFT:
                pointOptionEnd = PointOption.point(border, dims.height /2);
                break;
            case UP:
                pointOptionEnd = PointOption.point(dims.width / 2, border);
                break;
            case DOWN:
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - border);
                break;
            default:
                throw new IllegalElementException("swipe is NOT supported");
            
        }
        
        new TouchAction<>(driver)
            .press(pointOptionStart)
            .waitAction(WaitOption.waitOptions(Duration.ofMillis(press)))
            .moveTo(pointOptionEnd)
            .release()
            .perform();
    }    
}
