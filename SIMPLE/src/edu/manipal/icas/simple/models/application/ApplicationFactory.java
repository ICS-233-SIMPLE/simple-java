package edu.manipal.icas.simple.models.application;

/**
 * Singleton factory that creates {@link Application} objects.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class ApplicationFactory {
	private static ApplicationFactory INSTANCE = new ApplicationFactory();

	private ApplicationFactory() {
	}

	public static ApplicationFactory getInstance() {
		return INSTANCE;
	}

	/**
	 * Gets a new application of the given type.
	 * 
	 * @param applicationType type of application to create; can be fresh or
	 *                        re-issue
	 * @return a new application
	 */
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

	/**
	 * Gets a pre-existing application given the application ID.
	 * 
	 * @param applicationId ID of the application to create
	 * @return application object created based on existing database records
	 */
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
