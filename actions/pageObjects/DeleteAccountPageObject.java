package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.FundTransferPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class DeleteAccountPageObject extends AbstractPage {
	private WebDriver driver;

	public DeleteAccountPageObject(WebDriver mappingdriver) {
		driver = mappingdriver;
	}
}
