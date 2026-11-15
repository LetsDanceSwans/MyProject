package com.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.time.LocalDate;

public class KalendarDisableDateTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
    }

    @Test
    public void  CheckVidachaTest() {
        driver.get("https://travel.yandex.ru/");
        homePage.OpenCalendar();
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();
        int month = currentDate.getMonthValue();
        int today = currentDate.getDayOfMonth();
        // Поиск в  динамисеском хпаф
    for (int i = 1; i < today; i++) {
        String dateStr = String.format("%d-%02d-%02d", year, month, i);
        String xpathExpression = String.format("//*[@data-qa='calendar-day-%s']", dateStr);
        String ariaDisabledValue = driver.findElement(By.xpath(xpathExpression)).getAttribute("aria-disabled");
        boolean isSuccessful = "true".equals(ariaDisabledValue);
        Assertions.assertFalse(isSuccessful, "ТЕСТ ПРОВАЛЕН: Дата " + dateStr + " должна быть задизейблена!");
    }
    System.out.println("ТЕСТ ПРОЙДЕН даты задизейблены");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}