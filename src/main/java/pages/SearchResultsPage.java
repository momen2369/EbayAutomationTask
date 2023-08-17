package pages;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultsPage {

    private WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    //locators
    private By searchResults = By.cssSelector("ul.srp-results div.s-item__info a");
    private By manualCheckBoxFilter = By.xpath("//input[@aria-label = 'Manual']");


    public int getResultsCount() {
        return driver.findElements(searchResults).size();
    }

    /**
     * this method loops through search results, converts them to list of strings, and validating the strings against a keyword
     * @return status = false if any of the search results doesn't have the "infoValidationKeyword"
     * @throws IOException
     * @throws ParseException
     */
    public boolean validateSearchResults() throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("src/main/resources/testData/testData.json"));
        JSONObject testData = (JSONObject) obj;
        boolean status = true;

        List<String> resultsInfo = driver.findElements(searchResults).stream().map(WebElement::getText).toList();
        for(var info : resultsInfo){
            if(!info.contains((String)testData.get("infoValidationKeyword"))){
//            if(!info.contains("MX-5")){
                status = false;
                break;
            }
        }
        return status;
    }

    public void clickOnManualFilter() {
        driver.findElement(manualCheckBoxFilter).click();
    }
}
