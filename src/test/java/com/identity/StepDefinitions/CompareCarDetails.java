package StepDefinitions;

import com.coreLibriaries.GetWebDriver;
import com.coreLibriaries.PageSetup;
import com.helpers.GetCarDetailsFromFile;
import com.helpers.GetCarRegFromFile;
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
            HashMap<String, String> innerMap = new HashMap<String, String>();

            if (identityPageActions.isVehicleFound()) {
                if (identityPageActions.getRegNumber().equals(regNumber)){
                    innerMap.put("make",identityPageActions.getMake());
                    innerMap.put("model",identityPageActions.getModel());
                    innerMap.put("color",identityPageActions.getColour());
                    innerMap.put("year",identityPageActions.getYear());
                }
                else if (identityPageActions.isVehicleNotFound())  {
                    System.out.println(format("Vehicle details NOT found on the website for %s", regNumber));
                    innerMap.put("make","NOT FOUND");
                    identityPageActions.clickOnTryAgain();
                }
            }
            actualCarDetails.put(key,innerMap);
            homePage.navigateTo(homePage.APP_URL);

        }
    }

    @Test
    @Then("compare the results with the output file and highlight the mismatches")
    public void compare_the_results_with_the_output_file_and_highlight_the_mismatches() {
        System.out.println("Running Test: Compare Results");
        HashMap<String, String> outputCarDetailsMap = new HashMap<String, String>();
        HashMap<String, String> actualCarDetailsMap = new HashMap<String, String>();
        //Iterate the car details from Actual
        for (String key : actualCarDetails.keySet()) {
            System.out.println(format("Comparing the results for RegNumber %s", key));
            System.out.println("Key: " + key + ", Value: " + actualCarDetails.get(key));
            // first check outPutCarDetails has the vehicle details
            if (!outputCarDetails.containsKey(key)) {
                System.out.println(format("MIS MATCHED - No Vehicle details found for Reg: %s in OUTPUT car details",key));
                continue;
            }
            //now get the innermap for RegNumber from both maps
            outputCarDetailsMap = outputCarDetails.get(key);
            actualCarDetailsMap = actualCarDetails.get(key);
            //if the innermap of actual car details doesn't exist then report mismatch
            if (actualCarDetailsMap.size() < 1) {
                System.out.println(format("MIS MATCHED - No Vehicle details found on the website for Reg: %s ",key));
                continue;
            }
            //now iterate the innermap and compare each values
            for (String innerKey : actualCarDetailsMap.keySet()) {
                String expectedValue = actualCarDetailsMap.get(innerKey);
                String actualValue;
                if (outputCarDetailsMap.containsKey(innerKey)) {
                    actualValue = outputCarDetailsMap.get(innerKey);
                    if (expectedValue.equals(actualValue)) {
                        System.out.println(format("MATCHED - Actual - Key : %s - Value %s ",innerKey,actualValue));
                    } else {
                        System.out.println(format("MIS MATCHED - Expected %s is %s. But the actual is: %s ",innerKey,expectedValue, actualValue));
                    }
                } else {
                    System.out.println(format("No value preset in Actual for %s", innerKey));
                }
            }
        }

    }
    @After("@CompareCarDetails")
    public void cleanUp(){
        outputCarDetails = new HashMap<String, HashMap<String, String>>();
        actualCarDetails = new HashMap<String, HashMap<String, String>>();
        driver.close();
    }
}
