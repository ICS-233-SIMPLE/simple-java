package edu.manipal.icas.simple.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.manipal.icas.simple.databases.PassportOfficeDatabase;
import edu.manipal.icas.simple.databases.PassportOfficerDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessPassportOfficeDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessPassportOfficerDatabase;

/**
 * Class that represents a passport office. <br/>
 * A passport office is a physical entity with passport officer employees which
 * provides passport services to citizens.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class PassportOffice {

	/** Unique ID of the office */
	private Integer officeId;

	/** Physical address of the office */
	private String officeAddress;

	/** List of officers who work at this office */
	private List<PassportOfficer> officers;

	private static final PassportOfficeDatabase db = MsAccessPassportOfficeDatabase.getDatabase();
	private static final PassportOfficerDatabase pDb = MsAccessPassportOfficerDatabase.getDatabase();

	/** List of all offices that are currently in operation */
	private static List<PassportOffice> offices;

	static {
		offices = new ArrayList<>();
		for (int i = 1; i <= 16; ++i) {
			offices.add(new PassportOffice(i));
		}
	}

	/**
	 * Gets a list of all passport offices currently in operation.
	 * 
	 * @return list of operational offices
	 */
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

	/**
	 * Gets a list of unbooked/unblocked passport application slots at this office.
	 * 
	 * @return list of available slots
	 */
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

	/**
	 * Blocks a slot and removes it from the list of available slots.
	 * 
	 * @param date slot to block
	 */
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

	/**
	 * Gets the address of this office.
	 * 
	 * @return physical address
	 */
	public String getOfficeAddress() {
		return officeAddress;
	}

	/**
	 * Gets a unique ID representing this office.
	 * 
	 * @return office ID
	 */
	public Integer getOfficeId() {
		return officeId;
	}

	/**
	 * Gets a list of officers working at this office.
	 * 
	 * @return list of officers
	 */
	public List<PassportOfficer> getOfficers() {
		return officers;
	}
}
