package ch.web.web_shop.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {
    public static void main(String[] args) {
        /*
        // Setze den Pfad zum Chrome Webdriver
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");

        // Erstelle eine Instanz des Chrome Webdrivers
        WebDriver driver = new ChromeDriver();

        // Navigiere zur Login-Seite
        driver.get("http://localhost:3000/login");

        // Warte bis das Eingabefeld für die E-Mail-Adresse angezeigt wird
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emailInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("email")));

        // Eingabe der E-Mail-Adresse
        emailInput.sendKeys("example@example.com");

        // Warte bis das Eingabefeld für das Passwort angezeigt wird
        WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("password")));

        // Eingabe des Passworts
        passwordInput.sendKeys("password123");

        // Absenden des Login-Formulars
        WebElement loginButton = driver.findElement(By.xpath("//button[text()='Login']"));
        loginButton.click();

        // Warte auf die erfolgreiche Anmeldung und das Navigieren zur Dashboard-Seite
        WebElement dashboardHeading = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h2")));
        if (dashboardHeading.getText().equals("Dashboard")) {
            System.out.println("Login successful");
        } else {
            System.out.println("Login failed");
        }

        // Schließe den Webdriver
        driver.quit();

         */
    }
}
