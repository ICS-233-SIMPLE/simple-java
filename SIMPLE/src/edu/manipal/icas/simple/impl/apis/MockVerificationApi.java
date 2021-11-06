package edu.manipal.icas.simple.impl.apis;

import edu.manipal.icas.simple.apis.Document;
import edu.manipal.icas.simple.apis.VerificationApi;

/**
 * Dummy implementation of the documents verification API.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class MockVerificationApi implements VerificationApi {

	@Override
	public Boolean verifyDocument(Document document) {
		return true;
	}

}
