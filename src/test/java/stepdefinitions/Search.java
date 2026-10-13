package stepdefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Search {

	private WebDriver getDriver() {
		return DriverFactory.getDriver();
	}

	@Given("User opens the Application")
	public void user_opens_the_application() {
		getDriver().get("https://tutorialsninja.com/demo/");
	}

	@When("User enters valid product {string} into Search box field")
	public void user_enters_valid_product_into_search_box_field(String productName) {

		WebElement searchBox = getDriver().findElement(By.name("search"));

		searchBox.clear();
		searchBox.sendKeys(productName);
	}

	@When("User clicks on Search button")
	public void user_clicks_on_search_button() {
		getDriver().findElement(By.cssSelector("button.btn-default.btn-lg")).click();
	}

	@Then("User should get valid product displayed in search results")
	public void user_should_get_valid_product_displayed_in_search_results() {

		boolean productDisplayed = !getDriver().findElements(By.cssSelector(".product-thumb")).isEmpty();

		if (!productDisplayed) {
			throw new AssertionError("No products were displayed in the search results.");
		}
	}

	@When("User enters invalid product {string} into Search box field")
	public void user_enters_invalid_product_into_search_box_field(String productName) {

		WebElement searchBox = getDriver().findElement(By.name("search"));

		searchBox.clear();
		searchBox.sendKeys(productName);
	}

	@Then("User should get a message about no product matching")
	public void user_should_get_a_message_about_no_product_matching() {

		WebElement message = getDriver().findElement(By.id("content"));

		if (!message.getText().contains("There is no product that matches the search criteria")) {

			throw new AssertionError("Expected no-matching-product message was not displayed. "
					+ "Actual page content: " + message.getText());
		}
	}

	@When("User dont enter any product name into Search box field")
	public void user_dont_enter_any_product_name_into_search_box_field() {

		WebElement searchBox = getDriver().findElement(By.name("search"));

		searchBox.clear();
	}
}