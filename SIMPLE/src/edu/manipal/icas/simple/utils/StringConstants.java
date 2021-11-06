package edu.manipal.icas.simple.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class that holds business strings commonly used across the
 * application.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
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
	public final static Map<Double, String> APPLICATION_QUESTIONS;

	static {
		APPLICATION_QUESTIONS = new HashMap<>();
		APPLICATION_QUESTIONS.put(1.0, "1) Provide the following details if there are any criminal proceedings pending against the applicant.");
		APPLICATION_QUESTIONS.put(1.1, "Are any proceedings in respect of an offence alleged to have been committed by you pending before a criminal court in India?");
		APPLICATION_QUESTIONS.put(1.2, "Has any warrant or summons for your appearance been issued and pending before a court under any law for the time being in force?");
		APPLICATION_QUESTIONS.put(1.3, "Has a warrant for your arrest been issued by a court under any law for the time being in force?");
		APPLICATION_QUESTIONS.put(1.4, "Has an order prohibiting your departure from India been made by any court?");

		APPLICATION_QUESTIONS.put(2.0, "2) Provide the following details if the applicant has been convicted by a court in India.");
		APPLICATION_QUESTIONS.put(2.1, "Have you, at any time during the period of five years immediately preceding the date of this application, been convicted by a court in India for any offence involving moral turpitude and sentenced in respect thereof to imprisonment for not less than two years?");

		APPLICATION_QUESTIONS.put(3.0, "3) Provide the following details if the applicant has been refused/denied passport.");
		APPLICATION_QUESTIONS.put(3.1, "Have you ever been refused/denied passport?");
		APPLICATION_QUESTIONS.put(3.2, "Has your passport ever been impounded?");
		APPLICATION_QUESTIONS.put(3.3, "Has your passport ever been revoked?");

		APPLICATION_QUESTIONS.put(4.0, "4) Provide the following details if applicant has applied for or been granted foreign citizenship");
		APPLICATION_QUESTIONS.put(4.1, "Have you ever been granted citizenship by any other country?");
		APPLICATION_QUESTIONS.put(4.2, "Have you ever held the passport of any other country at any time?");
		APPLICATION_QUESTIONS.put(4.3, "Have you ever surrendered your Indian passport?");
		APPLICATION_QUESTIONS.put(4.4, "Have you ever applied for renunciation of Indian citizenship?");

		APPLICATION_QUESTIONS.put(5.0, "5) Provide the following details if applicant has returned to India on Emergency Certificate.");
		APPLICATION_QUESTIONS.put(5.1, "Have you ever returned to India on Emergency Certificate (EC)?");
		APPLICATION_QUESTIONS.put(5.2, "Have you ever been deported from any country?");
		APPLICATION_QUESTIONS.put(5.3, "Have you ever been repatriated from any country back to India?");
	}
}
