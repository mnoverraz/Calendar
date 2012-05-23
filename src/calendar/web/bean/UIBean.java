package calendar.web.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import calendar.core.application.XMLResourceBundleControl;

public class UIBean {
	private ResourceBundle resourceBundle;
	
	public UIBean(String lang) {
		resourceBundle = ResourceBundle.getBundle("lang", new Locale(lang), 
				 new XMLResourceBundleControl());
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	
}
