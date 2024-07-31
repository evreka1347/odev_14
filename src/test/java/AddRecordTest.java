import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class AddRecordTest {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAddRecord(){
        driver.get("https://demoqa.com/webtables");

        WebElement addButton = driver.findElement(By.id("addNewRecordButton"));
        addButton.click();

        driver.findElement(By.id("firstName")).sendKeys("Esma");
        driver.findElement(By.id("lastName")).sendKeys("Coban");
        driver.findElement(By.id("userEmail")).sendKeys("esmacoban@gmail.com");
        driver.findElement(By.id("age")).sendKeys("31");
        driver.findElement(By.id("salary")).sendKeys("47500");
        driver.findElement(By.id("department")).sendKeys("Yazılım Departmanı");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

        driver.findElement(By.id("submit")).click();

        WebElement editButton = driver.findElement(By.cssSelector("span[title='Edit']"));
        editButton.click();
        WebElement firstNameField = driver.findElement(By.id("firstName"));
        firstNameField.clear();
        firstNameField.sendKeys("Sertan");
        driver.findElement(By.id("submit")).click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

        WebElement editedRecord = driver.findElement(By.cssSelector("div.rt-tr-group:nth-child(4) div.rt-td:nth-child(1)"));
        Assert.assertEquals(editedRecord.getText(),"Sertan");
    }

    @AfterClass
    public void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }
}
