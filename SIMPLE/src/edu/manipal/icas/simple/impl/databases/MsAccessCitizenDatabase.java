package edu.manipal.icas.simple.impl.databases;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.healthmarketscience.jackcess.Row;

import edu.manipal.icas.simple.databases.CitizenDatabase;

/**
 * Concrete class that implements persistence API operations from
 * {@link CitizenDatabase}
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class MsAccessCitizenDatabase extends MsAccessDatabase implements CitizenDatabase {

	private static final MsAccessCitizenDatabase DATABASE = new MsAccessCitizenDatabase();

	private MsAccessCitizenDatabase() {
		super("Citizens");
	}

	public static MsAccessCitizenDatabase getDatabase() {
		return DATABASE;
	}

	@Override
	public void createCitizen(String emailAddress) throws IOException {
		if (citizenExists(emailAddress)) {
			throw new IOException("Duplicate row for citizen " + emailAddress);
		}
		// order is: password | name | email | ...
		table.addRow("", "", emailAddress);
	}

	@Override
	public Boolean citizenExists(String emailAddress) {
		return rowExists(emailAddress);
	}

	@Override
	public void savePassword(String emailAddress, String password) throws IOException {
		Row row = getRow(emailAddress);
		row.put(FIELD_PASSWORD, password);
		table.updateRow(row);
	}

	@Override
	public String fetchPassword(String emailAddress) throws IOException {
		return getRow(emailAddress).getString(FIELD_PASSWORD);
	}

	@Override
	public void saveName(String emailAddress, String name) throws IOException {
		Row row = getRow(emailAddress);
		row.put(FIELD_NAME, name);
		table.updateRow(row);
	}

	@Override
	public String fetchName(String emailAddress) throws IOException {
		return getRow(emailAddress).getString(FIELD_NAME);
	}

	@Override
	public void saveGender(String emailAddress, String gender) throws IOException {
		Row row = getRow(emailAddress);
		row.put(FIELD_GENDER, gender);
		table.updateRow(row);
	}

	@Override
	public String fetchGender(String emailAddress) throws IOException {
		return getRow(emailAddress).getString(FIELD_GENDER);
	}

	@Override
	public void saveDateOfBirth(String emailAddress, Date dob) throws IOException {
		Row row = getRow(emailAddress);
		row.put(FIELD_DATE_OF_BIRTH, dob);
		table.updateRow(row);
	}

	@Override
	public Date fetchDateOfBirth(String emailAddress) throws IOException {
		LocalDateTime dateTime = getRow(emailAddress).getLocalDateTime(FIELD_DATE_OF_BIRTH);
		return Date.from(dateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public void saveContactNumber(String emailAddress, Long contactNumber) throws IOException {
		Row row = getRow(emailAddress);
		row.put(FIELD_CONTACT_NUMBER, Double.parseDouble("" + contactNumber));
		table.updateRow(row);
	}

	@Override
	public Long fetchContactNumber(String emailAddress) throws IOException {
		return Long.parseLong(getRow(emailAddress).get(FIELD_CONTACT_NUMBER) + "");
	}

	@Override
	public void savePassportOfficeId(String emailAddress, Integer passportOfficeId) throws IOException {
		Row row = getRow(emailAddress);
		row.put(FIELD_PASSPORT_OFFICE_ID, passportOfficeId);
		table.updateRow(row);
	}

	@Override
	public Integer fetchPassportOfficeId(String emailAddress) throws IOException {
		return getRow(emailAddress).getInt(FIELD_PASSPORT_OFFICE_ID);
	}

	@Override
	public void savePoliceBadgeId(String emailAddress, Integer policeBadgeId) throws IOException {
		Row row = getRow(emailAddress);
		row.put(FIELD_POLICE_BADGE_ID, policeBadgeId);
		table.updateRow(row);
	}

	@Override
	public Integer fetchPoliceBadgeId(String emailAddress) throws IOException {
		return getRow(emailAddress).getInt(FIELD_POLICE_BADGE_ID);
	}

	@Override
	public void savePassportIds(String emailAddress, List<Integer> passportIds) throws IOException {
		List<String> serialisedStrings = new ArrayList<>();
		for (Integer pId : passportIds) {
			serialisedStrings.add(pId + "");
		}

		Row row = getRow(emailAddress);
		row.put(FIELD_PASSPORT_IDS, String.join(",", serialisedStrings));
		table.updateRow(row);
	}

	@Override
	public List<Integer> fetchPassportIds(String emailAddress) throws IOException {
		List<String> deserialisedStrings = Arrays.asList(getRow(emailAddress).getString(FIELD_PASSPORT_IDS).split(","));
		List<Integer> pIds = new ArrayList<>();
		for (String string : deserialisedStrings) {
			pIds.add(Integer.parseInt(string));
		}
		return pIds;
	}

	@Override
	public void saveApplicationIds(String emailAddress, List<String> applicationIds) throws IOException {
		Row row = getRow(emailAddress);
		row.put(FIELD_PASSPORT_IDS, String.join(",", applicationIds));
		table.updateRow(row);

	}

	@Override
	public List<String> fetchApplicationIds(String emailAddress) throws IOException {
		return Arrays.asList(getRow(emailAddress).getString(FIELD_APPLICATION_IDS).split(","));
	}
}
