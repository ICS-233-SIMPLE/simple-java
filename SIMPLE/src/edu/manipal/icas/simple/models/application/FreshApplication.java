package edu.manipal.icas.simple.models.application;

import java.util.Arrays;
import java.util.List;

public class FreshApplication extends Application {

	public FreshApplication(Integer applicationId) {
		super(applicationId, ApplicationType.FRESH);
	}

	public FreshApplication() throws Exception {
		super(ApplicationType.FRESH);
	}

	@Override
	public Boolean hasRequiredDocuments() {
		List<AcceptedDocumentType> requiredDocuments = Arrays
				.asList(new AcceptedDocumentType[] { AcceptedDocumentType.AADHAR, AcceptedDocumentType.SIGNATURE,
						AcceptedDocumentType.ADDRESS_PROOF, AcceptedDocumentType.EDUCATIONAL_QUALIFICATION });
		
		for (AcceptedDocumentType document : requiredDocuments) {
			if (!getDocuments().containsKey(document))
				return false;
		}
		return true;
	}

}
