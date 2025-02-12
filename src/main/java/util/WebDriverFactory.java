package util;

import exception.CustomErrorException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    private static WebDriver driver;

    public static void setDriver() throws CustomErrorException {
        if (driver == null) { // Evita múltiples instancias
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("El WebDriver no ha sido inicializado. Llama a setDriver() primero.");
        }
        return driver;
    }

    public static void closeSetup() {
        if (driver != null) {
            driver.close();
        }
    }

    public static void quitSetup() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
