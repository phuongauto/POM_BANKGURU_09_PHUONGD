package commons;

import org.openqa.selenium.WebDriver;

import bankguru.SingletonPattern.HomePageObjectSingletonPattern;
import bankguru.SingletonPattern.LoginPageObjectSingletonPattern;
import bankguru.SingletonPattern.RegisterPageObjectSingletonPattern;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class PageFactoryManagerSingleton {
	private static LoginPageObjectSingletonPattern loginPage;
	private static HomePageObjectSingletonPattern homePage;
	private static RegisterPageObjectSingletonPattern registerPage;
	
	public static LoginPageObjectSingletonPattern getLoginPage(WebDriver driver) {
		return new LoginPageObjectSingletonPattern(driver);
	}
	
	public static HomePageObjectSingletonPattern gethomePage(WebDriver driver) {
			return new HomePageObjectSingletonPattern(driver);
	}
	
	public static RegisterPageObjectSingletonPattern getregisterPage(WebDriver driver) {
		return new RegisterPageObjectSingletonPattern(driver);
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
