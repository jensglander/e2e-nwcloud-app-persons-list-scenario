package com.sap.netweaver.cloud.sample.ui.integrationtest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.thoughtworks.selenium.Wait;
import com.thoughtworks.selenium.Wait.WaitTimedOutException;

@SuppressWarnings({ "javadoc", "nls" })
public class AppWebPage {

	private WebDriver driver;

	@FindBy(id = "firstNameFieldId")
	protected WebElement firstNameField;

	@FindBy(id = "lastNameFieldId")
	protected WebElement lastNameField;

	@FindBy(id = "addPersonButtonId")
	protected WebElement addPersonButton;

	public String getDefaultFirstName() {
		return firstNameField.getAttribute("value");

	}

	public String getDefaultLastName() {
		return lastNameField.getAttribute("value");

	}

	public void enterFirstNameInField(String firstName) {
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
		pause(500);
	}

	public void enterLastNameInField(String lastName) {
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
		pause(500);
	}

	public void chooseAddPersonButton() {
		addPersonButton.click();
		pause(3000);
	}

	public boolean personNameIsPartOfTable(WebDriver driver, String firstName,
			String lastName) {

		WebElement firstNameFieldInTable;
		WebElement lastNameFieldInTable;

		for (int i = 0; i < 5; i++) {
			firstNameFieldInTable = driver.findElement(By
					.id("__field0-col0-row" + i));
			lastNameFieldInTable = driver.findElement(By.id("__field1-col1-row"
					+ i));

			if (firstName.equals(firstNameFieldInTable.getAttribute("value"))
					&& lastName.equals(lastNameFieldInTable
							.getAttribute("value"))) {
				return true;
			}
		}
		return false;
	}

	public static AppWebPage navigateTo(WebDriver driver, String pageUrl)
			throws Exception {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(pageUrl);
		pause(1000);
		AppWebPage appWebPage = PageFactory.initElements(driver, AppWebPage.class);
		appWebPage.setDriver(driver);
		return appWebPage;
	}

	private static Wait waitForever = new Wait() {
		@Override
		public boolean until() {
			return false;
		}
	};

	private static void pause(int waitTimeMillis) {
		try {
			waitForever.wait("Timeout reached", waitTimeMillis);
		} catch (WaitTimedOutException e) {
			// don't care
		}
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}