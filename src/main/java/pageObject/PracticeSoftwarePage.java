package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;

public class PracticeSoftwarePage extends BasePage {
    By searchId=cssSelector("input#search-query");
    By searchButton=xpath("//button[@data-test='search-submit']");
    By textInside=cssSelector("#search-query");
    By bar= xpath("//span[@data-test='search-term']");
    By card= cssSelector("div.card-body");

    public void visitUrl(String url) {
        driver.get(url);
    }

    public void closeBrowser() {
        driver.close();

    }

    public void clickSearch(){
        driver.findElement(searchId).click();
    }
    public void clickSearchButton(){
        driver.findElement(searchButton).click();
    }
    public void insertText(String text){
        driver.findElement(searchId).sendKeys(text);
    }
    public String getSearchText(){
        String text= driver.findElement(textInside).getAttribute("value");
        System.out.println(text);
        return text;
    }

    public void waitSearchComplete(String text){
        wait.until(ExpectedConditions.textToBe(bar, text));
        wait.until(driverInstance -> itemsList().size() < 5);
    }

    public List<WebElement> itemsList(){
       return driver.findElements(card);
    }

    public boolean checkAllItems(String text){
        List<WebElement>items=itemsList();
            for (WebElement item : items) {
                String txt=item.getText();
                System.out.println(txt);
            boolean check = txt.contains(text);
            if (!check) {
                return false;
            }

        }
    return true;

    }
}
