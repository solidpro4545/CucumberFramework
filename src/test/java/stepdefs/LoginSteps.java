package stepdefs;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class LoginSteps {
    private WebDriver driver;

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter a valid username and password")
    public void i_enter_valid_username_and_password() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
    }

    @Then("I should be redirected to the dashboard")
    public void i_should_be_redirected_to_the_dashboard() {
        assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Then("I should see {string} at the top of the page")
    public void i_should_see_text_at_the_top_of_the_page(String expectedText) {
        String actualText = driver.findElement(By.className("app_logo")).getText();
        assertTrue("Expected text not found!", actualText.equals(expectedText));
    }

    // --- Cart steps ---
    @When("I add {string} to the cart")
    public void i_add_product_to_the_cart(String productName) {
        String buttonId;
        switch (productName) {
            case "Sauce Labs Backpack":
                buttonId = "add-to-cart-sauce-labs-backpack"; break;
            case "Sauce Labs Bike Light":
                buttonId = "add-to-cart-sauce-labs-bike-light"; break;
            case "Sauce Labs Bolt T-Shirt":
                buttonId = "add-to-cart-sauce-labs-bolt-t-shirt"; break;
            case "Sauce Labs Fleece Jacket":
                buttonId = "add-to-cart-sauce-labs-fleece-jacket"; break;
            case "Sauce Labs Onesie":
                buttonId = "add-to-cart-sauce-labs-onesie"; break;
            case "Test.allTheThings() T-Shirt (Red)":
                buttonId = "add-to-cart-test.allthethings()-t-shirt-(red)"; break;
            default:
                throw new IllegalArgumentException("Unknown product: " + productName);
        }
        driver.findElement(By.id(buttonId)).click();
    }

    @When("I open the cart")
    public void i_open_the_cart() {
        driver.findElement(By.className("shopping_cart_link")).click();
    }

    @Then("I should see {string} in the cart")
    public void i_should_see_in_the_cart(String expectedItem) {
        List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
        boolean found = items.stream().anyMatch(el -> expectedItem.equals(el.getText()));
        assertTrue("Item not found in cart: " + expectedItem, found);
    }

    @Then("the cart badge should show {string}")
    public void the_cart_badge_should_show(String expectedCount) {
        String actual = driver.findElement(By.className("shopping_cart_badge")).getText();
        assertTrue("Badge mismatch. Expected: " + expectedCount + " but was: " + actual,
                actual.equals(expectedCount));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
