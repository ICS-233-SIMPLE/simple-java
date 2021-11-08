package edu.manipal.icas.simple.impl.databases;

import edu.manipal.icas.simple.databases.PoliceDatabase;

/**
 * Concrete class that implements persistence API operations from
 * {@link PoliceDatabase}
 *
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class MsAccessPoliceDatabase extends MsAccessDatabase implements PoliceDatabase {
	private static final MsAccessPoliceDatabase DATABASE = new MsAccessPoliceDatabase();

	private MsAccessPoliceDatabase() {
		super("Police");
	}

	public static MsAccessPoliceDatabase getDatabase() {
		return DATABASE;
	}

	@Override
	public Boolean policeExists(Integer badgeId) {
		return rowExists(badgeId);
	}

}
