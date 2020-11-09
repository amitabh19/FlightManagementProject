package com.flight.selenium.stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SignupFail {
	WebDriver driver;

	@Given("^User is currently on Flight Management Signup Page$")
	public void user_is_currently_on_Flight_Management_Signup_Page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\seleniumTools\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200/signup");
		driver.manage().window().maximize();
		assert driver.getTitle().contains("Flight");
	}

	@When("^User logs in with invalid credentials$")
	public void user_logs_in_with_invalid_credentials() throws Throwable {
		driver.findElement(By.id("username")).sendKeys("te");
		driver.findElement(By.id("email")).sendKeys("testUse");
		driver.findElement(By.id("mobileNo")).sendKeys("343434343");
		driver.findElement(By.id("password")).sendKeys("testPa");
		//driver.findElement(By.id("submitBtn")).click();
		
	}

	@Then("^User should be shown signup error$")
	public void user_should_be_shown_signup_error() throws Throwable {
		assert driver.findElement(By.id("errorMinUser")).isDisplayed();
		driver.close();
		
	}

}
