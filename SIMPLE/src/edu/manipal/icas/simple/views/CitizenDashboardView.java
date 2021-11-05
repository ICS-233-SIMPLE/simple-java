/**
 * 
 */
package edu.manipal.icas.simple.views;
import javax.swing.JButton;
import javax.swing.JComboBox;
/**
 * A view that enables user to Apply for a new passport,Apply for Re-Issue,Check their Application's status 
 * and check application procedure if they need any kind of clarification w.r.t application procedure/FAQ. 
 * @author Mehsheed(syed.ahmed2@learner.manipal.edu)
 *
 * 
 */
public interface CitizenDashboardView extends View {
	/**
	 * Gets the button that the citizen clicks on to start application for a new passport.
	 * 
	 * @return Apply for new passport button
	 */
	JButton getAppForNewPPButton();
	/**
	 * Gets the button that the citizen clicks on to start application for Re-Issue of a passport.
	 * 
	 * @return Apply for re-issue passport button
	 */
	
	
	JButton getredirect2AppstepsButton();
	/**
	 * Gets the log out button that the citizen clicks on to logout from the current session
	 * 
	 * @return Logout button
	 */
	JButton getLogoutButton();
	/**
	 * Gets the check application button that the citizen clicks on to check their application status
	 * 
	 * @return Check Application status button
	 */
	
	JButton checkApplicationStatusButton();
	/**
	 * Gets all the responses for JTable for viewing application responses
	 * 
	 * @return string array
	 */
	
	String[][] getTableResponses();
	
	/**
	 * Gets the combo box that is to be populated with the application IDs of citizens.
	 * 
	 * @return the application ID combo box
	 */
	
	JComboBox<String> getApplicationIdComboBox();
	
	
	
	
	
	
	

}