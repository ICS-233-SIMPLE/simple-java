package edu.manipal.icas.simple.databases;

import java.io.IOException;
import java.util.List;

import edu.manipal.icas.simple.models.PassportOfficerRole;

/**
 * Interface that defines operations to be implemented in an API that persists
 * data about passport officers.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface PassportOfficerDatabase {
	/**
	 * Column representing a unique ID for each passport officer. To be used as the
	 * primary key.
	 */
	static final String FIELD_ID = "ID";

	/** Column that holds the role played by a given officer. */
	static final String FIELD_OFFICER_ROLE = "ROLE";

	/**
	 * Checks whether a passport officer of a given ID exists in the database.
	 * 
	 * @param officerId ID of the officer
	 * @return {@code true} if the entry exists, {@code false} otherwise
	 */
	Boolean passportOfficerExists(Integer officerId);

	/**
	 * Fetches the role played by a given passport officer from the database.
	 * 
	 * @param officerId ID of the officer whose role is to be obtained
	 * @return one of {@link edu.manipal.icas.simple.models.PassportOfficerRole}
	 * @throws IOException if no passport officer was found for the passed ID
	 */
	PassportOfficerRole fetchOfficerRole(Integer officerId) throws IOException;

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
