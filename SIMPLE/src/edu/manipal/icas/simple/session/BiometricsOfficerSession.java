package edu.manipal.icas.simple.session;

import edu.manipal.icas.simple.controllers.Route;
import edu.manipal.icas.simple.models.BiometricsOfficer;

/**
 * Represents a session created by a biometrics officer.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class BiometricsOfficerSession extends Session {
	private BiometricsOfficer biometricsOfficer;

	public BiometricsOfficerSession(BiometricsOfficer biometricsOfficer) {
		this.biometricsOfficer = biometricsOfficer;
	}

	@Override
	public SessionType getSessionType() {
		return SessionType.BIOMETRICS_OFFICER;
	}

	@Override
	public String getSessionId() {
		return biometricsOfficer.getOfficerId() + "";
	}

	@Override
	public Boolean hasAccess(Route route) {
		switch (route) {
		case BIOMETRICS_DASHBOARD:
			return true;
		default:
			return false;
		}
	}

	@Override
	public Route getDefaultRoute() {
		return Route.BIOMETRICS_DASHBOARD;
	}
	
	public BiometricsOfficer getOfficer() {
		return biometricsOfficer;
	}

}
