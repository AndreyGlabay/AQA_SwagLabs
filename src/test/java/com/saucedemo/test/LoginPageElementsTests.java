package com.saucedemo.test;

import com.saucedemo.pageobject.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginPageTests {
    WebDriver driver;
    public String gridUrl = "http://192.168.0.102:4444";
    public String baseUrl = "https://www.saucedemo.com/v1/";



//    @BeforeTest
//    public void startChromeInstance() throws MalformedURLException {
//
//    }

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
        FirefoxOptions options = new FirefoxOptions();                // New instance for GC browser;
        driver = new RemoteWebDriver(new URL(gridUrl), options);    // Driver initialization on SeleniumGrid using GC;
        driver.get(baseUrl);                                        // Open web page by URL;
        var loginPage = new LoginPage(driver);                      // Make an instance of web page;
        Assert.assertTrue(loginPage.checkIsInputUsernamePresent(), "Username input is missing on the page");
        Assert.assertTrue(loginPage.checkIsInputPasswordPresent(), "Password input is missing on the page");
        Assert.assertTrue(loginPage.checkIsButtonLoginPresent(), "Login button is missing on the page");
        Assert.assertTrue(loginPage.checkIsImageLogoPresent(), "Logo image is missing on the page");
        Assert.assertTrue(loginPage.checkIsImageBotPresent(), "Bot image is missing on the page");
    }

//    @Test (testName = "Check Title")
//    public void checkTitle() {
//        driver.get(pageLogin);
//    }
//
//    @Test (testName = "Check Login")
//    public void checkLogin() {
//        driver.get(pageLogin);
//    }

}
