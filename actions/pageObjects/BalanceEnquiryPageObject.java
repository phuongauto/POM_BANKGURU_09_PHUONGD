package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.FundTransferPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class BalanceEnquiryPageObject extends AbstractPage {
	private WebDriver driver;

	public BalanceEnquiryPageObject(WebDriver mappingdriver) {
		driver = mappingdriver;
	}
}
