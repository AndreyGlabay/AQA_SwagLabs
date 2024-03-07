package com.saucedemo.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage { // (2.1) Create the test class and implement Page Object for the Login flow.
    private WebDriver driver;
    public static final String USERNAME_LOCATOR = "//*[@id=\"user-name\"]"; // XPath
    private static final String PASSWORD_LOCATOR = "//*[@id=\"password\"]"; // XPath
    private static final String BUTTON_LOCATOR = "//*[@id=\"login-button\"]"; // XPath
    private static final String MESSAGE_LOCATOR = "//h3[@data-test=\"error\"]"; // XPath
    private static final String LOGO_LOCATOR = ".login_logo"; // CSS Selector
    private static final String BOT_LOCATOR = ".bot_column"; // CSS Selector

    private WebElement inputUsername;
    private WebElement inputPassword;
    private WebElement buttonLogin;
    private WebElement messageError;
    private WebElement imageLogo;
    private WebElement imageBot;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        inputUsername = driver.findElement(By.xpath(USERNAME_LOCATOR));
        inputPassword = driver.findElement(By.xpath(PASSWORD_LOCATOR));
        buttonLogin = driver.findElement(By.xpath(BUTTON_LOCATOR));
        imageLogo = driver.findElement(By.cssSelector(LOGO_LOCATOR));
        imageBot = driver.findElement(By.cssSelector(BOT_LOCATOR));
    }

    // Check if the input Username is on the page
    public boolean checkIsInputUsernamePresent() {
        return inputUsername.isDisplayed();
    }

    // Check if the input Password is on the page
    public boolean checkIsInputPasswordPresent() {
        return inputPassword.isDisplayed();
    }

    // Check if the button Login is on the page
    public boolean checkIsButtonLoginPresent() {
        return buttonLogin.isDisplayed();
    }

    // Check if Logo image is on the page
    public boolean checkIsImageLogoPresent() {
        return imageLogo.isDisplayed();
    }

    // Check if Bot image is on the page
    public boolean checkIsImageBotPresent() {
        return imageBot.isDisplayed();
    }





    // Login flow.
    public void login(String username, String password) {
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
        buttonLogin.click();
    }

    // Get and check Error Message.
    public boolean checkMessage(String str) {
        messageError = driver.findElement(By.xpath(MESSAGE_LOCATOR));
        return (messageError.getText().contains(str));
    }
}