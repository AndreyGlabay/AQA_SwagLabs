package com.saucedemo.test;

import com.saucedemo.pageobject.InventoryPage;
import com.saucedemo.pageobject.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class SwagLabsTest { // (2.3) Create the test class and realize 3 tests, using Page Objects;

    // private static WebDriver driver; // (5.a) Due to ISSUE-2 move the driver's declaration to each test method;
    public String gridUrl = "http://192.168.0.102:4444";
    public String baseUrl = "https://www.saucedemo.com/v1";

    // CHECK SUCCESS LOGIN
    @Test (testName = "TC1_GC_StandardUser")
    public void SuccessLoginStandardUser() throws MalformedURLException, InterruptedException {
        // (2.4.a) Create new instance for GC browser;
        ChromeOptions options = new ChromeOptions();

        // (2.4.b) Initialize driver on Selenium Grid using GC options;
        // (5.a) Due to ISSUE-2 move the driver's declaration to each test method;
        WebDriver driver = new RemoteWebDriver(new URL(gridUrl), options);
        driver.get(baseUrl);
        var loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("inventory.html"));
        var inventoryPage = new InventoryPage(driver);
        Assert.assertEquals(inventoryPage.getNumberOfInventoryItems(), 6);

        // (5.b) & (5.c) Move delay and the driver's stop here from @AfterTest for the ISSUE-2 resolving.
        Thread.sleep(5000);
        driver.quit();
    }

    // CHECK THE ERROR MESSAGE (AFTER UNSUCCESS LOGIN OF THE LOCKED USER) CONTAINS CORRESPONDING TEXT
    @Test (testName = "TC2_FF_LockedUser")
    public void UnSuccessLoginLockedUser() throws MalformedURLException, InterruptedException {
        // (2.4.a) Create new instance for FF browser;
        FirefoxOptions options = new FirefoxOptions();

        // (2.4.b) Initialize driver on Selenium Grid using FF options;
        // (5.a) Due to ISSUE-2 move the driver's declaration to each test method;
        WebDriver driver = new RemoteWebDriver(new URL(gridUrl), options);
        var errMsg = "this user has been locked out";
        driver.get(baseUrl);
        var loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(loginPage.checkMessage(errMsg), "Error message must contain: " + errMsg);

        // (5.b) & (5.c) Move delay and the driver's stop here from @AfterTest for the ISSUE-2 resolving.
        Thread.sleep(5000);
        driver.quit();
    }

    // CHECK THAT POSSIBLE TO SEE BROKEN LINKS
    @Test (testName = "TC3_FF_ProblemUser")
    public void ShouldSeeBrokenLinks() throws MalformedURLException, InterruptedException {
        // (2.4.a) Create new instance for FF browser;
        FirefoxOptions options = new FirefoxOptions();

        // (2.4.b) Initialize driver on Selenium Grid using FF options;
        // (5.a) Due to ISSUE-2 move the driver's declaration to each test method;
        WebDriver driver = new RemoteWebDriver(new URL(gridUrl), options);
        driver.get(baseUrl);
        var loginPage = new LoginPage(driver);
        loginPage.login("problem_user", "secret_sauce");
        Assert.assertTrue(driver.getCurrentUrl().endsWith("inventory.html"));
        var inventoryPage = new InventoryPage(driver);
        List<String> images = inventoryPage.getImageUrls();

        // (5.b) & (5.c) Move delay and the driver's stop here from @AfterTest for the ISSUE-2 resolving.
        Thread.sleep(5000);
        driver.quit();
    }
}
