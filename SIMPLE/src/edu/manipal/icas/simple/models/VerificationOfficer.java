package edu.manipal.icas.simple.models;

import edu.manipal.icas.simple.apis.VerificationApi;
import edu.manipal.icas.simple.impl.apis.MockVerificationApi;
import edu.manipal.icas.simple.models.application.Application;

/**
 * Class that represents a verification officer. <br/>
 * The verification officer previews all documents and marks an application as
 * inauthentic or verified.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class VerificationOfficer extends PassportOfficer {

	private VerificationApi verificationApi;

	public VerificationOfficer(Integer officerId) {
		super(officerId);
		verificationApi = new MockVerificationApi();
	}

	@Override
	public void processApplication(Application application) {
		for (Document document : application.getDocuments().values()) {
			if(!verificationApi.verifyDocument(document)) {
				application.setDocumentsAuthentic(false);
				break;
			}
		}
		
		for (Document biometric : application.getBiometrics().values()) {
			if(!verificationApi.verifyDocument(biometric)) {
				application.setDocumentsAuthentic(false);
				break;
			}
		}
		
		application.setDocumentsAuthentic(true);
		application.advanceApplicationStatus();
	}

}
