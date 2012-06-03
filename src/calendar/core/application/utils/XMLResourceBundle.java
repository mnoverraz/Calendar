package calendar.core.application.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * This class gives a ResourceBundle the ability to parse XML files
 * 
 * 
 * @author BELLATALLA Alain, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.0704
 */
public class XMLResourceBundle extends ResourceBundle {

	private Properties props;

	/**
	 * 
	 * @param stream
	 * @throws IOException
	 */
	XMLResourceBundle(InputStream stream) throws IOException {
		props = new Properties();
		props.loadFromXML(stream);
	}

	/**
	 * @param key (The key of the xml file to retrieve the value)
	 * @return object (property)
	 */
	protected Object handleGetObject(String key) {
		return props.getProperty(key);
	}

	/**
	 * Returns all keys contained in this ResourceBundle and its parents bundles
	 * in a Collection
	 * 
	 */
	public Enumeration<String> getKeys() {
		Set<String> handleKeys = props.stringPropertyNames();

		return Collections.enumeration(handleKeys);
	}

}
