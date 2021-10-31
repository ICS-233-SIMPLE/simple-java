package edu.manipal.icas.simple.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

/**
 * A view that enables a citizen to set up their profile. The citizen is
 * expected to enter their email address and set up a password. They are then
 * prompted to enter personal details like their name, gender, and date of
 * birth.The last step of profile creation involves selecting the desired
 * passport office branch.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface ProfileCreationView extends View{
	/**
	 * Gets the panel that contains all the steps in this creation wizard.
	 * 
	 * @return the steps container panel
	 */
	JPanel getStepsContainerPanel();

	/**
	 * Gets the panel that contains form inputs for entering and verifying the
	 * citizen's email address.
	 * 
	 * @return the panel containing step 1 form fields
	 */
	JPanel getStep1Panel();

	/**
	 * Gets the panel that contains form inputs for entering and confirming the
	 * citizen's password.
	 * 
	 * @return the panel containing step 2 form fields
	 */

	JPanel getStep2Panel();

	/**
	 * Gets the panel that contains form inputs for entering the citizen's name,
	 * gender, and date of birth.
	 * 
	 * @return the panel containing step 3 form fields
	 */
	JPanel getStep3Panel();

	/**
	 * Gets the panel that contains form inputs for selecting the desired passport
	 * office branch and completing the process of profile creation.
	 * 
	 * @return the panel containing step 4 form fields
	 */
	JPanel getStep4Panel();

	/**
	 * Gets the text field in which the citizen enters their email address.
	 * 
	 * @return the email address text field
	 */
	JTextField getEmailAddressTextField();

	/**
	 * Gets the button that the citizen clicks on to verify their email address and
	 * move to the second step of profile creation.
	 * 
	 * @return the verify and continue button
	 */
	JButton getVerifyAndContinueToStep2Button();

	/**
	 * Gets the password field in which the citizen enters their password.
	 * 
	 * @return the password password field
	 */
	JPasswordField getPasswordField();

	/**
	 * Gets the password field in which the citizen re-enters their password.
	 * 
	 * @return the confirm password password field
	 */
	JPasswordField getConfirmPasswordField();

	/**
	 * Gets the button that the citizen clicks on to move to the third step of
	 * profile creation.
	 * 
	 * @return the continue button
	 */
	JButton getContinueToStep3Button();

	/**
	 * Gets the text field in which the citizen enters their name.
	 * 
	 * @return the name text field
	 */
	JTextField getNameTextField();

	/**
	 * Gets the text field in which the citizen enters their gender.
	 * 
	 * @return the gender text field
	 */
	JTextField getGenderTextField();

	/**
	 * Gets the date picker in which the citizen enters their DOB.
	 * 
	 * @return the date picker
	 */
	DatePicker getDateOfBirthDatePicker();

	/**
	 * Gets the text field in which the citizen enters their contact number.
	 * 
	 * @return the contact number text field
	 */
	JTextField getContactNumberTextField();

	/**
	 * Gets the button that the citizen clicks on to proceed to the next step of
	 * profile creation.
	 * 
	 * @return the continue button
	 */
	JButton getContinueToStep4Button();

	/**
	 * Gets the combo box which is populated with the different passport office
	 * branches.
	 * 
	 * @return the passport office combo box
	 */
	JComboBox<String> getPassportOfficeComboBox();

	/**
	 * Gets the button that the citizen clicks on to finish profile creation.
	 * 
	 * @return the finish button
	 */
	JButton getFinishButton();

	/**
	 * Gets the label that displays an error message to the citizen if they enter
	 * incorrect details.
	 * 
	 * @return the error message label
	 */
	JLabel getErrorMessageLabel();
}
