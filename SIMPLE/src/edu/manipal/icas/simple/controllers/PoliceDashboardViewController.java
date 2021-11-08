package edu.manipal.icas.simple.controllers;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import edu.manipal.icas.simple.models.PoliceOfficer;
import edu.manipal.icas.simple.models.application.Application;
import edu.manipal.icas.simple.session.PoliceOfficerSession;
import edu.manipal.icas.simple.session.Session;
import edu.manipal.icas.simple.views.PoliceDashboardView;
import edu.manipal.icas.simple.views.View;
public class PoliceDashboardViewController {

	private PoliceDashboardView poView ;
	private Session session;
	private Application application;
	private PoliceOfficer  policeOfficer;


	public PoliceDashboardViewController(PoliceDashboardView poView)
	{
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
//		TODO Fetching of applications needs to be implemented in PoliceOfficer Model firstly
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

	private void showPendingApplications(Application application)

	{

//		TODO @Vishwas please Populate the JTable
	}





}
