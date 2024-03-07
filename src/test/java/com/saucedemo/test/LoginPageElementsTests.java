package com.saucedemo.test;

import com.saucedemo.pageobject.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginPageElementsTests {
    WebDriver driver;
    public String gridUrl = "http://192.168.0.102:4444";
    public String baseUrl = "https://www.saucedemo.com/v1/";

    @AfterTest
    public void stopChromeInstance() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }

    @Test (testName = "GC_CheckElements")
    public void findElementsChrome() throws InterruptedException, MalformedURLException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var loginPage = new LoginPage(driver);                      // Make an instance of web page;
        Assert.assertTrue(loginPage.checkIsInputUsernamePresent(), "Username input is missing on the page");
        Assert.assertTrue(loginPage.checkIsInputPasswordPresent(), "Password input is missing on the page");
        Assert.assertTrue(loginPage.checkIsButtonLoginPresent(), "Login button is missing on the page");
        Assert.assertTrue(loginPage.checkIsImageLogoPresent(), "Logo image is missing on the page");
        Assert.assertTrue(loginPage.checkIsImageBotPresent(), "Bot image is missing on the page");
    }

    @Test (testName = "FF_CheckElements")
    public void findElementsFirefox() throws InterruptedException, MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for FF browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using FF;
        driver.get(baseUrl);                                        // Open web page by URL;
        var loginPage = new LoginPage(driver);                      // Make an instance of web page;
        Assert.assertTrue(loginPage.checkIsInputUsernamePresent(), "Username input is missing on the page");
        Assert.assertTrue(loginPage.checkIsInputPasswordPresent(), "Password input is missing on the page");
        Assert.assertTrue(loginPage.checkIsButtonLoginPresent(), "Login button is missing on the page");
        Assert.assertTrue(loginPage.checkIsImageLogoPresent(), "Logo image is missing on the page");
        Assert.assertTrue(loginPage.checkIsImageBotPresent(), "Bot image is missing on the page");
    }

    @Test (testName = "GC_CheckTitle")
    public void checkTitleChrome() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var title = driver.getTitle();                              // Represent the title of web page;
        Assert.assertEquals(title, "Swag Labs");
    }

    @Test (testName = "FF_CheckTitle")
    public void checkTitleFirefox() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();              // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var title = driver.getTitle();                              // Represent the title of web page;
        Assert.assertEquals(title, "Swag Labs");
    }
}
