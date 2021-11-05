package edu.manipal.icas.simple.models.application;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import edu.manipal.icas.simple.databases.ApplicationDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessApplicationDatabase;
import edu.manipal.icas.simple.models.Citizen;
import edu.manipal.icas.simple.models.Document;

public abstract class Application {
	private Integer applicationId;
	private Map<ApplicationQuestion, Boolean> questions;
	private String birthAddress;
	private String permanentAddress;
	private String presentAddress;
	private String nameOfFather;
	private String nameOfMother;
	private Date dateCreated;
	private Date dateOfAppointment;
	private Map<AcceptedDocumentType, Document> documents;
	private Map<AcceptedBiometricType, Document> biometrics;
	private Citizen applicant;
	private Citizen emergencyContact;
	private ApplicationType type;
	private ApplicationStatus status;
	private static final ApplicationDatabase db = MsAccessApplicationDatabase.getDatabase();

	public Application(Integer applicationId, ApplicationType type) {
		this.applicationId = applicationId;
		this.type = type;
		status = ApplicationStatus.INITIATED;
		try {
			birthAddress = db.fetchBirthAddress(applicationId);
			permanentAddress = db.fetchPermanentAddress(applicationId);
			presentAddress = db.fetchPresentAddress(applicationId);
			nameOfFather = db.fetchNameOfFather(applicationId);
			nameOfMother = db.fetchNameOfMother(applicationId);
			//dateCreated = ???
			dateOfAppointment = db.fetchDateOfAppointment(applicationId);
			applicant = new Citizen(db.fetchApplicantId(applicationId));
			emergencyContact = new Citizen(db.fetchContactId(applicationId));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public Application(ApplicationType type) throws Exception {
		this(db.createApplication(), type);
	}

	public void setAnswerForQuestion(ApplicationQuestion question, Boolean answer) {
		questions.put(question, answer);
	}

	public void uploadDocument(AcceptedDocumentType documentType, Document document) {
		documents.put(documentType, document);
	}

	public void uploadBiometric(AcceptedBiometricType biometricType, Document document) {
		biometrics.put(biometricType, document);
	}

	public Map<AcceptedDocumentType, Document> getDocuments() {
		return documents;
	}

	public void advanceApplicationStatus() {
		switch (status) {
		case INITIATED:
			status = ApplicationStatus.FORM_FILLED;
			break;
		case FORM_FILLED:
			status = ApplicationStatus.SLOT_BOOKED;
			break;

		default:
			break;
		}
	}

	public abstract Boolean hasRequiredDocuments();
}
