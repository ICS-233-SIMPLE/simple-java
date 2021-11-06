package edu.manipal.icas.simple.models.application;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import edu.manipal.icas.simple.databases.ApplicationDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessApplicationDatabase;
import edu.manipal.icas.simple.models.Citizen;
import edu.manipal.icas.simple.models.Document;
import edu.manipal.icas.simple.models.Payment;

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
	private Boolean documentsAuthentic;
	private Payment payment;
	
	private static final Double APPLICATION_PAYMENT_AMOUNT = 700.50;
	
	private static final ApplicationDatabase db = MsAccessApplicationDatabase.getDatabase();

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
			setPayment(new Payment(APPLICATION_PAYMENT_AMOUNT));
			dateCreated = db.fetchDateCreated(applicationId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setAnswerForQuestion(ApplicationQuestion question, Boolean answer) {
		questions.put(question, answer);
		try {
			db.saveAnswerForQuestion(applicationId, question.number, answer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void uploadDocument(AcceptedDocumentType documentType, Document document) {
		documents.put(documentType, document);
		try {
			db.saveDocument(applicationId, documentType, document.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void uploadBiometric(AcceptedBiometricType biometricType, Document document) {
		biometrics.put(biometricType, document);
		try {
			db.saveBiometric(applicationId, biometricType, document.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

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
	}

	public Integer getApplicationId() {
		return applicationId;
	}

	public String getBirthAddress() {
		return birthAddress;
	}

	public void setBirthAddress(String birthAddress) {
		this.birthAddress = birthAddress;
		try {
			db.saveBirthAddress(applicationId, birthAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
		try {
			db.savePermanentAddress(applicationId, permanentAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
		try {
			db.savePresentAddress(applicationId, presentAddress);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getNameOfFather() {
		return nameOfFather;
	}

	public void setNameOfFather(String name) {
		nameOfFather = name;
		try {
			db.saveNameOfFather(applicationId, name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getNameOfMother() {
		return nameOfMother;
	}

	public void setNameOfMother(String name) {
		nameOfMother = name;
		try {
			db.saveNameOfMother(applicationId, name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public Date getDateOfAppointment() {
		return dateOfAppointment;
	}

	public void setDateOfAppointment(Date date) {
		dateOfAppointment = date;
		try {
			db.saveDateOfAppointment(applicationId, date);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Citizen getApplicant() {
		return applicant;
	}

	public void setApplicant(Citizen applicant) {
		this.applicant = applicant;
		try {
			db.saveApplicantId(applicationId, applicant.getEmailAddress());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Citizen getEmergencyContact() {
		return emergencyContact;
	}

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

	public Boolean getDocumentsAuthentic() {
		return documentsAuthentic;
	}

	public void setDocumentsAuthentic(Boolean authentic) {
		documentsAuthentic = authentic;
		try {
			db.saveDocumentsAuthentic(applicationId, authentic);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Payment getPayment() {
		return payment;
	}
	
	private void setPayment(Payment payment) {
		this.payment = payment;
		try {
			db.savePaymentId(applicationId, payment.getPaymentId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ApplicationType getType() {
		return type;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public Map<AcceptedDocumentType, Document> getDocuments() {
		return documents;
	}

	public Map<AcceptedBiometricType, Document> getBiometrics() {
		return biometrics;
	}
	
	public Document getDocument(AcceptedDocumentType documentType) {
		return documents.get(documentType);
	}
	
	public Document getBiometric(AcceptedBiometricType biometricType) {
		return biometrics.get(biometricType);
	}

	public abstract Boolean hasRequiredDocuments();
}
