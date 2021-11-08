package edu.manipal.icas.simple.impl.databases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

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

	@Override
	public List<Integer> fetchApplications(Integer badgeId) throws IOException {
		List<Integer> applicationIds = new ArrayList<>();

		Table applicationsTable = MsAccessApplicationDatabase.getDatabase().table;
		Row row = null;
		while ((row = applicationsTable.getNextRow()) != null) {
			try {
				String applicantId = row.getString(MsAccessApplicationDatabase.FIELD_APPLICANT_ID);
				Integer rowBadgeId = MsAccessCitizenDatabase.getDatabase().fetchPoliceBadgeId(applicantId);
				if (rowBadgeId == badgeId) {
					applicationIds.add(row.getInt(MsAccessApplicationDatabase.FIELD_ID));
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		return applicationIds;
	}

}
