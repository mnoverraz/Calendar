package calendar.ui.bean;

import java.util.Locale;
import java.util.ResourceBundle;

import calendar.tools.utils.XMLResourceBundleControl;
/**
 * Holds the applications translations
 * This class prevents direct access to resource bundles
 * 
 * @author AFFOLTER Nicolas, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.06.06
 */
public class UIBean {
	private ResourceBundle resourceBundle;
	/*
	 * Current language of resource bundle
	 */
	private String lang;
	/*
	 * Path to language files 
	 */
	private String resourcePath;
		
	/**
	 * An error is thrown in JSPs if no default constructor exists
	 */
	public UIBean() {};
	
	public UIBean(String resourcePath, String defaultLang) {
		this.resourcePath = resourcePath;
		setLang(defaultLang);
	}
	
	/**
	 * Changes the language of the resource bundle	
	 * @param lang
	 */
	public void setLang(String lang) {
		this.lang = lang;
		resourceBundle = ResourceBundle.getBundle(resourcePath, new Locale(this.lang), 
				 new XMLResourceBundleControl());
	}
	
	/**
	 * Gets the current language
	 * @return String
	 */
	public String getLang() {
		return lang;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}
	
	/**
	 * Gets language text corresponding to the attribute specified
	 * @param attribute
	 * @return String
	 */
	public String getLangText(String attribute) {
		if (resourceBundle.containsKey(attribute))
			return resourceBundle.getString(attribute);
		else
			return "Attribute '" + attribute + "' not found.";
	}
	
	
}
