package stepdefs;

import io.cucumber.java.en.*;
import io.cucumber.java.After;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

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
        WebElement header = driver.findElement(By.className("app_logo"));
        String actualText = header.getText();
        assertTrue("Expected text not found!", actualText.equals(expectedText));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
