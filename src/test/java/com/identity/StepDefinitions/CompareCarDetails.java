package StepDefinitions;

import com.coreLibriaries.GetWebDriver;
import com.coreLibriaries.PageSetup;
import com.helpers.GetCarDetailsFromFile;
import com.helpers.GetCarRegFromFile;
import com.helpers.compareCarDetails;
import com.pageObjects.CarTaxHomePage;
import com.pageObjects.VehicleDetailsPage;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

public class CompareCarDetails {

    WebDriver driver = GetWebDriver.initializeDriver();
    PageSetup homePage = new PageSetup(driver);
    CarTaxHomePage homePageActions = new CarTaxHomePage(driver);
    VehicleDetailsPage identityPageActions = new VehicleDetailsPage(driver);

    ArrayList<String> searchRegNumbers;
    Map<String, HashMap<String, String>> outputCarDetails;
    Map<String, HashMap<String, String>> actualCarDetails = new HashMap<String, HashMap<String, String>>();

    @Test
    @Given("^car registration numbers to read from (.*)$")
    public void carRegistrationNumbersToReadUser(String inputFile) {
        System.out.println("Running Test: Read registration numbers from Input File: " + inputFile);
        searchRegNumbers = new ArrayList(GetCarRegFromFile.read("src/main/resources/"+ inputFile));
    }

    @Test
    @And("^read car details for each registration number from (.*)$")
    public void readCarDetailsForEachRegistrationNumberAgent(String outputFile) {
        System.out.println("Running Test: Get car details from output File: " + outputFile);
        outputCarDetails = GetCarDetailsFromFile.read("src/main/resources/"+ outputFile);
    }

    @Test
    @When("I open website")
    public void i_open_website() {
        System.out.println("Running Test: Navigates to the website");
        homePage.navigateTo(homePage.APP_URL);
        Assert.assertTrue(homePageActions.isPageDisplayed());
    }

    @Test
    @When("Search car details for each registration number")
    public void search_car_details_for_each_registration_number() {
        System.out.println("Running Test: Search each registration numbers on website");
        for (String regNumber : searchRegNumbers) {
            homePageActions.enterRegNumber(regNumber);
            homePageActions.submitFreeCarCheck();
            String key = regNumber;
            actualCarDetails.put(key, identityPageActions.getVehicleDetails(driver, key));
            homePage.navigateTo(homePage.APP_URL);
        }
    }

    @Test
    @Then("compare the results with the output file and highlight the mismatches")
    public void compare_the_results_with_the_output_file_and_highlight_the_mismatches() {
        System.out.println("Running Test: Compare Results");
        compareCarDetails.compareCarCheck(outputCarDetails,actualCarDetails);

    }
    @After("@CompareCarDetails")
    public void cleanUp(){
        outputCarDetails = new HashMap<String, HashMap<String, String>>();
        actualCarDetails = new HashMap<String, HashMap<String, String>>();
        driver.close();
    }
}
