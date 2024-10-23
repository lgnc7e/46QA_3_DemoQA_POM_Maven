package demoqa.pages;

import demoqa.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.*;

import static org.testng.Assert.assertTrue;


public class WidgetsPage extends BasePage {

    public WidgetsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "oldSelectMenu")
    WebElement oldSelectMenu;

    public WidgetsPage selectOldStyle(String color) {
        Select select = new Select(oldSelectMenu);
        select.selectByVisibleText(color);
        shouldHaveText(oldSelectMenu, color, 5000);
        return this;
    }

    @FindBy(id = "react-select-4-input")
    WebElement inputSelect;

    @FindBy(css = "html")
    WebElement space;

    public WidgetsPage multiSelect(String[] colors) {
        for (String color : colors) {
            //for(int i = 0; i < colors.length; i++)
            if (color != null) {
                inputSelect.sendKeys(color);
                inputSelect.sendKeys(Keys.ENTER);
            }
            //click(space);
            inputSelect.sendKeys((Keys.ESCAPE));
        }
            return this;
        }

    @FindBy(css = ".css-12jo7m5")
    List<WebElement> selectedColorsElements;

    public boolean areColorsSelected(String[] colors) {
        List<String> selectedText = new ArrayList<>();
        for (WebElement element : selectedColorsElements){
            selectedText.add(element.getText());
        }
        for(String color : colors) {
            if(!selectedText.contains(color)) {
                return false;
            }
        }
        return true;
    }

    //!домашка

    @FindBy(css = "option[value='volvo']")
    WebElement volvoOption;

    @FindBy(css = "option[value='saab']")
    WebElement saabOption;

    @FindBy(css = "option[value='opel']")
    WebElement opelOption;

    @FindBy(css = "option[value='audi']")
    WebElement audiOption;

    public WidgetsPage selectStandartMultiSelect(String car) {
        WebElement carElement = null;

        switch (car.toLowerCase()) {
            case "volvo":
                carElement = volvoOption;
                break;
            case "saab":
                carElement = saabOption;
                break;
            case "opel":
                carElement = opelOption;
                break;
            case "audi":
                carElement = audiOption;
                break;
            default:
                throw new IllegalArgumentException("Please enter the correct value");
        }

        if (carElement != null) {
            carElement.click();
        }

        assertTrue(carElement.isSelected());
        return this;
    }

    public WidgetsPage selectMultipleCars(String... cars) {
        Actions actions = new Actions(driver);

        actions.keyDown(Keys.CONTROL).perform();

        for (String car : cars) {
            WebElement carOption = driver.findElement(By.cssSelector("option[value='" + car.toLowerCase() + "']"));
            carOption.click();
        }
        actions.keyUp(Keys.CONTROL).perform();
        return this;
    }



    //?классная работа
    @FindBy(id = "cars")
    WebElement cars;

    public WidgetsPage standartMultiSelectByIndex(int index) {
        Select select = new Select(cars);
        if (select.isMultiple()) {
            select.selectByIndex(index);
        }
        List<WebElement> options = select.getOptions();
        String selectedText = options.get(index).getText();
        System.out.println(selectedText);

        //System.out.println(select.getOptions().get(index).getText());
        return this;

    }

    public WidgetsPage verifyByIndex(int index) {
        Select select = new Select(cars);
        List<WebElement> options = select.getOptions();
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        String selectedText = options.get(index).getText();
        boolean textFound = false;
        //проходим по всем вывбранным элементам списка
        for(WebElement element : selectedOptions){
            if(element.getText().equals(selectedText)){
                textFound = true;
                break;
            }
        }
        System.out.println(textFound);
        Assert.assertTrue(textFound);
        //assert textFound;
        return this;
    }

    public WidgetsPage standartMultiSelectByCars(String[] car) {
        Select select = new Select(cars);
        if (select.isMultiple()) {
            for(String element : car){
                select.selectByVisibleText(element);
            }
        }
        return this;
    }

    public WidgetsPage verifyMultiSelectByCars(String[] expected) {
        Select select = new Select(cars);
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        //хранит список выбранных автомобилей в данный момент в списке
        List<String>  selectedText = new ArrayList<>();

        for(WebElement option : selectedOptions){
            selectedText.add(option.getText());
        }
        //автомобили которые мы передаем для сравнения (ожидаемые)
        List<String> expectedText = Arrays.asList(expected);

        //сравнение массив должен содержать такие же элементы как другой массив
        assert new HashSet<>(selectedText).containsAll(expectedText);
        return this;
    }
}

