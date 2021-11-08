package edu.manipal.icas.simple.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.util.PropertiesManager;

import edu.manipal.icas.simple.models.PoliceOfficer;
import edu.manipal.icas.simple.models.application.AcceptedDocumentType;
import edu.manipal.icas.simple.models.application.Application;
import edu.manipal.icas.simple.models.application.ApplicationStatus;
import edu.manipal.icas.simple.session.PoliceOfficerSession;
import edu.manipal.icas.simple.session.Session;
import edu.manipal.icas.simple.views.PoliceDashboardView;
import edu.manipal.icas.simple.views.View;

public class PoliceDashboardViewController {

	private PoliceDashboardView poView;
	private Session session;
	private Application application;
	private PoliceOfficer policeOfficer;

	public PoliceDashboardViewController(PoliceDashboardView poView) {
		this.poView = poView;
		application = null;

		poView.getFrame().addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				session = SessionController.getController().getCurrentSession();
				policeOfficer = ((PoliceOfficerSession) session).getOfficer();
				initPoliceOfficerFields();
			}
		});

		initLogOutHandlers();
		initAppplicationNavigationButtons();

	}

	private void initPoliceOfficerFields() {
		final List<Application> applications = policeOfficer.getApplications();
		poView.getOfficerIdLabel().setText("" + policeOfficer.getBadgeId());
		poView.getApplicationIdComboBox().removeAllItems();
		for (Application application : applications) {
			poView.getApplicationIdComboBox().addItem(application.getApplicationId());
		}

		poView.getApplicationIdComboBox().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				application = applications.get(poView.getApplicationIdComboBox().getSelectedIndex());
				boolean editable = application.getStatus() == ApplicationStatus.PENDING_ADDRESS_VERIFICATION;
				poView.getIncompleteButton().setEnabled(editable);
				poView.getAdverseButton().setEnabled(editable);
				poView.getClearButton().setEnabled(editable);
			}
		});

		try {
			poView.getApplicationIdComboBox().setSelectedIndex(0);
		} catch (Exception e) {
			// officer has no applications to process
		}

		DefaultTableModel model = (DefaultTableModel) poView.getPendingVerificationTable().getModel();
		for (int i = model.getRowCount() - 1; i >= 0; --i) {
			model.removeRow(i);
		}

		for (Application app : applications) {
			model.addRow(
					new Object[] { app.getApplicationId(), app.getApplicant().getName(), app.getPresentAddress() });
		}

		poView.getAdverseButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				policeOfficer.verifyAddress(application.getApplicant(),
						application.getDocument(AcceptedDocumentType.ADDRESS_PROOF));
				application.setStatus(ApplicationStatus.ADDRESS_VERIFICATION_ADVERSE);
				application.setStatus(ApplicationStatus.REJECTED);
				disableButtons();
			}
		});

		poView.getClearButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				policeOfficer.verifyAddress(application.getApplicant(),
						application.getDocument(AcceptedDocumentType.ADDRESS_PROOF));
				application.setStatus(ApplicationStatus.ADDRESS_VERIFICATION_CLEAR);
				application.setStatus(ApplicationStatus.COMPLETED_AND_SUCCESSFUL);
				disableButtons();
			}
		});

		poView.getIncompleteButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				policeOfficer.verifyAddress(application.getApplicant(),
						application.getDocument(AcceptedDocumentType.ADDRESS_PROOF));
				application.setStatus(ApplicationStatus.ADDRESS_VERIFICATION_INCOMPLETE);
				application.setStatus(ApplicationStatus.REJECTED);
				disableButtons();
			}
		});

		poView.getViewDocumentButton().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PropertiesManager properties = new PropertiesManager(System.getProperties(),
						ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
				properties.setBoolean("application.viewerpreferences.hidetoolbar", Boolean.TRUE);
				SwingController pdfController = new SwingController();
				pdfController.openDocument(application.getDocument(AcceptedDocumentType.ADDRESS_PROOF).getPath());
				
				SwingViewBuilder factory = new SwingViewBuilder(pdfController, properties);
				JFrame frame = new JFrame();
				frame.add(factory.buildViewerPanel());
				frame.setSize(1000, 1000);
				frame.setVisible(true);
			}
		});
	}

	public View getPoliceOfficerView() {
		return poView;
	}

	private void initLogOutHandlers() {
		ActionListener logOutListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SessionController.getController().endCurrentSession();
				RouteController.getController().routeTo(Route.OFFICER_LOGIN);
			}
		};
		poView.getLogOutButton().addActionListener(logOutListener);
	}

	private void initAppplicationNavigationButtons() {
		poView.getNextApplicationIdButton()
				.addActionListener(new NavigationListener(poView.getApplicationIdComboBox(), true));
		poView.getPreviousApplicationIdButton()
				.addActionListener(new NavigationListener(poView.getApplicationIdComboBox(), false));

	}

	private void showError(String message) {
		JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
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

	private void disableButtons() {
		poView.getAdverseButton().setEnabled(false);
		poView.getClearButton().setEnabled(false);
		poView.getIncompleteButton().setEnabled(false);
	}

}
