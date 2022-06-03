package com.canal.automation.bouletCorp.aleatoires.pageObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.canal.automation.bouletCorp.utils.BasePage;

public class AleatoirePage extends BasePage {

	public AleatoirePage() {

		PageFactory.initElements(driver, this);

	}

	/* @FindBy */

	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Aléatoire')]")
	public static WebElement aleatoire;

	@FindBy(how = How.XPATH, using = "//img[@title='Facebook']")
	public static WebElement facebook;

	@FindBy(how = How.XPATH, using = "//img[@title='Twitter']")
	public static WebElement twitter;

	@FindBy(how = How.XPATH, using = "//a[@href='https://www.tumblr.com/share']")
	public static WebElement tumblr;

	/* Methods */
	public void clickOnLink() {
		aleatoire.click();
	}

	public String getPageTitle() {

		String titlePage = driver.getTitle();
		return titlePage;

	}

	public void clickfacebook() {

		facebook.click();
	}

	public void clicktwitter() {

		twitter.click();
	}

	public void clicktmblr() {

		tumblr.click();
	}

}
