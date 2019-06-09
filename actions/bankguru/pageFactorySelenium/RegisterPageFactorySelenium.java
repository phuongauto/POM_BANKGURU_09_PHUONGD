package bankguru.pageFactorySelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;

public class RegisterPageFactorySelenium extends AbstractPage{
	private WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//input[@name='emailid']")
	private WebElement emailIDTextbox;
	
	@FindBy(how = How.NAME, using ="btnLogin")
	private WebElement submitButton;
	
	@FindBy(how = How.XPATH, using = "//td[text()='User ID :']/following-sibling::td")
	private WebElement userIDText;
	
	@FindBy(how = How.XPATH, using = "//td[text()='Password :']/following-sibling::td")
	private WebElement passwordText;
	
	public RegisterPageFactorySelenium(WebDriver mappingDriver) {
		driver = mappingDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isRegisterPageDisplayed() {
		return emailIDTextbox.isDisplayed();
	}
	
	public void inputToEmailIDTextbox(String email) {
		emailIDTextbox.sendKeys(email);
	}
	
	public void clickToLoginButton() {
		submitButton.click();
	}
	
	public void openLoginPage(String loginPageUrl) {
		openAnyURL(driver, loginPageUrl);
	}
	
	public String getUserIDInfor() {
		return userIDText.getText();
	}
	
	public String getPasswordInfor() {
		return passwordText.getText();
	}
	
}
