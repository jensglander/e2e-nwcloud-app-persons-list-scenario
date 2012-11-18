package com.sap.netweaver.cloud.sample.ui.integrationtest;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


@SuppressWarnings({ "javadoc", "nls" })
public class FirefoxAppWebPageTest extends AppWebPageTestDefinition {

	@SuppressWarnings("deprecation")
	protected WebDriver createNewWebDriver() {
		FirefoxProfile profile = new FirefoxProfile();
		Proxy localhostProxy = new Proxy();
		localhostProxy.setProxyAutoconfigUrl("http://proxy:8083");
		profile.setProxyPreferences(localhostProxy);
		profile.setEnableNativeEvents(true);
		return new FirefoxDriver(profile);
	}

}