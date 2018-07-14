package StepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Steps {

	WebDriver driver;
	WebDriverWait wait;
	WebElement element;
	String session_count1;
	String session_count2;

	@Given("^I go to highcharts.com$")
	public void navigate_to_using_phantom() throws Throwable {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
				"C:/Program Files/phantomjs-2.1.1-windows/bin/phantomjs.exe");
		driver = new PhantomJSDriver(caps);
		driver.manage().window().maximize();
		driver.get("https://www.highcharts.com/demo/line-ajax");
		wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				".//*[@id='container']/div/*[name()='svg']/*[name()='g'][7]/*[name()='g'][4]/*[name()='path'][19]")));

		System.out.println("User was navigated to highcharts.com.");

	}

	@When("^I Mouseover on Jan 5, 2018 date in the sessions curve in graph$")
	public void mouseover_value() throws Throwable {
		element = driver.findElement(By.xpath(
				".//*[@id='container']/div/*[name()='svg']/*[name()='g'][7]/*[name()='g'][4]/*[name()='path'][19]"));

		Actions builder = new Actions(driver);
		builder.moveToElement(element).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath(".//*[@id='container']/div/*[name()='svg']/*[name()='g'][15]//*[name()='tspan'][7]")));
		session_count1 = driver
				.findElement(
						By.xpath(".//*[@id='container']/div/*[name()='svg']/*[name()='g'][15]//*[name()='tspan'][7]"))
				.getText();
		System.out.println("Mouseover on Jan 5, 2018 in session curve.");
	}

	@And("^I click on the Jan 5, 2018 date in the sessions curve in graph$")
	public void click_on_value() throws Throwable {
		element.click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//div[@class='highslide-maincontent']")));
		session_count2 = driver.findElement(By.xpath(".//div[@class='highslide-maincontent']")).getText();
		System.out.println("clicked on Jan 5, 2018 in the sessions curve.");
	}

	@Then("^Session values in both the cases should be same$")
	public void verify_values() throws Throwable {
		if (session_count2.contains(session_count1))
			System.out.println("Session count matched.");
		else
			System.out.println("Session count didn't  match.");
		driver.quit();
	}

}