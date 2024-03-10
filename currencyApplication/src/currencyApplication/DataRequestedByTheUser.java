package currencyApplication;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DataRequestedByTheUser {
	
	Scanner scan = new Scanner(System.in);
	
	public String getUserYear() { //Gets the year information from the user and checks for incorrect values.
		   System.out.println("Lütfen Erişmek İstediğiniz Yılı Girininiz. (2024 ile 1996 arası) / Please Enter The Year You Want to Access. (Between 2024 and 1996)");
	        try {
	            int intYear = scan.nextInt();
	            while(intYear>2024 || intYear<1996) {
	            	System.out.println("Lütfen 2024 ile 1996 arası değerleri giriniz / Please Enter Values Between 2024 and 1996");
	            	return getUserYear(); 
	            }
	            return String.valueOf(intYear);
	        } catch (InputMismatchException e) {
	            return "Hatalı değer girdiniz. / You Entered İnvalid Value";
	        }
		
	}
	
	public String getUserMonth() { //Gets the month information from the user and checks for incorrect values.
		  System.out.println("Lütfen Erişmek İstediğiniz Ayı Girininiz. (12 ile 1 arası) / Please Enter The Month You Want to Access. (Between 12 and 1)");
	        try {
	            int intMonth = scan.nextInt();
	            while(intMonth>12 || intMonth<1) {
	            	System.out.println("Lütfen 12 ile 1 arası değerleri giriniz / Please Enter Values Between 12 and 1");
	            	return getUserMonth();
	            }
	            String stringMonth = String.valueOf(intMonth);
	            if(Integer.valueOf(stringMonth)<10) {
	    			stringMonth = "0"+stringMonth;
	    		}
	            return stringMonth;
	            
	        }catch (InputMismatchException e) {
	            return "Hatalı değer girdiniz. / You Entered İnvalid Value";
	        }
	}
	
}
