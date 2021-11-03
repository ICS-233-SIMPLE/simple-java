package edu.manipal.icas.simple.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import edu.manipal.icas.simple.impl.databases.MsAccessSessionDatabase;
import edu.manipal.icas.simple.models.Citizen;
import edu.manipal.icas.simple.session.Session;
import edu.manipal.icas.simple.session.SessionFactory;
import edu.manipal.icas.simple.session.SessionType;
import edu.manipal.icas.simple.views.CitizenLoginView;

public class LoginController {
	private CitizenLoginView citizenLoginView;

	public LoginController(CitizenLoginView citizenLoginView) {
		this.citizenLoginView = citizenLoginView;
		initCitizenLoginClickHandler();
		initCreateProfileRedirectHandler();
		initTextFieldValueChangeHandlers();

	}

	private void initCitizenLoginClickHandler() {
		citizenLoginView.getLoginButton().setEnabled(false);
		citizenLoginView.getLoginButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String email = citizenLoginView.getEmailTextField().getText();
				String password = new String(citizenLoginView.getPasswordPasswordField().getPassword());
				try {
					if (Citizen.authenticate(email, password)) {
						Session session = SessionFactory.getFactory().getSession(SessionType.CITIZEN, email);
						MsAccessSessionDatabase.getDatabase().startSession(session);
						RouteController.getController().routeTo(session.getDefaultRoute());
					}
				} catch (IllegalArgumentException e1) {
					showError("Citizen does not exist. Create a new citizen profile to be able to log in");
				} catch (IOException e1) {
					showError("An internal error occurred. Please try again later.");
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

		DocumentListener textChangeListener = new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				update();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				update();
			}

			private void update() {
				citizenLoginView.getLoginButton().setEnabled(
						!(emailField.getText().isEmpty() || new String(passwordField.getPassword()).isEmpty()));
			}
		};

		emailField.getDocument().addDocumentListener(textChangeListener);
		passwordField.getDocument().addDocumentListener(textChangeListener);
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(citizenLoginView.getFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
	}

}
