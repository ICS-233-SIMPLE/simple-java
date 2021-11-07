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

import org.icepdf.ri.common.SwingController;

import javax.swing.JComboBox;
import javax.swing.JLabel;

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
	JComboBox<Integer> getApplicationIdComboBox();

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

	/**
	 * Gets the label that holds the ID of the officer working with the view.
	 * 
	 * @return ID label
	 */
	JLabel getOfficerIdButton();

	/**
	 * Gets the combo box that holds the type of biometrics accepted by the
	 * application.
	 * 
	 * @return combo box
	 */
	JComboBox<String> getBiometricTypeComboBox();

	/**
	 * Gets the controller of the PDF viewer that shows a preview of each biometric.
	 * 
	 * @return pdf controller
	 */
	SwingController getPdfController();
}