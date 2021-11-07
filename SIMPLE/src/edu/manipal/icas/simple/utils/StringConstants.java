package edu.manipal.icas.simple.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import edu.manipal.icas.simple.models.application.AcceptedDocumentType;
import edu.manipal.icas.simple.models.application.ApplicationQuestion;
import edu.manipal.icas.simple.models.application.ApplicationStatus;

/**
 * A utility class that holds business strings commonly used across the
 * application.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 * @author Priya Parashar (priya.parashar@manipal.edu)
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public final class StringConstants {
	private StringConstants() {
	}

	/**
	 * String that holds the name of the product. <br/>
	 * The product name is the user-facing name of the application. This is
	 * different from the internal name of the product, which is "SIMPLE".<br/>
	 * The business name represented here is used in views where branding is to be
	 * predominantly displayed.</br>
	 * 
	 * Changing this string will reflect the business name across all views.
	 */
	public final static String PRODUCT_NAME = "Passport Services";

	/**
	 * Strings that hold the prompts for yes/no questions to be answered by a
	 * passport applicant. </br>
	 * Each string corresponds to one question in ApplicationQuestion.
	 */
	public final static Map<ApplicationQuestion, String> APPLICATION_QUESTIONS;

	/***
	 * Strings that hold headings for each section of yes/no questions in the
	 * application form.
	 */
	public final static Map<Double, String> APPLICATION_QUESTION_HEADINGS;

	/** Strings that hold user-facing names for each accepted document type. */
	public final static Map<AcceptedDocumentType, String> ACCEPTED_DOCUMENT_TYPE_NAMES;
	
	/** String that hold user-facing names for application status */
	public final static Map<ApplicationStatus, String> APPLICATION_STATUS_STRINGS;

	static {
		APPLICATION_QUESTIONS = new TreeMap<>();
		APPLICATION_QUESTIONS.put(ApplicationQuestion.PROCEEDINGS_IN_COURT,
				"Are any proceedings in respect of an offence alleged to have been committed by you pending before a criminal court in India?");
		APPLICATION_QUESTIONS.put(ApplicationQuestion.SUMMONS_IN_COURT,
				"Has any warrant or summons for your appearance been issued and pending before a court under any law for the time being in force?");
		APPLICATION_QUESTIONS.put(ApplicationQuestion.ARREST_WARRANT,
				"Has a warrant for your arrest been issued by a court under any law for the time being in force?");
		APPLICATION_QUESTIONS.put(ApplicationQuestion.PROHIBIT_DEPARTURE_FROM_COUNTRY,
				"Has an order prohibiting your departure from India been made by any court?");

		APPLICATION_QUESTIONS.put(ApplicationQuestion.CONVICTION_OR_SENTENCING,
				"<html>Have you, at any time during the period of five years immediately preceding the date of this application, been convicted by a court in India<br>for any offence involving moral turpitude and sentenced in respect thereof to imprisonment for not less than two years?");

		APPLICATION_QUESTIONS.put(ApplicationQuestion.DENIED_PASSPORT, "Have you ever been refused/denied passport?");
		APPLICATION_QUESTIONS.put(ApplicationQuestion.PASSPORT_IMPOUNDED, "Has your passport ever been impounded?");
		APPLICATION_QUESTIONS.put(ApplicationQuestion.PASSPORT_REVOKED, "Has your passport ever been revoked?");

		APPLICATION_QUESTIONS.put(ApplicationQuestion.CITIZENSHIP_OTHER_COUNTRY,
				"Have you ever been granted citizenship by any other country?");
		APPLICATION_QUESTIONS.put(ApplicationQuestion.PASSPORT_OF_OTHER_COUNTRY,
				"Have you ever held the passport of any other country at any time?");
		APPLICATION_QUESTIONS.put(ApplicationQuestion.SURRENDERED_INDIAN_PASSPORT,
				"Have you ever surrendered your Indian passport?");
		APPLICATION_QUESTIONS.put(ApplicationQuestion.RENUNCIATION_OF_INDIAN_PASSPORT,
				"Have you ever applied for renunciation of Indian citizenship?");

		APPLICATION_QUESTIONS.put(ApplicationQuestion.RETURN_USING_EC,
				"Have you ever returned to India on Emergency Certificate (EC)?");
		APPLICATION_QUESTIONS.put(ApplicationQuestion.DEPORTED, "Have you ever been deported from any country?");
		APPLICATION_QUESTIONS.put(ApplicationQuestion.REPATRIATED,
				"Have you ever been repatriated from any country back to India?");

		APPLICATION_QUESTION_HEADINGS = new HashMap<>();
		APPLICATION_QUESTION_HEADINGS.put(1.0,
				"1) Provide the following details if there are any criminal proceedings pending against the applicant.");
		APPLICATION_QUESTION_HEADINGS.put(2.0,
				"2) Provide the following details if the applicant has been convicted by a court in India.");
		APPLICATION_QUESTION_HEADINGS.put(3.0,
				"3) Provide the following details if the applicant has been refused/denied passport.");
		APPLICATION_QUESTION_HEADINGS.put(4.0,
				"4) Provide the following details if applicant has applied for or been granted foreign citizenship");
		APPLICATION_QUESTION_HEADINGS.put(5.0,
				"5) Provide the following details if applicant has returned to India on Emergency Certificate.");
		
		ACCEPTED_DOCUMENT_TYPE_NAMES = new HashMap<>();
		ACCEPTED_DOCUMENT_TYPE_NAMES.put(AcceptedDocumentType.AADHAR, "Aadhar");
		ACCEPTED_DOCUMENT_TYPE_NAMES.put(AcceptedDocumentType.PAN, "PAN Card");
		ACCEPTED_DOCUMENT_TYPE_NAMES.put(AcceptedDocumentType.DRIVERS_LICENSE, "Driver's License");
		ACCEPTED_DOCUMENT_TYPE_NAMES.put(AcceptedDocumentType.VOTER_ID, "Voter ID");
		ACCEPTED_DOCUMENT_TYPE_NAMES.put(AcceptedDocumentType.EDUCATIONAL_QUALIFICATION, "Educational qualification (Class X/XII certificate, Degree certificate, etc.)");
		ACCEPTED_DOCUMENT_TYPE_NAMES.put(AcceptedDocumentType.SIGNATURE, "Signature");
		ACCEPTED_DOCUMENT_TYPE_NAMES.put(AcceptedDocumentType.ADDRESS_PROOF, "Proof of present address of residence");
		ACCEPTED_DOCUMENT_TYPE_NAMES.put(AcceptedDocumentType.OLD_PASSPORT, "Old passport (first and last two pages)");
		
		APPLICATION_STATUS_STRINGS = new HashMap<>();
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.INITIATED, "Application initiated");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.FORM_FILLED, "Application form filled");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.SLOT_BOOKED, "Appointment slot booked");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.PAYMENT_COMPLETE, "Payment complete");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.CAPTURING_BIOMETRICS, "Biometrics capture");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.UNDER_VERIFICATION, "Documents being verified");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.UNDER_REVIEW, "Under review");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.PENDING_ADDRESS_VERIFICATION, "Awaiting address verification");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.ADDRESS_VERIFICATION_CLEAR, "Address verification done");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.ADDRESS_VERIFICATION_ADVERSE, "Address verification unsuccessful");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.ADDRESS_VERIFICATION_INCOMPLETE, "Address verification incomplete");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.COMPLETED_AND_SUCCESSFUL, "Completed; Passport issued");
		APPLICATION_STATUS_STRINGS.put(ApplicationStatus.REJECTED, "Rejected");
	}
}
