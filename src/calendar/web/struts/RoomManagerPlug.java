package calendar.web.struts;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.sql.DataSource;

//import metier.RoomManager;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ModuleConfig;

//import persist.RoomDB;

public class RoomManagerPlug implements PlugIn {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(ActionServlet servlet, ModuleConfig config)
			throws ServletException {
/*		try {
		      Context initCtx = new InitialContext();
		      Context envCtx = (Context) initCtx.lookup("java:comp/env");
		      DataSource ds = (DataSource) envCtx.lookup("jdbc/room");
		      RoomManager roomMgr = new RoomDB(ds);
		      servlet.getServletContext().setAttribute("room_manager", roomMgr);
	    } catch (NamingException e) {
	      e.printStackTrace();
	    }*/
		
	}

}
