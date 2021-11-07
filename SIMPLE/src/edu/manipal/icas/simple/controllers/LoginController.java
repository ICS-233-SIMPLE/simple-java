package edu.manipal.icas.simple.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.manipal.icas.simple.models.Citizen;
import edu.manipal.icas.simple.models.PassportOfficer;
import edu.manipal.icas.simple.models.PassportOfficerRole;
import edu.manipal.icas.simple.session.Session;
import edu.manipal.icas.simple.session.SessionFactory;
import edu.manipal.icas.simple.session.SessionType;
import edu.manipal.icas.simple.utils.DocumentAdapter;
import edu.manipal.icas.simple.views.CitizenLoginView;
import edu.manipal.icas.simple.views.OfficerLoginView;
import edu.manipal.icas.simple.views.View;

/**
 * Controller that handles citizen and officer login use-cases.
 *
 * @author Mehsheed(syed.ahmed2@learner.manipal.edu)
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class LoginController {

	PassportOfficerRole temppr;

	private CitizenLoginView citizenLoginView;
	private OfficerLoginView officerLoginView;

	public LoginController(CitizenLoginView citizenLoginView, OfficerLoginView officerLoginView) {
		this.citizenLoginView = citizenLoginView;
		this.officerLoginView = officerLoginView;

		initCitizenLoginClickHandler();
		initCreateProfileRedirectHandler();
		initTextFieldValueChangeHandlers();
		initRedirectToOfficerLoginViewHandlers();
		initOfficerLoginClickHandler();
	}

	private void initOfficerLoginClickHandler() {
		officerLoginView.getOfficerLoginButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SessionType st = null;
				String selectedOfficerType = (String) officerLoginView.getOfficerTypeComboBox().getSelectedItem();
				String idString = officerLoginView.getOfficerIdTextField().getText().trim();
				Integer officerId = null;
				try {
					officerId = Integer.parseInt(idString);
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
				}
				switch (selectedOfficerType) {
				case "Biometrics Officer":
					temppr = PassportOfficerRole.BIOMETRICS;
					st = SessionType.BIOMETRICS_OFFICER;
					break;
				case "Verification Officer":
					temppr = PassportOfficerRole.VERIFICATION;
					st = SessionType.VERIFICATION_OFFICER;
					break;
				case "Passport Granting Officer":
					temppr = PassportOfficerRole.GRANTING;
					st = SessionType.GRANTING_OFFICER;
					break;
				default:
					break;
				}

				if (PassportOfficer.authenticate(officerId, temppr)) {
					Session session = SessionFactory.getFactory().getSession(st, idString);
					if (SessionController.getController().startSession(session))
						RouteController.getController().routeTo(session.getDefaultRoute());
					else
						showError("An internal error has occurred. Please try again later.");

				} else
					showError("Officer doesn't exist");

			}

		});

	}

	private void initRedirectToOfficerLoginViewHandlers() {
		citizenLoginView.getRedirectToOfficerLoginViewButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RouteController.getController().routeTo(Route.OFFICER_LOGIN);
			}
		});
	}

	public View getCitizenLoginView() {
		return citizenLoginView;
	}

	public View getOfficerLoginView() {
		return officerLoginView;

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
