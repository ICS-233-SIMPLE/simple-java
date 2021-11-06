package edu.manipal.icas.simple.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.manipal.icas.simple.databases.PassportOfficeDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessPassportOfficeDatabase;

public class PassportOffice {
	private Integer officeId;
	private String officeAddress;

	private static final PassportOfficeDatabase db = MsAccessPassportOfficeDatabase.getDatabase();

	public static List<PassportOffice> getAllPassportOffices() {
		return null;
	}

	public PassportOffice(Integer officeId) {
		this.officeId = officeId;
		try {
			officeAddress = db.fetchOfficeAddress(officeId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Date> getAvailableSlots() {
		try {
			if (db.fetchAvailableSlots(officeId) == null) {
				List<Date> slots = new ArrayList<>();
				for (int i = 0; i < 5; i++) {
					slots.add(new Date(System.currentTimeMillis() + i * 3600 * 60 * 60 * 24));
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
}
