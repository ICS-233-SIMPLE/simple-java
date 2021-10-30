package edu.manipal.icas.simple.session;

import edu.manipal.icas.simple.controllers.Route;
import edu.manipal.icas.simple.models.Citizen;

/**
 * Class representing a session started by a citizen user.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class CitizenSession extends Session {
	private Citizen citizen;

	public CitizenSession(Citizen citizen) {
		this.citizen = citizen;
	}

	@Override
	public SessionType getSessionType() {
		return SessionType.CITIZEN;
	}

	@Override
	public String getSessionId() {
		return citizen.getEmailAddress();
	}
	
	@Override
	public Boolean hasAccess(Route route) {
		switch (route) {
		case CITIZEN_DASHBOARD:
		case APPLICATION_FORM:
			return true;
		default:
			return false;
		}
	}
	
	@Override
		public Route getDefaultRoute() {
			return Route.CITIZEN_DASHBOARD;
		}
}
