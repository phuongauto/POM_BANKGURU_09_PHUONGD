package commons;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Common_RegisterToSystem extends AbstractTest {
	private WebDriver driver;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	public static String USER_ID_INFOR, PASSWORD_INFOR;
	
	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}
	
	@Parameters({"browser","url"})
	@BeforeTest
	public void beforeClass(String browserName, String urlName) {
		driver = openMultiBrowser(browserName, urlName);

		log.info("Create User: Step 01 - Open Login Page");
		loginPage = PageFactoryManager.getLoginPage(driver);

		log.info("Create User: Step 02 - Verify Login Form displayed");
		Assert.assertTrue((loginPage.isLoginFormDisplayed()));

		log.info("Create User: Step 03 - Click to 'here' link");
		registerPage = loginPage.clickToHereLink();

		log.info("Create User: Step 04 - Verify Register Page displayed");
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());

		log.info("Create User: Step 05 - Input to Email ID textbox");
		registerPage.inputToDynamicTextboxTextArea(driver, "emailid", Constants.EMAIL+ randomNumber() + "@gmail.com");

		log.info("Create User: Step 06 - Click to Submit button");
		registerPage.clickToDynamicButton(driver, "btnLogin");

		log.info("Create User: Step 07 - Get UserID and Password Infor");
		USER_ID_INFOR = registerPage.getTextDynamicTableRowName(driver, "User ID :");
		PASSWORD_INFOR = registerPage.getTextDynamicTableRowName(driver, "Password :");
		
		log.info("Create User: Step 08 - Quit Browser");
		closeBrowserAndDriver(driver);

	}

	
}
