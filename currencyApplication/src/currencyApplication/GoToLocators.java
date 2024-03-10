package currencyApplication;


import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoToLocators {

	private Locators locators;
	private WebDriverWait webdriverWait;

	public GoToLocators(Locators locators, WebDriverWait webdriverWait) {
		this.locators = locators;
		this.webdriverWait = webdriverWait;

	}
	
	
	public void clickNextYearBtn(String userYear) {    //When list year is finished, it moves to the next list year
	    try {
	        WebElement nextButton = locators.getLocationOfTheNextButton();
	        nextButton.click();
	        webdriverWait.until(ExpectedConditions.stalenessOf(nextButton));
	        goToYear(userYear);
	    } catch (NoSuchElementException e) {
	        
	    }
		
	}

	
	public void goToYear(String userYear) {      //Goes to the user's desired year from list YearList

		List<WebElement> years = locators.getLocationOfTheYears(); 
			for(WebElement year : years) {
				if(year.getText().equals(userYear)) {
					year.click();
				return;}
			}
			
				clickNextYearBtn(userYear);
	
	}
		
	
	public String divPathSelector(String userYear) {     //This method is to determine the path the locator will take. I wrote this method to capture this layout in the css selector
		int orderOfYear = 0;
		List<WebElement> years = locators.getLocationOfTheYears(); //The years are placed in a list and they have a guiding system that goes 1 to the first element of the list and 2 to the next.
			for(WebElement year : years) {
				orderOfYear++;
				if(year.getText().equals(userYear)) {
				break;}
			}
			String divPathSelector = String.valueOf(orderOfYear);
			return divPathSelector;}
	
	
	public void goToMonth(String userYear,String userMonth) {// Goes to the user's desired month
			
		List<WebElement> months = locators.getLocationOfTheMonths(userYear);
			for(WebElement month : months) {
				if(month.getText().equals(userMonth)) {
					month.click();
				break;}
			}
	}
	
	
	public void ShowBtn(String userYear) {//Click on showbtn and the page where we will get the data opens.
	
		WebElement gosterBtn = locators.getLocationOfTheGosterBtn(divPathSelector(userYear));
		webdriverWait.until(ExpectedConditions.visibilityOf(gosterBtn));
		gosterBtn.click();
			
			
	}
	
	
	public void BackBtn(String userYear) {  //Returns to Next Day from data sheet
		
		WebElement backBtn = locators.getLocationOfTheBackBtn();
		webdriverWait.until(ExpectedConditions.visibilityOf(backBtn));
		backBtn.click();
	}

}



