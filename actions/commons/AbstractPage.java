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
import org.testng.annotations.Test;

import bankguru.AbstractPageUI;
import pageObjects.DepositPageObject;
import pageObjects.FundTransferPageObject;
import pageObjects.HomePageObject;
import pageObjects.NewAccountPageObject;

/* các hàm dùng chung cho tầng pageObjects */

public class AbstractPage {
	WebElement element, elementSource, elementTarget;
	List<WebElement> elements;
	JavascriptExecutor javascriptExecutor;
	WebDriverWait waitExplicit;
	Actions action;
	By byLocator;
	
	
	public void openAnyURL(WebDriver driver, String URL) {
		driver.get(URL);
		// void org.openqa.selenium.WebDriver.get(String arg0)
	}

	public String getCurrentPageUrl(WebDriver driver) {
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
	
	// hàm cũ khi chưa sử dụng Rest Parameter
	public void clickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
		// nếu để xpath là 1 giá trị nào đó cố định thì chỉ chạy được 1 lần:
		// //input[@id='submit']
		// nhưng nếu để là 1 biến như trên thì sẽ dễ dàng click vào bất kỳ 1 element nào
		// mỗi khi ta gán cái biến đó cho 1 xpath mới.

	}

	// hàm cũ khi chưa sử dụng Rest Parameter
	public void sendkeyToElement(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(value);
		
	}
	public void sendkeyToElement(WebDriver driver, String locator, String valueToSendkey, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(valueToSendkey);

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

	// hàm cũ khi chưa sử dụng Rest Parameter
	public String getTextElement(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.getText();
		
	}
	public String getTextElement(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		element = driver.findElement(By.xpath(locator));
		return element.getText();
		
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

	// hàm cũ khi chưa sử dụng Rest Parameter
	public boolean isControlDisplayed(WebDriver driver, String locator) {
		element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}
	public boolean isControlDisplayed(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
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

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		element = driver.findElement(By.xpath(locator));
		action = new Actions(driver);
		action.sendKeys(element, key).perform();;
		
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
		waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(byLocator));
		
	}
	
	// hàm cũ khi chưa sử dụng Rest Parameter
	public  void waitForElementVisible(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
		
	}
	
	public  void waitForElementClickable(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.elementToBeClickable(byLocator));
		
	}
	
	public  void waitForElementInvisible(WebDriver driver, String locator) {
		waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
		
	}
	
	public  void waitForAlertPresence(WebDriver driver) {
		waitExplicit = new WebDriverWait(driver, Constants.LONG_TIMEOUT);
		waitExplicit.until(ExpectedConditions.alertIsPresent());
		
	}
	
