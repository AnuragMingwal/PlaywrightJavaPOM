package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {
	
	static Playwright playwright;
	static BrowserContext browserContext;
	 Browser browser;
	 static Page page;
	
	Properties prop;
	
	
	private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	private static ThreadLocal<BrowserContext> tlbrowserContext = new ThreadLocal<>();
	private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	
	private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();
	
	
	public static Playwright getPlaywright() {
		try {
			return tlPlaywright.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return playwright;
	}
	
	public static  Page getPage() {
		try {
			return tlPage.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return page;
	}
	
	
	
	
	public static BrowserContext getBrowserContext() {
		try {
			return tlbrowserContext.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return browserContext;
	}
	
	
	
	public  Browser getBrowser() {
		try {
			return tlBrowser.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return browser;
	}
	
	
	
	
	
	
	
	
	
	public Page initBrowser(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		System.out.println("Browser Name is: " + browserName);
		
		//playwright = Playwright.create();
		tlPlaywright.set(Playwright.create());
		
		switch (browserName.toLowerCase()) {
			case "chrome":
				//browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
				
				tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(true)));
				
				break;
        
			
			case "firefox":
				//browser=playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
				tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(true)));

				break;		
			
			case "safari":
				//browser=playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
				tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(true)));

				break;
			
		default:
			System.out.println("Please pass The exact Browser");
			break;
		}
		
		
		
		tlbrowserContext.set(getBrowser().newContext());
		
		tlPage.set(getBrowserContext().newPage());
		getPage().navigate(prop.getProperty("url").trim());
		return getPage();
		
//		browserContext = browser.newContext();
//		page = browserContext.newPage();
//		page.navigate(prop.getProperty("url").trim());
//		return page;
		
		
		
		
		
			}
	
	/**
	 * this method is use to initialise the properties from config files 
	 * @throws FileNotFoundException 
	 */
	
	
	public Properties init_Prop() {
	    prop = new Properties();
	    try {
	        InputStream ip = getClass().getClassLoader()
	                .getResourceAsStream("config/config.properties");
	        prop.load(ip);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return prop;
	}
	   
	  
	  
	  /**
	   * Screenshot Method 
	 * @return 
	   */
	  
//	  public   static String takeScreenshot() {
//		   String path =  System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
//	          getPage().screenshot(new Page.ScreenshotOptions()
//	        		  .setPath(Paths.get(path))
//	        		  .setFullPage(true));
//	          return path;
//	          
//	  
//	  }
	  
	  
	  public static String takeScreenshot() {

		    String dir = System.getProperty("user.dir") + "/screenshots/";
		    
		    // ensure folder exists
		    new File(dir).mkdirs();

		    String path = dir + System.currentTimeMillis() + ".png";

		    try {
		        getPage().screenshot(new Page.ScreenshotOptions()
		                .setPath(Paths.get(path))
		                .setFullPage(true));
		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return path;
		}
	  
		}
	   

		

