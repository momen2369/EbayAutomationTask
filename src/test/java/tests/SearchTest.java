package tests;

import baseTest.BaseTest;
import io.qameta.allure.Step;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchResultsPage;

import java.io.IOException;

public class SearchTest extends BaseTest {

    @Test(description = "Ebay search validation")
    public void searchTest1() throws IOException, ParseException {
        //making sure we landed in home page
        Assert.assertTrue(homePage.getCurrentUrl().contains("ebay.com"), "URL should contain ebay.com");
        Assert.assertTrue(homePage.isLogoVisible(), "ebay logo should be visible");
        //searching for a keyword
        var resultsPage = homePage.search();
        //getting number of search results
        System.out.println("number of search results = " + resultsPage.getResultsCount());
        //validating search results
        Assert.assertTrue(resultsPage.validateSearchResults(), "all search results should contain the keyword used in the search");
        resultsPage.clickOnManualFilter();
    }

}
