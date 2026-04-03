package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {
	
	private Page page;
	
	private String email_Id = "//input[@id='input-email']";
	private String password = "//input[@id='input-password']";
	private String loginBtn = "//input[@type='submit']";
	private String forgotLink = "//div[@class='form-group']//a[contains(@href,'forgotten')]";
	private String logoutLink = "//*[@id='column-right']//a[contains(@href,'logout')]";
	
	
	
	public LoginPage(Page page) {
		this.page = page;
	}
	
	
	public boolean doLogin(String email, String pwd ) {
		System.out.println("Email  is " +email + "Password is " + pwd);
		page.waitForSelector(email_Id);
		 page.fill(email_Id, email);
		 page.fill(password, pwd);
		 page.click(loginBtn);
		 page.waitForSelector(logoutLink);
		 if(page.isVisible(logoutLink)) {
			 System.out.println("User is Logged in Successfully...... ");
			 return true;
		 }
		return false;
	}

	
	
	
	public String getLoginTitle() {
	String title =	page.title();
	System.out.println(title +"This is Title of Login Page ");
	return title;
	}
	
	
	
	public boolean forgottenLink() {
		page.waitForSelector(forgotLink);
		return	true;
		
	}
}
