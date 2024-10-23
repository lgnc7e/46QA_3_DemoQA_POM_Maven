package demoqa.widgets;

import demoqa.core.TestBase;
import demoqa.pages.HomePage;
import demoqa.pages.SidePage;
import demoqa.pages.WidgetsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class SelectTests extends TestBase {

    @BeforeMethod
    public void precondition() {
        new HomePage(app.driver).getWidgets().hideAds();
        new SidePage(app.driver).selectSelectMenu().hideAds();
    }

    @Test
    public void oldStyleSelectMenuTest() {
        new WidgetsPage(app.driver)
                .selectOldStyle("Indigo");
    }

    @Test
    public void multiSelectTest() {
        String[] colorsSelect = {"Green", "Blue"};
        new WidgetsPage(app.driver).multiSelect(colorsSelect);
        Assert.assertTrue(new WidgetsPage(app.driver).areColorsSelected(colorsSelect));
    }

    //домашка
    @Test
    public void multiSelectCarTest() {
        new WidgetsPage(app.driver)
                .selectStandartMultiSelect("Saab");
    }


    //домашка
    @Test
    public void testSelectMultipleCars() {
        String[] carsToSelect = {"volvo", "saab", "opel", "audi"};
        new WidgetsPage(app.driver)
                .selectMultipleCars(carsToSelect);
        for (String car : carsToSelect) {
            WebElement carOption = app.driver.findElement(By.cssSelector("option[value='" + car.toLowerCase() + "']"));
            Assert.assertTrue(carOption.isSelected(), "Car " + car + " was not selected");
        }
    }


    @Test
    public void standartMultiSelectByIndexTest (){
        new WidgetsPage(app.driver)
                .standartMultiSelectByIndex(2)
                .verifyByIndex(2);
    }

    @Test
    public void standartMultiSelectByCarsTest(){
        String[] cars = {"Volvo", "Opel", "Saab"};
        new WidgetsPage(app.driver)
                .standartMultiSelectByCars(cars)
                .verifyMultiSelectByCars(cars);

    }

}
