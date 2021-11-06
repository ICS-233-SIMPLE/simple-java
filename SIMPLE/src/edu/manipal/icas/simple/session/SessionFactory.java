package edu.manipal.icas.simple.session;

import edu.manipal.icas.simple.models.BiometricsOfficer;
import edu.manipal.icas.simple.models.Citizen;
import edu.manipal.icas.simple.models.PassportGrantingOfficer;
import edu.manipal.icas.simple.models.VerificationOfficer;

/**
 * Singleton factory that creates new sessions.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class SessionFactory {
	private static final SessionFactory FACTORY = new SessionFactory();

	private SessionFactory() {
	}

	public static SessionFactory getFactory() {
		return FACTORY;
	}

	/**
	 * Gets a session of a given type and ID
	 * 
	 * @param type      one of {@link SessionType}
	 * @param sessionId ID that is used to reconstruct the session. See each
	 *                  implementation to for details on what the ID is
	 * @return a new session object
	 */
	public Session getSession(SessionType type, String sessionId) {
		switch (type) {
		case CITIZEN:
			return new CitizenSession(new Citizen(sessionId));
		case VERIFICATION_OFFICER:
			return new VerificationOfficerSession(new VerificationOfficer(Integer.parseInt(sessionId)));
		case BIOMETRICS_OFFICER:
			return new BiometricsOfficerSession(new BiometricsOfficer(Integer.parseInt(sessionId)));
		case GRANTING_OFFICER:
			return new PassportGrantingOfficerSession(new PassportGrantingOfficer(Integer.parseInt(sessionId)));

		default:
			throw new IllegalArgumentException("Unknown session type " + type);
		}
	}
}
