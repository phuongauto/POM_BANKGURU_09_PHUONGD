package com.bankguru.customer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewCustomerPageObject;

public class NewCustomer {
	WebDriver driver;
	JavascriptExecutor jsexecutor;
	WebDriverWait waitExplicit;
	LoginPageObject loginPage;
	HomePageObject homePage;
	NewCustomerPageObject newCustomerPage;
	
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4");
	}

	@Test
	public void createNewCustomer() {
		loginPage  = new LoginPageObject(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.inputToUserIDTextbox("mngr191985");
		loginPage.inputToPasswordTextbox("agUqAve");
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isWelcomeMessageDisplayed());
		
		newCustomerPage = new NewCustomerPageObject(driver);
		Assert.assertTrue(newCustomerPage.isAddNewCustomerDisplayed());
		newCustomerPage.inputToCustomerName();
		newCustomerPage.selectGenderRadioButton();
		newCustomerPage.inputToDOB();
		newCustomerPage.inputToAddress();
		newCustomerPage.inputToCity();
		newCustomerPage.inputToState();
		newCustomerPage.inputToPIN();
		newCustomerPage.inputToMobileNumber();
		newCustomerPage.inputToEmail();
		newCustomerPage.inputToPassword();
		newCustomerPage.clickToSubmit();
		
		// đã tới bước click thử submit a new customer
		
		
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	
	}

}
