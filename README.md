# Currency_Dataset_Creator
This project takes the year and month information requested by the User and shows the User the value of the Turkish lira against the dollar in that month and creates a data set.


## Programming Languages Used

Java

**automation tool:** Selenium

  
## For Use

Create a txt file and place its file path in the "PATH/" section. With this, the data set can now be written to the desired txt file.


```
  public class Main {

	public static void main(String[] args) throws IOException {
		
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver();
		DataRequestedByTheUser dataRequestedByTheUser = new DataRequestedByTheUser();
		Locators locators = new Locators(driver);
		WebDriverWait webdriverWait = new WebDriverWait(driver,Duration.ofSeconds(3));
		BufferedWriter writer = new BufferedWriter(new FileWriter("PATH/")); //TYPE THE PATH OF A BLANK TXT FİLE ON YOUR COMPUTER HERE TO SAVE IT IN YOUR OWN TXT FİLE.
		GoToLocators go = new GoToLocators(locators,webdriverWait);
		DataOperations dataOperations = new DataOperations(go,locators,webdriverWait,dataRequestedByTheUser,writer);
		
		locators.getUrl();
		dataOperations.execute();
		

		
	}
```
