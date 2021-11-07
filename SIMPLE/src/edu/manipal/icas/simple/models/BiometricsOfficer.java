package edu.manipal.icas.simple.models;

import edu.manipal.icas.simple.apis.BiometricsCaptureApi;
import edu.manipal.icas.simple.impl.apis.MockBiometricsCaptureApi;
import edu.manipal.icas.simple.models.application.AcceptedBiometricType;
import edu.manipal.icas.simple.models.application.Application;

/**
 * Class that represents a biometrics officer. <br/>
 * The biometrics officer collects all the required biometrics from the citizen.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class BiometricsOfficer extends PassportOfficer {
	private BiometricsCaptureApi biometricsCaptureApi;

	public BiometricsOfficer(Integer officerId) {
		super(officerId);
		biometricsCaptureApi = new MockBiometricsCaptureApi();
	}

	@Override
	public void processApplication(Application application) {
		Document leftFingerprintDocument = biometricsCaptureApi.captureFingerprints();
		application.uploadBiometric(AcceptedBiometricType.FINGERPRINTS_LEFT, leftFingerprintDocument);

		Document rightFingerprintDocument = biometricsCaptureApi.captureFingerprints();
		application.uploadBiometric(AcceptedBiometricType.FINGERPRINTS_RIGHT, rightFingerprintDocument);

		Document thumbprints = biometricsCaptureApi.captureThumbPrints();
		application.uploadBiometric(AcceptedBiometricType.THUMBPRINTS, thumbprints);

		Document photograph = biometricsCaptureApi.capturePhotograph();
		application.uploadBiometric(AcceptedBiometricType.PHOTOGRAPH, photograph);
	}

}
