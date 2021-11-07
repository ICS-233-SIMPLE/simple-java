package edu.manipal.icas.simple.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 * A view that enables user to Apply for a new passport,Apply for Re-Issue,Check
 * their Application's status and check application procedure if they need any
 * kind of clarification w.r.t application procedure/FAQ.
 *
 * @author Mehsheed (syed.ahmed2@learner.manipal.edu)
 *
 */
public interface CitizenDashboardView extends View {

	/**
	 * Gets the button that the citizen clicks on to start application for a new
	 * passport.
	 *
	 * @return Apply for new passport button
	 */
	JButton getApplicationForNewPassportButton();

	/**
	 * Gets the button that the citizen clicks on to start application for Re-Issue
	 * of a passport.
	 *
	 * @return Apply for re-issue passport button
	 */
	JButton getRedirectToAppStepsButton();

	/**
	 * Gets the log out button that the citizen clicks on to logout from the current
	 * session
	 *
	 * @return Logout button
	 */
	JButton getLogoutButton();

	/**
	 * Gets all the JTable for viewing application responses
	 *
	 * @return string array
	 */
	JTable getApplicationTable();

	/**
	 * Gets the combo box that is to be populated with the application IDs of
	 * citizens.
	 *
	 * @return the application ID combo box
	 */
	JComboBox<Integer> getApplicationIdComboBox();

	/**
	 * Gets the JLabel that holds application status.
	 *
	 * @return the application status JLabel
	 */
	JLabel getApplicationStatus();

	/**
	 * Gets the label that holds the citizen's email address.
	 * 
	 * @return email address label
	 */
	JLabel getEmailAddressTextField();
}