	//===========================================================================
	// Viết các hàm để mở ra 14 pages - Bài học WebDriver LifeCylce:
	public HomePageObject openHomePage(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.HOME_PAGE_LINK);
		clickToElement(driver, AbstractPageUI.HOME_PAGE_LINK);
		return PageFactoryManager.getHomePage(driver);
		
	}
	
	public DepositPageObject openDepositPage(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.DEPOSIT_LINK);
		clickToElement(driver, AbstractPageUI.DEPOSIT_LINK);
		return PageFactoryManager.getDepositPage(driver);
		
	}

	public FundTransferPageObject openFundTransferPage(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.FUND_TRANSFER_LINK);
		clickToElement(driver, AbstractPageUI.FUND_TRANSFER_LINK);
		return PageFactoryManager.getFundTransferPage(driver);
		
	}
	
	public NewAccountPageObject openNewAccountPage(WebDriver driver) {
		waitForElementVisible(driver, AbstractPageUI.NEW_ACCOUNT_LINK);
		clickToElement(driver, AbstractPageUI.NEW_ACCOUNT_LINK);
		return PageFactoryManager.getNewAccountPage(driver);
		
	}
	
	//===========================================================================
	
	// Đây là hàm đại điện ở bài học Dynamic Locator. Nó được viết ra để thay cho 14 hàm ở bài WebDriver LifeCylce.
	// Lưu ý là cần refactor lại 2 hàm con waitForElementVisible() và clickToElement()
	public AbstractPage openMultiplePage(WebDriver driver, String pageName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
		
		switch (pageName) {
		case "Manager":
			return PageFactoryManager.getHomePage(driver);
		case "New Account":
			return PageFactoryManager.getNewAccountPage(driver);
		case "Deposit":
			return PageFactoryManager.getDepositPage(driver);
		case "Fund Transfer":
			return PageFactoryManager.getFundTransferPage(driver);
		default: 
			return PageFactoryManager.getHomePage(driver);
		}
		
		/* Switch case kia có thể thay bằng cách sử dụng if else như sau:
		if(pageName.equalsIgnoreCase("Manager")) {
			return PageFactoryManager.getHomePage(driver);
		}	else if(pageName.equalsIgnoreCase("New Account")) {
			return PageFactoryManager.getNewAccountPage(driver);
		}	else if(pageName.equalsIgnoreCase("Deposit")) {
			return PageFactoryManager.getDepositPage(driver);
		}	else if(pageName.equalsIgnoreCase("Fund Transfer")) {
			return PageFactoryManager.getFundTransferPage(driver);
		}*/
		
	}
	
	// vậy ta cần refactor lại hàm này bằng cách: thêm 1 tham số String value
	public  void waitForElementVisible(WebDriver driver, String locator, String... dynamicValue) {
		waitExplicit = new WebDriverWait(driver, 30);
		locator = String.format(locator,(Object[]) dynamicValue);
		byLocator = By.xpath(locator);
		waitExplicit.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
	}
	
	// vậy ta cần refactor lại hàm này bằng cách: thêm 1 tham số String value
	public void clickToElement(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}
	
	// nếu có quá nhiều page thì không thể cứ switch case mãi được(tầm 20 page trở lên thì sao?). Ta sẽ code như sau:
	public void openMultiplePages(WebDriver driver, String pageName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
	}
	// Sau đó code ở testcase thì sẽ như sau:
	/*
		@Test
		public void TC04() {
		// Cách code khi mà có quá nhiều page, không thể define mãi vào switch case bên trong hàm openMultiplePage ở class AbstractPage được: 
		homePage.openMultiplePages(driver, "New Account");
		newAccountPage = PageFactoryManager.getNewAccountPage(driver);
		
		newAccountPage.openMultiplePages(driver, "Deposit");
		depositPage = PageFactoryManager.getDepositPage(driver);
		
		depositPage.openMultiplePages(driver, "Fund Transfer");
		fundTransferPage = PageFactoryManager.getFundTransferPage(driver);
		
		fundTransferPage.openMultiplePages(driver, "Manager");
		homePage = PageFactoryManager.getHomePage(driver);
	}
	*/
	//===========================================================================
	// Các hàm dynamic ở bài học Dynamic Element:
	public void inputToDynamicTextboxTextArea(WebDriver driver, String fieldName, String valueToSendkey) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXT_AREA_BUTTON_CHECKBOX, fieldName);
		sendkeyToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXT_AREA_BUTTON_CHECKBOX, valueToSendkey, fieldName);
	}
	
	public void clickToDynamicButtonTextboxTextArea(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXT_AREA_BUTTON_CHECKBOX, fieldName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_TEXTBOX_TEXT_AREA_BUTTON_CHECKBOX, fieldName);
	}
	
	public String getDynamicErrorMessage(WebDriver driver, String fieldName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE, fieldName);
		return getTextElement(driver, AbstractPageUI.DYNAMIC_ERROR_MESSAGE, fieldName);
	}
	
	public boolean isDynamicPageTitleDisplayed(WebDriver driver, String pageTitle) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_PAGE_TITLE, pageTitle);
		return isControlDisplayed(driver, AbstractPageUI.DYNAMIC_PAGE_TITLE, pageTitle);
	}
}
