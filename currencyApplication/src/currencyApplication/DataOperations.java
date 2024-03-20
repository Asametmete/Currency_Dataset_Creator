package currencyApplication;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DataOperations {

	private GoToLocators goLoc; 
	private Locators locators;
	private DataRequestedByTheUser dataRequestedByTheUser;
	private WebDriverWait webdriverWait;
	private BufferedWriter writer;
	
	
	public DataOperations(GoToLocators goLoc, Locators locators, WebDriverWait webdriverWait ,DataRequestedByTheUser dataRequestedByTheUser, BufferedWriter writer) {
		this.goLoc = goLoc;
		this.locators =locators;
		this.dataRequestedByTheUser = dataRequestedByTheUser;
		this.webdriverWait = webdriverWait;
		this.writer = writer;
	}
	
	
	private String getBuyDollarInf() {
		webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locators.getLocationOfTheStringBuyDollar())));  //There is some information that is not on the page when it is opened, so we must wait until this information is loaded.
		return locators.getLocationOfTheBuyDollar().getText() +" : ";
	}
	
	
	private String getSaleDollarInf() {
		webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locators.getLocationOfTheStringDollarSale())));  //There is some information that is not on the page when it is opened, so we must wait until this information is loaded.
		return locators.getLocationOfTheSaleDollar().getText();
	}
		
	
	private String dayValidator(WebElement day) {     //When we get the day information. it comes with the formation 1,2,3...,10,11, but we want to create it like the formation 01,02,03....,10,11
		if(Integer.valueOf(day.getText())<10) {
        	String dayText = "0"+day.getText();
        	return dayText;}
		else {
			return day.getText();
		}
	}
	

	private boolean pageChecker(String date) {     //When the page we want to get data from is opened. it can display data on the screen or display a warning message on the screen. That's why we want to separate these two screens.
		 try {
			 webdriverWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(locators.getLocationOfTheStringPageDateInf()), ""+date+" Günü Saat 15:30'da Belirlenen Gösterge Niteliğindeki Türkiye Cumhuriyet Merkez Bankası Kurları"));
			 return locators.getLocationOfThePageDateInf().isDisplayed();}  
		 catch(Exception e) { 
		  	  return false;} //if An Exception happened in this method. it will return a false
	}
	
		
	private void writerWrite(String text) {
		 try {
			writer.write(text);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void writerNewLine() {
		try 
		{
			writer.newLine();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void writerCloser() {
		try {
			writer.close();
		}catch(IOException e ) {
			e.printStackTrace();
		}
	}
	
	
	private void GetDataAndWriteTheFile(String userYear,String userMonth) {	
	   List<WebElement> days = locators.getLocationOfTheDays(goLoc.divPathSelector(userYear));   //We took the all the days in the calendar in this list
	   writerWrite("Tarih/Date: "+"Dolar Alış/Dollar Buy: "+"Dolar Satış/Dollar Sell");
	   writerNewLine();
		    for (int i = 0; i < days.size(); i++) {        //handle to calendar
		        try 
		        {
		            WebElement day = days.get(i);  
		            String dayText = dayValidator(day);
		            String date = dayText+"."+userMonth+"."+userYear;
		            writerWrite(date+" : ");
		            WebElement clickableDay = webdriverWait.until(ExpectedConditions.elementToBeClickable(day));   //We are waiting for Ah elements to be loaded
		            clickableDay.click();
 
		            goLoc.ShowBtn(userYear);

		            if (!pageChecker(date)) 
		            {                    
		            	writerNewLine();
		            	goLoc.BackBtn(userYear);
		                System.out.println(""+date+" Günü için Resmi tatil, hafta sonu ve yarım iş günü çalışılan günlerde gösterge niteliginde kur bilgisi yayımlanmamaktadır."
		                		+ "/"+date+" Indicative exchange currency information is not published on public holidays, weekends and half-working days.");
		                //We gave some informations to panel
		                continue;// 
		            }
		          
		            writerWrite(getBuyDollarInf());    //Give The dollar value
		            writerWrite(getSaleDollarInf());
		            writerNewLine();
		     		goLoc.BackBtn(userYear);
		            days = locators.getLocationOfTheDays(goLoc.divPathSelector(userYear)); 

		        } 
		        catch (StaleElementReferenceException e) // if something goes wrong while a day element is running. Returns to "for" and tries again
		        {
		            System.out.println("Stale element reference encountered. Retrying...");
		            days = locators.getLocationOfTheDays(goLoc.divPathSelector(userYear)); 
		            i--;
		            ;
		        }
		    }writerCloser();
		    
			
	}
	

	public void execute() {   //This is the execution part
			String userYear = dataRequestedByTheUser.getUserYear();
			goLoc.goToYear(userYear);
			String userMonth = dataRequestedByTheUser.getUserMonth();
			goLoc.goToMonth(userYear,userMonth);
			GetDataAndWriteTheFile(userYear,userMonth);
	}

}
