package edu.manipal.icas.simple.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import edu.manipal.icas.simple.models.BiometricsOfficer;
import edu.manipal.icas.simple.models.Document;
import edu.manipal.icas.simple.models.application.AcceptedBiometricType;
import edu.manipal.icas.simple.models.application.Application;
import edu.manipal.icas.simple.models.application.ApplicationStatus;
import edu.manipal.icas.simple.session.BiometricsOfficerSession;
import edu.manipal.icas.simple.session.Session;
import edu.manipal.icas.simple.utils.StringConstants;
import edu.manipal.icas.simple.views.BiometricOfficerView;
import edu.manipal.icas.simple.views.View;

public class ProcessApplicationController {
	private BiometricOfficerView bioView;
	private Session session;
	private Application application;
	private BiometricsOfficer bioOfficer;

	public ProcessApplicationController(BiometricOfficerView bioView) {
		this.bioView = bioView;
		application = null;
		session = SessionController.getController().getCurrentSession();
		bioView.getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				bioOfficer = ((BiometricsOfficerSession) session).getOfficer();
				initBiometricOfficerFields();
			}
		});
		initLogOutHandlers();
		initAppplicationNavigationButtons();
	}

	public View getBiometricOfficerView() {
		return bioView;
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
		bioView.getApplicationIdComboBox().setSelectedIndex(0);

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

	private void initLogOutHandlers() {
		ActionListener logOutListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SessionController.getController().endCurrentSession();
				RouteController.getController().routeTo(Route.OFFICER_LOGIN);
			}
		};
		bioView.getLogoutButton().addActionListener(logOutListener);
	}

	private void initAppplicationNavigationButtons() {
		bioView.getNextAppIdButton()
				.addActionListener(new NavigationListener(bioView.getApplicationIdComboBox(), true));
		bioView.getPrevAppIdButton()
				.addActionListener(new NavigationListener(bioView.getApplicationIdComboBox(), false));
	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void showInfo(String message) {
		JOptionPane.showMessageDialog(null, message);
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
