package pages;

import io.qameta.allure.Step;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.FileReader;
import java.io.IOException;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    private By logo = By.id("gh-logo");
    private By searchInput = By.id("gh-ac");
    private By searchBtn = By.id("gh-btn");


    /**
     * this method is searching for a specific keyword parsed from the test data json file
     * @return a new instance of SearchResultsPage and passes the driver
     * @throws IOException
     * @throws ParseException
     */
    public SearchResultsPage search() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("src/main/resources/testData/testData.json"));
        JSONObject testData = (JSONObject) obj;

        driver.findElement(searchInput).sendKeys((String) testData.get("searchKeyword"));
        driver.findElement(searchBtn).click();
        return new SearchResultsPage(driver);
    }
    public boolean isLogoVisible(){
        boolean visible;
        try {
            driver.findElement(logo);
             visible = true;
        }catch (NoSuchElementException e){
             visible = false;
        }
        return visible;
    }
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

}
