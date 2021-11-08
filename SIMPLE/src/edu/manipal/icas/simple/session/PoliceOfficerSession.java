package edu.manipal.icas.simple.session;
import edu.manipal.icas.simple.controllers.Route;
import edu.manipal.icas.simple.models.PoliceOfficer;


/**
 * Represents a session created by a police officer.
 * 
 * @author Mehsheed Ahmed (syed.ahmed2@learner.manipal.edu@learner.manipal.edu)
 *
 */
public class PoliceOfficerSession extends Session {
	private PoliceOfficer policeOfficer;
	public PoliceOfficerSession( PoliceOfficer policeOfficer)
	{
		this.policeOfficer=policeOfficer;
		
	}
	public SessionType getSessionType() {
		return SessionType.POLICE;
	}
	public String getSessionId()
	{
		return policeOfficer.getBadgeId()+"";
	}
	public Boolean hasAccess(Route route) {
		// TODO Auto-generated method stub
		switch(route)
		{
		   case POLICE_DASHBOARD:
			return true;
			default:
				return false;
		}
	}
	public Route getDefaultRoute() {
		return Route.POLICE_DASHBOARD;
	}
	public PoliceOfficer getOfficer() {
		return policeOfficer;
	}
	
	

}
