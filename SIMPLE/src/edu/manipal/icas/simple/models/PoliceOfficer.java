package edu.manipal.icas.simple.models;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.manipal.icas.simple.databases.PoliceDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessPoliceDatabase;
import edu.manipal.icas.simple.models.application.Application;
import edu.manipal.icas.simple.models.application.ApplicationFactory;

public class PoliceOfficer {
	private Integer badgeId;
	private static final PoliceDatabase db = MsAccessPoliceDatabase.getDatabase();

	public PoliceOfficer(Integer badgeId) {
		this.badgeId = badgeId;
	}

	public Integer getBadgeId() {
		return badgeId;
	}

	public void verifyAddress(Citizen citizen, Document addressProof) {
	/*
		String address = citizen.getPermanentAddress();
		if (address == "") {
			throw new Exception("Incomplete");
		}
		// no exception is treated as "clear"
		Passport passport = new Passport(citizen);
		citizen.setPassport(passport);
	*/
	}

	public static Boolean authenticate(Integer badgeId) {
		return db.policeExists(badgeId);

	}

	public List<Application> getApplications() {
		List<Application> applications = new ArrayList<>();
		try {
			List<Integer> appIds = db.fetchApplications(badgeId);
			for (Integer appId : appIds) {
				applications.add(ApplicationFactory.getInstance().getApplication(appId));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applications;

	}
}
