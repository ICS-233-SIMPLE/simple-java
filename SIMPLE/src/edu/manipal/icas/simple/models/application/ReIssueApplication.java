package edu.manipal.icas.simple.models.application;

import java.util.Arrays;
import java.util.List;

public class ReIssueApplication extends Application {
	public ReIssueApplication(Integer applicationId) {
		super(applicationId);
	}

	public ReIssueApplication() {
		super(ApplicationType.RE_ISSUE);
	}

	@Override
	public Boolean hasRequiredDocuments() {
		List<AcceptedDocumentType> requiredDocuments = Arrays.asList(new AcceptedDocumentType[] {
				AcceptedDocumentType.AADHAR, AcceptedDocumentType.SIGNATURE, AcceptedDocumentType.ADDRESS_PROOF,
				AcceptedDocumentType.EDUCATIONAL_QUALIFICATION, AcceptedDocumentType.OLD_PASSPORT });

		for (AcceptedDocumentType document : requiredDocuments) {
			if (!getDocuments().containsKey(document))
				return false;
		}
		return true;
	}
}
