package com.canal.automation.bouletCorp.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class TearDown {



		@After
		public void quitDriver(Scenario scenario) {

			if (scenario.isFailed()) {
				final byte[] screenshot = ((TakesScreenshot) Setup.driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "screenshot");
			}
			Setup.driver.quit();
		}
	
}
