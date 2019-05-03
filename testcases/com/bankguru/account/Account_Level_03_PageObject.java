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

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Account_Level_03_PageObject {
	WebDriver driver;
	String userIDInfor, passwordInfor, loginPageUrl, email;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	@BeforeTest
	public void beforeTest() {
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
		// do ở test case này mình phải mở Login Page ra đầu tiên, nên phải khai báo khởi tạo để driver được map qua lại
		loginPage = new LoginPageObject(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPageUrl = loginPage.getLoginPageUrl();
		loginPage.clickToHereLink();
		// => qua Register Page, do đó phải khai báo khởi tạo để driver được map qua lại
		registerPage = new RegisterPageObject(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		registerPage.inputToEmailIDTextbox(email);
		registerPage.clickToLoginButton();
		userIDInfor = registerPage.getUserIDInfor();
		passwordInfor = registerPage.getPasswordInfor();
		System.out.println("User: " + userIDInfor + " - Password: " + passwordInfor);

	}

	@Test
	public void TC02_LoginToSystem() {
		// đang ở Register Page thì mở Login Page ra
		registerPage.openLoginPage(loginPageUrl);
		
		loginPage = new LoginPageObject(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.inputToUserIDTextbox(userIDInfor);
		loginPage.inputToPasswordTextbox(passwordInfor);
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		Assert.assertTrue(homePage.isUserIDDisplayed(userIDInfor));

	}

	// @Test
	public boolean TC03(WebElement homePage) {
		homePage = driver.findElement(By.xpath(""));
		return homePage.isDisplayed();
	}

	@Test
	public void TC04() {

	}

	@AfterTest
	public void afterTest() {
		driver.quit();

	}

}
