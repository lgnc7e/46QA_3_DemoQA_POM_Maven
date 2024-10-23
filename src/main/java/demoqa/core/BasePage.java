package demoqa.core;

import demoqa.pages.SidePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasePage {

   public WebDriver driver;
   public JavascriptExecutor js;
    public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver,this);
    }

    public void hideAds(){
        js.executeScript("document.getElementById('adplus-anchor').style.display='none';");
        js.executeScript("document.querySelector('footer').style.display='none';");
    }

    public void click(WebElement element) {
        scrollToElement(element);
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            click(element);
            element.clear();
            element.sendKeys(text);
        }
    }

    protected void clickWitJS(WebElement element, int x, int y) {
        //window.scrollBy(0,100)
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        //js.executeScript("window.scrollBy({},{})",x,y);
        click(element);
    }

    protected void scrollTo(int y) {
        js.executeScript("window.scrollBy("+0+"," + y + ")");
    }

    protected void scrollWithPageDown(int count){ //count - количество нажатий / срабатываний метода
        try {
            Robot robot = new Robot();
            for(int i=0; i < count; i++){
                robot.keyPress(KeyEvent.VK_PAGE_DOWN);
                robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
                robot.delay(100); //задержка чтобы не возникало залипание
            }

        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public void scrollToElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        js.executeScript("arguments[0].scrollIntoView(true);",element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void shouldHaveText(WebElement element, String text, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(timeout));
        try {
            //результат ожидания элемента
            boolean isTextPresent =  wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            Assert.assertTrue(isTextPresent, "Expected text: ["+ text +"], actual text: ["+element.getText()+"] was not found in element: ["+element+"]");
        } catch (TimeoutException e) {
            throw new TimeoutException(e);
//        } catch (RuntimeException) {
//            throw new RuntimeException();
        }
    }

    @FindBy(css = ".top-card:nth-child(1)")
    WebElement elements;

    public BasePage getElements() {
        click(elements);
        return new SidePage(driver);
    }

    public void verifyLink(String urlToCheck) {
        try {
            URL url = new URL(urlToCheck);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3000);
            connection.connect();

            // Получение кода ответа
            int responseCode = connection.getResponseCode();
            // Получение заголовка ответа
            String responseMessage = connection.getResponseMessage();

            if (responseCode >= 400) {
                // broken link
                System.err.println("URL to check: [" + urlToCheck + "], " + "response code: [" + responseCode + "], " + "response message: [" + responseMessage + "] is broken link");
            } else {
                // correct link
                System.out.println("URL to check: [" + urlToCheck + "], " + "response code: [" + responseCode + "], " + "response message: [" + responseMessage + "] is valid link");
            }

        } catch (MalformedURLException error) {
            System.err.println("Error: Malformed URL: ["+urlToCheck+"], error message: [" + error.getMessage()+"]");
            //throw new RuntimeException(e);
        } catch (IOException error) {
            System.err.println("Error occurred: ["+error.getMessage()+"] for URL: [" + urlToCheck +"]");
            //throw new RuntimeException(e);
        }
    }

    //window.scrollBy(0, 500) прокрутка страницы на 500 пикселей
}
