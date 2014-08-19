package by.filippov.library.utils;

import java.util.ResourceBundle;

public class JspManager {
	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("properties.jsp");

	private JspManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
