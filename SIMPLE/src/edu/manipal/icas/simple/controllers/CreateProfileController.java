package edu.manipal.icas.simple.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentListener;

import edu.manipal.icas.simple.impl.databases.MsAccessSessionDatabase;
import edu.manipal.icas.simple.models.Citizen;
import edu.manipal.icas.simple.models.PassportOffice;
import edu.manipal.icas.simple.session.CitizenSession;
import edu.manipal.icas.simple.session.Session;
import edu.manipal.icas.simple.session.SessionFactory;
import edu.manipal.icas.simple.session.SessionType;
import edu.manipal.icas.simple.utils.DocumentAdapter;
import edu.manipal.icas.simple.views.ProfileCreationView;
import edu.manipal.icas.simple.views.View;

/**
 * Controller that handles creation of citizen profiles.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class CreateProfileController {
	private ProfileCreationView view;
	private Citizen citizen;

	public CreateProfileController(ProfileCreationView profileCreationView) {
		this.view = profileCreationView;
		citizen = null;
		initStep1Fields();
		initStep2Fields();
		initStep3Fields();
		initStep4Fields();
		initTextFields();
		initCloseHandler();
	}

	public View getProfileCreationView() {
		return view;
	}

	private void initStep1Fields() {
		view.getVerifyAndContinueToStep2Button().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String email = view.getEmailAddressTextField().getText().trim();
				try {
					Citizen.authenticate(email, "");
					showError("A citizen with the same email address already exists!\nTry logging in instead");
				} catch (IllegalArgumentException e) {
					citizen = ((CitizenSession) SessionFactory.getFactory().getSession(SessionType.CITIZEN, email))
							.getCitizen();
					continueToStep(view.getStep2Panel());
				}
			}
		});
	}

	private void initStep2Fields() {
		view.getContinueToStep3Button().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String password = new String(view.getPasswordField().getPassword());
				String repeatedPassword = new String(view.getConfirmPasswordField().getPassword());
				if (!password.equals(repeatedPassword)) {
					showError(
							"Passwords do not match.\nPlease make sure you have typed the same password in both fields");
					return;
				}
				citizen.setPassword(password);
				continueToStep(view.getStep3Panel());
			}
		});
	}

	private void initStep3Fields() {
		view.getContinueToStep4Button().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				continueToStep(view.getStep4Panel());
			}
		});

		view.getSaveNameButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					citizen.setName(view.getNameTextField().getText().trim());
					showInfo("Name set successfully!");
				} catch (IllegalArgumentException e) {
					showError("Name cannot be empty!");
				}
			}
		});

		view.getSaveGenderButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				citizen.setGender(view.getGenderTextField().getText().trim());
				showInfo("Gender set successfully!");
			}
		});

		view.getSaveContactNumberButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					citizen.setContactNumber(Long.parseLong(view.getContactNumberTextField().getText()));
					showInfo("Contact number set successfully!");
				} catch (IllegalArgumentException e) {
					showError("Contact number must be a 10 digit number!");
				}
			}
		});

		view.getSaveDateOfBirthButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					Date dob = Date.from(
							view.getDateOfBirthDatePicker().getDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
					citizen.setDateOfBirth(dob);
					showInfo("Date of birth set successfully!");
				} catch (IllegalArgumentException e) {
					showError("You must be 18 years of age or older to avail passport services from this application.");
				}
			}
		});
	}

	private void initStep4Fields() {
		view.getPassportOfficeComboBox().removeAllItems();
		for (PassportOffice office : PassportOffice.getAllPassportOffices()) {
			view.getPassportOfficeComboBox().addItem(breakTextIntoLines(office.getOfficeAddress()));
		}

		view.getFinishButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer passportOfficeId = view.getPassportOfficeComboBox().getSelectedIndex();
				if (passportOfficeId == -1) {
					showError("Select a passport office!");
					return;
				}
				citizen.setPassportOffice(PassportOffice.getAllPassportOffices().get(passportOfficeId));
				try {
					Session session = SessionFactory.getFactory().getSession(SessionType.CITIZEN,
							citizen.getEmailAddress());
					MsAccessSessionDatabase.getDatabase().startSession(session);
					citizen = null;
					showInfo("Profile created!\nLogging you in...");
					RouteController.getController().routeTo(session.getDefaultRoute());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void initTextFields() {
		view.getVerifyAndContinueToStep2Button().setEnabled(false);
		view.getEmailAddressTextField().getDocument().addDocumentListener(new DocumentAdapter() {
			@Override
			protected void update() {
				view.getVerifyAndContinueToStep2Button()
						.setEnabled(!view.getEmailAddressTextField().getText().isBlank());
			}
		});

		view.getContinueToStep3Button().setEnabled(false);
		DocumentListener passwordChangeListener = new DocumentAdapter() {
			@Override
			protected void update() {
				view.getContinueToStep3Button().setEnabled(view.getPasswordField().getPassword().length == view
						.getConfirmPasswordField().getPassword().length);
			}
		};
		view.getPasswordField().getDocument().addDocumentListener(passwordChangeListener);
		view.getConfirmPasswordField().getDocument().addDocumentListener(passwordChangeListener);
	}

	private void initCloseHandler() {
		view.getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(view.getFrame(),
						"Closing the application now will result in an incomplete profile being created.\nAre you sure you wish to close the application?");
				if (result == 0) {
					view.getFrame().dispose();
				}
			}
		});
	}

	private void continueToStep(JPanel step) {
		JPanel container = view.getStepsContainerPanel();
		container.removeAll();
		container.add(step);
		container.revalidate();
		container.repaint();
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(view.getFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void showInfo(String message) {
		JOptionPane.showMessageDialog(view.getFrame(), message);
	}

	private String breakTextIntoLines(String text) {
		return "<html>" + String.join("<br>", text.split(","));
	}
}
