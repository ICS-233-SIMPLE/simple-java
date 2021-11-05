package edu.manipal.icas.simple.models.application;

public class ApplicationFactory {
	private static ApplicationFactory INSTANCE = new ApplicationFactory();

	private ApplicationFactory() {
	}

	public static ApplicationFactory getInstance() {
		return INSTANCE;
	}

	public Application getApplication(ApplicationType applicationType) {
		try {
			switch (applicationType) {
			case FRESH:
				return new FreshApplication();
			case RE_ISSUE:
				return new ReIssueApplication();

			default:
				throw new IllegalArgumentException("Unknown application type " + applicationType);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Application getApplication(ApplicationType applicationType, Integer applicationId) {
		switch (applicationType) {
		case FRESH:
			return new FreshApplication(applicationId);
		case RE_ISSUE:
			return new ReIssueApplication(applicationId);

		default:
			throw new IllegalArgumentException("Unknown application type " + applicationType);
		}
	}
}
