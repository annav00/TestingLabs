package hw3;

import hw3.PageObjects.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.util.regex.Pattern;

public class SecondPage {
    SoftAssert softAssert;
    Checkbox checkboxes;
    Radio radios;
    Dropdown dropdowns;
    InfoPanel infoPanel;
    Button buttons;

    @FindBy(css = "#mCSB_2")
    public WebElement rightSection;
    @FindBy(css = "#mCSB_1")
    WebElement leftSection;

    public SecondPage(WebDriver webDriver) {
        softAssert = new SoftAssert();
        checkboxes = new Checkbox();
        checkboxes.initElement(webDriver);
        radios = new Radio();
        radios.initElement(webDriver);
        dropdowns = new Dropdown();
        dropdowns.initElement(webDriver);
        buttons = new Button();
        buttons.initElement(webDriver);
        infoPanel = new InfoPanel();
        infoPanel.initElement(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    public int getCheckboxNum(){
        return checkboxes.getSize();
    }

    public int getRadioNum(){
        return radios.getSize();
    }

    public int getdropdownNum(){
        return dropdowns.getSize();
    }

    public int getbuttonNum(){
        return buttons.getSize();
    }

    public void clickCheckbox(String str){
        checkboxes.select(str);
    }

    public void checkInfoPanel(Pattern str, int recordNum){
        softAssert.assertTrue(str.matcher(infoPanel.getRecord(recordNum)).matches());
        softAssert.assertAll();
    }

    public void clickRadio(String str){
        radios.select(str);
    }

    public void clickDropdown(String str){
        dropdowns.select(str);
    }
}