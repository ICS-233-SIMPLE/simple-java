package edu.manipal.icas.simple.databases;

import java.io.IOException;
import java.util.Date;

import edu.manipal.icas.simple.models.PassportStatus;

/**
 * Interface that defines operations to be implemented in an API that persists
 * passport data.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface PassportDatabase {
	/**
	 * Column associated with the unique passport ID. To be used as the primary key.
	 */
	static final String FIELD_PASSPORT_ID = "ID";

	/**
	 * Column associated with the name of the document used as the photograph in the
	 * passport. Foreign key from
	 * {@link edu.manipal.icas.simple.databases.DocumentDatabase}
	 */
	static final String FIELD_PHOTOGRAPH = "PHOTOGRAPH";

	/** Column that holds the birth address used in the passport. */
	static final String FIELD_BIRTH_ADDRESS = "BIRTH_ADDRESS";

	/** Column that holds the permanent address used in the passport. */
	static final String FIELD_PERMANENT_ADDRESS = "PERMANENT_ADDRESS";

	/** Column associated with the name of the passport holder's father. */
	static final String FIELD_NAME_OF_FATHER = "FATHER_NAME";

	/** Column associated with the name of the passport holder's mother. */
	static final String FIELD_NAME_OF_MOTHER = "MOTHER_NAME";

	/** Column that holds the date up to which this passport is valid. */
	static final String FIELD_EXPIRY_DATE = "EXPIRY";

	/**
	 * Column that holds the current
	 * {@link edu.manipal.icas.simple.models.PassportStatus}
	 */
	static final String FIELD_STATUS = "STATUS";

	/**
	 * Column associated with the owner of the passport. Foreign key from
	 * {@link edu.manipal.icas.simple.databases.CitizenDatabase}
	 */
	static final String FIELD_OWNER_ID = "OWNER_ID";

	/**
	 * Checks whether a passport of the given ID exists in the database.
	 * 
	 * @param passportId id to find in the database
	 * @return {@code true} if the passport exists, {@code false} otherwise
	 */
	Boolean passportExists(String passportId);

	/**
	 * Creates a new immutable passport record in the database. Note: Only the
	 * passport status can be updated in the database beyond this point.
	 * 
	 * @param photographName   name of the document associated with the photograph
	 *                         to be used
	 * @param birthAddress     birth address of the holder
	 * @param permanentAddress permanent address of the holder
	 * @param nameOfFather     holder's father's name
	 * @param nameOfMother     holder's mother's name
	 * @param expiryDate       date up till which passport will be valid
	 * @param ownerId          primary key of the citizen who owns this passport
	 * @return unique ID of the passport
	 * @throws IOException if the provided passportId was not unique
	 */
	Integer createPassport(String photographName, String birthAddress, String permanentAddress, String nameOfFather,
			String nameOfMother, Date expiryDate, String ownerId) throws IOException;

	/**
	 * Fetches the name of the photograph document associated with the passport.
	 * 
	 * @param passportId the passport whose photograph is to be fetched
	 * @return unique ID identifying the photograph document
	 * @throws IOException if no passport was found for the passed ID
	 */
	String fetchPhotographName(String passportId) throws IOException;

	/**
	 * Fetches the birth address associated with the passport's holder.
	 * 
	 * @param passportId passport whose attribute is to be fetched
	 * @return the birth address as listed in the passport
	 * @throws IOException if no passport was found for the passed ID
	 */
	String fetchBirthAddress(String passportId) throws IOException;

	/**
	 * Fetches the permanent address associated with the passport's holder.
	 * 
	 * @param passportId passport whose attribute is to be fetched
	 * @return the permanent address as listed in the passport
	 * @throws IOException if no passport was found for the passed ID
	 */
	String fetchPermanentAddress(String passportId) throws IOException;

	/**
	 * Fetches the father's name associated with the passport's holder.
	 * 
	 * @param passportId passport whose attribute is to be fetched
	 * @return the father's name as listed in the passport
	 * @throws IOException if no passport was found for the passed ID
	 */
	String fetchNameOfFather(String passportId) throws IOException;

	/**
	 * Fetches the mother's name associated with the passport's holder.
	 * 
	 * @param passportId passport whose attribute is to be fetched
	 * @return the mother's name as listed in the passport
	 * @throws IOException if no passport was found for the passed ID
	 */
	String fetchNameOfMother(String passportId) throws IOException;

	/**
	 * Fetches the date on which the passport will expire.
	 * 
	 * @param passportId passport whose attribute is to be fetched
	 * @return the expiry date as listed in the passport
	 * @throws IOException if no passport was found for the passed ID
	 */
	Date fetchExpiryDate(String passportId) throws IOException;

	/**
	 * Saves the status of the passport in the database.
	 * 
	 * @param passportId the passport whose status is to be saved
	 * @param status     the new status
	 * @throws IOException if no passport was found for the passed ID
	 */
	void saveStatus(String passportId, PassportStatus status) throws IOException;

	/**
	 * Fetches the {@link edu.manipal.icas.simple.models.PassportStatus} of the
	 * passport.
	 * 
	 * @param passportId passport whose attribute is to be fetched
	 * @return the status of the passport
	 * @throws IOException if no passport was found for the passed ID
	 */
	PassportStatus fetchStatus(String passportId) throws IOException;

	/**
	 * Fetches the ID of the owner of the passport.
	 * 
	 * @param passportId passport whose attribute is to be fetched
	 * @return unique ID corresponding to the owner citizen of the passport
	 * @throws IOException if no passport was found for the passed ID
	 */
	String fetchOwnerId(String passportId) throws IOException;
}
