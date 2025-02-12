package util;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;

public class ScreenShotUtil {
    /**
     * Toma una captura de pantalla con Selenium y la adjunta al reporte de Allure.
     * @param driver instancia de WebDriver
     * @param attachmentName nombre del attachment en el reporte de Allure
     */
    public static void captureScreenshot(WebDriver driver, String attachmentName) {
        // Convertir la captura en un arreglo de bytes
        byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        // Adjuntar la captura al reporte de Allure
        Allure.addAttachment(attachmentName, new ByteArrayInputStream(screenshotBytes));
    }
}