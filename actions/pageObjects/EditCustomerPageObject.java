package pageObjects;
import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class EditCustomerPageObject extends AbstractPage {
	WebDriver driver;
	
	public EditCustomerPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openHomePageUrl(String homePageUrl) {
		openAnyURL(driver, homePageUrl);
	}
}
