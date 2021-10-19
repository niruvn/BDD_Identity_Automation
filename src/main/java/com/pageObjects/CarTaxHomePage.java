package com.pageObjects;

import com.coreLibriaries.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

// All the page related elements and actions go here
public class CarTaxHomePage extends Page {

    public CarTaxHomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "vrm-input")
    WebElement regInput;

    @FindBy(className = "jsx-1164392954")
    WebElement freeChcekButton;

    public boolean isPageDisplayed() {
        return driver.getTitle().equals("Car Tax Check | Free Car Check");
    }

    public void enterRegNumber(String regNumber){
        regInput.sendKeys(regNumber);
    }

    public void submitFreeCarCheck(){
        freeChcekButton.click();
    }


}
