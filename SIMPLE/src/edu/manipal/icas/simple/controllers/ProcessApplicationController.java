package edu.manipal.icas.simple.controllers;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import edu.manipal.icas.simple.models.BiometricsOfficer;
import edu.manipal.icas.simple.models.Document;
import edu.manipal.icas.simple.models.PassportGrantingOfficer;
import edu.manipal.icas.simple.models.VerificationOfficer;
import edu.manipal.icas.simple.models.application.AcceptedBiometricType;
import edu.manipal.icas.simple.models.application.AcceptedDocumentType;
import edu.manipal.icas.simple.models.application.Application;
import edu.manipal.icas.simple.models.application.ApplicationQuestion;
import edu.manipal.icas.simple.models.application.ApplicationStatus;
import edu.manipal.icas.simple.session.BiometricsOfficerSession;
import edu.manipal.icas.simple.session.PassportGrantingOfficerSession;
import edu.manipal.icas.simple.session.Session;
import edu.manipal.icas.simple.session.VerificationOfficerSession;
import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.utils.StringConstants;
import edu.manipal.icas.simple.views.BiometricOfficerView;
import edu.manipal.icas.simple.views.PassportGrantingOfficerDashboardView;
import edu.manipal.icas.simple.views.VerificationOfficerDashboardView;
import edu.manipal.icas.simple.views.View;

public class ProcessApplicationController {
	private BiometricOfficerView bioView;
	private VerificationOfficerDashboardView verifView;
	private PassportGrantingOfficerDashboardView pgoView;

	private Session session;
	private Application application;

	private BiometricsOfficer bioOfficer;
	private VerificationOfficer verifOfficer;
	private PassportGrantingOfficer pgOfficer;

