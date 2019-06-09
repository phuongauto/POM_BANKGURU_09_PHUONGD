package bankguru.pageFactorySelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;

public class HomePageFactorySelenium extends AbstractPage{
	private WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")
	private WebElement welcomeMessage;
	
	public HomePageFactorySelenium(WebDriver mappingDriver) {
		driver = mappingDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isWelcomeMessageDisplayed() {
		return welcomeMessage.isDisplayed();
	}
	
}
