package edu.manipal.icas.simple.views;

/**
 * A view that enable the Biometrics Officer to scan and upload 
 * applicants' biometrics. The scan is executed and uploaded with the help
 * of buttons.
 * 
 * @author Yodhin Agarwal (yodhin.agarwal@learner.manipal.edu)
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 */

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public interface BiometricOfficerView extends View {

	/**
	 * Gets the button that the Biometric Officer clicks on to initizate the
	 * scanning of selected biometric type.
	 * 
	 * @return the scan button
	 */
	JButton getScanButton();

	/**
	 * Gets the button that the Biometric Officer clicks on to submit the scan.
	 * 
	 * @return the submit scan button
	 */
	JButton getSubmitScanButton();

	/**
	 * Gets the button that the Biometric Officer clicks on to logout from the
	 * dashboard.
	 * 
	 * @return the logout button
	 */
	JButton getLogoutButton();

	/**
	 * Gets the combobox that the Biometric Officer uses to select application ID
	 * 
	 * @return the application ID combobox
	 */
	JComboBox getApplicationIdComboBox();

	/**
	 * Gets the button that the Biometric Officer uses to return to the previous
	 * application ID
	 * 
	 * @return the prev app ID button
	 */
	JButton getPrevAppIdButton();

	/**
	 * Gets the button that the Biometric Officer uses to move to the next
	 * application ID
	 * 
	 * @return the next app ID button
	 */
	JButton getNextAppIdButton();
}