package edu.manipal.icas.simple.models.application;

/**
 * Enumeration representing all yes/no questions to be answered by an applicant
 * while submitting a new application.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public enum ApplicationQuestion {
	PROCEEDINGS_IN_COURT(1.1),
	SUMMONS_IN_COURT(1.2),
	ARREST_WARRANT(1.3),
	PROHIBIT_DEPARTURE_FROM_COUNTRY(1.4),
	
	CONVICTION_OR_SENTENCING(2.1),
	
	DENIED_PASSPORT(3.1),
	PASSPORT_IMPOUNDED(3.2),
	PASSPORT_REVOKED(3.3),
	
	CITIZENSHIP_OTHER_COUNTRY(4.1),
	PASSPORT_OF_OTHER_COUNTRY(4.2),
	SURRENDERED_INDIAN_PASSPORT(4.3),
	RENUNCIATION_OF_INDIAN_PASSPORT(4.4),
	
	RETURN_USING_EC(5.1),
	DEPORTED(5.2),
	REPATRIATED(5.3);
	
	public Double number;

	ApplicationQuestion(Double questionNumber) {
		number = questionNumber;
	}
}
