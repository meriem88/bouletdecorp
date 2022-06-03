package com.canal.automation.bouletCorp.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;

public class Setup {
	
	public static WebDriver driver;
	
	@Before
		public void setWebDriver() {

			String browser = System.getProperty("browser");
			if (browser == null) {
				browser = "chrome";
			}
			switch (browser) {
			case "chrome":
				System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
				ChromeOptions chromeOptions = new ChromeOptions();
				chromeOptions.addArguments("['start-maximized']");
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
			default:
				throw new IllegalArgumentException("Browser \"" + browser + "\" is not supported.");

			}
		}

}
