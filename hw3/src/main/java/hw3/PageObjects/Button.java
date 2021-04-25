package hw3.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Button {
    @FindBy(css = "div.main-content-hg .uui-button")
    List<WebElement> buttons;

    public void initElement(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public String getText() {
        return null;
    }

    public boolean isDisplayed(String name) {
        return true;
    }

    public int getSize() {
        return buttons.size();
    }

    public void select(String name) {
        for (WebElement button : buttons) {
            if (button.getText().equals(name)) {
                button.click();
            }
        }
    }

    public boolean isSelected(String name) throws Exception {
        for (WebElement button : buttons) {
            if (button.getText().equals(name)) {
                return button.isSelected();
            }
        }
        throw new Exception("There is no such a button.");
    }
}