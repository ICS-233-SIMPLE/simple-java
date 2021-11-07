package edu.manipal.icas.simple.session;

import edu.manipal.icas.simple.controllers.Route;
import edu.manipal.icas.simple.models.BiometricsOfficer;
import edu.manipal.icas.simple.models.VerificationOfficer;

/**
 * Represents a session created by a verification officer.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class VerificationOfficerSession extends Session {
	private VerificationOfficer verificationOfficer;

	public VerificationOfficerSession(VerificationOfficer verificationOfficer) {
		this.verificationOfficer = verificationOfficer;
	}

	@Override
	public SessionType getSessionType() {
		return SessionType.VERIFICATION_OFFICER;
	}

	@Override
	public String getSessionId() {
		return verificationOfficer.getOfficerId() + "";
	}

	@Override
	public Boolean hasAccess(Route route) {
		switch (route) {
		case VERIFICATION_DASHBOARD:
			return true;
		default:
			return false;
		}
	}

	@Override
	public Route getDefaultRoute() {
		return Route.VERIFICATION_DASHBOARD;
	}
	
	public BiometricsOfficer getOfficer() {
		return biometricsOfficer;
	}

}
