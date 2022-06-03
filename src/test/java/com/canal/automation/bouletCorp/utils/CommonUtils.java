package com.canal.automation.bouletCorp.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CommonUtils extends BasePage {
	
	protected Properties properties;

    protected FileInputStream configFis;

    public CommonUtils()
    {
        super();
        this.driver = Setup.driver;
    }

    /**
     * method to open specified url
     * @param url to open
     */
    public void get(String url)
    {
        driver.get(url);
    }

    /**
     * method to navigate to specified page
     * @param url navigation url
     */
    public void navigate(String url)
    {
        driver.navigate().to(url);
    }

    /**
     * method to click on an element with action class
     * @param element to be clicked
     */
    public void clickOnElementUsingActions(By element)
    {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(element));
        actions.click().perform();
    }

    /**
     * method to click on an element using javascript
     * @param element to be clicked
     */
    public void clickOnElementUsingJs(By element)
    {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement webElement = driver.findElement(element);
        jsExecutor.executeScript("arguments[0].click();", webElement);
    }

    /**
     * method to get title of current webpage 
     * @return String name of a webpage
     */
    public String getTitle()
    {
        return driver.getTitle();
    }

    /**
     * method to wait until page is loaded completely
     * @param pageName String name of current webpage
     * @param status String status of current webpage
     */
    public void waitForPageToLoad(String pageName, String status)
    {
        String pageLoadStatus;
        JavascriptExecutor jsExecutor;

        do
        {
            jsExecutor = (JavascriptExecutor) driver;
            pageLoadStatus = (String) jsExecutor.executeScript("return document.readyState");
            log.info(".");
        }
        while (!pageLoadStatus.equals(status));

        log.info(pageName + " page loaded successfully");
    }

    /**
     * method to find an element
     * @param locator element to be found
     * @return WebElement if found else throws NoSuchElementException
     */
    public WebElement findElement(By locator)
    {
        try
        {
            return driver.findElement(locator);
        }
        catch (NoSuchElementException e)
        {
            log.error(this.getClass().getName(), "findElement", "Element not found " + locator);
            String message = e.getMessage();
            log.warn(message);
            throw new NoSuchElementException(message);
        }
    }

    /**
     * method to find all the elements of specific locator
     * @param locator element to be found
     * @return return the list of elements if found else throws NoSuchElementException
     */
    public List<WebElement> findElements(By locator)
    {
        try
        {
            return driver.findElements(locator);
        }
        catch (NoSuchElementException e)
        {
            log.error(this.getClass().getName(), "findElements", "element not found" + locator);
            throw new NoSuchElementException(e.getMessage());
        }
    }

    /**
     * method to accept alert, exception is thrown if no alert is present
     */
    public void acceptAlert()
    {
        try
        {
            Alert alert = driver.switchTo().alert();
            alert.accept();

        }
        catch (NoAlertPresentException e)
        {
            throw new NoAlertPresentException();
        }
    }

    /**
     * method to get message test of alert 
     * @return message text which is displayed
     */
    public String getAlertText()
    {
        try
        {
            Alert alert = driver.switchTo().alert();
            return alert.getText();
        }
        catch (NoAlertPresentException e)
        {
            throw new NoAlertPresentException();
        }
    }

    /**
     * method to clear field
     */
    public void clearField(WebElement element)
    {
        try
        {
            element.clear();
        }
        catch (Exception e)
        {
            log.info(String.format("The following element could not be cleared: [%s]", element.getText()));
        }
    }

    /**
     * Accesseur de l attribut application url.
     * @param url application url
     * @return application url
     * @throws IOException
     */
    public String getUrl(String url) throws IOException 
    {
        properties = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/configs/Config.properties");
        properties.load(fis);
        Setup.driver.get(properties.getProperty(url));
        return url;
    }
    
    /**
     * Methode click on element.
     * @param             <T> 
     * @param elementAttr 
     */
    public <T> void click(T elementAttr) {
        if (elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }

    /**
     * methode write text.
     * @param             <T> 
     * @param elementAttr 
     * @param string
     */
    public <T> void writeText(T elementAttr, String text) {
        if (elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).sendKeys(text);
        } else {
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    /**
     * methode read text.
     * @param             <T> 
     * @param elementAttr 
     * @return string
     */
    public <T> String readText(T elementAttr) {
        if (elementAttr.getClass().getName().contains("By")) {
            return driver.findElement((By) elementAttr).getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }
    
    /**
     * methode Upload file with robot.
     *
     * @param imagePath path of the file
     */
    public void uploadFile(String imagePath)
    {

        StringSelection stringSelection = new StringSelection(imagePath);
        Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipBoard.setContents(stringSelection, null);

        Robot robot = null;
        try
        {
            robot = new Robot();
        }
        catch (AWTException e)
        {
            log.info(e);
        }

        robot.delay(250);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(150);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }


}
