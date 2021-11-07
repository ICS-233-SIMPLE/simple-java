package edu.manipal.icas.simple.impl.databases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

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

	@Override
	public List<Integer> fetchApplications(Integer officerId) throws IOException {
		List<Integer> applicationIds = new ArrayList<>();
		
		Table citizensTable = MsAccessApplicationDatabase.getDatabase().table;
		Row row = null;
		while((row = citizensTable.getNextRow()) != null) {
			try {
				String applicantId = row.getString(MsAccessApplicationDatabase.FIELD_APPLICANT_ID);
				Integer officeId = MsAccessCitizenDatabase.getDatabase().fetchPassportOfficeId(applicantId);
				if (MsAccessPassportOfficeDatabase.getDatabase().fetchOfficers(officeId).contains(officerId)) {
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
