package calendar.web.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import calendar.core.application.utils.XMLResourceBundleControl;

public class UIBean {
	private ResourceBundle resourceBundle;
		
	public void setLang(String lang) {
		if (lang == null)
			lang = "fr";
		resourceBundle = ResourceBundle.getBundle("calendar.core.res.lang", new Locale(lang), 
				 new XMLResourceBundleControl());
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
