package com.coreLibriaries;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;


public class GetWebDriver {
    public static WebDriver driver;

    public static WebDriver initializeDriver() {
        String browserName = ConfigReader.getProperty("browser");
        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", ConfigReader.getProperty("chromeDriverPath"));
            driver = new ChromeDriver(new ChromeOptions());
        } else if (browserName.equals("FireFox")) {
            System.setProperty("webdriver.gecko.driver", ConfigReader.getProperty("fireFoxDriverPath"));
            driver = new FirefoxDriver();
        } else { //default load FireFox browser
            System.setProperty("webdriver.gecko.driver", ConfigReader.getProperty("fireFoxDriverPath"));
            driver = new FirefoxDriver();
//            DesiredCapabilities capabilities = null;
//            capabilities = DesiredCapabilities.firefox();
//            FirefoxOptions options = new FirefoxOptions();
//            capabilities.setJavascriptEnabled(true);
//            capabilities.setCapability("takesScreenshot", true);
//            capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
//            FirefoxDriver driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(ConfigReader.getProperty("pageLoadTimeOut")), TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(ConfigReader.getProperty("implicitlyWait")), TimeUnit.SECONDS);
        return driver;
    }

}
