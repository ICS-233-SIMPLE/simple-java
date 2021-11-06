package edu.manipal.icas.simple.views;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

/**
 * A view that enable the Biometrics Officer to scan and upload applicants'
 * biometrics. The scan is executed and uploaded with the help of buttons.
 * 
 * @author Yodhin Agarwal (yodhin.agarwal@learner.manipal.edu)
 */
public interface BiometricOfficerView extends View {

	JButton getScanButton();

	JButton getSubmitScanButton();

	JTextField getApplicationIdTextField();

	JButton getPrevButton();

	JButton getNextButton();

	JButton getLogoutButton();

	JComboBox<String> getApplicationIdComboBox();
}
