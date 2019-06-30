package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.FundTransferPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class ChangePasswordPageObject extends AbstractPage {
	private WebDriver driver;

	public ChangePasswordPageObject(WebDriver mappingdriver) {
		driver = mappingdriver;
	}
}
