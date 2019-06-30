package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.FundTransferPageUI;
import commons.AbstractPage;
import commons.PageFactoryManager;

public class DeleteCustomerPageObject extends AbstractPage {
	private WebDriver driver;

	public DeleteCustomerPageObject(WebDriver mappingdriver) {
		driver = mappingdriver;
	}
}
