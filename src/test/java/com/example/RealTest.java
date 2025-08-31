package com.example;

import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;

public class RealTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        Assume.assumeTrue("Skipping RealTest because PreCheckTest failed", TestStatus.preCheckPassed);

        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless=new");

        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogleTitle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        System.out.println("RealTest Page title is: " + title);
        assertTrue(title.contains("Google"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
