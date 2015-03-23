package util;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
public class FirstTestCase {
	
	 public static void main(String[] args) throws Exception{
	        // Create a new instance of the Firefox driver
	        WebDriver driver = new FirefoxDriver();
	        // And now use this to visit Google
	        driver.get("http://www.google.com");
            Thread.sleep(2000L);
	        // Find the text input element by its name
	        WebElement element = driver.findElement(By.name("q"));
	        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	        Thread.sleep(2000L);
	        // Enter something to search for
	        element.sendKeys("Cheese!");
	        Thread.sleep(1000L);
	        // Now submit the form. WebDriver will find the form for us from the element
	        element.submit();
	        // Check the title of the page
	        System.out.println("Page title is: " + driver.getTitle());
	        
	        //Close the browser
	        driver.quit();
	    }
}

