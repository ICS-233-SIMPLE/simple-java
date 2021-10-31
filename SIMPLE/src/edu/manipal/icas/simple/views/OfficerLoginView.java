package edu.manipal.icas.simple.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 * A view that enables the different kinds of officers to access their
 * respective dashboards. The officer is expected to enter their Officer ID and
 * Officer type (verfication, biometrics, granting or police) to complete the
 * login process. They can then click the login button which will redirect them
 * to their respective dashboard. <br/>
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public interface OfficerLoginView extends View {
	/**
	 * Gets the button that the officer clicks on to view their dashboard.
	 * 
	 * @return the officer login button
	 */
	JButton getOfficerLoginButton();

	/**
	 * Gets the text field in which the officer enters their unique officer id.
	 * 
	 * @return the officer id text field
	 */
	JTextField getOfficerIdTextField();

	/**
	 * Gets the combo box having entries that correspond to the different types of
	 * officers.
	 * 
	 * @return the officer type combo box
	 */
	JComboBox<String> getOfficerTypeComboBox();

}
