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

import commons.AbstractPage;

public class Account_Level_02_ApplyAbstractPage {
	WebDriver driver;
	String userIDInfor, passwordInfor, loginPageUrl, email;
	AbstractPage abstractPage;
	
	
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	@BeforeTest
	public void beforeTest() {
		// System.setProperty("webdriver.gecko.driver", "resources/geckodrivermac");
		driver = new FirefoxDriver();
		abstractPage = new AbstractPage();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		abstractPage.openAnyURL(driver, "http://demo.guru99.com/v4/");
		email = "selenium" + randomNumber() + "@gmail.com";
	}

	@Test
	public void TC01_RegisterToSystem() {
		//Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//form[@name='frmLogin']"));
		
		loginPageUrl = abstractPage.getCurrentPageUrl(driver);
		
		//driver.findElement(By.xpath("//a[text()='here']")).click();
		abstractPage.clickToElement(driver, "//a[text()='here']");
		
		//Assert.assertTrue(driver.findElement(By.xpath("//input[@name='emailid']")).isDisplayed());
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//input[@name='emailid']"));
		
		//driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		abstractPage.sendkeyToElement(driver, "//input[@name='emailid']", email);
		
		//driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		
		//userIDInfor = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userIDInfor = abstractPage.getTextElement(driver, "//td[text()='User ID :']/following-sibling::td");
		
		//passwordInfor = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		passwordInfor = abstractPage.getTextElement(driver, "//td[text()='Password :']/following-sibling::td");
		
		System.out.println("User: " + userIDInfor + " - Password: " + passwordInfor);

	}

	@Test
	public void TC02_LoginToSystem() {
		//driver.get(loginPageUrl);
		abstractPage.openAnyURL(driver, loginPageUrl);
		
		//Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//form[@name='frmLogin']"));
		
		//driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userIDInfor);
		abstractPage.sendkeyToElement(driver, "//input[@name='uid']", userIDInfor);
		
		//driver.findElement(By.xpath("//input[@name='password']")).sendKeys(passwordInfor);
		abstractPage.sendkeyToElement(driver, "//input[@name='password']", passwordInfor);
		
		//driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		
		//Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
		
		//Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userIDInfor + "']")).isDisplayed());
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//td[text()='Manger Id : " + userIDInfor + "']"));
		
		
	}

	//@Test
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
