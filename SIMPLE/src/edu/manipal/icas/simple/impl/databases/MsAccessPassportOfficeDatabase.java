package edu.manipal.icas.simple.impl.databases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.healthmarketscience.jackcess.Row;

import edu.manipal.icas.simple.databases.PassportOfficeDatabase;

/**
 * Concrete class that implements persistence API operations from
 * {@link PassportOfficeDatabase}
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class MsAccessPassportOfficeDatabase extends MsAccessDatabase implements PassportOfficeDatabase {
	private static final MsAccessPassportOfficeDatabase DATABASE = new MsAccessPassportOfficeDatabase();

	private MsAccessPassportOfficeDatabase() {
		super("PassportOffices");
	}

	public static MsAccessPassportOfficeDatabase getDatabase() {
		return DATABASE;
	}

	@Override
	public Boolean passportOfficeExists(Integer officeId) {
		return rowExists(officeId);
	}

	@Override
	public String fetchOfficeAddress(Integer officeId) throws IOException {
		return getRow(officeId).getString(FIELD_ADDRESS);
	}

	@Override
	public void saveAvailableSlots(Integer officeId, List<Date> availableSlots) throws IOException {
		Row row = getRow(officeId);
		List<String> serialisedSlots = new ArrayList<>();
		for (Date slot : availableSlots) {
			serialisedSlots.add(slot.getTime() + "");
		}
		row.put(FIELD_AVAILABLE_SLOTS, String.join(",", serialisedSlots));
		table.updateRow(row);
	}

	@Override
	public List<Date> fetchAvailableSlots(Integer officeId) throws IOException {
		List<Date> deserialisedSlots = new ArrayList<>();
		List<String> serialisedSlots = Arrays.asList(getRow(officeId).getString(FIELD_AVAILABLE_SLOTS).split(","));

		for (String slot : serialisedSlots) {
			deserialisedSlots.add(new Date(Long.parseLong(slot)));
		}
		return deserialisedSlots;
	}

	@Override
	public List<Integer> fetchOfficers(Integer officeId) {
		// TODO Auto-generated method stub
		return null;
	}

}
