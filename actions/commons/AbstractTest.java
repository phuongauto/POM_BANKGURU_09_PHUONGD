package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/* các hàm dùng chung cho tầng testcases */

public class AbstractTest {
	private WebDriver driver;
	protected final Log log;

	public AbstractTest() {
		log = LogFactory.getLog(getClass());
	}

	public int randomNumber() {
		Random random = new Random();
		return random.nextInt(999999);
	}

	// cho may MAC: System.setProperty("webdriver.gecko.driver",
	// "resources/geckodriver");

	protected WebDriver openMultiBrowser(String browserName, String url) {
		if (browserName.equalsIgnoreCase("firefox")) {
			// nếu dùng FF version > 47 thì thêm lệnh này:
			// System.setProperty("webdriver.gecko.driver",".\\resources\\geckodriver.exe");
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
		} else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", ".\\resources\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		// Các cái hằng số này định nghĩa trong file Constants.java để dễ thay đổi
		if (url.equalsIgnoreCase("bankguru")) {
			driver.get(Constants.BANKGURU_DEV_APP_URL);
		} else if (url.equalsIgnoreCase("livegurufrontend")) {
			driver.get(Constants.LIVEGURU_FRONT_END);
		} else if (url.equalsIgnoreCase("livegurubackend")) {
			driver.get(Constants.LIVEGURU_BACK_END);
		}

		driver.manage().timeouts().implicitlyWait(Constants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		if (driver.toString().toLowerCase().contains("internet explorer")) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return driver;
		// xem đoạn 01h:50phut của video để biết tại sao lại return driver nhé
	}

	public void closeBrowserAndDriver(WebDriver driver) {
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name= " + osName);

			String cmd = "";
			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}

			if (driver.toString().toLowerCase().contains("chrome")) {
				if (osName.toLowerCase().contains("mac")) {
					cmd = "pkill chromedriver";
				} else if (osName.toString().toLowerCase().contains("windows")) {
					cmd = "taskkill/F /FI\"IMAGENAME eq chromedriver*\"";
				}
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			}
			if (driver.toString().toLowerCase().contains("internetexplorer")) {
				if (osName.toLowerCase().contains("windows")) {
					cmd = "taskkill/F /FI\"IMAGENAME eq chromedriver*\"";
					Process process = Runtime.getRuntime().exec(cmd);
					process.waitFor();
				}
			}
			log.info("============= QUIT BROWSER SUCCESSFULLY =============");
		} catch (Exception e) {
			log.info(e.getMessage());
		}

	}
	
	private boolean checkPassed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true)
				log.info("============= PASSED =============");
			else
				log.info("============= FAILED =============");
			Assert.assertTrue(condition);
		} catch (Throwable e) {
			pass = false;
			// Add failure to ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkPassed(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {
			if (condition == false)
				log.info("============= PASSED =============");
			else
				log.info("============= FAILED =============");
			Assert.assertFalse(condition);
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		boolean status;
		try {
			if (actual instanceof String && expected instanceof String) {
				actual = actual.toString().trim();
				log.info("Actual = " + actual);
				expected = expected.toString().trim();
				log.info("Expected = " + expected);
				status = (actual.equals(expected));
			} else {
				status = (actual == expected);
			}

			log.info("Compare value = " + status);
			if (status) {
				log.info("============= PASSED =============");
			} else {
				log.info("============= FAILED =============");
			}
			Assert.assertEquals(actual, expected, "Value is not matched!");
		} catch (Throwable e) {
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
	
	public String getToday() {
		String today = getCurrentYear() + "-" + getCurrentMonth() + "-" + getCurrentDay();
		return today;
	}
	
	public String getCurrentDay() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		return String.valueOf(nowUTC.getDayOfMonth());
	}

	public String getCurrentMonth() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		int month = nowUTC.getMonthOfYear();
		if (month < 10) {
			return "0" + month;
		}
		return String.valueOf(month);
	}

	public String getCurrentYear() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
		return String.valueOf(nowUTC.getYear());
	}

	
}