package edu.manipal.icas.simple.impl.apis;

import edu.manipal.icas.simple.apis.BiometricsCaptureApi;
import edu.manipal.icas.simple.apis.Document;

/**
 * Dummy implementation of the biometrics capture API.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class MockBiometricsCaptureApi implements BiometricsCaptureApi{

	@Override
	public Document captureFingerprints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document captureThumbPrints() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Document capturePhotograph() {
		// TODO Auto-generated method stub
		return null;
	}

}
