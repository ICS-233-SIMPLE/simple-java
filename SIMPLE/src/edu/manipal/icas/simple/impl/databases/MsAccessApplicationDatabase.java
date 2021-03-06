package edu.manipal.icas.simple.impl.databases;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Row;

import edu.manipal.icas.simple.databases.ApplicationDatabase;
import edu.manipal.icas.simple.models.application.AcceptedBiometricType;
import edu.manipal.icas.simple.models.application.AcceptedDocumentType;
import edu.manipal.icas.simple.models.application.ApplicationStatus;
import edu.manipal.icas.simple.models.application.ApplicationType;

/**
 * Concrete class that implements persistence API operations from
 * {@link ApplicationDatabase}
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class MsAccessApplicationDatabase extends MsAccessDatabase implements ApplicationDatabase {

	private static final MsAccessApplicationDatabase DATABASE = new MsAccessApplicationDatabase();

	private MsAccessApplicationDatabase() {
		super("Applications");
	}

	public static MsAccessApplicationDatabase getDatabase() {
		return DATABASE;
	}

	@Override
	public Boolean applicationExists(Integer applicationId) {
		return rowExists(applicationId);
	}

	@Override
	public Integer createApplication() throws IOException {
		Integer appId = (Integer) table.addRow(Column.AUTO_NUMBER)[0];
		Row row = getRow(appId);
		row.put(FIELD_DATE_CREATED, new Date(System.currentTimeMillis()));
		table.updateRow(row);
		return appId;
	}

	@Override
	public Date fetchDateCreated(Integer applicationId) throws IOException {
		LocalDateTime dateTime = getRow(applicationId).getLocalDateTime(FIELD_DATE_CREATED);
		return Date.from(dateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public void savePermanentAddress(Integer applicationId, String permanentAddress) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_PERMANENT_ADDRESS, permanentAddress);
		table.updateRow(row);
	}

	@Override
	public String fetchPermanentAddress(Integer applicationId) throws IOException {
		return getRow(applicationId).getString(FIELD_PERMANENT_ADDRESS);
	}

	@Override
	public void saveBirthAddress(Integer applicationId, String birthAddress) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_BIRTH_ADDRESS, birthAddress);
		table.updateRow(row);
	}

	@Override
	public String fetchBirthAddress(Integer applicationId) throws IOException {
		return getRow(applicationId).getString(FIELD_BIRTH_ADDRESS);
	}

	@Override
	public void saveNameOfFather(Integer applicationId, String nameOfFather) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_NAME_OF_FATHER, nameOfFather);
		table.updateRow(row);
	}

	@Override
	public String fetchNameOfFather(Integer applicationId) throws IOException {
		return getRow(applicationId).getString(FIELD_NAME_OF_FATHER);
	}

	@Override
	public void saveNameOfMother(Integer applicationId, String nameOfMother) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_NAME_OF_MOTHER, nameOfMother);
		table.updateRow(row);
	}

	@Override
	public String fetchNameOfMother(Integer applicationId) throws IOException {
		return getRow(applicationId).getString(FIELD_NAME_OF_MOTHER);
	}

	@Override
	public void saveDocumentsAuthentic(Integer applicationId, Boolean documentsAuthentic) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_DOCUMENTS_AUTHENTIC, documentsAuthentic);
		table.updateRow(row);
	}

	@Override
	public Boolean fetchDocumentsAuthentic(Integer applicationId) throws IOException {
		return getRow(applicationId).getBoolean(FIELD_DOCUMENTS_AUTHENTIC);
	}

	@Override
	public void saveDateOfAppointment(Integer applicationId, Date appointmentDate) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_DATE_OF_APPOINTMENT, appointmentDate);
		table.updateRow(row);
	}

	@Override
	public Date fetchDateOfAppointment(Integer applicationId) throws IOException {
		LocalDateTime dateTime = getRow(applicationId).getLocalDateTime(FIELD_DATE_OF_APPOINTMENT);
		return Date.from(dateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public void savePaymentId(Integer applicationId, Integer paymentId) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_PAYMENT_ID, paymentId);
		table.updateRow(row);
	}

	@Override
	public Integer fetchPaymentId(Integer applicationId) throws IOException {
		return getRow(applicationId).getInt(FIELD_PAYMENT_ID);
	}

	@Override
	public void saveApplicantId(Integer applicationId, String applicantId) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_APPLICANT_ID, applicantId);
		table.updateRow(row);
	}

	@Override
	public String fetchApplicantId(Integer applicationId) throws IOException {
		return getRow(applicationId).getString(FIELD_APPLICANT_ID);
	}

	@Override
	public void saveAnswerForQuestion(Integer applicationId, Double questionNumber, Boolean answer) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_QUESTION + new String(questionNumber + "").replace('.', '-'), answer);
		table.updateRow(row);
	}

	@Override
	public Boolean fetchAnswerForQuestion(Integer applicationId, Double questionNumber) throws IOException {
		return getRow(applicationId).getBoolean(FIELD_QUESTION + new String(questionNumber + "").replace('.', '-'));
	}

	@Override
	public void savePresentAddress(Integer applicationId, String currentAddress) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_PRESENT_ADDRESS, currentAddress);
		table.updateRow(row);
	}

	@Override
	public String fetchPresentAddress(Integer applicationId) throws IOException {
		return getRow(applicationId).getString(FIELD_PRESENT_ADDRESS);
	}

	@Override
	public void saveApplicationType(Integer applicationId, ApplicationType applicationType) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_APPLICATION_TYPE, applicationType.toString());
		table.updateRow(row);
	}

	@Override
	public ApplicationType fetchApplicationType(Integer applicationId) throws IOException {
		return ApplicationType.valueOf(getRow(applicationId).getString(FIELD_APPLICATION_TYPE));
	}

	@Override
	public void saveContactId(Integer applicationId, String contactId) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_CONTACT_ID, contactId);
		table.updateRow(row);
	}

	@Override
	public String fetchContactId(Integer applicationId) throws IOException {
		return getRow(applicationId).getString(FIELD_CONTACT_ID);
	}

	@Override
	public void saveStatus(Integer applicationId, ApplicationStatus status) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_STATUS, status.toString());
		table.updateRow(row);
	}

	@Override
	public ApplicationStatus fetchStatus(Integer applicationId) throws IOException {
		return ApplicationStatus.valueOf(getRow(applicationId).getString(FIELD_STATUS));
	}

	@Override
	public void saveDocument(Integer applicationId, AcceptedDocumentType documentType, String name) throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_DOCUMENTS + documentType.toString(), name);
		table.updateRow(row);
	}

	@Override
	public String fetchDocument(Integer applicationId, AcceptedDocumentType documentType) throws IOException {
		return getRow(applicationId).getString(FIELD_DOCUMENTS + documentType.toString());
	}

	@Override
	public void saveBiometric(Integer applicationId, AcceptedBiometricType biometricType, String name)
			throws IOException {
		Row row = getRow(applicationId);
		row.put(FIELD_BIOMETRICS + biometricType.toString(), name);
		table.updateRow(row);

	}

	@Override
	public String fetchBiometric(Integer applicationId, AcceptedBiometricType biometricType) throws IOException {
		return getRow(applicationId).getString(FIELD_BIOMETRICS + biometricType.toString());
	}

}
