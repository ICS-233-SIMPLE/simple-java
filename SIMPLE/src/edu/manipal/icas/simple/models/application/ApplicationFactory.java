package edu.manipal.icas.simple.models.application;

public class ApplicationFactory {
	private static ApplicationFactory INSTANCE = new ApplicationFactory();

	private ApplicationFactory() {
	}

	public static ApplicationFactory getInstance() {
		return INSTANCE;
	}

	public Application getApplication(ApplicationType applicationType) {
		switch (applicationType) {
		case FRESH:
			return new FreshApplication();
		case RE_ISSUE:
			return new ReIssueApplication();

		default:
			throw new IllegalArgumentException("Unknown application type " + applicationType);
		}
	}

	public Application getApplication(Integer applicationId) {
		Application application = new Application(applicationId) {
			@Override
			public Boolean hasRequiredDocuments() {
				return null;
			}
		};
		
		switch (application.getType()) {
		case FRESH:
			return new FreshApplication(applicationId);
		case RE_ISSUE:
			return new ReIssueApplication(applicationId);
		}
		return null;
	}
}
