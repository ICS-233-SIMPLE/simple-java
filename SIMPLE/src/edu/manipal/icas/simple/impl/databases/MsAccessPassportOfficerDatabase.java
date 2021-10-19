package edu.manipal.icas.simple.impl.databases;

import java.io.IOException;

import edu.manipal.icas.simple.databases.PassportOfficerDatabase;
import edu.manipal.icas.simple.models.PassportOfficerRole;

/**
 * Concrete class that implements persistence API operations from
 * {@link PassportOfficerDatabaser}
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class MsAccessPassportOfficerDatabase extends MsAccessDatabase implements PassportOfficerDatabase {
	private static final MsAccessPassportOfficerDatabase DATABASE = new MsAccessPassportOfficerDatabase();

	private MsAccessPassportOfficerDatabase() {
		super("PassportOfficers");
	}

	public static MsAccessPassportOfficerDatabase getDatabase() {
		return DATABASE;
	}

	@Override
	public Boolean passportOfficerExists(Integer officerId) {
		return rowExists(officerId);
	}

	@Override
	public PassportOfficerRole fetchOfficerRole(Integer officerId) throws IOException {
		return PassportOfficerRole.valueOf(getRow(officerId).getString(FIELD_OFFICER_ROLE));
	}

}
