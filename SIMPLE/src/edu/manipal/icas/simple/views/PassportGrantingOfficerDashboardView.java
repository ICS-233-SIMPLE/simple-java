package edu.manipal.icas.simple.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

public interface PassportGrantingOfficerDashboardView extends View {
	/**
	 * Gets the combo box that is populated with the application IDs of citizens.
	 * 
	 * @return the application ID combo box
	 */
	JComboBox<String> getApplicationIdComboBox();

	/**
	 * Gets the button that is used to move to the next application ID.
	 * 
	 * @return the next application ID button
	 */
	JButton getNextApplicationIdButton();

	/**
	 * Gets the button that is used to go to the previous application ID.
	 * 
	 * @return the previous application ID
	 */
	JButton getPreviousApplicationIdButton();

	/**
	 * Gets the button that is used to approve an application.
	 * 
	 * @return the approve button
	 */
	JButton getApproveButton();

	/**
	 * Gets the button that is used to reject an application.
	 * 
	 * @return the reject button
	 */
	JButton getRejectButton();

	/**
	 * Gets the button that the passport granting officer clicks on to request
	 * police verification.
	 * 
	 * @return the request police verification button
	 */
	JButton getRequestPoliceVerificationButton();

	/**
	 * Gets the officer ID label.
	 * 
	 * @return the officer ID label
	 */
	JLabel getOfficerIdLabel();

	/**
	 * Gets the button that the officer clicks on to logout.
	 * 
	 * @return the logout button
	 */
	JButton getLogoutButton();

	/**
	 * Gets the tabbed pane.
	 * 
	 * @return the tabbed pane
	 */
	JTabbedPane getTabbedPane();
}
