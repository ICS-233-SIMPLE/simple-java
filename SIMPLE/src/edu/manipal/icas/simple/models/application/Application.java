package edu.manipal.icas.simple.models.application;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.manipal.icas.simple.databases.ApplicationDatabase;
import edu.manipal.icas.simple.impl.apis.MockPaymentApi;
import edu.manipal.icas.simple.impl.databases.MsAccessApplicationDatabase;
import edu.manipal.icas.simple.models.Citizen;
import edu.manipal.icas.simple.models.Document;
import edu.manipal.icas.simple.models.Payment;

/**
 * Class that represents a passport application that is submitted by a citizen.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public abstract class Application {

	/** Unique ID representing the application */
	private Integer applicationId;

	/**
	 * A map of yes/no questions that the applicant answers. See
	 * {@link ApplicationQuestion} for a list of all questions.
	 */
	private Map<ApplicationQuestion, Boolean> questions;

	/** Birth address of the applicant */
	private String birthAddress;

	/** Permanent address of the applicant */
	private String permanentAddress;

	/** Present address of the applicant */
	private String presentAddress;

	/** Name of the applicant's father */
	private String nameOfFather;

	/** Name of the applicant's mother */
	private String nameOfMother;

	/** Date this application was created */
	private Date dateCreated;

	/**
	 * Date of the scheduled in-person appointment for processing this application
	 * at the Passport Office
	 */
	private Date dateOfAppointment;

	/** All the documents this application accepts */
	private Map<AcceptedDocumentType, Document> documents;

	/** All the biometrics required by the application */
	private Map<AcceptedBiometricType, Document> biometrics;

	/** The applicant who has submitted the application */
	private Citizen applicant;

	/**
	 * Emergency contact listed for the application who is the secondary contact if
	 * the applicant cannot be contacted
	 */
	private Citizen emergencyContact;

	/**
	 * Type of the application. Can be fresh or re-issue. The application process is
	 * the same for both except the documents that have to be submitted
	 */
	private ApplicationType type;

	/**
	 * Status of the application as it moves from being initiated to being processed
	 * and finally being completed
	 */
	private ApplicationStatus status;

	/** Whether the documents submitted were authentic */
	private Boolean documentsAuthentic;

	/**
	 * The payment associated with this application which handles fulfilment of the
	 * application fee
	 */
	private Payment payment;

	/** Fee to process applications */
	public static final Double APPLICATION_PAYMENT_AMOUNT = 700.50;

	private static final ApplicationDatabase db = MsAccessApplicationDatabase.getDatabase();

	/**
	 * Creates a new application object based on a pre-existing application record
	 * in the database.
	 * 
	 * @param applicationId unique ID of the application
	 */
	public Application(Integer applicationId) {
		this.applicationId = applicationId;
		try {
			type = db.fetchApplicationType(applicationId);
			status = db.fetchStatus(applicationId);
			birthAddress = db.fetchBirthAddress(applicationId);
			permanentAddress = db.fetchPermanentAddress(applicationId);
			presentAddress = db.fetchPresentAddress(applicationId);
			nameOfFather = db.fetchNameOfFather(applicationId);
			nameOfMother = db.fetchNameOfMother(applicationId);
			dateCreated = db.fetchDateCreated(applicationId);
			dateOfAppointment = db.fetchDateOfAppointment(applicationId);
			applicant = new Citizen(db.fetchApplicantId(applicationId));
			emergencyContact = new Citizen(db.fetchContactId(applicationId));
			documentsAuthentic = db.fetchDocumentsAuthentic(applicationId);
			payment = new Payment(db.fetchPaymentId(applicationId));

			questions = new HashMap<>();
			for (ApplicationQuestion question : ApplicationQuestion.values()) {
				questions.put(question, db.fetchAnswerForQuestion(applicationId, question.number));
			}

			documents = new HashMap<>();
			for (AcceptedDocumentType documentType : AcceptedDocumentType.values()) {
				String documentName = db.fetchDocument(applicationId, documentType);
				if (documentName != null && !documentName.equals(""))
					documents.put(documentType, new Document(documentName));
			}

			biometrics = new HashMap<>();
			for (AcceptedBiometricType biometricType : AcceptedBiometricType.values()) {
				String documentName = db.fetchBiometric(applicationId, biometricType);
				if (documentName != null && !documentName.equals(""))
					biometrics.put(biometricType, new Document(documentName));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new application and persists it in the database.
	 * 
	 * @param type the type of application to create
	 */
	public Application(ApplicationType type) {
		try {
			applicationId = db.createApplication();
			this.type = type;
			db.saveApplicationType(applicationId, type);
			status = ApplicationStatus.INITIATED;
			setBirthAddress("");
			setPermanentAddress("");
			setPresentAddress("");
			setNameOfFather("");
			setNameOfMother("");
			documentsAuthentic = false;
			questions = new HashMap<>();
			biometrics = new HashMap<>();
			documents = new HashMap<>();
			setPayment(new MockPaymentApi().createPayment(APPLICATION_PAYMENT_AMOUNT));
			dateCreated = db.fetchDateCreated(applicationId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the answer to a yes/no question.
	 * 
	 * @param question
	 * @return
	 */
	public Boolean getAnswerForQuestion(ApplicationQuestion question) {
		return questions.get(question);
	}

	/**
	 * Sets the answer to a yes/no question.
	 * 
	 * @param question the question to set the answer for
	 * @param answer   yes/no response that maps directly to true/false
	 */
	public void setAnswerForQuestion(ApplicationQuestion question, Boolean answer) {
		questions.put(question, answer);
		try {
			db.saveAnswerForQuestion(applicationId, question.number, answer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Attaches a document to the corresponding accepted type for this application.
	 * 
	 * @param documentType type of the document that is being uploaded
	 * @param document     document that is to be attached
	 */
	public void uploadDocument(AcceptedDocumentType documentType, Document document) {
		documents.put(documentType, document);
		try {
			db.saveDocument(applicationId, documentType, document.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Attaches a biometric document to the corresponding accepted type for this
	 * application.
	 * 
	 * @param biometricType type of the biometric that is being uploaded
	 * @param document      document that is to be attached
	 */
	public void uploadBiometric(AcceptedBiometricType biometricType, Document document) {
		biometrics.put(biometricType, document);
		try {
			db.saveBiometric(applicationId, biometricType, document.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Moves the status of the application to its next state linearly. This method
	 * does not account for branched advance operations, such as advancing to
	 * reject/approve after processing. Use the setStatus method for this purpose.
	 */
	public void advanceApplicationStatus() {
		switch (status) {
		case INITIATED:
			status = ApplicationStatus.FORM_FILLED;
			break;
		case FORM_FILLED:
			status = ApplicationStatus.SLOT_BOOKED;
			break;
		case SLOT_BOOKED:
			status = ApplicationStatus.PAYMENT_COMPLETE;
			break;
		case PAYMENT_COMPLETE:
			status = ApplicationStatus.CAPTURING_BIOMETRICS;
			break;
		case CAPTURING_BIOMETRICS:
			status = ApplicationStatus.UNDER_VERIFICATION;
			break;
		case UNDER_VERIFICATION:
			status = ApplicationStatus.UNDER_REVIEW;
			break;
		case UNDER_REVIEW:
			status = ApplicationStatus.PENDING_ADDRESS_VERIFICATION;
			break;
		case PENDING_ADDRESS_VERIFICATION:
			status = ApplicationStatus.COMPLETED_AND_SUCCESSFUL;
			break;
		default:
			break;
		}
		try {
			db.saveStatus(applicationId, status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the unique ID representing this application.
	 * 
	 * @return ID of the application
	 */
	public Integer getApplicationId() {
		return applicationId;
	}

	/**
	 * Gets the birth address of the applicant.
	 * 
	 * @return birth address
	 */
	public String getBirthAddress() {
		return birthAddress;
	}

	/**
	 * Sets the birth address of the applicant.
	 * 
	 * @param birthAddress new birth address
	 */
	public void setBirthAddress(String birthAddress) {
		this.birthAddress = birthAddress;
		try {
			db.saveBirthAddress(applicationId, birthAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the permanent address of the applicant.
	 * 
	 * @return permanent address
	 */
	public String getPermanentAddress() {
		return permanentAddress;
	}

	/**
	 * Sets the permanent address of the applicant.
	 * 
	 * @param permanentAddress new permanent address
	 */
	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
		try {
			db.savePermanentAddress(applicationId, permanentAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the present address of the applicant.
	 * 
	 * @return current residence address
	 */
	public String getPresentAddress() {
		return presentAddress;
	}

	/**
	 * Sets the present address of the applicant.
	 * 
	 * @param presentAddress new current address
	 */
	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
		try {
			db.savePresentAddress(applicationId, presentAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the name of the applicant's father.
	 * 
	 * @return name
	 */
	public String getNameOfFather() {
		return nameOfFather;
	}

	/**
	 * Sets the name of the applicant's father.
	 * 
	 * @param name
	 */
	public void setNameOfFather(String name) {
		nameOfFather = name;
		try {
			db.saveNameOfFather(applicationId, name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the name of the applicant's mother.
	 * 
	 * @return name
	 */
	public String getNameOfMother() {
		return nameOfMother;
	}

	/**
	 * Sets the name of the applicant's mother.
	 * 
	 * @param name
	 */
	public void setNameOfMother(String name) {
		nameOfMother = name;
		try {
			db.saveNameOfMother(applicationId, name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the date this application was created.
	 * 
	 * @return date of creation
	 */
	public Date getDateCreated() {
		return dateCreated;
	}

	/**
	 * Gets the date of appointment for this application.
	 * 
	 * @return appointment date
	 */
	public Date getDateOfAppointment() {
		return dateOfAppointment;
	}

	/**
	 * Sets the scheduled date of appointment for this application.
	 * 
	 * @param date date of appointment
	 */
	public void setDateOfAppointment(Date date) {
		dateOfAppointment = date;
		applicant.getPassportOffice().blockSlot(date);
		try {
			db.saveDateOfAppointment(applicationId, date);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the applicant who submitted this application.
	 * 
	 * @return citizen who is the applicant
	 */
	public Citizen getApplicant() {
		return applicant;
	}

	/**
	 * Sets the applicant for this application.
	 * 
	 * @param applicant
	 */
	public void setApplicant(Citizen applicant) {
		this.applicant = applicant;
		this.applicant.addApplication(this);
		try {
			db.saveApplicantId(applicationId, applicant.getEmailAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the emergency contact listed for this application.
	 * 
	 * @return emergency contact
	 */
	public Citizen getEmergencyContact() {
		return emergencyContact;
	}

	/**
	 * Sets the emergency contact listed for this application.
	 * 
	 * @param contact
	 * @throws IllegalArgumentException if the applicant and emergency contact are
	 *                                  the same person
	 */
	public void setEmergencyContact(Citizen contact) throws IllegalArgumentException {
		if (contact.getEmailAddress().equals(applicant.getEmailAddress())) {
			throw new IllegalArgumentException("Applicant and emergency contact cannot be the same citizen!");
		}
		emergencyContact = contact;
		try {
			db.saveContactId(applicationId, contact.getEmailAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets whether the documents submitted were authentic or not
	 * 
	 * @return {@code true} if the documents were authentic, {@code false} otherwise
	 */
	public Boolean getDocumentsAuthentic() {
		return documentsAuthentic;
	}

	/**
	 * Sets whether the submitted documents are authentic.
	 * 
	 * @param authentic
	 */
	public void setDocumentsAuthentic(Boolean authentic) {
		documentsAuthentic = authentic;
		try {
			db.saveDocumentsAuthentic(applicationId, authentic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the payment associated with this application.
	 * 
	 * @return payment
	 */
	public Payment getPayment() {
		return payment;
	}

	/**
	 * Sets the payment associated with this application.
	 * 
	 * @param payment
	 */
	private void setPayment(Payment payment) {
		this.payment = payment;
		try {
			db.savePaymentId(applicationId, payment.getPaymentId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the type of the application.
	 * 
	 * @return application type
	 */
	public ApplicationType getType() {
		return type;
	}

	/**
	 * Gets the status of the application.
	 * 
	 * @return status
	 */
	public ApplicationStatus getStatus() {
		return status;
	}

	/**
	 * Gets a map of all submitted documents.
	 * 
	 * @return documents
	 */
	public Map<AcceptedDocumentType, Document> getDocuments() {
		return documents;
	}

	/**
	 * Gets a map of all submitted biometrics.
	 * 
	 * @return biometrics
	 */
	public Map<AcceptedBiometricType, Document> getBiometrics() {
		return biometrics;
	}

	/**
	 * Gets a single document from all submitted documents.
	 * 
	 * @param documentType type of document to get
	 * @return document
	 */
	public Document getDocument(AcceptedDocumentType documentType) {
		return documents.get(documentType);
	}

	/**
	 * Gets a single biometric from all submitted biometrics.
	 * 
	 * @param biometricType type of biometric to get
	 * @return biometric
	 */
	public Document getBiometric(AcceptedBiometricType biometricType) {
		return biometrics.get(biometricType);
	}

	/**
	 * Whether the application has all the documents required to begin processing.
	 * 
	 * @return {@code true} if the application has all documents, {@code false}
	 *         otherwise
	 */
	public abstract Boolean hasRequiredDocuments();
}
