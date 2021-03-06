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

import bankguru.SingletonPattern.HomePageObjectSingletonPattern;
import bankguru.SingletonPattern.LoginPageObjectSingletonPattern;
import bankguru.SingletonPattern.RegisterPageObjectSingletonPattern;
import commons.PageFactoryManagerSingleton;

public class Account_Level_05_PageFactoryManager_SingletonPattern {
	WebDriver driver;
	String userIDInfor, passwordInfor, loginPageUrl, email;
	LoginPageObjectSingletonPattern loginPage;
	RegisterPageObjectSingletonPattern registerPage;
	HomePageObjectSingletonPattern homePage;

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	@BeforeTest
	public void beforeTest() {
		// System.setProperty("webdriver.gecko.driver", "resources/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
		email = "selenium" + randomNumber() + "@gmail.com";
		// khởi tạo 1 lần cho LoginPageObjectSingletonPattern
		//loginPage = new LoginPageObjectSingletonPattern(driver);
		loginPage = PageFactoryManagerSingleton.getLoginPage(driver);
	}

	@Test
	public void TC01_RegisterToSystem() {
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPageUrl = loginPage.getLoginPageUrl(); 
		registerPage = loginPage.clickToHereLink();
		// registerPage = new RegisterPageObjectSingletonPattern(driver);
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		registerPage.inputToEmailIDTextbox(email);
		registerPage.clickToLoginButton();
		userIDInfor = registerPage.getUserIDInfor();
		passwordInfor = registerPage.getPasswordInfor();
		System.out.println("User: " + userIDInfor + " - Password: " + passwordInfor);
		
	}

	@Test
	public void TC02_LoginToSystem() {
		loginPage = registerPage.openLoginPage(loginPageUrl);
		// loginPage = new LoginPageObjectSingletonPattern(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.inputToUserIDTextbox(userIDInfor);
		loginPage.inputToPasswordTextbox(passwordInfor);
		homePage = loginPage.clickToLoginButton();
		// homePage = new HomePageObjectSingletonPattern(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		Assert.assertTrue(homePage.isUserIDDisplayed(userIDInfor));
		
		// nếu thêm 1 step là từ Home page ta logout ra -> login page
		// vậy sẽ phải khởi tạo Login page 1 lần nữa
		loginPage = homePage.clickToLogoutLink();
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		
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
