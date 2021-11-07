package edu.manipal.icas.simple.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

import edu.manipal.icas.simple.models.application.Application;
import edu.manipal.icas.simple.models.application.ApplicationQuestion;
import edu.manipal.icas.simple.session.CitizenSession;
import edu.manipal.icas.simple.utils.StringConstants;
import edu.manipal.icas.simple.views.CitizenDashboardView;
import edu.manipal.icas.simple.views.View;

public class ViewApplicationsController {
	private CitizenDashboardView view;
	private CitizenSession session;

	public ViewApplicationsController(CitizenDashboardView dashboardView) {
		this.view = dashboardView;
		initLogoutHandler();
		initNewApplicationHandler();
		initFaqHandler();

		dashboardView.getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				session = (CitizenSession) SessionController.getController().getCurrentSession();
				initCitizenDetailFields();
				initTrackApplicationFields();
			}
		});
	}

	public View getDashboardView() {
		return view;
	}

	private void initLogoutHandler() {
		view.getLogoutButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SessionController.getController().endCurrentSession();
				RouteController.getController().routeTo(Route.CITIZEN_LOGIN);
			}
		});
	}

	private void initNewApplicationHandler() {
		view.getApplicationForNewPassportButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RouteController.getController().routeTo(Route.APPLICATION_FORM);
			}
		});
	}

	private void initCitizenDetailFields() {
		view.getEmailAddressTextField().setText(session.getCitizen().getEmailAddress());
	}

	private void initTrackApplicationFields() {
		view.getApplicationIdComboBox().removeAllItems();
		for (Application application : session.getCitizen().getApplications()) {
			view.getApplicationIdComboBox().addItem(application.getApplicationId());
		}

		view.getApplicationStatus().setText("-");

		view.getApplicationIdComboBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer selection = (Integer) view.getApplicationIdComboBox().getSelectedItem();
				if (selection == null)
					return;

				for (Application application : session.getCitizen().getApplications()) {
					if (application.getApplicationId() == selection) {
						showTrackingForApplication(application);
						break;
					}
				}
			}
		});
	}

	private void initFaqHandler() {
		view.getRedirectToAppStepsButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					java.awt.Desktop.getDesktop().browse(java.net.URI.create("https://www.passportindia.gov.in/AppOnlineProject/online/faqMainPage"));
				} catch (IOException e1) {

					e1.printStackTrace();
				}
			}
		});
	}

	private void showTrackingForApplication(Application application) {
		view.getApplicationStatus().setText(StringConstants.APPLICATION_STATUS_STRINGS.get(application.getStatus()));
		DefaultTableModel model = (DefaultTableModel) view.getApplicationTable().getModel();

		for (int i = model.getRowCount() - 1; i >= 0; --i) {
			model.removeRow(i);
		}

		model.addRow(new Object[] { "Application ID", application.getApplicationId() });
		model.addRow(new Object[] { "Applicant's Name", application.getApplicant().getName() });
		model.addRow(new Object[] { "Application Created On", application.getDateCreated() });
		model.addRow(new Object[] { "Appointment Scheduled On", application.getDateOfAppointment() });
		model.addRow(new Object[] { "Application Type", application.getType() });
		model.addRow(new Object[] { "Birth Address", application.getBirthAddress() });
		model.addRow(new Object[] { "Permanent Address", application.getPermanentAddress() });
		model.addRow(new Object[] { "Present Address", application.getPresentAddress() });
		model.addRow(new Object[] { "Father's Name", application.getNameOfFather() });
		model.addRow(new Object[] { "Mother's Name", application.getNameOfMother() });

		for (ApplicationQuestion question : ApplicationQuestion.values()) {
			model.addRow(new Object[] { StringConstants.APPLICATION_QUESTIONS.get(question),
					application.getAnswerForQuestion(question) ? "Yes" : "No" });
		}

	}
}
