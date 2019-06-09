package com.bankguru.account;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bankguru.pageFactorySelenium.HomePageFactorySelenium;
import bankguru.pageFactorySelenium.LoginPageFactorySelenium;
import bankguru.pageFactorySelenium.RegisterPageFactorySelenium;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Account_Level_04_PageFactorySelenium {
	WebDriver driver;
	String userIDInfor, passwordInfor, loginPageUrl, email;
	RegisterPageFactorySelenium registerPFS;
	HomePageFactorySelenium homePFS;
	LoginPageFactorySelenium	loginPFS;
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	@BeforeTest
	public void beforeTest() {
		// cái này cho máy MAC:
		// System.setProperty("webdriver.gecko.driver", "resources/geckodriver");
		driver = new FirefoxDriver();
		//abstractPage = new AbstractPage();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		//abstractPage.openAnyURL(driver, "http://demo.guru99.com/v4/");
		driver.get("http://demo.guru99.com/v4/");
		email = "selenium" + randomNumber() + "@gmail.com";
	}

	@Test
	public void TC01_RegisterToSystem() {
		// dùng kiểu PageObject:
		// loginPage = new LoginPageObject(driver);
		
		// dùng kiểu PageFactory(selenium):
		loginPFS = new LoginPageFactorySelenium(driver);
		
		// dùng kiểu PageObject:
		// Assert.assertTrue(loginPage.isLoginFormDisplayed());
		// dùng kiểu PageFactory(selenium):
		Assert.assertTrue(loginPFS.isLoginFormDisplayed());
		
		// dùng kiểu PageObject:
		// loginPageUrl = loginPage.getLoginPageUrl();
		// dùng kiểu PageFactory(selenium):
		loginPageUrl = loginPFS.getLoginPageUrl();
		
		// dùng kiểu PageObject:
		// loginPage.clickToHereLink();
		// dùng kiểu PageFactory(selenium):
		loginPFS.clickToHereLink();
		
		registerPFS = new RegisterPageFactorySelenium(driver);
		Assert.assertTrue(registerPFS.isRegisterPageDisplayed());
		
		registerPFS.inputToEmailIDTextbox(email);
		registerPFS.clickToLoginButton();
		userIDInfor=registerPFS.getUserIDInfor();
		passwordInfor=registerPFS.getPasswordInfor();
		
	}

	@Test
	public void TC02_LoginToSystem() {
		// đang ở Register Page thì mở Login Page ra
		registerPFS.openLoginPage(loginPageUrl);
		
		loginPFS = new LoginPageFactorySelenium(driver);
		Assert.assertTrue(loginPFS.isLoginFormDisplayed());
		
		loginPFS.inputToUserIDTextbox(userIDInfor);
		loginPFS.inputToPasswordTextbox(passwordInfor);
		loginPFS.clickToLoginButton();
		
		homePFS = new HomePageFactorySelenium(driver);
		Assert.assertTrue(homePFS.isWelcomeMessageDisplayed());

	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}
