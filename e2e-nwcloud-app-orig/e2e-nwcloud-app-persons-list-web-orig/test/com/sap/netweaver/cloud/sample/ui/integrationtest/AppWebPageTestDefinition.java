package com.sap.netweaver.cloud.sample.ui.integrationtest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SuppressWarnings({ "javadoc", "nls" })
public abstract class AppWebPageTestDefinition {
		@Rule
		public TestName testName = new TestName();

		private AppWebPage appWebPage;

		protected static String APP_WEB_PAGE_URL;
		protected static String BUILD_TARGET_DIRECTORY_PATH;
		protected static String SCREENSHOTS_FOLDER_NAME = "screenshots";

		private static final Logger LOGGER = LoggerFactory.getLogger(AppWebPageTestDefinition.class);

		private WebDriver webDriver;

		private static String nameSupplement;

		@BeforeClass
		public static void setup() {
			readConfigurationParameters();
		}

		protected static void readConfigurationParameters() {
			APP_WEB_PAGE_URL = System.getProperty("e2e-nwcloud-app-WebPageUrl");
			if (APP_WEB_PAGE_URL == null) {
				throw new IllegalArgumentException("You haven't specified java system propery [myPageUrl]"); //$NON-NLS-1$
			}
			// this property should point to the build folder of the project ("target") and is used for screenshots (optional)
			BUILD_TARGET_DIRECTORY_PATH = System.getProperty("buildTargetDirectory");
		}

		protected abstract WebDriver createNewWebDriver();

		@Before
		public void initBrowserForTest() throws Exception {
			webDriver = createNewWebDriver();
			appWebPage = AppWebPage.navigateTo(webDriver, APP_WEB_PAGE_URL);

	        // Compute random name supplement that is appended and checked for later during the tests
	        nameSupplement = Long.toHexString(new Random(System.currentTimeMillis()).nextLong());
		}

		@Test
		public void testAddPerson() throws Exception {
	        appWebPage.enterFirstNameInField("John_" + nameSupplement);
	        appWebPage.enterLastNameInField("Smith_" + nameSupplement);
	        appWebPage.chooseAddPersonButton();

	        assertAddedPersonIsPartOfTable(appWebPage);
		}
		private void assertAddedPersonIsPartOfTable(AppWebPage appWebPage) throws Exception {
			boolean addedPersonNameIsPartOfTable = appWebPage.personNameIsPartOfTable(appWebPage.getDriver(), "John_" + nameSupplement, "Smith_" + nameSupplement);
			assertThat(addedPersonNameIsPartOfTable, is(true));
		}

		@After
		public void cleanup() throws IOException {
			takeScreenshot();
			closeAllWindows();
		}

		protected void takeScreenshot() throws IOException {
			if (BUILD_TARGET_DIRECTORY_PATH == null) {
				LOGGER.warn("No build directory configured, not taking a screenshot");
				return;
			}
			File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
			// copy it into the target sub-folder
			File targetDir = new File(BUILD_TARGET_DIRECTORY_PATH, SCREENSHOTS_FOLDER_NAME);
			if (!targetDir.exists())
				targetDir.mkdir();
			String className = this.getClass().getSimpleName();
			String methodName = this.testName.getMethodName();
			String fileName = className + "." + methodName + ".jpg";
			File targetFile = new File(targetDir, fileName);
			FileUtils.copyFile(scrFile, targetFile);
			LOGGER.info("Screenshot was taken: " + targetFile.getAbsolutePath());
		}

		private void closeAllWindows() {
			for (String windowHandle : webDriver.getWindowHandles()) {
				webDriver.switchTo().window(windowHandle).close();
			}
		}
	}
