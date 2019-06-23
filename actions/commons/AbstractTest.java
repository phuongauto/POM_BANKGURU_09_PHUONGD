package commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

/* các hàm dùng chung cho tầng testcases */

public class AbstractTest {
	private WebDriver driver;

	// cho may MAC: System.setProperty("webdriver.gecko.driver",
	// "resources/geckodriver");

	protected WebDriver openMultiBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("chromeheadless")) {
			System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1366x768");
			driver = new ChromeDriver(options);
		}
		// else if(browserName.equalsIgnoreCase("ie")) {
		// System.setProperty("webdriver.ie.driver",
		// ".\\resources\\IEDriverServer.exe");
		// driver = new InternetExplorerDriver();
		// }

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(Constants.DEV_APP_URL);
		return driver;
		// xem đoạn 01h:50phut của video để biết tại sao lại return driver nhé
	}
}