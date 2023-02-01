package com.shopping_service.security.config;

public class Urls {
	
	public static final String[] publicUrls = {
			"/api/registration",
			"/api/registration/**",
			"/api/products",
			"/api/products/**",
			"/api/auth",
			"/api/auth/**"
	};
	
	public static final String[] administrator = {
			"/api/admin/mgt/**"
	};
	
	public static final String[] superAdministrator = {
			"/api/mgt",
			"/api/mgt/**"
	};
	
	public static final String[] systemAdministrator = {
			"/api/system/**"
	};

}
