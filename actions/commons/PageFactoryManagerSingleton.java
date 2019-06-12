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
}
