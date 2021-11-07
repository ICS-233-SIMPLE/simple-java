package edu.manipal.icas.simple.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 * A view that enables user to Apply for a new passport,Apply for Re-Issue,Check
 * their Application's status and check application procedure if they need any
 * kind of clarification w.r.t application procedure/FAQ.
 *
 * @author Mehsheed(syed.ahmed2@learner.manipal.edu)
 * @author Yodhin(yodhin.agarwal@learner.manipal.edu)
 *
 */
public interface PoliceDashboardView extends View {

	/**
	 * Gets the button that is used to go to the previous application ID.
	 *
	 * @return the previous application ID
	 */
	JButton getPreviousApplicationIdButton();

	/**
	 * Gets the button that is used to move to the next application ID.
	 *
	 * @return the next application ID button
	 */

	JButton getNextApplicationIdButton();

	/**
	 * Gets the combo box that is populated with the application IDs of citizens.
	 *
	 * @return the application ID combo box
	 */

	JComboBox<String> getApplicationIdComboBox();

	/**
	 * Gets the officer Id label.
	 *
	 * @return the officer Id label
	 */
	JLabel getofficerIdLabel();

	/**
	 * Gets the button that the officer clicks on to logout.
	 *
	 * @return the logout button
	 */

	JButton getLogOutButton();

	/**
	 * Gets the button which is used to check addressproof of citizen.
	 *
	 * @return the view addressproof button
	 */

	JButton getviewDocumentButton();

	/**
	 * Gets the button which is used to update a citizen's address proof as adverse
	 *
	 * @return the adverse status button
	 */

	JButton getAdverseButton();

	/**
	 * Gets the button which is used to update a citizen's address proof as clear
	 *
	 * @return the clear status button
	 */

	JButton getClearButton();

	/**
	 * Gets the button which is used to update a citizen's address proof as
	 * Incomplete
	 *
	 * @return the incomplete status button
	 */

	JButton getIncompleteButton();

	/**
	 * Gets all the entries for JTable for viewing list of pending verification
	 * applications
	 *
	 * @return string array
	 */

	String[][] getPendingVerificationTableEntries();

}
