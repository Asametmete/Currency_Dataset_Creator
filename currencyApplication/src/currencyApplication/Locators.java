package currencyApplication;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



public class Locators { 

	WebDriver driver;
	
	public Locators(WebDriver driver) {
		this.driver = driver;
	}
	
	public void getUrl() {
	 driver.get("https://www.tcmb.gov.tr/kurlar/kurlar_tr.html");}

	
	public List<WebElement> getLocationOfTheYears() {
		return driver.findElements(By.cssSelector("a[class*='block-tabs-tab w-inline-block w-tab-link'] div"));
	}

	public WebElement getLocationOfTheNextButton(){
		return driver.findElement(By.cssSelector("a[class = 'block-tabs-tab next w-inline-block w-tab-link'] div"));
	}
	
	public List<WebElement> getLocationOfTheDays(String PathSelector) {
		return driver.findElements(By.cssSelector("body > main:nth-child(1) > section:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child("+PathSelector+") > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > table:nth-child(2) > tbody:nth-child(2) > tr > td[data-handler = 'selectDay']"));
	}
	
	public List<WebElement> getLocationOfTheMonths(String userYear){
		return driver.findElements(By.cssSelector("div[class='calendar-months-list'] a[data-year ='"+userYear+"'] div[class = 'calendar-month-number']"));
	}
	
	public WebElement getLocationOfTheGosterBtn(String PathSelector) {
		return driver.findElement(By.xpath("(//input[@value='Göster'])["+PathSelector+"]"));
	}
	
	public WebElement getLocationOfTheBackBtn() {
		return driver.findElement(By.cssSelector("a[id='back'] div"));
	}
	
	public String getLocationOfTheStringPageDateInf() {
		return "//div[@id = 'kurlarContainer']//h1";
	}
	
	public WebElement getLocationOfThePageDateInf() {
		return driver.findElement(By.xpath("//div[@id = 'kurlarContainer']//h1"));
	}
	
	public WebElement getLocationOfTheBuyDollar() {
		return driver.findElement(By.xpath("//td[@class='para'][normalize-space()='ABD DOLARI']//following-sibling::td[1][@class = 'deger']"));
	}
	
	public String getLocationOfTheStringBuyDollar() {
		return "//td[@class='para'][normalize-space()='ABD DOLARI']//following-sibling::td[1][@class = 'deger']";
	}
	
	public WebElement getLocationOfTheSaleDollar() {
		return driver.findElement(By.xpath("//td[@class='para'][normalize-space()='ABD DOLARI']//following-sibling::td[2][@class = 'deger']"));
	}
	
	public String getLocationOfTheStringDollarSale() {
		return "//td[@class='para'][normalize-space()='ABD DOLARI']//following-sibling::td[2][@class = 'deger']";
	}
	
	
	public WebElement getLocationOfTheWarning() {
		return driver.findElement(By.cssSelector("div[id='data'] p"));
	}
	public String getLocationOfStringTheWarning() {
		return "div[id='data'] p";
	}
	
	

}
