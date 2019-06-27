package com.bankguru.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bankguru.NewAccountPageUI;
import commons.AbstractTest;
import commons.PageFactoryManager;
import commons.PageFactoryManagerSingleton;
import pageObjects.DepositPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.RegisterPageObject;

public class Account_Level_08_DynamicLocator_RestParameter extends AbstractTest {
	WebDriver driver;
	String userIDInfor, passwordInfor, loginPageUrl, email;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	NewAccountPageObject newAccountPage;
	DepositPageObject depositPage;
	FundTransferPageObject fundTransferPage;
	

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	@Parameters("browser")
	@BeforeTest
	public void beforeTest(String browserName, String url) {
		driver = openMultiBrowser(browserName, url);
		email = "selenium" + randomNumber() + "@gmail.com";
		
		// Mở app ra thì nó vào trang Login
		loginPage = PageFactoryManager.getLoginPage(driver);
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
		// Register (open link của Login page) => quay về Login page
		loginPage = registerPage.openLoginPage(loginPageUrl);
		// loginPage = new LoginPageObjectSingletonPattern(driver);
		
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.inputToUserIDTextbox(userIDInfor);
		loginPage.inputToPasswordTextbox(passwordInfor);
		
		// Từ Login page => quay về Home Page
		homePage = loginPage.clickToLoginButton();
		// homePage = new HomePageObjectSingletonPattern(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		Assert.assertTrue(homePage.isUserIDDisplayed(userIDInfor));

		// nếu thêm 1 step là từ Home page ta logout ra -> login page
		// vậy sẽ phải khởi tạo Login page 1 lần nữa
		//=> loginPage = homePage.clickToLogoutLink();
		//=> Assert.assertTrue(loginPage.isLoginFormDisplayed());

	}

	//@Test
	public void TC03_OpenMultiPage() {
		/*code cũ khi mà chưa có AbstractPageUI và các hàm dùng chung trong AbstractPage
		 * // Home Page qua New Account Page
		newAccountPage = homePage.openNewAccountPage();
		
		// New Account Page qua Deposit Page
		depositPage = newAccountPage.openDepositPage();
		
		// Deposit Page qua FundTransfer Page
		fundTransferPage =  depositPage.openFundTransferPage();
		
		// FundTransfer qua lại Home Page
		homePage = fundTransferPage.openHomePage();*/
		
		// code mới sau khi đã có AbstractPageUI và các hàm dùng chung trong AbstractPage
		
		/*
		newAccountPage = homePage.openNewAccountPage(driver);
		depositPage = newAccountPage.openDepositPage(driver);
		fundTransferPage = depositPage.openFundTransferPage(driver);
		homePage = fundTransferPage.openHomePage(driver);  */
		
		// => sau khi sử dụng Rest parameter thì:
		// trước tiên code như này đã newAccountPage = homePage.openMultiplePage(driver, "New Account");
		// xong rồi add cast (NewAccountPageObject) là được như dưới
		newAccountPage = (NewAccountPageObject) homePage.openMultiplePage(driver, "New Account");
		Assert.assertTrue(newAccountPage.isNewAccountPageDisplayed());
		
		depositPage = (DepositPageObject) newAccountPage.openMultiplePage(driver, "Deposit");
		Assert.assertTrue(depositPage.isDepositPageDisplayed());
		
		fundTransferPage = (FundTransferPageObject) depositPage.openMultiplePage(driver, "Fund Transfer");
		Assert.assertTrue(fundTransferPage.isFundTransferPageDisplayed());
		
		homePage = (HomePageObject) fundTransferPage.openMultiplePage(driver, "Manager");
		Assert.assertTrue(homePage.isHomePageDisplayed());
		
	}

	@Test
	public void TC04() {
		// Cách code khi mà có quá nhiều page, không thể define mãi vào switch case bên trong hàm openMultiplePage ở class AbstractPage được: 
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage = PageFactoryManager.getNewAccountPage(driver);
		Assert.assertTrue(newAccountPage.isNewAccountPageDisplayed());
		
		newAccountPage.openMultiplePages(driver, "Deposit");
		depositPage = PageFactoryManager.getDepositPage(driver);
		Assert.assertTrue(depositPage.isDepositPageDisplayed());
		
		depositPage.openMultiplePages(driver, "Fund Transfer");
		fundTransferPage = PageFactoryManager.getFundTransferPage(driver);
		Assert.assertTrue(fundTransferPage.isFundTransferPageDisplayed());
		
		fundTransferPage.openMultiplePages(driver, "Manager");
		homePage = PageFactoryManager.getHomePage(driver);
		Assert.assertTrue(homePage.isHomePageDisplayed());
		
	}


	@AfterTest
	public void afterTest() {
		//driver.quit();

	}

}
