package edu.manipal.icas.simple.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateVetoPolicy;

import edu.manipal.icas.simple.models.Citizen;
import edu.manipal.icas.simple.models.Document;
import edu.manipal.icas.simple.models.application.AcceptedDocumentType;
import edu.manipal.icas.simple.models.application.Application;
import edu.manipal.icas.simple.models.application.ApplicationFactory;
import edu.manipal.icas.simple.models.application.ApplicationQuestion;
import edu.manipal.icas.simple.models.application.ApplicationType;
import edu.manipal.icas.simple.session.CitizenSession;
import edu.manipal.icas.simple.views.ApplicationFormView;
import edu.manipal.icas.simple.views.View;

public class ApplyForPassportController {
	private ApplicationFormView view;
	private Application application;
	private Map<AcceptedDocumentType, String> documentPaths;
	private Citizen emergencyContact;

	public ApplyForPassportController(ApplicationFormView view) {
		this.view = view;

		initSubmitButtonHandler();
		initDocumentUploadButtonsHandlers();
		initFindCitizenHandler();

		view.getEmergencyNameTextField().setEnabled(false);
		view.getEmergencyPhoneTextField().setEnabled(false);

		view.getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				application = null;
				emergencyContact = null;
				initButtons();
				documentPaths = new HashMap<AcceptedDocumentType, String>();
				initAppointmentDatePicker();
			}
		});
	}

	public View getApplicationFormView() {
		return view;
	}

	public void initButtons() {
		view.getBookSlotButton().setEnabled(false);
		view.getPayButton().setEnabled(false);

		view.getBookSlotButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Find better conversion strategy between LocalDate and Date
				application.setDateOfAppointment(
						new Date(view.getAppointmentDatePicker().getDate().toEpochDay() * 24 * 60 * 60 * 1000));
				application.advanceApplicationStatus();
				showInfo("Slot booked!");
			}
		});
		
		view.getPayButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				application.getPayment().fulfil();
				application.advanceApplicationStatus();
				showInfo("Payment successful.\nApplication successfully submitted!");
				RouteController.getController().routeTo(Route.CITIZEN_DASHBOARD);
			}
		});
	}

	private void initSubmitButtonHandler() {
		view.getSubmitButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				if (view.getApplicationTypeButtonGroup().getSelection() == null) {
					showError("Select an application type first");
					return;
				}

				if (formFieldsEmpty()) {
					showError("Fill all form fields before continuing");
					return;
				}

				if (questionsUnanswered()) {
					showError("Answer all yes/no questions before continuing");
					return;
				}

				if (view.getEmergencyNameTextField().getText().trim().isEmpty()) {
					showError("Select a valid emergency contact for the application");
					return;
				}

				if (!view.getSelfDeclarationCheckBox().isSelected()) {
					showError("Sign the self-declaration by selecting the checkbox!");
					return;
				}

				if (application == null) {
					int selection = view.getApplicationTypeButtonGroup().getSelection().getMnemonic();
					ApplicationType type = (selection == 0 ? ApplicationType.FRESH : ApplicationType.RE_ISSUE);
					application = ApplicationFactory.getInstance().getApplication(type);
				}
				CitizenSession session = (CitizenSession) SessionController.getController().getCurrentSession();
				application.setApplicant(session.getCitizen());
				view.getPayerNameLabel().setText(application.getApplicant().getName());
				view.getPaymentAmountLabel().setText("Rs." + Application.APPLICATION_PAYMENT_AMOUNT);
				
				try {
					application.setEmergencyContact(emergencyContact);
				} catch (IllegalArgumentException e) {
					showError("Applicant cannot be the same as emergency contact!");
					return;
				}

				application.setBirthAddress(view.getPlaceOfBirthTextField().getText().trim());
				application.setPermanentAddress(view.getPermanentAddressTextField().getText().trim());
				application.setPresentAddress(view.getPresentAddressTextField().getText().trim());
				application.setNameOfFather(view.getFathersNameTextField().getText().trim());
				application.setNameOfMother(view.getMothersNameTextField().getText().trim());

				for (ApplicationQuestion question : view.getApplicationQuestionButtonGroups().keySet()) {
					int qSelection = view.getApplicationQuestionButtonGroups().get(question).getSelection()
							.getMnemonic();
					application.setAnswerForQuestion(question, qSelection == 1);
				}

				for (AcceptedDocumentType documentType : documentPaths.keySet()) {
					application.uploadDocument(documentType, new Document(
							application.getApplicationId() + documentType.toString() + System.currentTimeMillis(),
							documentPaths.get(documentType)));
				}
				documentPaths.clear();

				if (!application.hasRequiredDocuments()) {
					showError("Please upload all required documents!");
					return;
				}

				application.advanceApplicationStatus();

				view.getSubmitButton().setText("Application saved");
				view.getSubmitButton().setEnabled(false);
				view.getBookSlotButton().setEnabled(true);
				view.getPayButton().setEnabled(true);
			}
		});
	}

	private void initDocumentUploadButtonsHandlers() {
		for (AcceptedDocumentType type : view.getDocumentUploadButtons().keySet()) {
			JButton button = view.getDocumentUploadButtons().get(type);
			button.addActionListener(new DocumentUploadListener(type, button));
		}
	}

	private void initFindCitizenHandler() {
		view.getFindCitizenButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				try {
					String email = view.getEmergencyEmailTextField().getText().trim();
					Citizen.authenticate(email, "");
					emergencyContact = new Citizen(email);
					view.getEmergencyNameTextField().setText(emergencyContact.getName());
					view.getEmergencyPhoneTextField().setText(emergencyContact.getContactNumber() + "");
				} catch (Exception e) {
					showError("Emergency contact citizen not found!");
				}
			}
		});
	}

	private void initAppointmentDatePicker() {
		DatePickerSettings dateSettings = view.getAppointmentDatePicker().getSettings();
		dateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
		dateSettings.setVetoPolicy(new DateVetoPolicy() {

			@SuppressWarnings("deprecation")
			@Override
			public boolean isDateAllowed(LocalDate requestedSlot) {
				if (requestedSlot.isBefore(LocalDate.now()))
					return false;
				CitizenSession session = (CitizenSession) SessionController.getController().getCurrentSession();
				for (Date slot : session.getCitizen().getPassportOffice().getAvailableSlots()) {
					if (requestedSlot.equals(LocalDate.of(slot.getYear() + 1900, slot.getMonth() + 1, slot.getDate())))
						return true;
				}
				return false;
			}
		});
	}

	private boolean formFieldsEmpty() {
		JTextComponent[] fields = new JTextComponent[] { view.getPlaceOfBirthTextField(),
				view.getMothersNameTextField(), view.getFathersNameTextField(), view.getEmergencyEmailTextField(),
				view.getPermanentAddressTextField(), view.getPresentAddressTextField() };
		for (JTextComponent field : fields) {
			if (field.getText().trim().isEmpty()) {
				return true;
			}
		}
		return false;
	}

	private boolean questionsUnanswered() {
		for (ButtonGroup buttonGroup : view.getApplicationQuestionButtonGroups().values()) {
			if (buttonGroup.getSelection() == null)
				return true;
		}
		return false;
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(view.getFrame(), message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void showInfo(String message) {
		JOptionPane.showMessageDialog(view.getFrame(), message);
	}

	private class DocumentUploadListener implements ActionListener {
		AcceptedDocumentType type;
		JButton button;

		DocumentUploadListener(AcceptedDocumentType type, JButton button) {
			this.type = type;
			this.button = button;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
			fileChooser.showOpenDialog(null);
			if (fileChooser.getSelectedFile() == null)
				return;
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			documentPaths.put(type, path);
			showInfo("Document \"" + path + "\" selected");
			button.setText("Document uploaded");
		}

	}
}
