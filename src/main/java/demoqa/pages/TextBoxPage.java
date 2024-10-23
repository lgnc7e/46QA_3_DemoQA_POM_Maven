package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TextBoxPage extends BasePage {

    Robot robot = new Robot();

    public TextBoxPage(WebDriver driver) throws AWTException {
        super(driver);
    }

    @FindBy(id = "userName")
    WebElement userName;

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    @FindBy(id = "permanentAddress")
    WebElement permanentAddress;

    @FindBy(id = "submit")
    WebElement submit;


    public TextBoxPage enterPersonalData(String name, String mail, String address) {
        type(userName, name);
        type(userEmail, mail);
        type(currentAddress, address);
        return this;
    }

    public TextBoxPage keyboardEvent() {
        //ctrl + A
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_A);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        //ctrl + C
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_C);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        //TAB
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

        //ctrl + V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        //TAB
        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);

        //ENTER
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        return this;
    }


    @FindBy(xpath = "(//p[@id='currentAddress'])[1]")
    WebElement outputCurrentAddress;

    @FindBy(xpath = "(//p[@id='permanentAddress'])[1]")
    WebElement outputPermanentAddress;

    public TextBoxPage verifyCopyPasteAddress() {

        String currentAddressText = outputCurrentAddress.getText();
        String permanentAddressText = outputPermanentAddress.getText();
        String currentAddress = currentAddressText.split(":")[1].trim();
        String permanentAddress = permanentAddressText.split(":")[1].trim();

        assert currentAddress.equals(permanentAddress) : "The addresses do not match";

        return this;
    }

}
