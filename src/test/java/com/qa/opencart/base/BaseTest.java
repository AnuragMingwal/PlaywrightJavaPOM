package com.qa.opencart.base;

import java.util.Properties;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Page;
import com.qa.opencart.factory.PlaywrightFactory;
import com.qa.opencart.pages.HomePage;
import com.qa.opencart.pages.LoginPage;

public class BaseTest {
		protected Page page; 
		protected PlaywrightFactory pf;
		protected HomePage homePage;
		protected LoginPage loginPage;
		protected Properties prop;
	
		@BeforeMethod(alwaysRun = true)
		public void setUp() {
			System.out.println("SETUP START");
		pf = new PlaywrightFactory();
		prop = pf.init_Prop();
		page = pf.initBrowser(prop);
		homePage = new HomePage(page);
		loginPage = new LoginPage(page);
		System.out.println("page = " + page);
		System.out.println("homePage = " + homePage);
		System.out.println("prop = " + prop);
		
		
	}
	
		@AfterMethod(alwaysRun = true)
		public void tearDown() {
			 if (page != null) {
		            page.context().browser().close();
        }
	}
	
	

}
