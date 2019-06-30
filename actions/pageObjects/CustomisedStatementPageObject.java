package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.FundTransferPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class CustomisedStatementPageObject extends AbstractPage {
	private WebDriver driver;

	public CustomisedStatementPageObject(WebDriver mappingdriver) {
		driver = mappingdriver;
	}
}
