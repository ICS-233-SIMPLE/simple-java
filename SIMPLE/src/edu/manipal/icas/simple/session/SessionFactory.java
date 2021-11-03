package edu.manipal.icas.simple.session;

import edu.manipal.icas.simple.models.Citizen;

public class SessionFactory {
	private static final SessionFactory FACTORY = new SessionFactory();

	private SessionFactory() {
	}

	public static SessionFactory getFactory() {
		return FACTORY;
	}

	public Session getSession(SessionType type, String sessionId) {
		switch (type) {
		case CITIZEN:
			return new CitizenSession(new Citizen(sessionId));

		default:
			throw new IllegalArgumentException("Unknown session type " + type);
		}
	}
}
