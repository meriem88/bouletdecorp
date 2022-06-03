package com.canal.automation.bouletCorp.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;

public class Assertions extends BasePage {

	 /* testCaseStatus the status of the test case.*/
    boolean testCaseStatus = true;

    /* test status. */
    boolean testStatus;

    /* test screenshot dir. */
    private String testScreenshotDir;

    /**
     * Instanciation de assertions.
     */
    public Assertions()
    {
        super();
    }

    /**
     * method verify whether element is present on screen.
     *
     * @param targetElement element to be present
     * @return true if element is present else throws exception
     */
    public Boolean isElementPresent(By targetElement)
    {
        // Boolean isPresent = Setup.driver.findElements(targetElement).size() > 0;
        return Setup.driver.findElements(targetElement).size() > 0;
    }

    /**
     * methode Checks if is element displayed.
     *
     * @param element element web
     * @return boolean
     */
    public Boolean isElementDisplayed(WebElement element)
    {
        return element.isDisplayed();
    }
    
    /**
     * methode Checks if is element enabled.
     *
     * @param element 
     * @return boolean
     */
    public Boolean isElementEnabled(WebElement element)
    {
        return element.isEnabled();
    }

    /**
     * method verify whether element is not present on screen.
     *
     * @param targetElement element not to be present
     * @return true if element is not present else throws exception
     */
    public Boolean isElementNotPresent(By targetElement)
    {
        // Boolean isPresent = (Setup.driver.findElements(targetElement).size() == 0);
        return Setup.driver.findElements(targetElement).size() == 0;
    }

    /**
     * method to take screenshot.
     *
     * @return path where screenshot has been saved
     */
    public String screenShot()
    {
        String screenshotPath = "screenshot" + new SimpleDateFormat("MM-dd-yyyy-HH-mm-ss", Locale.FRANCE)
            .format(new GregorianCalendar().getTime())
            + ".png";

        log.info(screenshotPath);
        File scrFile = ((TakesScreenshot) driver)
            .getScreenshotAs(OutputType.FILE);
        try
        {
            FileUtils.copyFile(scrFile, new File(testScreenshotDir + screenshotPath));
        }
        catch (IOException e)
        {
            log.info("Exception: ", e);
            screenshotPath = "";
        }
        return screenshotPath;
    }

    /**
     * method to verify the actual value with expected value.
     *
     * @param actual              actual text displayed
     * @param expected            expected text to be displayed
     * @param message             message should be displayed on failure of assertion
     * @param screenshotOnFailure
     * @param exitOnFailure
     * @return true, si c'est vrai
     */
    public boolean verifyEquals(Object actual, Object expected, String message, boolean screenshotOnFailure, boolean exitOnFailure)
    {
        testStatus = true;
        Reporter.log("<br>");
        try
        {
            Assert.assertEquals(actual, expected, message);
            Reporter.log("<Font Color=#008000> PASS </Font>" + message);

        }
        catch (AssertionError e)
        {

            testStatus = false;

            if (screenshotOnFailure)
            {

                Reporter.log("<Font Color=red> FAIL </Font> " + message + " Actual: " + actual + " Expected: " + expected
                    + " Please check the screenshot " + "<a href='" + screenShot()
                    + "'> <Font Color=red> here </Font> </a>");

            }

            if (exitOnFailure)
            {
                Reporter.log("<br>");
                Reporter.log("Exiting this function as exitOnFail flag is set to True. Will move to next test.");
                throw e;
            }

            Reporter.log("<Font Color=red> FAIL </Font> " + message + " Actual: " + actual + " Expected: " + expected);

        }
        return testStatus;
    }

    /**
     * method to verify if the condition is true.
     *
     * @param condition           statement to verify
     * @param message             message should be displayed on failure of assertion
     * @param screenshotOnFailure true if screenshot has to be taken in case of failure
     * @param exitOnFailure       true if execution to be stopped in case of failure
     * @return true if assertion passes, false if fails
     */
    public boolean verifyTrue(boolean condition, String message,
        boolean screenshotOnFailure, boolean exitOnFailure)
    {

        Reporter.log("<br>");

        try
        {

            Assert.assertTrue(condition, message);
            Reporter.log("<Font Color=#008000> PASS </Font>" + message);

        }
        catch (AssertionError e)
        {
            log.info(message);
            this.testCaseStatus = false;

            if (screenshotOnFailure)
            {
                Reporter.log("<Font Color=red> FAIL </Font> " + message + " Actual: FALSE Expected: TRUE."
                    + " Please check the screenshot " + "<a href='" + screenShot()
                    + "'> <Font Color=red> here </Font> </a>");

            }
            else
            {

                Reporter.log("<Font Color=red> FAIL </Font> " + message);

            }

            if (exitOnFailure)
            {
                Reporter.log("<br>");

                Reporter.log("Exiting this function as exitOnFail flag is set to True.");

                throw e;

            }

        }

        return this.testCaseStatus;
    }

    /**
     * methode Check field is empty.
     * @param elementAttr
     */
    public void checkFieldIsEmpty(WebElement elementAttr)
    {
        WebElement inputText = elementAttr;
        String text = inputText.getAttribute("value");

        if (text.isEmpty())
        {
            log.info("input box is empty");
        }
    }

    /**
     * methode Text exist find elemnts.
     * @param elementAttr 
     */
    public void textExistFindElemnts(List<WebElement> elementAttr)
    {
        List<WebElement> list = elementAttr;

        if (list.size() > 0)
        {
            log.info("Text is present.");
        }
        else
        {
            log.info("Text is not present.");
        }
    }

    /**
     * methode Text pg source to check if text exisit in field.
     * @param text 
     */
    public void textPgSource(String text)
    {
        if (driver.getPageSource().contains(text))
        {
            log.info("Text is present.");
        }
        else
        {
            log.info("Text is not present.");
        }
    }
	
}
