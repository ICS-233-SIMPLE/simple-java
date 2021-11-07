package edu.manipal.icas.simple.session;

import edu.manipal.icas.simple.controllers.Route;
import edu.manipal.icas.simple.models.BiometricsOfficer;
import edu.manipal.icas.simple.models.PassportGrantingOfficer;

/**
 * Represents a session created by the passport granting officer.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class PassportGrantingOfficerSession extends Session {

	private PassportGrantingOfficer passportGrantingOfficer;

	public PassportGrantingOfficerSession(PassportGrantingOfficer passportGrantingOfficer) {
		this.passportGrantingOfficer = passportGrantingOfficer;
	}

	@Override
	public SessionType getSessionType() {
		return SessionType.GRANTING_OFFICER;
	}

	@Override
	public String getSessionId() {
		return passportGrantingOfficer.getOfficerId() + "";
	}

	@Override
	public Boolean hasAccess(Route route) {
		switch (route) {
		case GRANTING_DASHBOARD:
			return true;
		default:
			return false;
		}
	}

	@Override
	public Route getDefaultRoute() {
		return Route.GRANTING_DASHBOARD;
	}
	
	public BiometricsOfficer getOfficer() {
		return biometricsOfficer;
	}

}
