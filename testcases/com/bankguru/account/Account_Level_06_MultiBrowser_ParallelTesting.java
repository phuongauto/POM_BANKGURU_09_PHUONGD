package com.bankguru.account;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bankguru.SingletonPattern.HomePageObjectSingletonPattern;
import bankguru.SingletonPattern.LoginPageObjectSingletonPattern;
import bankguru.SingletonPattern.RegisterPageObjectSingletonPattern;
import commons.AbstractTest;
import commons.PageFactoryManagerSingleton;

public class Account_Level_06_MultiBrowser_ParallelTesting extends AbstractTest {
	WebDriver driver;
	String userIDInfor, passwordInfor, loginPageUrl, email;
	LoginPageObjectSingletonPattern loginPage;
	RegisterPageObjectSingletonPattern registerPage;
	HomePageObjectSingletonPattern homePage;

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName) {
		driver = openMultiBrowser(browserName);
		email = "selenium" + randomNumber() + "@gmail.com";
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
