package calendar.web.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import calendar.core.application.utils.XMLResourceBundleControl;

public class UIBean {
	private ResourceBundle resourceBundle;
	private String lang;
	
	public UIBean() {
		setLang("fr");
	}
		
	public void setLang(String lang) {
		this.lang = lang;
		resourceBundle = ResourceBundle.getBundle("calendar.core.res.lang", new Locale(this.lang), 
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
