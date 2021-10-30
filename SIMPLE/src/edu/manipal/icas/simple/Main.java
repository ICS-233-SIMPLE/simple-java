package edu.manipal.icas.simple;

import edu.manipal.icas.simple.controllers.RouteController;
import edu.manipal.icas.simple.controllers.Route;

public class Main {

	public static void main(String[] args) {
		RouteController.getController().routeTo(Route.CITIZEN_LOGIN);
	}

}
