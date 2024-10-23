package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;


public class PracticeFormPage extends BasePage {

    Robot robot = new Robot();

    public PracticeFormPage(WebDriver driver) throws AWTException {
        super(driver);
    }

    @FindBy(id = "firstName")
    WebElement firstName;
    @FindBy(id = "lastName")
    WebElement lastName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userNumber")
    WebElement userNumber;

    public PracticeFormPage enterPersonalData(String name, String surname, String mail, String number) {
        type(firstName, name);
        type(lastName, surname);
        type(userEmail, mail);
        type(userNumber, number);
        return this;
    }

//    @FindBy(xpath = "(//label[@class='custom-control-label'])[1]")
//    WebElement male;
//    //(//label[@class='custom-control-label'])[2]
//    @FindBy(xpath = "(//label[@class='custom-control-label'])[2]")
//    WebElement female;
//    //(//label[@class='custom-control-label'])[3]
//    @FindBy(xpath = "(//label[@class='custom-control-label'])[3]")
//    WebElement other;

    //label[contains(text(),'Other')]
    //label[contains(text(),'Male')]
    //label[contains(text(),'Female')]

    public PracticeFormPage selectGender(String gender) {
        try {
            String xpathGender = "//label[contains(text(),'" + gender + "')]";
            WebElement genderLocator = driver.findElement(By.xpath(xpathGender));
            click(genderLocator);
        } catch (NoSuchElementException e) {
            System.out.println("Gender element not found: ["+ gender +"]");
            throw new RuntimeException(e);
        } catch (Exception e) {
            System.out.println("Error selecting gender: ["+ gender +"]");
            throw new RuntimeException(e);
        }
        return this;
    }

    // react-datepicker__month-select
    // react-datepicker__year-select
    //dateOfBirthInput

    @FindBy(id = "dateOfBirthInput")
    WebElement dateOfBirthInput;
    @FindBy(css = ".react-datepicker__month-select")
    WebElement monthSelect;
    @FindBy(css = ".react-datepicker__year-select")
    WebElement yearSelect;

    public PracticeFormPage chooseDate(String day, String month, String year) {
        click(dateOfBirthInput);
        new Select(monthSelect).selectByVisibleText(month);
        new Select(yearSelect).selectByVisibleText(year);
        //div[@class='react-datepicker__week']//div[.='4']
        driver.findElement(By.xpath("//div[@class='react-datepicker__week']//div[.='"+day+"']")).click();
        return this;
    }


    @FindBy(id = "subjectsInput")
    WebElement subjectsInput;

    public PracticeFormPage enterSubject(String[] subjects) {
       for(String subject : subjects){
           if(subject != null) {
               type(subjectsInput, subject);
               subjectsInput.sendKeys(Keys.ENTER);
           }
       }
        return this;
    }

    //label[.='Reading']

    public PracticeFormPage chooseHobbies(String[] hobbies) {
        for (String hobby : hobbies){
            if(hobby !=null){
                driver.findElement(By.xpath("//label[.='"+ hobby +"']")).click();
            }
        }
        return this;
    }

    @FindBy(id = "uploadPicture")
    WebElement uploadPicture;

    public PracticeFormPage uploadPicture(String imgPath) {
        uploadPicture.sendKeys(imgPath);
        return this;
    }


    @FindBy(id = "currentAddress")
    WebElement currentAddress;

    public PracticeFormPage enterCurrentAddress(String address) {
    type (currentAddress, address);
        return this;
    }

    @FindBy(id = "state")
    WebElement stateContainer;

    //react-select-3-input
    @FindBy(id = "react-select-3-input")
    WebElement stateInput;

    public PracticeFormPage enterState(String state) {
        click(stateContainer);
        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);
        return this;
    }

//HW
//    public PracticeFormPage chooseDateAsString(String date) throws AWTException {
//        Robot robot = new Robot();
//        click(dateOfBirthInput);
//        robot.keyPress(KeyEvent.VK_CONTROL);
//        robot.keyPress(KeyEvent.VK_A);
//        robot.keyRelease(KeyEvent.VK_A);
//        robot.keyRelease(KeyEvent.VK_CONTROL);
//        dateOfBirthInput.sendKeys(date);
//        dateOfBirthInput.sendKeys(Keys.ENTER);
//        return this;
//    }

    public PracticeFormPage chooseDateAsString(String date) {
        click(dateOfBirthInput);
        String os = System.getProperty("os.name"); //! получает значение операционной системы
        //type(dateOfBirthInput,date);
        if(os.contains("Mac")){
            dateOfBirthInput.sendKeys(Keys.COMMAND, "a"); //! в случае если мак нажимает кнопку комманд вместо контрол
        } else {
            dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
        }
        dateOfBirthInput.sendKeys(date);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        return this;
    }



    @FindBy(id = "city")
    WebElement cityContainer;

    //react-select-4-input
    @FindBy(id = "react-select-4-input")
    WebElement cityInput;


    public PracticeFormPage enterCity(String city) {
        click(cityContainer);
        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
        return this;
    }


    @FindBy(id = "submit")
    WebElement submitButton;

    public PracticeFormPage submitForm() {
        click(submitButton);
        return this;
    }

    @FindBy(id = "example-modal-sizes-title-lg")
    WebElement message;

    public PracticeFormPage verifySuccessRegistration(String text) {
        shouldHaveText(message, text, 5000);
        return this;
    }
}
