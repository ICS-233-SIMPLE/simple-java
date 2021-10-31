package edu.manipal.icas.simple.apis;

import edu.manipal.icas.simple.models.Document;

/**
 * Interface that defines contracts that a third-party biometrics hardware API
 * provider must adhere to. <br/>
 * This API involves operations related to capturing finger prints, thumb
 * prints, and photographs by providing a facade over the hardware that drives
 * the capture process.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface BiometricsCaptureApi {
	/**
	 * Returns the finger prints as captured by the hardware in the form of a
	 * Document.
	 * 
	 * @return a document whose name is [TIMESTAMP + NAME_OF_API_IMPL +
	 *         "FINGERPRINTS"]
	 */
	Document captureFingerprints();

	/**
	 * Returns the thumb prints as captured by the hardware in the form of a
	 * Document.
	 * 
	 * @return a document named [TIMESTAMP + NAME_OF_API_IMPL + "THUMBPRINTS"]
	 */
	Document captureThumbPrints();

	/**
	 * Returns the photograph as captured by the API's underlying camera in the form
	 * of a Document.
	 * 
	 * @return a document named [TIMESTAMP + NAME_OF_API_IMPL + "PHOTOGRAPH"]
	 */
	Document capturePhotograph();
}
