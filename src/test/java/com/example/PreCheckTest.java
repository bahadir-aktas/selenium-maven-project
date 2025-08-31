package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.Assert;

public class PreCheckTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");          // Recommended for Linux containers
        options.addArguments("--headless=new");        // Run headless to avoid GUI

        driver = new ChromeDriver(options);
    }

    @Test
    public void equalTest() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();

        try {
            Assert.assertEquals("Googe", title);
            System.out.println("PreCheckTest passed.");
            TestStatus.preCheckPassed = true; // mark success for other tests
        } catch (AssertionError e) {
            System.out.println("PreCheckTest failed.");
            Assert.fail("Title did not match 'Google'.");
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
