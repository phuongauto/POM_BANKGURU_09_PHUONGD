package bankguru.pageFactorySelenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;

/*Class LoginPageFactorySelenium này ở đây là thay cho class LoginPageObject bên PageObjectModel
   Thay vì khai báo tất cả UI của LoginPage ở LoginPageUI trong folder Interfaces,
   ta khai báo toàn bộ UI trong class này luôn. Chính là đám private mà có @FindBy bên dưới */

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

	/* Cả đống private trên là thay cho đám dưới này: 
	 * public static final String LOGIN_FORM = "//form[@name='frmLogin']"; 
	 * public static final String USER_ID_TEXTBOX = "//input[@name='uid']";
	 * public static final String PASSWORD_TEXTBOX = "//input[@name='password']";
	   public static final String LOGIN_BUTTON = "//input[@name='btnLogin']";
	   public static final String RESET_BUTTON = "//input[@name='btnReset']";
	   public static final String HERE_LINK = "//a[text()='here']";
	 */

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

	public void clickToHereLink() {
		
	}

	public void inputToUserIDTextbox(String userIDInfor) {
	}

	public void inputToPasswordTextbox(String passwordInfor) {
	}

	public void clickToLoginButton() {
	}
}














