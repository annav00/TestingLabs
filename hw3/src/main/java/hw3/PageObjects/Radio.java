package hw3.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Radio {
    @FindBy(css = "label.label-radio")
    List<WebElement> radios;

    public void initElement(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public String getText() {
        return null;
    }

    public boolean isDisplayed(String str) {
        return false;
    }

    public int getSize() {
        return radios.size();
    }

    public void select(String name) {
        for (WebElement checkbox : radios) {
            if (checkbox.getText().equals(name)) {
                checkbox.click();
            }
        }
    }

    public boolean isSelected(String name) {
        for (WebElement checkbox : radios) {
            if (checkbox.getText().equals(name) && checkbox.isSelected()) {
                return true;
            }
        }
        return false;
    }
}