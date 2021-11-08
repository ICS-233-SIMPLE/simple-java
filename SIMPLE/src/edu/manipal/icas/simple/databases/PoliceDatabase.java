package edu.manipal.icas.simple.databases;

import java.io.IOException;
import java.util.List;

/**
 * Interface that defines operations to be implemented in an API that persists
 * data about police officers in the system.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface PoliceDatabase {
	/**
	 * Column representing a unique ID for each police officer. To be used as the
	 * primary key.
	 */
	static final String FIELD_BADGE_ID = "ID";

	/**
	 * Checks whether a police officer exists in the database.
	 * 
	 * @param badgeId ID of the officer who is to be found
	 * @return {@code true} if the entry was found, {@code false} otherwise
	 */
	Boolean policeExists(Integer badgeId);

	/**
	 * Fetches a list of applications that a given officer is responsible for
	 * handling.
	 * 
	 * @param officerId ID of the officer
	 * @return list of applications
	 * @throws IOException if no passport officer was found for the passed ID
	 */
	List<Integer> fetchApplications(Integer officerId) throws IOException;
}
