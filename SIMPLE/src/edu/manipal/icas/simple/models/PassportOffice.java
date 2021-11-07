package edu.manipal.icas.simple.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.manipal.icas.simple.databases.PassportOfficeDatabase;
import edu.manipal.icas.simple.databases.PassportOfficerDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessPassportOfficeDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessPassportOfficerDatabase;

public class PassportOffice {
	private Integer officeId;
	private String officeAddress;
	private List<PassportOfficer> officers;

	private static final PassportOfficeDatabase db = MsAccessPassportOfficeDatabase.getDatabase();
	private static final PassportOfficerDatabase pDb = MsAccessPassportOfficerDatabase.getDatabase();
	private static List<PassportOffice> offices;

	static {
		offices = new ArrayList<>();
		for (int i = 1; i <= 16; ++i) {
			offices.add(new PassportOffice(i));
		}
	}

	public static List<PassportOffice> getAllPassportOffices() {
		return offices;
	}

	private PassportOffice(Integer officeId) {
		this.officeId = officeId;
		try {
			officeAddress = db.fetchOfficeAddress(officeId);
			List<Integer> officerIds = db.fetchOfficers(officeId);
			officers = new ArrayList<>();
			for (Integer id : officerIds) {
				switch (pDb.fetchOfficerRole(officeId)) {
				case VERIFICATION:
					officers.add(new VerificationOfficer(officeId));
					break;
				case BIOMETRICS:
					officers.add(new BiometricsOfficer(officeId));
					break;
				case GRANTING:
					officers.add(new PassportGrantingOfficer(officeId));
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	public List<Date> getAvailableSlots() {
		try {
			if (db.fetchAvailableSlots(officeId) == null || db.fetchAvailableSlots(officeId).isEmpty()) {
				List<Date> slots = new ArrayList<>();
				Date today = new Date(System.currentTimeMillis());
				today.setHours(0);
				today.setMinutes(0);
				today.setSeconds(0);
				for (int i = 0; i < 5; i++) {
					slots.add(new Date(today.getTime()));
					today = new Date(today.getTime() + 1000 * 60 * 60 * 24);
				}
				db.saveAvailableSlots(officeId, slots);
				return slots;
			}
			return db.fetchAvailableSlots(officeId);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void blockSlot(Date date) {
		try {
			List<Date> slots = db.fetchAvailableSlots(officeId);
			slots.remove(date);
			db.saveAvailableSlots(officeId, slots);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public Integer getOfficeId() {
		return officeId;
	}

	public List<PassportOfficer> getOfficers() {
		return officers;
	}
}
