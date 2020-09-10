package Demo;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TestActionPerformer {
	

	public void performAction(String action, WebElementDTO weDto, String webEleValue, WebDriver driver, Logger log) throws InterruptedException
	{
		if(action!=null && weDto!=null && driver!=null)
		{			
			switch(action.toLowerCase())
			{
				case "gotourl":
					driver.get(webEleValue);
					driver.manage().window().maximize();
					log.info("Oprning Website");
					break;
				case "settext":
					driver.findElement(this.getWebElementLocator(weDto.getLocatorType(), weDto.getLocatorValue())).clear();
					driver.findElement(this.getWebElementLocator(weDto.getLocatorType(), weDto.getLocatorValue())).sendKeys(webEleValue);
					log.info("Entering Text " + webEleValue );
					break;
				case "click":
					driver.findElement(this.getWebElementLocator(weDto.getLocatorType(), weDto.getLocatorValue())).click();
					log.info("Clicked Button");
					break;
				case "select":
					Select dropdown = new Select(driver.findElement(this.getWebElementLocator(weDto.getLocatorType(), weDto.getLocatorValue())));
					dropdown.selectByValue(webEleValue);
					log.info("Selected Value " + webEleValue );
					break;
				case "tryfind":
					boolean isPresent = VerifyElementExists(this.getWebElementLocator(weDto.getLocatorType(), weDto.getLocatorValue()), driver);
					if(isPresent)
						log.info("Element Is Present");
					break;
			}
			
			Thread.sleep(3000);
		}
	}
	
	/* Return the By object to locate the web element. */
	private By getWebElementLocator(String locatorType, String locatorValue)
	{
		switch(locatorType.toLowerCase()){
			case "xpath":
				return By.xpath(locatorValue);
			case "id":
				return By.id(locatorValue);
			case "name":
				return By.name(locatorValue);
			case "tagname":
				return By.tagName(locatorValue);
			case "className":
				return By.className(locatorValue);
			default:
				return null;
		}
	}
	
	private Boolean VerifyElementExists(By locator, WebDriver driver)
	{
		boolean present;
		try {
		   driver.findElement(locator);
		   present = true;
		} catch (NoSuchElementException e) {
		   present = false;
		}
		return present;
		
	}

}
