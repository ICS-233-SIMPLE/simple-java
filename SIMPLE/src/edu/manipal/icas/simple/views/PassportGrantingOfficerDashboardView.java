package edu.manipal.icas.simple.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import org.icepdf.ri.common.SwingController;

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
	 * Gets the label that stores the name of the citizen.
	 * 
	 * @return the name label
	 */
	JLabel getNameLabel();

	/**
	 * Gets the label that stores the gender of the citizen.
	 * 
	 * @return the gender label
	 */
	JLabel getGenderLabel();

	/**
	 * Gets the label that stores the email address of the citizen.
	 * 
	 * @return the email address label
	 */
	JLabel getEmailAddressLabel();

	/**
	 * Gets the label that stores the citizen's father's name.
	 * 
	 * @return the name of father label
	 */
	JLabel getNameOfFatherLabel();

	/**
	 * Gets the label that stores the citizen's mother's name.
	 * 
	 * @return the name of mother label
	 */
	JLabel getNameOfMotherLabel();

	/**
	 * Gets the label that stores the contact number of the citizen.
	 * 
	 * @return the contact number label
	 */
	JLabel getContactNumberLabel();

	/**
	 * Gets the label that stores the date of birth of the citizen.
	 * 
	 * @return the date of birth label
	 */
	JLabel getDateOfBirthLabel();

	/**
	 * Gets the label that stores the birth address of the citizen.
	 * 
	 * @return the birth address label
	 */
	JLabel getBirthAddressLabel();

	/**
	 * Gets the label that stores the permanent address of the citizen.
	 * 
	 * @return the permanent address label
	 */
	JLabel getPermanentAddressLabel();

	/**
	 * Gets the label that stores the present address of the citizen.
	 * 
	 * @return the present address label
	 */
	JLabel getPresentAddressLabel();

	/**
	 * Gets the controller that is responsible for displaying PDFs in the biometrics
	 * tab.
	 * 
	 * @return the PDF biometrics controller
	 */
	SwingController getPdfBiometricsController();

	/**
	 * Gets the controller that is responsible for displaying PDFs in the documents
	 * tab.
	 * 
	 * @return the PDF documents controller
	 */
	SwingController getPdfDocumentsController();

}
