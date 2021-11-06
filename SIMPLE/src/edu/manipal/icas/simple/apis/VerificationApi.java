package edu.manipal.icas.simple.apis;

import edu.manipal.icas.simple.models.Document;

/**
 * Interface that defines operations for a third-party document verification
 * service provider to implement.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface VerificationApi {
	/**
	 * Verifies whether the document passed was authentic or not.
	 * 
	 * @param document the document to verify
	 * @return {@code true} if the document was verified to be authentic,
	 *         {@code false} otherwise
	 */
	Boolean verifyDocument(Document document);
}
