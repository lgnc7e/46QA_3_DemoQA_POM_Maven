package demoqa.forms;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.PracticeFormPage;
import demoqa.pages.SidePage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.awt.*;

public class PracticeFormTests extends TestBase {

    @BeforeMethod
    public void precondition() throws AWTException {
        new HomePage(app.driver).getForms().hideAds();
        new SidePage(app.driver).selectPracticeForm().hideAds();
    }

    @Test
    public void practiceFormTest() throws AWTException {
        new PracticeFormPage(app.driver)
                .enterPersonalData("Max","Mustermann","muster@gmail.com", "1234567890")
                .selectGender("Male")
                .chooseDateAsString("01 January 1984")
                //.chooseDate("1","January","1984")
                .enterSubject(new String[]{"Maths", "English"})
                .chooseHobbies(new String[]{"Sports", "Music"})
                .uploadPicture("C:/QA_Project/46QA_3_DemoQA_POM_Maven/Image.jpg")
                .enterCurrentAddress("Hauptstrasse 1, Hamburg, Germany")
                .enterState("NCR")
                .enterCity("Delhi")
                .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form")
                ;
    }

    //"Max","Mustermann","muster@gmail.com", "1234567890"

    @Test
    @Parameters({"firstName","lastName","email", "phone"})
    public void practiceFormParametersTest(String firstName, String lastName, String email, String phone) throws AWTException {
        new PracticeFormPage(app.driver)
                .enterPersonalData(firstName,lastName,email, phone)
                .selectGender("Male")
                .chooseDateAsString("01 January 1984")
                //.chooseDate("1","January","1984")
                .enterSubject(new String[]{"Maths", "English"})
                .chooseHobbies(new String[]{"Sports", "Music"})
                .uploadPicture("C:/QA_Project/46QA_3_DemoQA_POM_Maven/Image.jpg")
                .enterCurrentAddress("Hauptstrasse 1, Hamburg, Germany")
                .enterState("NCR")
                .enterCity("Delhi")
                .submitForm()
                .verifySuccessRegistration("Thanks for submitting the form")
        ;
    }

}
