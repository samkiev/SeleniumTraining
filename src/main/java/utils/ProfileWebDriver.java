package utils;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class ProfileWebDriver {
	
	
	public static FirefoxProfile getFirefoxProfile() {				
				FirefoxProfile fp = new FirefoxProfile();	  
				fp.setPreference("intl.accept_languages", Language.getCurrentLanguage().toString()); 	
			 return fp;
	}

	public static ChromeOptions getChromeProfile() {				
			ChromeOptions cp = new ChromeOptions();
			cp.addArguments("--lang=" + Language.getCurrentLanguage());					
		return cp;
	}
}