	public ProcessApplicationController(BiometricOfficerView bioView, VerificationOfficerDashboardView verifView,
			PassportGrantingOfficerDashboardView pgoView) {
		this.bioView = bioView;
		this.verifView = verifView;
		this.pgoView = pgoView;

		application = null;
		bioView.getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				session = SessionController.getController().getCurrentSession();
				bioOfficer = ((BiometricsOfficerSession) session).getOfficer();
				initBiometricOfficerFields();
			}
		});

		verifView.getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				session = SessionController.getController().getCurrentSession();
				verifOfficer = ((VerificationOfficerSession) session).getOfficer();
				initVerificationOfficerFields();
			}
		});

		pgoView.getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				session = SessionController.getController().getCurrentSession();
				pgOfficer = ((PassportGrantingOfficerSession) session).getOfficer();
				initGrantingOfficerFields();
			}
		});

		initLogOutHandlers();
		initAppplicationNavigationButtons();
	}

	public View getBiometricOfficerView() {
		return bioView;
	}

	public View getVerificationOfficerView() {
		return verifView;
	}

	public View getPassportGrantingOfficerView() {
		return pgoView;
	}

	private void initBiometricOfficerFields() {
		final List<Application> applications = bioOfficer.getApplications();
		bioView.getOfficerIdButton().setText("Officer ID: " + bioOfficer.getOfficerId());
		bioView.getApplicationIdComboBox().removeAllItems();
		for (Application application : applications) {
			bioView.getApplicationIdComboBox().addItem(application.getApplicationId());
		}

		bioView.getApplicationIdComboBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				application = applications.get(bioView.getApplicationIdComboBox().getSelectedIndex());
				boolean editable = application.getStatus() == ApplicationStatus.PAYMENT_COMPLETE;
				bioView.getScanButton().setEnabled(editable);
				bioView.getSubmitScanButton().setEnabled(editable);
				bioView.getBiometricTypeComboBox().setSelectedIndex(0);
			}
		});
		
		try {
			bioView.getApplicationIdComboBox().setSelectedIndex(0);
		} catch (Exception e) {
			// officer has no applications to process
		}

		bioView.getScanButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				bioOfficer.processApplication(application);
			}
		});

		bioView.getSubmitScanButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				application.advanceApplicationStatus();
				bioView.getScanButton().setEnabled(false);
				bioView.getSubmitScanButton().setEnabled(false);
			}
		});

		bioView.getBiometricTypeComboBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AcceptedBiometricType selectedType = StringConstants.ACCEPTED_BIOMETRIC_TYPE_NAMES
						.get(bioView.getBiometricTypeComboBox().getSelectedItem());
				Document document = application.getBiometric(selectedType);
				if (document != null)
					bioView.getPdfController().openDocument(document.getPath());
			}
		});
		bioView.getBiometricTypeComboBox().setSelectedIndex(0);
	}

	private void initVerificationOfficerFields() {
		final List<Application> applications = verifOfficer.getApplications();
		verifView.getOfficerIdLabel().setText(verifOfficer.getOfficerId() + "");
		verifView.getApplicationIdComboBox().removeAllItems();
		for (Application application : applications) {
			verifView.getApplicationIdComboBox().addItem(application.getApplicationId());
		}

		verifView.getApplicationIdComboBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				application = applications.get(verifView.getApplicationIdComboBox().getSelectedIndex());
				boolean editable = application.getStatus() == ApplicationStatus.CAPTURING_BIOMETRICS;
				verifView.getMarkAsInauthenticButton().setEnabled(editable);
				verifView.getMarkAsVerifiedButton().setEnabled(editable);

				DefaultTableModel model = (DefaultTableModel) verifView.getDocumentsListTable().getModel();
				for (int i = model.getRowCount() - 1; i >= 0; --i) {
					model.removeRow(i);
				}

				for (AcceptedDocumentType documentType : application.getDocuments().keySet()) {
					model.addRow(new Object[] { StringConstants.ACCEPTED_DOCUMENT_TYPE_NAMES.get(documentType),
							"Authentic" });
				}

				verifView.getDocumentsListTable().addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int row = verifView.getDocumentsListTable().rowAtPoint(e.getPoint());
						verifView.getPdfController().openDocument(
								application.getDocuments().values().toArray(new Document[0])[row].getPath());
					}
				});

			}
		});

		try {
			verifView.getApplicationIdComboBox().setSelectedIndex(0);
		} catch (Exception e) {
			// officer has no applications to process
		}

		verifView.getMarkAsVerifiedButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				verifOfficer.processApplication(application);
				application.setDocumentsAuthentic(true);
				verifView.getMarkAsInauthenticButton().setEnabled(false);
				verifView.getMarkAsVerifiedButton().setEnabled(false);
			}
		});
		verifView.getMarkAsInauthenticButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				verifOfficer.processApplication(application);
				application.setDocumentsAuthentic(false);
				verifView.getMarkAsInauthenticButton().setEnabled(false);
				verifView.getMarkAsVerifiedButton().setEnabled(false);
			}
		});
	}

	private void initGrantingOfficerFields() {
		final List<Application> applications = pgOfficer.getApplications();
		pgoView.getOfficerIdLabel().setText(pgOfficer.getOfficerId() + "");
		pgoView.getApplicationIdComboBox().removeAllItems();
		for (Application application : applications) {
			pgoView.getApplicationIdComboBox().addItem(application.getApplicationId());
		}
		
		pgoView.getApproveButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pgOfficer.processApplication(application, true);
				pgoView.getRequestPoliceVerificationButton().setEnabled(true);
			}
		});
		
		pgoView.getRejectButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pgOfficer.processApplication(application, false);
			}
		});
		
		pgoView.getRequestPoliceVerificationButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pgOfficer.requestAddressVerification(application);
			}
		});

		pgoView.getApplicationIdComboBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				application = applications.get(pgoView.getApplicationIdComboBox().getSelectedIndex());
				boolean editable = application.getStatus() == ApplicationStatus.UNDER_VERIFICATION;
				pgoView.getApproveButton().setEnabled(editable);
				pgoView.getRejectButton().setEnabled(editable);
				pgoView.getRequestPoliceVerificationButton().setEnabled(false);

				pgoView.getNameLabel().setText(application.getApplicant().getName());
				pgoView.getGenderLabel().setText(application.getApplicant().getGender());

				SimpleDateFormat formatter = new SimpleDateFormat("DD/MM/YYYY");
				pgoView.getDateOfBirthLabel().setText(formatter.format(application.getApplicant().getDateOfBirth()));
				pgoView.getEmailAddressLabel().setText(application.getApplicant().getEmailAddress());
				pgoView.getContactNumberLabel().setText(application.getApplicant().getContactNumber() + "");

				pgoView.getBirthAddressLabel().setText(application.getBirthAddress());
				pgoView.getPermanentAddressLabel().setText(application.getPermanentAddress());
				pgoView.getPresentAddressLabel().setText(application.getPresentAddress());
				pgoView.getNameOfMotherLabel().setText(application.getNameOfMother());
				pgoView.getNameOfFatherLabel().setText(application.getNameOfFather());

				pgoView.getQuestionsPanel().removeAll();
				GridBagConstraints c = new GridBagConstraints();
				c.gridx = 0;
				c.gridy = 0;
				c.fill = GridBagConstraints.HORIZONTAL;
				c.weightx = 1;
				c.weighty = 1;
				for (ApplicationQuestion question : ApplicationQuestion.values()) {
					c.insets = new Insets(0, 0, 0, 0);
					JLabel questionLabel = new JLabel(StringConstants.APPLICATION_QUESTIONS.get(question));
					questionLabel.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
					pgoView.getQuestionsPanel().add(questionLabel, c);
					c.gridy++;

					c.insets = new Insets(0, 0, 20, 0);
					JLabel response = new JLabel(application.getAnswerForQuestion(question) ? "Yes" : "No");
					response.setFont(ResourceConstants.FONT_LABEL_BOLD);
					pgoView.getQuestionsPanel().add(response, c);
					c.gridy++;
				}
				pgoView.getQuestionsPanel().revalidate();
				pgoView.getQuestionsPanel().repaint();

				pgoView.getDocumentTypeComboBox().removeAllItems();
				for (AcceptedDocumentType documentType : application.getDocuments().keySet()) {
					pgoView.getDocumentTypeComboBox()
							.addItem(StringConstants.ACCEPTED_DOCUMENT_TYPE_NAMES.get(documentType));
				}

				pgoView.getDocumentTypeComboBox().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int index = pgoView.getDocumentTypeComboBox().getSelectedIndex();
						if (index != -1)
							pgoView.getPdfDocumentsController().openDocument(
									application.getDocuments().values().toArray(new Document[0])[index].getPath());
					}
				});
				try {
					pgoView.getDocumentTypeComboBox().setSelectedIndex(0);
				} catch (IllegalArgumentException ex) {
					showError("No documents in this application!");
				}

				pgoView.getBiometricTypeComboBox().removeAllItems();
				for (AcceptedBiometricType biometricType : application.getBiometrics().keySet()) {
					pgoView.getBiometricTypeComboBox().addItem(getBiometricTypeString(biometricType));
				}

				pgoView.getBiometricTypeComboBox().addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int index = pgoView.getBiometricTypeComboBox().getSelectedIndex();
						if (index != -1)
							pgoView.getPdfBiometricsController().openDocument(
									application.getBiometrics().values().toArray(new Document[0])[index].getPath());
					}
				});
				try {
					pgoView.getBiometricTypeComboBox().setSelectedIndex(0);
				} catch (IllegalArgumentException ex) {
					showError("No b in this application!");
				}
			}
		});
		
		try {
			pgoView.getApplicationIdComboBox().setSelectedIndex(0);
		} catch (Exception e) {
			// officer has no applications to process
		}
	}

	private void initLogOutHandlers() {
		ActionListener logOutListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SessionController.getController().endCurrentSession();
				RouteController.getController().routeTo(Route.OFFICER_LOGIN);
			}
		};
		bioView.getLogoutButton().addActionListener(logOutListener);
		verifView.getLogoutButton().addActionListener(logOutListener);
		pgoView.getLogoutButton().addActionListener(logOutListener);
	}

	private void initAppplicationNavigationButtons() {
		bioView.getNextAppIdButton()
				.addActionListener(new NavigationListener(bioView.getApplicationIdComboBox(), true));
		bioView.getPrevAppIdButton()
				.addActionListener(new NavigationListener(bioView.getApplicationIdComboBox(), false));

		verifView.getNextApplicationIdButton()
				.addActionListener(new NavigationListener(verifView.getApplicationIdComboBox(), true));
		verifView.getPreviousApplicationIdButton()
				.addActionListener(new NavigationListener(verifView.getApplicationIdComboBox(), false));

		pgoView.getNextApplicationIdButton()
				.addActionListener(new NavigationListener(pgoView.getApplicationIdComboBox(), true));
		pgoView.getPreviousApplicationIdButton()
				.addActionListener(new NavigationListener(pgoView.getApplicationIdComboBox(), false));
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private String getBiometricTypeString(AcceptedBiometricType type) {
		for (String string : StringConstants.ACCEPTED_BIOMETRIC_TYPE_NAMES.keySet()) {
			if (StringConstants.ACCEPTED_BIOMETRIC_TYPE_NAMES.get(string) == type)
				return string;
		}
		return "";
	}

	private class NavigationListener implements ActionListener {
		private JComboBox<?> comboBox;
		private boolean isEventForNavigatingToNext;

		NavigationListener(JComboBox<?> comboBox, boolean flag) {
			this.comboBox = comboBox;
			isEventForNavigatingToNext = flag;
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			int newIndex = comboBox.getSelectedIndex() + (isEventForNavigatingToNext ? 1 : -1);
			if (newIndex < 0 || newIndex >= comboBox.getItemCount()) {
				showError("You've reached the end of the list!");
				return;
			}
			comboBox.setSelectedIndex(newIndex);
		}

	}
}
