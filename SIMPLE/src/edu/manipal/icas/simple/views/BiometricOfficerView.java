package edu.manipal.icas.simple.views;

/**
 * A view that enable the Biometrics Officer to scan and upload 
 * applicants' biometrics. The scan is executed and uploaded with the help
 * of buttons.
 */

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;


public interface BiometricOfficerView extends View{
	
	JButton getScanButton();
	
	JButton getSubmitScanButton();
	
	JTextField getApplicationIDTextField();
	
	JButton getPrevButton();
	
	JButton getNextButton();

	JButton getLogoutButton();

	JComboBox getApplicationIDComboBox();
}
