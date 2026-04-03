package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
   private Page page; 
	
	private String search = "input[name='search']";
	private String searchIcon = "//button[contains(@class,'btn btn-default btn-lg')]";
    private String searchPageHeaderString = "div#content h1";
    private String myAccountIcon = "//a[contains(@title,'My Account')]";
    private String loginBtn = "//ul[@class='dropdown-menu dropdown-menu-right']//li[2]//a";
    
    
    //2. page constructor
    
    public HomePage(Page page) {
    	this.page = page;
    }
     //3. page actions and methods 
    
    public  String getHomePageTitle() {
    	
    	String title =  page.title();
    	System.out.println("title is " + title);
    	 return title;
    }
    
    
    public String getHomePageUrl() {
    	String url = page.url();
    	System.out.println("Url is " + url);
    	return url;
    }
    
    
    public String doSearch(String productName) {
    	page.fill(search, productName);
    	page.click(searchIcon);
    	String header = page.textContent(searchPageHeaderString);
    	System.out.println("Header of Page is " + header);
    	return header;
    	
    	
    	
    }
    
    public LoginPage navigateToLoginPage() {
    	page.click(myAccountIcon);
    	page.click(loginBtn);
    	
    	return new LoginPage(page);
    	
    }
    
    
    
    
    
}
