package com.flight.selenium.stepDefinition;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class UpdateUser {
	WebDriver driver;

	@Given("^User is on FlightManagement EditDetais Page$")
	public void user_is_on_FlightManagement_EditDetais_Page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\seleniumTools\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:4200/");
		driver.manage().window().maximize();
		driver.findElement(By.id("email")).sendKeys("test@u.com");
		driver.findElement(By.id("pass")).sendKeys("testpass");
		driver.findElement(By.id("loginn")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("ViewUser")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id("updateUser")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("http://localhost:4200/editDetails", driver.getCurrentUrl()); 
	}

	@When("^User enters new valid details$")
	public void user_enters_new_valid_details() throws Throwable {
		
		driver.findElement(By.name("mobileNo")).clear();
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("username")).clear();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("mobileNo")).sendKeys("2323232323");
		driver.findElement(By.name("email")).sendKeys("newtestUser@gmail.com");
		driver.findElement(By.name("username")).sendKeys("newtest");
		driver.findElement(By.name("password")).sendKeys("newtestPassword");
		driver.findElement(By.id("updatBtn")).click();
	}

	@Then("^User should be shown user updated message$")
	public void user_should_be_shown_user_updated_message() throws Throwable {
		Alert a = driver.switchTo().alert();
		System.out.println(a.getText());
		assertEquals("user details updated", a.getText());
		a.accept();
		driver.close();
	}


}
