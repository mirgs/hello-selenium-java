package com.example.hello_selenium_java;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
public class ImdbTestRemoteChrome {
    private WebDriver driver;
    private String browser;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        open("about:blank");
        driver = getWebDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
    
    @Test
    public void imdb() {

        driver.get("https://www.imdb.com/");
        driver.manage().window().setSize(new Dimension(907, 886));
        driver.findElement(By.id("suggestion-search")).sendKeys("wandavision");
        driver.findElement(By.id("suggestion-search")).sendKeys(Keys.ENTER);
        WebElement we = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(By.linkText("WandaVision")));
        we.click();
        //driver.findElement(By.linkText("WandaVision")).click();
        we = new WebDriverWait(driver, 15)
                .until(ExpectedConditions.elementToBeClickable(By.linkText("TRIVIA")));
        we.click();
    }
}
