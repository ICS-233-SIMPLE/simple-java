package edu.manipal.icas.simple.models;
import java.util.List;

import edu.manipal.icas.simple.databases.PoliceDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessPoliceDatabase;
import edu.manipal.icas.simple.models.application.Application;

public class PoliceOfficer {
	private Integer badgeId;
	protected static final PoliceDatabase db = MsAccessPoliceDatabase.getDatabase();
	public PoliceOfficer(Integer badgeId)
	{
		this.badgeId=badgeId;
	}
	public Integer getBadgeId()
	{
		return badgeId;
	}
	public void verifyAddress(Citizen citizen,Document addressProof)
	{

        //TODO



	}
	public static Boolean authenticate(Integer badgeId)
	{
		return db.policeExists(badgeId);

	}

	public List<Application> getApplications() {
		//TODO

             return null;

    }
}
