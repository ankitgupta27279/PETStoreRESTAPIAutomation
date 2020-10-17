package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

	private static ConfigManager manager;
	private static final Properties prop = new Properties();
	
	private ConfigManager() {
		try {
			InputStream inputStream = ConfigManager.class.getResourceAsStream("/config.properties");
			prop.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ConfigManager getInstance() {
		if(manager == null) {
			manager = new ConfigManager();
		}
		return manager;
	}
	
	public String getString(String key) {
		return System.getProperty(key, prop.getProperty(key));
	}
}
