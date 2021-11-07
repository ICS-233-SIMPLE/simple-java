package edu.manipal.icas.simple.databases;

import java.io.IOException;
import java.util.Date;
import edu.manipal.icas.simple.models.application.AcceptedBiometricType;
import edu.manipal.icas.simple.models.application.AcceptedDocumentType;
import edu.manipal.icas.simple.models.application.ApplicationStatus;
import edu.manipal.icas.simple.models.application.ApplicationType;

/**
 * Interface that defines operations to be implemented in an API that persists
 * passport applications.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface ApplicationDatabase {

	/**
	 * Column corresponding to the unique ID of each application. To be used as the
	 * primary key.
	 */
	static final String FIELD_ID = "ID";

	/**
	 * Set of columns each of which hold a true/false response to a question. This
	 * constant is to be used in conjunction with the question number to match the
	 * column name as in the database.<br/>
	 * E.g.: FIELD_QUESTION + "1.1"
	 */
	static final String FIELD_QUESTION = "QUESTION_";

	/** Column corresponding to the permanent address listed in the application. */
	static final String FIELD_PERMANENT_ADDRESS = "PERMANENT_ADDRESS";

	/** Column corresponding to the birth address listed in the application. */
	static final String FIELD_BIRTH_ADDRESS = "BIRTH_ADDRESS";

	/** Column corresponding to the present address of the applicant. */
	static final String FIELD_PRESENT_ADDRESS = "PRESENT_ADDRESS";

	/** Column corresponding to the applicant's father's name */
	static final String FIELD_NAME_OF_FATHER = "FATHER_NAME";

	/** Column corresponding to the applicant's mother's name */
	static final String FIELD_NAME_OF_MOTHER = "MOTHER_NAME";

	/** Column that holds the timestamp when the application was initiated. */
	static final String FIELD_DATE_CREATED = "DATE_CREATED";

	/**
	 * Column that holds information regarding the authenticity of documents
	 * submitted.
	 */
	static final String FIELD_DOCUMENTS_AUTHENTIC = "DOCUMENTS_AUTHENTIC";

	/** Column corresponding to the appointment date of the application. */
	static final String FIELD_DATE_OF_APPOINTMENT = "APPOINTMENT_DATE";

	/**
	 * Column corresponding to the type of the application Can be one of
	 * {@link edu.manipal.icas.simple.models.application.ApplicationType}
	 */
	static final String FIELD_APPLICATION_TYPE = "APPLICATION_TYPE";

	/**
	 * Column that holds the primary key from the
	 * {@link edu.manipal.icas.simple.databases.PaymentDatabase} associated with the
	 * application. To be used as a foreign key.
	 */
	static final String FIELD_PAYMENT_ID = "PAYMENT_ID";

	/**
	 * Column that holds the primary key from the
	 * {@link edu.manipal.icas.simple.databases.CitizenDatabase} associated with the
	 * applicant of the application. To be used as a foreign key.
	 */
	static final String FIELD_APPLICANT_ID = "APPLICANT_ID";

	/**
	 * Column that holds the primary key from the
	 * {@link edu.manipal.icas.simple.databases.CitizenDatabase} associated with the
	 * emergency contact listed in the application. To be used as a foreign key.
	 */
	static final String FIELD_CONTACT_ID = "CONTACT_ID";

	/**
	 * Column associated with the current status of the application. Can be one of
	 * {@link edu.manipal.icas.simple.models.application.ApplicationStatus}
	 */
	static final String FIELD_STATUS = "STATUS";

	/**
	 * Column associated with a list of document names, each of which is a foreign
	 * key from {@link edu.manipal.icas.simple.databases.DocumentDatabase}
	 */
	static final String FIELD_DOCUMENTS = "DOCUMENT_";

	/**
	 * Column associated with a list of document names representing biometrics data,
	 * each of which is a foreign key from
	 * {@link edu.manipal.icas.simple.databases.DocumentDatabase}
	 */
	static final String FIELD_BIOMETRICS = "BIOMETRICS_";

	/**
	 * Creates a new application in the database with a unique ID.
	 * 
	 * @return unique application ID (primary key)
	 * @throws IOException
	 */
	Integer createApplication() throws IOException;

	/**
	 * Checks if an application with the given ID exists in the database.
	 * 
	 * @param applicationId the ID of the application to check
	 * @return {@code true} if the application exists, {@code false} otherwise
	 */
	Boolean applicationExists(Integer applicationId);

	/**
	 * Gets the date the application was created in the database.
	 * 
	 * @param applicationId ID of the application to fetch the date of
	 * @return the date (day + time) when the application was initiated
	 * @throws IOException if no application was found for the ID
	 */
	Date fetchDateCreated(Integer applicationId) throws IOException;

	/**
	 * Saves the answer for a yes/no question for a given application in the
	 * database.
	 * 
	 * @param applicationId  ID of the application to modify
	 * @param questionNumber precision index of the question
	 * @param answer         true/false response to the question
	 * @throws IOException if no application was found for the ID
	 */
	void saveAnswerForQuestion(Integer applicationId, Double questionNumber, Boolean answer) throws IOException;

	/**
	 * Fetches the answer for a yes/no question from the database.
	 * 
	 * @param applicationId  ID of the application whose answer is to be fetched
	 * @param questionNumber precision index of the question whose answer is to be
	 *                       fetched
	 * @return {@code true} or {@code false} response to the question
	 * @throws IOException if no application was found for the ID
	 */
	Boolean fetchAnswerForQuestion(Integer applicationId, Double questionNumber) throws IOException;

	/**
	 * Saves the permanent address associated with the application in the database.
	 * 
	 * @param applicationId    ID of the application
	 * @param permanentAddress address to be saved
	 * @throws IOException if no application was found for the ID
	 */
	void savePermanentAddress(Integer applicationId, String permanentAddress) throws IOException;

	/**
	 * Fetches the permanent address of the application from the database.
	 * 
	 * @param applicationId ID of the application
	 * @return permanent address associated with the application
	 * @throws IOException if no application was found for the ID
	 */
	String fetchPermanentAddress(Integer applicationId) throws IOException;

	/**
	 * Saves the birth address associated with the application in the database.
	 * 
	 * @param applicationId ID of the application
	 * @param birthAddress  birth address associated with the application
	 * @throws IOException if no application was found for the ID
	 */
	void saveBirthAddress(Integer applicationId, String birthAddress) throws IOException;

	/**
	 * Fetches the birth address of the application from the database.
	 * 
	 * @param applicationId ID of the application
	 * @return birth address associated with the application
	 * @throws IOException if no application was found for the ID
	 */
	String fetchBirthAddress(Integer applicationId) throws IOException;

	/**
	 * Saves the current address associated with the application in the database.
	 * 
	 * @param applicationId  ID of the application
	 * @param currentAddress current address associated with the application
	 * @throws IOException if no application was found for the ID
	 */
	void savePresentAddress(Integer applicationId, String currentAddress) throws IOException;

	/**
	 * Fetches the present address of the application from the database.
	 * 
	 * @param applicationId ID of the application
	 * @return present address associated with the application
	 * @throws IOException if no application was found for the ID
	 */
	String fetchPresentAddress(Integer applicationId) throws IOException;

	/**
	 * Saves the name of the applicant's father associated with the application.
	 * 
	 * @param applicationId ID of the application
	 * @param nameOfFather  name of the father
	 * @throws IOException if no application was found for the ID
	 */
	void saveNameOfFather(Integer applicationId, String nameOfFather) throws IOException;

	/**
	 * Fetches the name of the applicant's father from the database.
	 * 
	 * @param applicationId ID of the application
	 * @return name of the father
	 * @throws IOException if no application was found for the ID
	 */
	String fetchNameOfFather(Integer applicationId) throws IOException;

	/**
	 * Saves the name of the applicant's mother associated with the application.
	 * 
	 * @param applicationId ID of the application
	 * @param nameOfMother  name of the mother
	 * @throws IOException if no application was found for the ID
	 */
	void saveNameOfMother(Integer applicationId, String nameOfMother) throws IOException;

	/**
	 * Fetches the name of the applicant's mother from the database.
	 * 
	 * @param applicationId ID of the application
	 * @return name of the mother
	 * @throws IOException if no application was found for the ID
	 */
	String fetchNameOfMother(Integer applicationId) throws IOException;

	/**
	 * Saves the authenticity state of the given application's documents.
	 * 
	 * @param applicationId      ID of the application to be modified
	 * @param documentsAuthentic {@code true} or {@code false} flag corresponding to
	 *                           the authenticity state
	 * @throws IOException if no application was found for the ID
	 */
	void saveDocumentsAuthentic(Integer applicationId, Boolean documentsAuthentic) throws IOException;

	/**
	 * Fetches the authenticity state of an application's documents from the
	 * database.
	 * 
	 * @param applicationId ID of the application
	 * @return {@code true} if the documents are authentic, {@code false} otherwise
	 * @throws IOException if no application was found for the ID
	 */
	Boolean fetchDocumentsAuthentic(Integer applicationId) throws IOException;

	/**
	 * Saves the appointment date of an application in the database.
	 * 
	 * @param applicationId   ID of the application to be modified
	 * @param appointmentDate date object representing the appointment
	 * @throws IOException if no application was found for the ID
	 */
	void saveDateOfAppointment(Integer applicationId, Date appointmentDate) throws IOException;

	/**
	 * Fetches the date of an application's appointment from the database.
	 * 
	 * @param applicationId ID of the application
	 * @return date of the appointment
	 * @throws IOException if no application was found for the ID
	 */
	Date fetchDateOfAppointment(Integer applicationId) throws IOException;

	/**
	 * Saves the {@link edu.manipal.icas.simple.models.application.ApplicationType}
	 * of the application in the database.
	 * 
	 * @param applicationId   ID of the application to be modified
	 * @param applicationType type of the application to be set
	 * @throws IOException if no application was found for the ID
	 */
	void saveApplicationType(Integer applicationId, ApplicationType applicationType) throws IOException;

	/**
	 * Fetches the type of the application from the database.
	 * 
	 * @param applicationId ID of the application
	 * @return One of
	 *         {@link edu.manipal.icas.simple.models.application.ApplicationType}
	 * @throws IOException
	 */
	ApplicationType fetchApplicationType(Integer applicationId) throws IOException;

	/**
	 * Saves the payment ID associated with the application.
	 * 
	 * @param applicationId ID of the application
	 * @param paymentId     ID of the payment
	 * @throws IOException if no application was found for the ID
	 */
	void savePaymentId(Integer applicationId, Integer paymentId) throws IOException;

	/**
	 * Fetches the ID of the payment associated with an application.
	 * 
	 * @param applicationId ID of the application
	 * @return ID of the payment which is a foreign key from
	 *         {@link edu.manipal.icas.simple.databases.PaymentDatabase}
	 * @throws IOException if no application was found for the ID
	 */
	Integer fetchPaymentId(Integer applicationId) throws IOException;

	/**
	 * Saves the citizen applicant associated with the application.
	 * 
	 * @param applicationId ID of the application
	 * @param applicantId   ID of the applicant which is a foreign key from
	 *                      {@link edu.manipal.icas.simple.databases.CitizenDatabase}
	 * @throws IOException if no application was found for the ID
	 */
	void saveApplicantId(Integer applicationId, String applicantId) throws IOException;

	/**
	 * Fetches the applicant's ID associated with an application from the database.
	 * 
	 * @param applicationId ID of the application
	 * @return ID of the applicant
	 * @throws IOException if no application was found for the ID
	 */
	String fetchApplicantId(Integer applicationId) throws IOException;

	/**
	 * Saves the citizen emergency contact associated with the application.
	 * 
	 * @param applicationId ID of the application
	 * @param contactId     ID of the emergency contact which is a foreign key from
	 *                      {@link edu.manipal.icas.simple.databases.CitizenDatabase}
	 * @throws IOException if no application was found for the ID
	 */
	void saveContactId(Integer applicationId, String contactId) throws IOException;

	/**
	 * Fetches the contact's ID associated with an application from the database.
	 * 
	 * @param applicationId ID of the application
	 * @return ID of the contact
	 * @throws IOException if no application was found for the ID
	 */
	String fetchContactId(Integer applicationId) throws IOException;

	/**
	 * Saves the status of the application.
	 * 
	 * @param applicationId ID of the application to be modified
	 * @param status        one of
	 *                      {@link edu.manipal.icas.simple.models.application.ApplicationStatus}
	 * @throws IOException if no application was found for the ID
	 */
	void saveStatus(Integer applicationId, ApplicationStatus status) throws IOException;

	/**
	 * Fetches the status of an application.
	 * 
	 * @param applicationId ID of the application
	 * @return one of
	 *         {@link edu.manipal.icas.simple.models.application.ApplicationStatus}
	 * @throws IOException
	 */
	ApplicationStatus fetchStatus(Integer applicationId) throws IOException;

	/**
	 * Saves a document ID associated with an application to the database.
	 * 
	 * @param applicationId ID of the application to modify
	 * @param documentType  type of the document, one of
	 *                      {@link AcceptedDocumentType}
	 * @param documentName  name of the document that is a foreign key from
	 *                      {@link DocumentDatabase}
	 * @throws IOException if no application was found for the ID
	 */
	void saveDocument(Integer applicationId, AcceptedDocumentType documentType, String documentName) throws IOException;

	/**
	 * Fetches the document name associated with an application.
	 * 
	 * @param applicationId ID of the application
	 * @return the document name for the given type, which is a foreign key from
	 *         {@link DocumentDatabase}
	 * @throws IOException if no application was found for the ID
	 */
	String fetchDocument(Integer applicationId, AcceptedDocumentType documentType) throws IOException;

	/**
	 * Saves a biometrics document ID associated with an application to the
	 * database.
	 * 
	 * @param applicationId ID of the application to modify
	 * @param biometricType type of the document, one of
	 *                      {@link AcceptedBiometricType}
	 * @param biometricName name of the document that is a foreign key from
	 *                      {@link DocumentDatabase}
	 * @throws IOException if no application was found for the ID
	 */
	void saveBiometric(Integer applicationId, AcceptedBiometricType biometricType, String biometricName)
			throws IOException;

	/**
	 * Fetches the biometrics document name associated with an application.
	 * 
	 * @param applicationId ID of the application
	 * @return biometrics document name, which is a foreign key from
	 *         {@link DocumentDatabase}
	 * @throws IOException if no application was found for the ID
	 */
	String fetchBiometric(Integer applicationId, AcceptedBiometricType biometricType) throws IOException;
}
