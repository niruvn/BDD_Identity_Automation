package com.pageObjects;

import com.coreLibriaries.Page;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VehicleDetailsPage extends Page {
    protected WebDriverWait wait;

    public VehicleDetailsPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }
    //jsx-3496807389
    @FindBy(xpath = "//*[@id=\"m\"]/div[2]/div[5]/div[1]/div/span/div[2]/dl[1]/dd")
    WebElement regNumber;

    @FindBy(xpath = "//*[@id=\"m\"]/div[2]/div[5]/div[1]/div/span/div[2]/dl[2]/dd")
    WebElement make;

    @FindBy(xpath = "//*[@id=\"m\"]/div[2]/div[5]/div[1]/div/span/div[2]/dl[3]/dd")
    WebElement model;

    @FindBy(xpath = "//*[@id=\"m\"]/div[2]/div[5]/div[1]/div/span/div[2]/dl[4]/dd")
    WebElement colour;

    @FindBy(xpath = "//*[@id=\"m\"]/div[2]/div[5]/div[1]/div/span/div[2]/dl[5]/dd")
    WebElement year;

    @FindBy(xpath = "//*[@id=\"m\"]/div[2]/div[12]/div/div/dl/div")
    WebElement notFoundModal;

    @FindBy(xpath = "//*[@id=\"m\"]/div[2]/div[12]/div/div/dl/div/dd[2]/a")
    WebElement modalTryAgain;

    public boolean isVehicleFound(){
        try {
            if (regNumber.isDisplayed()){
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return false;
    }

    public boolean isVehicleNotFound(){
        try {
            if (notFoundModal.isDisplayed()){
                return true;
            }
        } catch (NoSuchElementException e) {
            return false;
        }
        return false;

    }

    public void clickOnTryAgain(){
        if (isVehicleNotFound()){
            modalTryAgain.click();
        }
    }

    public String getRegNumber(){
        if (regNumber.isDisplayed()) {
            return regNumber.getText();
        }
        return "";
    }

    public String getMake(){
        if (make.isDisplayed()) {
            return make.getText();
        }
        return "";
    }

    public String getModel(){
        if (model.isDisplayed()) {
            return model.getText();
        }
        return "";
    }

    public String getColour(){
        if (colour.isDisplayed()) {
            return colour.getText();
        }
        return "";
    }

    public String getYear(){
        if (year.isDisplayed()) {
            return year.getText();
        }
        return "";
    }
}
