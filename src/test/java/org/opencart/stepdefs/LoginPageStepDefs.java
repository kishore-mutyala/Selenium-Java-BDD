package org.opencart.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.opencart.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class LoginPageStepDefs {

    private static final Logger log = LoggerFactory.getLogger(LoginPageStepDefs.class);
    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver", "src/Drivers/chromedriver");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown(){
        if(driver!=null){
            driver.quit();
        }
    }

    @Given("I am on the opencart login page")
    public void i_am_on_the_opencart_login_page() throws InterruptedException {
        driver.get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        loginPage = new LoginPage(driver);
    }

    @Given("Enter username and password")
    public void enter_username_and_password() throws InterruptedException {
        loginPage.enterEmail("qatestertest@gmail.com");
        loginPage.enterPassword("Test@123");
    }

    @When("Click on login button")
    public void click_on_login_button() throws InterruptedException {
        loginPage.clickLoginButton();
    }

    @Then("Verify logged in successfully")
    public void verify_logged_in_successfully() throws InterruptedException {
        Assert.assertEquals(loginPage.checkLogoutLink(),true);
    }

    @Given("Enter invalid {string} and {string}")
    public void enter_invalid_username_and_password(String username, String password) throws InterruptedException {
       loginPage.enterEmail(username);
       loginPage.enterPassword(password);
    }

    @Then("Verify the error message {string}")
    public void verify_the_error_message(String errorMessage) {
     Assert.assertEquals(driver.findElement(By.cssSelector(".alert-danger")).isDisplayed(),true);
    }

    @When("I click on the \"Forgotten Password\" link")
    public void i_click_on_the_forgotten_password_link() {
        loginPage.clickForgottenPasswordLink();
    }

    @Then("I should be redirected to the password reset page")
    public void i_should_be_redirected_to_the_password_reset_page() {
        // Assert that the current URL contains the password reset page route
        Assert.assertTrue(loginPage.getForgotPwdPageUrl().contains("account/forgotten"));
    }
}