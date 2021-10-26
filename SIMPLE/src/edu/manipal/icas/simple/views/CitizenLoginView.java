package edu.manipal.icas.simple.views;


import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



/**
 * A view that enables the citizen to access their citizen profile. The citizen
 * is expected to enter their email address and password and continue to login
 * via the button. <br/>
 * Because this is the entry point view of the software, internal actors such as
 * the passport officers and police officers are to be provided a button to
 * access their internal login portals.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public interface CitizenLoginView extends View {
	/**
	 * Gets the button that the citizen clicks on to initiate the authentication
	 * process.
	 * 
	 * @return the login button
	 */
	JButton getLoginButton();

	/**
	 * Gets the button that the officers click on to be redirected to their internal
	 * login portals.
	 * 
	 * @return the redirect button
	 */
	JButton getRedirectToOfficerLoginViewButton();

	/**
	 * Gets the text field in which the citizen enters their email address.
	 * 
	 * @return the email address text field
	 */
	JTextField getEmailTextField();

	/**
	 * Gets the password field in which the citizen enters their password.
	 * 
	 * @return the password password field
	 */
	JPasswordField getPasswordPasswordField();

	/**
	 * Gets the button that redirects a new citizen candidate to the profile
	 * creation view
	 * 
	 * @return the redirect button
	 */
	JButton getRedirectToCreateProfileViewButton();

	public void addLoginListener(ActionListener listenForLoginButton);

}
