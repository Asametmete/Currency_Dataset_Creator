package currencyApplication;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Main {

	public static void main(String[] args) throws IOException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		DataRequestedByTheUser dataRequestedByTheUser = new DataRequestedByTheUser();
		Locators locators = new Locators(driver);
		WebDriverWait webdriverWait = new WebDriverWait(driver,Duration.ofSeconds(3));
		BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\samet\\OneDrive\\Masaüstü\\currency.txt")); //Type the path of a blank txt file on your computer here to save it in your own txt file.
		GoToLocators go = new GoToLocators(locators,webdriverWait);
		DataOperations dataOperations = new DataOperations(go,locators,webdriverWait,dataRequestedByTheUser,writer);
		
		locators.getUrl();
		dataOperations.execute();
		

		
	}

}
