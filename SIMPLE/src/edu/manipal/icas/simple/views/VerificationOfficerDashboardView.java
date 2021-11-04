package edu.manipal.icas.simple.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.icepdf.ri.common.SwingController;

/**
 * A view that enables the verification officer to view the citizen's
 * application. Depending on the outcome of the verification process, the
 * verification officer can mark the application as "verified" or "inauthentic".
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public interface VerificationOfficerDashboardView extends View {
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
	 * Gets the button that is used to mark an application as inauthentic.
	 * 
	 * @return the mark as inauthentic button
	 */
	JButton getMarkAsInauthenticButton();

	/**
	 * Gets the button that is used to mark an application as verified.
	 * 
	 * @return the mark as verified button
	 */
	JButton getMarkAsVerifiedButton();

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
	 * Gets the scroll pane that displays the names of the citizen's documents.
	 * 
	 * @return the document name scroll pane
	 */
	JScrollPane getDocumentNameScrollPane();

	/**
	 * Gets the controller that is responsible for displaying PDFs.
	 * 
	 * @return the PDF controller
	 */
	SwingController getPdfController();

}