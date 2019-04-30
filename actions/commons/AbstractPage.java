package commons;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/* các hàm dùng chung cho tầng pageObjects */

public class AbstractPage {
	WebElement element, elementSource, elementTarget;
	List<WebElement> elements;
	JavascriptExecutor javascriptExecutor;
	WebDriverWait waitExplicit;
	Actions action;
	By byLocator;
	long shortTimeout = 5;
	long longTimeout = 30;
	
	
	public void openAnyURL(WebDriver driver, String URL) {
		driver.get(URL);
		// void org.openqa.selenium.WebDriver.get(String arg0)
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
		// String org.openqa.selenium.WebDriver.getCurrentUrl()
	}

	public String getTitle(WebDriver driver) {
		return driver.getTitle();
		// String org.openqa.selenium.WebDriver.getTitle()
	}

	public String getPageSource(WebDriver driver) {
		return driver.getPageSource();
		// String org.openqa.selenium.WebDriver.getPageSource()
	}

	public void refreshPage(WebDriver driver) {
		driver.navigate().refresh();
		// void org.openqa.selenium.WebDriver.Navigation.refresh()
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
		// void org.openqa.selenium.Alert.accept()
	}

	public void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
		// void org.openqa.selenium.Alert.dismiss()

	}

	public void sendkeyAlert(WebDriver driver) {
		driver.switchTo().alert().sendKeys("Everything will be OK");
		// void org.openqa.selenium.Alert.sendKeys(String arg0)
	}

	public String getTextInAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
		// String org.openqa.selenium.Alert.getText() => do đó phải để hàm ở kiểu String
		// và có return để lấy được text của Alert

	}

	/* WebElement */
	public void clickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
		// nếu để xpath là 1 giá trị nào đó cố định thì chỉ chạy được 1 lần:
		// //input[@id='submit']
		// nhưng nếu để là 1 biến như trên thì sẽ dễ dàng click vào bất kỳ 1 element nào
		// mỗi khi ta gán cái biến đó cho 1 xpath mới.

	}

	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(value);

	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public String getSelectedItemInDropdown(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
		// String org.openqa.selenium.WebElement.getText()
		// do đó phải return và kiểu dữ liệu của hàm là kiểu String, ko phải void

	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String allItemXpath,
			String expectedvalueItem) throws Exception {
		waitExplicit = new WebDriverWait(driver, 30);
		WebElement parentDropdown = driver.findElement(By.xpath(parentXpath));
		javascriptExecutor.executeScript("arguments[0].click()", parentDropdown);
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemXpath)));
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));
		for (WebElement childElement : allItems) {
			if (childElement.getText().equals(expectedvalueItem)) {
				javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", childElement);
				Thread.sleep(2000);
				if (childElement.isDisplayed()) {
					childElement.click();
				} else {
					javascriptExecutor.executeScript("arguments[0].click()", childElement);
				}
				Thread.sleep(2000);
				break;
			}
		}
	}

	public String getAttributeValue(WebDriver driver, String locator, String attributeName) {
		element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public int countElementNumber(WebDriver driver, String locator) {
		elements = driver.findElements(By.xpath(locator));
		return elements.size();

	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		if (!element.isSelected() == true) {
			element.click();
		}
	}

	public void unCheckToCheckbox(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		if (element.isSelected() == true) {
			element.click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlSelected(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}

	public boolean isControlEnabled(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}

	public boolean closeAllWithoutParentWindows(WebDriver driver, String locator, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentID)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToChildWindowByID(WebDriver driver, String locator, String parentID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindow : allWindows) {
			if (!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String locator, String expectedTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(expectedTitle)) {
				break;
			}
		}
	}

	public void switchToIframe(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(element);

	}

	public void backToTopWindow(WebDriver driver) {
		driver.switchTo().defaultContent();

	}

	public void hoverMouseToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.moveToElement(element).perform();

	}

	public void doubleClickToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.doubleClick(element).perform();

	}

	public void rightClickToElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.contextClick(element).perform();

	}

	public void dragAndDropToElement(WebDriver driver, String locatorSource, String locatorTarget) {
		elementSource = driver.findElement(By.xpath(locatorSource));
		elementTarget = driver.findElement(By.xpath(locatorTarget));
		action = new Actions(driver);
		action.dragAndDrop(elementSource, elementTarget).perform();

	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaScript);
	}

	public void highlightElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='6px groove red'", element);
	}

	public Object clickToElementByJS(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object sendkeyToElementByJS(WebDriver driver, String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public Object removeAttributeInDOM(WebDriver driver, String locator, String attribute) {
		element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}
	
	public Object setAttributeInDOM(WebDriver driver, String locator, String attribute, String value) {
		element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].setAttribute('" + attribute + "', '" + value + "');", element);
	}
	
	public Object scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public Object navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + url + "'");
	}
	
	public  void waitForElementPresence(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, 30);
		byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		
	}
	
	public  void waitForElementVisible(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, 30);
		byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
		
	}
	
	public  void waitForElementClickable(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, 30);
		byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byLocator));
		
	}
	
	public  void waitForElementInvisible(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, 30);
		byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
		
	}
	
	public  void waitForAlertPresence(WebDriver driver) {
		waitExplicit = new WebDriverWait(driver, 30);
		waitExplicit.until(ExpectedConditions.alertIsPresent());
		
	}
}
