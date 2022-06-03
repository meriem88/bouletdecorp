package com.canal.automation.bouletCorp.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;



public class BasePage {

	protected WebDriver driver;
	protected static Logger log = LogManager.getLogger();
	
	public BasePage( ) {
		
		this.driver = Setup.driver;
}
}
