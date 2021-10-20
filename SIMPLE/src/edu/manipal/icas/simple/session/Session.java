package edu.manipal.icas.simple.session;

/**
 * Class representing a session. <br/>
 * A session is a duration between the logging in of a user and the end of a
 * session. Essentially, it is the period when the user interacts with the
 * system. <br/>
 * <br/>
 * A session may be of several types, depending on the type of user currently
 * interacting with the system. The system uses session data as the source of
 * truth for delegating access to routes and views. <br/>
 * Session data is also used to get a singular reference to the currently active
 * user. This helps avoid multiple objects representing the same DB entity,
 * which could create race conditions in DB I/O operations.<br/>
 * <br/>
 * A session can end in one of two ways: either the currently logged in user
 * signs out of the system, or the session automatically expires once it goes
 * stale (say, 1h after being started). If the session ends due to such a
 * timeout, the system requests the user to initiate a new session by re-logging
 * in.<br/>
 * Note: A session can persist between subsequent application close/open
 * operations, i.e., a session is not destroyed if the user exits from the app
 * without logging out first.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public abstract class Session {
	/**
	 * Gets the session type. Used to reconstruct the session when the application
	 * is re-opened.
	 * 
	 * @return one of {@link SessionType}
	 */
	public abstract SessionType getSessionType();

	/**
	 * Gets a non-unique session identifier that is used in the reconstruction
	 * process to attach the correct actor to the session. However, note that this
	 * ID must uniquely identify this session among the set of other sessions of the
	 * same type.
	 * 
	 * @return an ID that represents the user who initiated this session
	 */
	public abstract String getSessionId();
}
