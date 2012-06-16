package calendar.tools.utils;

import org.hibernate.dialect.MySQLDialect;

public class CustomMySQLDialect extends MySQLDialect {
 
  @Override
  public String getTableTypeString() {
    return " DEFAULT CHARSET=utf8";
  }
 
}