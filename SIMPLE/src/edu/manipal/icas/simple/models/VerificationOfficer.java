package edu.manipal.icas.simple.models;

/**
 * Class that represents a verification officer. <br/>
 * The verification officer previews all documents and marks an application as
 * inauthentic or verified.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class VerificationOfficer extends PassportOfficer {

	public VerificationOfficer(Integer officerId) {
		super(officerId);
	}

	@Override
	public void processApplication() {
	}

}
