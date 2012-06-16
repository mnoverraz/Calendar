import java.text.ParseException;

import javax.naming.NamingException;

import calendar.console.InitDB;
import calendar.core.ejb.session.PersistException;


public class Main {
	public static void main(String[] args) throws NamingException,
			ParseException, PersistException {
		new InitDB();
	}
}