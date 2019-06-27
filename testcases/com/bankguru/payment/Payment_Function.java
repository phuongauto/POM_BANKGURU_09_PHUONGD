package com.bankguru.payment;

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

import commons.AbstractTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Payment_Function extends AbstractTest{
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
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
		email = "selenium" + randomNumber() + "@gmail.com";
	}

	
	@AfterTest
	public void afterTest() {
		closeBrowserAndDriver(driver);
	}

}
