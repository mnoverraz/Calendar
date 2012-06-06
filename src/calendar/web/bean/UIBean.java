package calendar.web.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import calendar.core.application.utils.XMLResourceBundleControl;

public class UIBean {
	private ResourceBundle resourceBundle;
	private String lang;
	private String resourcePath;
	
	public UIBean() {
		System.out.println("bean");
		this.resourcePath = "calendar.web.res.lang";
		setLang("fr");
	}
	
	public UIBean(String resourcePath, String defaultLang) {
		this.resourcePath = resourcePath;
		setLang(defaultLang);
	}
	
		
	public void setLang(String lang) {
		this.lang = lang;
		resourceBundle = ResourceBundle.getBundle(resourcePath, new Locale(this.lang), 
				 new XMLResourceBundleControl());
	}
	
	public String getLang() {
		return lang;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	public String getLangText(String attribute) {
		if (resourceBundle.containsKey(attribute))
			return resourceBundle.getString(attribute);
		else
			return "Attribute '" + attribute + "' not found.";
	}
	
	
}
