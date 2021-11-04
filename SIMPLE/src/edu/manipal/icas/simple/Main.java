package edu.manipal.icas.simple;

import edu.manipal.icas.simple.controllers.RouteController;
import edu.manipal.icas.simple.controllers.SessionController;
import edu.manipal.icas.simple.session.Session;
import edu.manipal.icas.simple.controllers.Route;

public class Main {

	public static void main(String[] args) {
		Session currentSession = SessionController.getController().getCurrentSession();
		if(currentSession != null) {
			RouteController.getController().routeTo(currentSession.getDefaultRoute());
		} else {
			RouteController.getController().routeTo(Route.CITIZEN_LOGIN);
		}
	}

}