package edu.manipal.icas.simple.models.application;

import java.util.HashMap;
import java.util.Map;

/**
 * Singleton factory that creates {@link Application} objects.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class ApplicationFactory {
	private static ApplicationFactory INSTANCE = new ApplicationFactory();

	private ApplicationFactory() {
		applications = new HashMap<>();
	}

	public static ApplicationFactory getInstance() {
		return INSTANCE;
	}

	private Map<Integer, Application> applications;

	/**
	 * Gets a new application of the given type.
	 * 
	 * @param applicationType type of application to create; can be fresh or
	 *                        re-issue
	 * @return a new application
	 */
	public Application getApplication(ApplicationType applicationType) {
		Application application;
		switch (applicationType) {
		case FRESH:
			application = new FreshApplication();
			break;
		case RE_ISSUE:
			application = new ReIssueApplication();
			break;

		default:
			throw new IllegalArgumentException("Unknown application type " + applicationType);
		}

		applications.put(application.getApplicationId(), application);
		return application;
	}

	/**
	 * Gets a pre-existing application given the application ID.
	 * 
	 * @param applicationId ID of the application to create
	 * @return application object created based on existing database records
	 */
	public Application getApplication(Integer applicationId) {
		if (applications.containsKey(applicationId)) {
			return applications.get(applicationId);
		}
		
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
