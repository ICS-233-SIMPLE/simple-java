package edu.manipal.icas.simple.controllers;

import edu.manipal.icas.simple.impl.views.ApplicationFormViewImpl;
import edu.manipal.icas.simple.impl.views.BiometricOfficerViewImpl;
import edu.manipal.icas.simple.impl.views.CitizenDashboardViewImpl;
import edu.manipal.icas.simple.impl.views.CitizenLoginViewImpl;
import edu.manipal.icas.simple.impl.views.OfficerLoginViewImpl;
import edu.manipal.icas.simple.impl.views.PassportGrantingOfficerDashboardViewImpl;
import edu.manipal.icas.simple.impl.views.PoliceDashboardViewImpl;
import edu.manipal.icas.simple.impl.views.ProfileCreationViewImpl;
import edu.manipal.icas.simple.impl.views.ApplicationFormViewImpl;
import edu.manipal.icas.simple.impl.views.VerificationOfficerDashboardViewImpl;
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
	private final SessionController sessions = SessionController.getController();

	private View currentView;

	private LoginController loginController;
	private CreateProfileController createProfileController;
	private ApplyForPassportController applyForPassportController;
	private ViewApplicationsController viewApplicationsController;
	private ProcessApplicationController processApplicationController;

	private RouteController() {
		currentView = null;

		loginController = new LoginController(new CitizenLoginViewImpl(), new OfficerLoginViewImpl());
		createProfileController = new CreateProfileController(new ProfileCreationViewImpl());
		applyForPassportController = new ApplyForPassportController(new ApplicationFormViewImpl());
		viewApplicationsController = new ViewApplicationsController(new CitizenDashboardViewImpl());
		processApplicationController = new ProcessApplicationController(new BiometricOfficerViewImpl(),
				new VerificationOfficerDashboardViewImpl(), new PassportGrantingOfficerDashboardViewImpl());
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
			displayView(loginController.getCitizenLoginView());
			break;
		case PROFILE_CREATION:
			displayView(createProfileController.getProfileCreationView());
			break;
		case OFFICER_LOGIN:
			displayView(loginController.getOfficerLoginView());
			break;
		case APPLICATION_FORM:
			displayView(applyForPassportController.getApplicationFormView());
			break;
		case CITIZEN_DASHBOARD:
			displayView(viewApplicationsController.getDashboardView());
			break;
		case BIOMETRICS_DASHBOARD:
			displayView(processApplicationController.getBiometricOfficerView());
			break;
		case POLICE_DASHBOARD:
			displayView(new PoliceDashboardViewImpl());
			break;
		case VERIFICATION_DASHBOARD:
			displayView(processApplicationController.getVerificationOfficerView());
			break;
		case GRANTING_DASHBOARD:
			displayView(processApplicationController.getPassportGrantingOfficerView());
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
		case UNAUTHORISED_403:
			return true;
		default:
			return false;
		}

	}

	/**
	 * Displays a view to the user.
	 *
	 * @param view the view that is to be displayed
	 */
	private void displayView(View view) {
		if (currentView != null)
			currentView.getFrame().setVisible(false);
		currentView = view;
		view.getFrame().setVisible(true);
	}

}
