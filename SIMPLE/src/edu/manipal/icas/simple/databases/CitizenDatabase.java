package edu.manipal.icas.simple.databases;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Interface that defines operations to be implemented in an API that persists
 * citizen profile data.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface CitizenDatabase {
	/**
	 * Column associated with the email address of the citizen. To be used as the
	 * primary key.
	 */
	static final String FIELD_EMAIL_ADDRESS = "EMAIL_ADDRESS";

	/** Column that holds a (salted) hash of the citizen's password */
	static final String FIELD_PASSWORD = "PASSWORD";

	/** Column associated with the name of the citizen */
	static final String FIELD_NAME = "NAME";

	/** Column associated with the gender of the citizen */
	static final String FIELD_GENDER = "GENDER";

	/** Column associated with the date of birth of the citizen */
	static final String FIELD_DATE_OF_BIRTH = "DOB";

	/** Column associated with the contact number of the citizen */
	static final String FIELD_CONTACT_NUMBER = "CONTACT_NUMBER";

	/**
	 * Column that holds the ID of the passport office that services the
	 * corresponding citizen. Foreign key from
	 * {@link edu.manipal.icas.simple.databases.PassportOfficeDatabase}
	 */
	static final String FIELD_PASSPORT_OFFICE_ID = "PASSPORT_OFFICE_ID";

	/**
	 * Column that holds the badge ID of the police officer who verifies the address
	 * of the corresponding citizen. Foreign key from
	 * {@link edu.manipal.icas.simple.databases.PoliceDatabase}
	 */
	static final String FIELD_POLICE_BADGE_ID = "POLICE_BADGE_ID";

	/**
	 * Column associated with a list of passports that the citizen has held over
	 * time. Each item in the list is a foreign key from
	 * {@link edu.manipal.icas.simple.databases.PassportDatabase}
	 */
	static final String FIELD_PASSPORT_IDS = "PASSPORT_IDS";

	/**
	 * Creates a new citizen entry in the database given a unique email address
	 * 
	 * @param emailAddress unique email address (primary key)
	 * @throws IOException if the email was not unique
	 */
	void createCitizen(String emailAddress) throws IOException;

	/**
	 * Checks whether a citizen of the given email address exists in the database.
	 * 
	 * @param emailAddress
	 * @return {@code true} if the citizen entry exists, {@code false} otherwise
	 */
	Boolean citizenExists(String emailAddress);

	/**
	 * Saves a salted hash of the citizen's password in the database.
	 * 
	 * @param emailAddress email address of the citizen associated with the password
	 * @param password     password to be saved
	 * @throws IOException if no citizen entry was found for the email address
	 */
	void savePassword(String emailAddress, String password) throws IOException;

	/**
	 * Fetches the password associated with a citizen from the database.
	 * 
	 * @param emailAddress email of the citizen whose password is to be fetched
	 * @return salted hash of the citizen's password
	 * @throws IOException if no citizen entry was found for the email address
	 */
	String fetchPassword(String emailAddress) throws IOException;

	/**
	 * Saves the name of a citizen in the database.
	 * 
	 * @param emailAddress email of the citizen associated with the new name
	 * @param name         name to be saved
	 * @throws IOException if no citizen entry was found for the email address
	 */
	void saveName(String emailAddress, String name) throws IOException;

	/**
	 * Fetches the name of a citizen from the database.
	 * 
	 * @param emailAddress email of the citizen whose name is to be fetched
	 * @return name of the citizen
	 * @throws IOException if no citizen entry was found for the email address
	 */
	String fetchName(String emailAddress) throws IOException;

	/**
	 * Saves the gender of a citizen in the database.
	 * 
	 * @param emailAddress email of the citizen associated with the gender
	 * @param gender       gender to be saved
	 * @throws IOException if no citizen entry was found for the email address
	 */
	void saveGender(String emailAddress, String gender) throws IOException;

	/**
	 * Fetches the gender of a citizen from the database.
	 * 
	 * @param emailAddress email of the citizen whose gender is to be fetched
	 * @return gender of the citizen
	 * @throws IOException if no citizen entry was found for the email address
	 */
	String fetchGender(String emailAddress) throws IOException;

	/**
	 * Saves the date of birth of a citizen in the database.
	 * 
	 * @param emailAddress email of the citizen associated with the date
	 * @param dob          date of birth to be saved
	 * @throws IOException if no citizen entry was found for the email address
	 */
	void saveDateOfBirth(String emailAddress, Date dob) throws IOException;

	/**
	 * Fetches the date of birth of a citizen from the database.
	 * 
	 * @param emailAddress email of the citizen whose dob is to be fetched
	 * @return date of birth of the citizen
	 * @throws IOException if no citizen entry was found for the email address
	 */
	Date fetchDateOfBirth(String emailAddress) throws IOException;

	/**
	 * Saves the phone/contact number of a citizen in the database.
	 * 
	 * @param emailAddress  email of the citizen associated with the contact number
	 * @param contactNumber 10-digit phone number to be saved
	 * @throws IOException if no citizen entry was found for the email address
	 */
	void saveContactNumber(String emailAddress, Long contactNumber) throws IOException;

	/**
	 * Fetches the phone number of a citizen from the database.
	 * 
	 * @param emailAddress email of the citizen whose contact number is to be
	 *                     fetched
	 * @return 10-digit phone number associated with the citizen
	 * @throws IOException if no citizen entry was found for the email address
	 */
	Long fetchContactNumber(String emailAddress) throws IOException;

	/**
	 * Saves the office ID of the passport office servicing the citizen.
	 * 
	 * @param emailAddress     email of the citizen serviced by the passport office
	 * @param passportOfficeId ID of the passport office (foreign key)
	 * @throws IOException if no citizen entry was found for the email address
	 */
	void savePassportOfficeId(String emailAddress, Integer passportOfficeId) throws IOException;

	/**
	 * Fetches the office ID of the passport office associated with the citizen from
	 * the database.
	 * 
	 * @param emailAddress email of the citizen whose associated PO is to be fetched
	 * @return unique key identifying each passport office
	 * @throws IOException if no citizen entry was found for the email address
	 */
	Integer fetchPassportOfficeId(String emailAddress) throws IOException;

	/**
	 * Saves the badge ID of the police officer associated with the citizen.
	 * 
	 * @param emailAddress  email of the citizen whose address is verified by the
	 *                      police having this badge number
	 * @param policeBadgeId badge number of the officer
	 * @throws IOException if no citizen entry was found for the email address
	 */
	void savePoliceBadgeId(String emailAddress, Integer policeBadgeId) throws IOException;

	/**
	 * Fetches the badge ID of the citizen's associated police officer from the
	 * database.
	 * 
	 * @param emailAddress email of the citizen whose associated officer is to be
	 *                     fetched
	 * @return unique key identifying the police officer (badge ID)
	 * @throws IOException if no citizen entry was found for the email address
	 */
	Integer fetchPoliceBadgeId(String emailAddress) throws IOException;

	/**
	 * Saves a list of IDs of passports owned by the citizen.
	 * 
	 * @param emailAddress email of the citizen
	 * @param passportIds  list of IDs, each of which corresponds to a unique
	 *                     passport
	 * @throws IOException if no citizen entry was found for the email address
	 */
	void savePassportIds(String emailAddress, List<String> passportIds) throws IOException;

	/**
	 * Fetches a list of passport IDs belonging to the citizen from the database.
	 * 
	 * @param emailAddress email of the citizen whose passports are to be fetched
	 * @return list of unique passport IDs
	 * @throws IOException if no citizen entry was found for the email address
	 */
	List<String> fetchPassportIds(String emailAddress) throws IOException;
}
