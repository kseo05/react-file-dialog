package com.webuhee.filedialog;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.gargoylesoftware.htmlunit.BrowserVersion;

public class FileDialogIntegrationTest {
	WebDriver driver = new HtmlUnitDriver(BrowserVersion.CHROME, true);

	@Before
	public void setup() {
		driver.get("http://localhost:8081/");
	}
	
	@Test
	public void shouldLoadFileDialogOnHomePage() {
		WebElement divContainer = driver.findElement(By.className("d-file-dialog-container"));
		WebElement divChild = divContainer.findElement(By.xpath("./div"));
		
		assertThat(divChild, notNullValue());
		assertThat(divChild.getAttribute("data-reactid"), notNullValue());
	}
}
