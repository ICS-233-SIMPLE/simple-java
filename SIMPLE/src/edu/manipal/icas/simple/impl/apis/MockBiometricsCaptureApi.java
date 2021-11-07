package edu.manipal.icas.simple.impl.apis;

import edu.manipal.icas.simple.apis.BiometricsCaptureApi;
import edu.manipal.icas.simple.models.Document;
import edu.manipal.icas.simple.resources.Resources;

/**
 * Dummy implementation of the biometrics capture API.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class MockBiometricsCaptureApi implements BiometricsCaptureApi{

	@Override
	public Document captureFingerprints() {
		return new Document(System.currentTimeMillis() + "_MOCK_API_FINGERPRINTS", Resources.getResourceUri("mock/fingerprints.pdf"));
	}

	@Override
	public Document captureThumbPrints() {
		return new Document(System.currentTimeMillis() + "_MOCK_API_THUMBPRINTS", Resources.getResourceUri("mock/thumbprints.pdf"));
	}

	@Override
	public Document capturePhotograph() {
		return new Document(System.currentTimeMillis() + "_MOCK_API_PHOTOGRAPH", Resources.getResourceUri("mock/photograph.pdf"));
	}

}
