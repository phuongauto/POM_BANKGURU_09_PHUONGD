package bankguru.pageFactorySelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;

public class LoginPageFactorySelenium extends AbstractPage {
	private WebDriver driver;
	
	@FindBy(how = How.XPATH, using = "//form[@name='frmLogin']")
	private WebElement loginForm;
	
	@FindBy(how = How.NAME, using = "uid")
	private WebElement userIDTextbox;
	
	@FindBy(how = How.XPATH, using = "//a[text()='here']")
	private WebElement hereLink;
	
	@FindBy(how = How.NAME, using = "password")
	private WebElement passwordTextbox;
	
	@FindBy(how = How.CSS, using = "//input[@name='btnLogin']")
	private WebElement loginButton;
	
	public LoginPageFactorySelenium(WebDriver mappingdriver) {
		this.driver = driver;
		PageFactory.initElements(driver, this.getClass());
	
	}
	
	public boolean isLoginFormDisplayed() {
		return true;
	}
	
	public String getLoginPageUrl() {
		return null;
	}
	
	public void clickToHereLink() {}
	
	public void inputToUserIDTextbox(String userIDInfor) {}
	
	public void inputToPasswordTextbox(String passwordInfor) {}
	
	public void clickToLoginButton() {} 
}
