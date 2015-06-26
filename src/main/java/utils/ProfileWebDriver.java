package utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class ProfileWebDriver {
	
	private static String getLanguageCode(){
		return System.getProperty("language").toLowerCase();		
	}
	public static FirefoxProfile getFirefoxProfile() {				
				FirefoxProfile fp = new FirefoxProfile();	  
				fp.setPreference("intl.accept_languages",getLanguageCode()); 	
			 return fp;
	}

	public static ChromeOptions getChromeProfile() {				
			ChromeOptions cp = new ChromeOptions();
			cp.addArguments("--lang=" + getLanguageCode());					
		return cp;
	}
}
