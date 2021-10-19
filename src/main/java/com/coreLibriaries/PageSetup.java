package com.coreLibriaries;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.Constructor;

public class PageSetup {
    public static String APP_URL = ConfigReader.getProperty("url");
    private final WebDriver webDriver;

    public PageSetup(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public <PageObject extends Page> PageObject init(Class<PageObject> pageObjectClass) {
        return instantiatePage(webDriver, pageObjectClass);
    }
    public <PageObject extends Page> PageObject load(Class<PageObject> pageObjectClass) {
        PageObject page = instantiatePage(webDriver, pageObjectClass);
        webDriver.get(page.getRelativeUrl());
        return page;
    }
    private static <T> T instantiatePage(WebDriver driver, Class<T> pageClassToProxy) {

        try {
            try {
                Constructor<T> constructor = pageClassToProxy.getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void navigateTo(String url)
    {
        webDriver.get(url);
    }
}
