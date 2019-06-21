package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.DepositPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.RegisterPageObject;

public class PageFactoryManager {
	/*private static LoginPageObject loginPage;
	private static HomePageObject homePage;
	private static RegisterPageObject registerPage;
	private static NewAccountPageObject NewAccountPage;
	private static DepositPageObject DepositPage;
	private static FundTransferPageObject FundTransferPage;*/
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	
	public static HomePageObject getHomePage(WebDriver driver) {
			return new HomePageObject(driver);
	}
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
	
	public static NewAccountPageObject getNewAccountPage(WebDriver driver) {
		return new NewAccountPageObject(driver);
	}
	
	public static DepositPageObject getDepositPage(WebDriver driver) {
		return new DepositPageObject(driver);
	}
	
	public static FundTransferPageObject getFundTransferPage(WebDriver driver) {
		return new FundTransferPageObject(driver);
	}
		
	
	
	
	
	
	
	/* Đoạn code này dùng cho đơn luồng(single thread) xem video topic 23 POM 06 Multiple browsers and Parallel Testing tầm phút 1:35
	public static LoginPageObjectSingletonPattern getLoginPage(WebDriver driver) {
		
		if(loginPage != null) {
			return loginPage;
		} else {
			return new LoginPageObjectSingletonPattern(driver);
		}
		
	}
	
	public static HomePageObjectSingletonPattern gethomePage(WebDriver driver) {
		
		if(homePage != null) {
			return homePage;
		} else {
			return new HomePageObjectSingletonPattern(driver);
		}
		
	}
	
	public static RegisterPageObjectSingletonPattern getregisterPage(WebDriver driver) {

		if(registerPage != null) {
			return registerPage;
		} else {
			return new RegisterPageObjectSingletonPattern(driver);
		}
		
	}
	*/
}
