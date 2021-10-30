package edu.manipal.icas.simple.controllers;

import edu.manipal.icas.simple.databases.SessionDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessSessionDatabase;
import edu.manipal.icas.simple.impl.views.CitizenLoginViewImpl;
import edu.manipal.icas.simple.views.View;

/**
 * Controller that manages view routing. All view controllers must call on this
 * class when they wish to redirect to another route.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public final class RouteController {
	private static final RouteController CONTROLLER = new RouteController();
	private final SessionDatabase sessions = MsAccessSessionDatabase.getDatabase();

	private RouteController() {
	}

	/**
	 * Gets the singleton route controller.
	 * 
	 * @return route controller
	 */
	public static RouteController getController() {
		return CONTROLLER;
	}

	/**
	 * Takes the application to the specified route. <br/>
	 * If the requested route needs authorisation for access, the application is
	 * redirected to the login route. Similarly, if the current session does not
	 * have access to the route, the application redirects to the default route for
	 * the currently running session.
	 * 
	 * @param route the route to redirect to
	 */
	public void routeTo(Route route) {
		if (!isOpenRoute(route) && sessions.getCurrentSession() == null) {
			routeTo(Route.CITIZEN_LOGIN);
		}

		if (!isOpenRoute(route) && !sessions.getCurrentSession().hasAccess(route)) {
			routeTo(Route.UNAUTHORISED_403);
			return;
		}

		switch (route) {
		case CITIZEN_LOGIN:
			View citizenLoginView = new CitizenLoginViewImpl();
			citizenLoginView.getFrame().setVisible(true);
			break;
		// TODO: Add other routes as they come
		default:
			throw new IllegalArgumentException("Unknown route " + route);
		}
	}

	/**
	 * Routes the application to the default route specified by the currently active
	 * session. If no session is running, this method call results in a no-op.
	 */
	public void routeToSessionDefault() {
		if (sessions.getCurrentSession() != null) {
			routeTo(sessions.getCurrentSession().getDefaultRoute());
		}
	}

	/**
	 * Whether a route is open access or not. <br/>
	 * An open access route is one which does not require user authentication to
	 * access. These are primarily routes that handle authentication themselves. If
	 * the route does not require an active session to work, then it is a likely
	 * candidate for an open route.
	 * 
	 * @param route the route to check if is an open access route
	 * @return {@code true} if the route is open access, {@code false} otherwise
	 */
	private Boolean isOpenRoute(Route route) {
		switch (route) {
		case CITIZEN_LOGIN:
		case OFFICER_LOGIN:
		case PROFILE_CREATION:
			return true;
		default:
			return false;
		}

	}

}
