package baseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

public class BaseTest {

    protected WebDriver driver = new ChromeDriver();
    protected HomePage homePage = new HomePage(driver);



    @BeforeMethod
    public void setup(){
        driver.get("https://www.ebay.com");
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
