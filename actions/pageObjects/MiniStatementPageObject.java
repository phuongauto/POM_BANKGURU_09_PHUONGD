package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.FundTransferPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class MiniStatementPageObject extends AbstractPage {
	private WebDriver driver;

	public MiniStatementPageObject(WebDriver mappingdriver) {
		driver = mappingdriver;
	}
}
