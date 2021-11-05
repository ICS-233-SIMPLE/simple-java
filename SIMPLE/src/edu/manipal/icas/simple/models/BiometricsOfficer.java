package edu.manipal.icas.simple.models;

/**
 * Class that represents a biometrics officer. <br/>
 * The biometrics officer collects all the required biometrics from the citizen.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class BiometricsOfficer extends PassportOfficer {

	public BiometricsOfficer(Integer officerId) {
		super(officerId);
	}

	@Override
	public void processApplication() {
	}

}
