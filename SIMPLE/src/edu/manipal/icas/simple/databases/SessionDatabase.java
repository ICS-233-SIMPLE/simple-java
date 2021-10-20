package edu.manipal.icas.simple.databases;

import java.io.IOException;

import edu.manipal.icas.simple.session.Session;

/**
 * Interface that defines operations for an API that handles session data.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface SessionDatabase {

	/**
	 * The timestamp associated with when the session began. <br/>
	 * Since no two sessions can start simultaneously, this column is considered the
	 * primary key of the table. This key is also used to calculate time deltas to
	 * automatically end stale sessions (say, sessions that have been active for >
	 * 1hr).
	 */
	static final String FIELD_TIMESTAMP = "TIMESTAMP";

	/**
	 * A non-unique ID provided by the Session which is used to reconstruct the
	 * Session object if the application is re-started and a session is still
	 * active.
	 */
	static final String FIELD_SESSION_ID = "SESSION_ID";

	/**
	 * Column that holds the type of the Session object. Can be one of
	 * {@link edu.manipal.icas.simple.session.SessionType}
	 */
	static final String FIELD_SESSION_TYPE = "SESSION_TYPE";

	/**
	 * Column representing the current state of a given session. <br/>
	 * Note: Only one session can be active at any given moment in time.
	 */
	static final String FIELD_ACTIVE = "ACTIVE";

	/**
	 * Starts a new session and marks it as the active session. The session entry in
	 * the database would consist of the timestamp of when the session began, an ID
	 * identifying the session, and an active flag.
	 * 
	 * @param session the Session object to save
	 * @throws Exception if a session was already active
	 */
	void startSession(Session session) throws IOException;

	/**
	 * Ends the current session and forces the user to start a new session should
	 * they wish to continue using the app.
	 * 
	 * @throws Exception if there was no session to end
	 */
	void endCurrentSession() throws IOException;

	/**
	 * Gets the current session, if available.
	 * 
	 * @return a Session object if available, {@code null} otherwise
	 */
	Session getCurrentSession();
}
