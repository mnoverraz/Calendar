package calendar.core.application;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;



/**
 * This class reads the property from an XML file
 * 
 * 
 * @author BELLATALLA Alain, MEIER Stefan, NOVERRAZ Mathieu
 * @version 2011.0704
 * @see java.util.ResourceBundle.Control
 */
public class XMLResourceBundleControl extends ResourceBundle.Control {
	private static String XML = "xml";

	/**
	 * Retunrs a list of XML files
	 */
	public List<String> getFormats(String baseName) {
		return Collections.singletonList(XML);
	}


	/**
	 * The advantage of overriding the newBundle() method is that it allows you
	 * to customize the default implementation of the Bundle instantiation to
	 * suit
	 * the specific needs. In this case, we want to plug in our
	 * XMLResourceBundle class,
	 * passing it a BufferedInputStream.
	 */
	public ResourceBundle newBundle(String baseName, Locale locale,
			String format, ClassLoader loader, boolean reload)
			throws IllegalAccessException, InstantiationException, IOException {
		// ---Check if every parameters are set
		if ((baseName == null) || (locale == null) || (format == null)
				|| (loader == null)) {
			throw new NullPointerException();
		}

		ResourceBundle bundle = null;

		// ---Check if the format is a xml file
		if (!format.equals(XML)) {
			return null;
		}

		String bundleName = toBundleName("tim.res.lang", locale);

		String resourceName = toResourceName(bundleName, format);
		URL url = loader.getResource(resourceName);

		// ---Check the path file
		if (url == null) {
			return null;
		}

		URLConnection connection = url.openConnection();

		// ---Check the connection
		if (connection == null) {
			return null;
		}

		if (reload) {
			connection.setUseCaches(false);
		}

		InputStream stream = connection.getInputStream();

		if (stream == null) {
			return null;
		}

		BufferedInputStream bis = new BufferedInputStream(stream);
		bundle = new XMLResourceBundle(bis);

		bis.close();

		return bundle;
	}
}