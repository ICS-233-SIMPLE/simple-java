package edu.manipal.icas.simple.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.manipal.icas.simple.models.Citizen;
import edu.manipal.icas.simple.session.Session;
import edu.manipal.icas.simple.session.SessionFactory;
import edu.manipal.icas.simple.session.SessionType;
import edu.manipal.icas.simple.utils.DocumentAdapter;
import edu.manipal.icas.simple.views.CitizenLoginView;
import edu.manipal.icas.simple.views.View;

/**
 * Controller that handles citizen and officer login use-cases.
 * 
 * @author Mehsheed(syed.ahmed2@learner.manipal.edu)
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class LoginController {
	private CitizenLoginView citizenLoginView;

	public LoginController(CitizenLoginView citizenLoginView) {
		this.citizenLoginView = citizenLoginView;
		initCitizenLoginClickHandler();
		initCreateProfileRedirectHandler();
		initTextFieldValueChangeHandlers();
	}

	public View getCitizenLoginView() {
		return citizenLoginView;
	}

	private void initCitizenLoginClickHandler() {
		citizenLoginView.getLoginButton().setEnabled(false);
		citizenLoginView.getLoginButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String email = citizenLoginView.getEmailTextField().getText().trim();
				String password = new String(citizenLoginView.getPasswordPasswordField().getPassword()).trim();
				try {
					if (Citizen.authenticate(email, password)) {
						// TODO: Replace direct db access with controller
						Session session = SessionFactory.getFactory().getSession(SessionType.CITIZEN, email);
						if (SessionController.getController().startSession(session))
							RouteController.getController().routeTo(session.getDefaultRoute());
						else
							showError("An internal error has occurred. Please try again later.");
					} else {
						showError("Email address and/or password are incorrect!");
					}
				} catch (IllegalArgumentException e) {
					showError("Citizen does not exist. Create a new citizen profile to be able to log in");
				}
			}
		});
	}

	private void initCreateProfileRedirectHandler() {
		citizenLoginView.getRedirectToCreateProfileViewButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RouteController.getController().routeTo(Route.PROFILE_CREATION);
			}
		});
	}

	private void initTextFieldValueChangeHandlers() {
		JTextField emailField = citizenLoginView.getEmailTextField();
		JPasswordField passwordField = citizenLoginView.getPasswordPasswordField();

		// Enables the login button only if both text fields are filled
		DocumentAdapter textChangeListener = new DocumentAdapter() {
			@Override
			protected void update() {
				citizenLoginView.getLoginButton().setEnabled(
						!(emailField.getText().isBlank() || new String(passwordField.getPassword()).isBlank()));
			}
		};
		emailField.getDocument().addDocumentListener(textChangeListener);
		passwordField.getDocument().addDocumentListener(textChangeListener);
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(citizenLoginView.getFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
