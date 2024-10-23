package demoqa.book_store;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.LoginPage;
import demoqa.pages.SidePage;
import demoqa.utils.RetryAnalyzer;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    //mustermail@gmail.com
    //Muster123456$

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver).getBookStore().hideAds();
        new SidePage(app.driver).selectLogin().hideAds();

    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginPositiveTest(){
        new LoginPage(app.driver)
                .enterPersonalData("mustermail@gmail.com", "Muster123456$")
                .clickOnLoginButton()
                .verifyUserName("mustermail@gmail.com");

    }




}
