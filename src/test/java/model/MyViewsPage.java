package model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class MyViewsPage extends HomePage {

    @FindBy(css = "a[title='New View']")
    private WebElement newView;

    @FindBy(css = ".tabBar .tab a[href*='/my-views/view/']")
    private List<WebElement> listViews;

    @FindBy(css = ".pane-header-title")
    private List<WebElement> listViewActiveFilters;

    @FindBy(xpath = "//a[@href='delete']")
    private WebElement deleteViewItem;

    @FindBy(id = "yui-gen1-button")
    private WebElement yesButtonDeleteView;

    @FindBy(xpath = "//tbody/tr/td/a")
    private List<WebElement> listProjects;

    @FindBy(id = "description-link")
    private WebElement descriptionLink;

    @FindBy(xpath = "//textarea[@name='description']")
    private WebElement descriptionField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@id='description']/div[1]")
    private WebElement displayedDescriptionText;

    @FindBy(xpath = "//ul[@id='breadcrumbs']/li[5]")
    private WebElement myViewItemOnTopBar;

    public MyViewsPage(WebDriver driver) {
        super(driver);
    }

    public NewViewPage clickNewView() {
        newView.click();

        return new NewViewPage(getDriver());
    }

    public String getListViewsNames() {
        StringBuilder listViewsNames = new StringBuilder();
        for (WebElement view : listViews) {
            listViewsNames.append(view.getText()).append(" ");
        }

        return listViewsNames.toString().trim();
    }

    public List<String> getActiveFiltersList() {

        return listViewActiveFilters.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public ViewPage clickView(String viewName) {
        getDriver().findElement(By.cssSelector(".tabBar .tab a[href*='/my-views/view/" + viewName + "/']")).click();

        return new ViewPage(getDriver());
    }

    public MyViewsPage deleteAllViews() {
        for (int i = listViews.size() - 1; i >= 0; i--) {
            listViews.get(i).click();
            deleteViewItem.click();
            yesButtonDeleteView.click();
        }

        return this;
    }

    public String getListProjectsNames() {
        StringBuilder listProjectsNames = new StringBuilder();
        for (WebElement projects : listProjects) {
            listProjectsNames.append(projects.getText()).append(" ");
        }

        return listProjectsNames.toString().trim();
    }

    public MyViewsPage clickAddDescription() {
        descriptionLink.click();

        return this;
    }

    public MyViewsPage sendKeysInDescriptionField(String descriptionText) {
        descriptionField.sendKeys(descriptionText);

        return this;
    }

    public MyViewsPage clickSaveButton() {
        saveButton.submit();

        return this;
    }

    public MyViewsPage clearDescriptionField() {
        descriptionField.clear();

        return this;
    }

    public String getDescriptionText() {

        return displayedDescriptionText.getText();
    }

    public String getMyViewItemNameOnTopBar() {

        return myViewItemOnTopBar.getText();
    }
}