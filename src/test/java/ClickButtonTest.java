import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
public class ClickButtonTest {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testClickButton(){
        driver.get("https://demoqa.com/elements");

        WebElement buttonsOption = driver.findElement(By.cssSelector("li#item-4"));
        buttonsOption.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

        List<WebElement> buttons = driver.findElements(By.cssSelector("div.mt-4 button.btn.btn-primary"));
        for (WebElement button : buttons) {
            if (button.getText().equals("Click Me")) {
                button.click();
                break;
            }
        }

        WebElement message = driver.findElement(By.id("dynamicClickMessage"));
        Assert.assertTrue(message.isDisplayed());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));
    }

    @AfterClass
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }
}
