package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ScreenShotUtil;
import util.WebDriverFactory;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;
    private static final long DEFAULT_TIME = 50;
    private static final long DEFAULT_SLEEP = 2;

    public BasePage() {
        try {
            WebDriverFactory.setDriver();  // 🔹 Asegura que el WebDriver está inicializado
         } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error al iniciar el WebDriver", e);
        }
        this.driver = WebDriverFactory.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIME), Duration.ofSeconds(DEFAULT_SLEEP));
    }
public void captureScreen() {
    ScreenShotUtil.captureScreenshot(WebDriverFactory.getDriver(), "Captura de Pantalla");
}
}
