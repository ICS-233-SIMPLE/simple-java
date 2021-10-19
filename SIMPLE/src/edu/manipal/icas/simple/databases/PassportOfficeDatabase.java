package edu.manipal.icas.simple.databases;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Interface that defines operations to be implemented in an API that persists
 * passport office data.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface PassportOfficeDatabase {
	/**
	 * Column representing a unique ID for each passport office. To be used as the
	 * primary key.
	 */
	static final String FIELD_ID = "ID";

	/** Column associated with the physical address of the passport office. */
	static final String FIELD_ADDRESS = "ADDRESS";

	/**
	 * Column that holds a list of available appointment slots in a given passport
	 * office.
	 */
	static final String FIELD_AVAILABLE_SLOTS = "AVAILABLE_SLOTS";

	/**
	 * Column associated with the IDs of officers who work in a given passport
	 * office.
	 */
	static final String FIELD_OFFICERS = "OFFICERS";

	/**
	 * Checks whether a passport office is in the database.
	 * 
	 * @param officeId ID of the passport office to check for
	 * @return {@code true} if the passport office exists, {@code false} otherwise
	 */
	Boolean passportOfficeExists(Integer officeId);

	/**
	 * Fetches the address of a passport office from the database.
	 * 
	 * @param officeId ID of the passport office whose address is to be fetched
	 * @return physical address of the office
	 * @throws IOException if no passport was found for the passed ID
	 */
	String fetchOfficeAddress(Integer officeId) throws IOException;

	/**
	 * Saves available slots in a given passport office in the database.
	 * 
	 * @param officeId       ID of the passport office
	 * @param availableSlots a list of Date objects, each of which represents one
	 *                       slot
	 * @throws IOException if no passport was found for the passed ID
	 */
	void saveAvailableSlots(Integer officeId, List<Date> availableSlots) throws IOException;

	/**
	 * Fetches a list of slots available to book in a passport office.
	 * 
	 * @param officeId ID of the passport office
	 * @return list of available slots as dates. Each date corresponds to one slot
	 * @throws IOException if no passport was found for the passed ID
	 */
	List<Date> fetchAvailableSlots(Integer officeId) throws IOException;

	/**
	 * Fetches a list of officers working in a given passport office.
	 * 
	 * @param officeId ID of the passport office
	 * @return list of officer IDs, each of which map to one
	 *         {@link edu.manipal.icas.simple.models.PassportOfficer}
	 */
	List<Integer> fetchOfficers(Integer officeId);
}
