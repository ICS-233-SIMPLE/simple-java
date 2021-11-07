package edu.manipal.icas.simple.impl.views;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.views.CitizenDashboardView;
import net.miginfocom.swing.MigLayout;

/**
 * Concrete class that defines the citizen dashboard view.
 *
 * @author Mehsheed (syed.ahmed2@learner.manipal.edu)
 *
 */
public class CitizenDashboardViewImpl extends JFrame implements CitizenDashboardView {

	private JButton applyForNewPaspportButton;
	private JButton redirectToAppstepsButton;
	private JButton logOutButton;

	private JComboBox<Integer> applicationCombo;
	private JLabel applicationStatus;
	private JLabel emailAddressTextField;

	private JTable viewApplicationTable;

	public CitizenDashboardViewImpl() {
		applyForNewPaspportButton = new JButton("Apply for New/Re-issue of Passport");
		redirectToAppstepsButton = new JButton("Click here");
		logOutButton = new JButton("Logout");
		applicationCombo = new JComboBox<Integer>();
		initialiseUI();

	}

	private void initialiseUI() {

		// JLabels:
		JLabel ctDB = new JLabel("Citizen Dashboard");
		ctDB.setFont(ResourceConstants.FONT_HEADING_BOLD);
		JLabel appLab = new JLabel("Application Panel");
		appLab.setFont(ResourceConstants.FONT_HEADING_BOLD);
		JLabel faq = new JLabel("To check application process/FAQ: ");
		faq.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		JTabbedPane tp = new JTabbedPane(JTabbedPane.TOP);
		JLabel checkApply = new JLabel("Select your Application Id.no:");
		checkApply.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		JLabel tLab = new JLabel("Track application status panel");
		tLab.setFont(ResourceConstants.FONT_HEADING_BOLD);
		JLabel supportLab = new JLabel("Support Panel");
		supportLab.setFont(ResourceConstants.FONT_HEADING_BOLD);
		JLabel supportClab = new JLabel(
				"For any information and suggestions on Passport services, please call at 1800-258-1800 (Toll Free)");
		JLabel supportClickLab = new JLabel("To check application process/FAQ: ");
		JLabel status = new JLabel("Application Status: ");
		applicationStatus = new JLabel("Initiated");
		status.setFont(ResourceConstants.FONT_HEADING_BOLD);
		applicationStatus.setFont(ResourceConstants.FONT_HEADING_BOLD);
		emailAddressTextField = new JLabel();

		// JPanels
		JPanel appNew = new JPanel();
		JPanel support = new JPanel();
		JPanel tcp = new JPanel();

		// JTabs
		tp.addTab("Track/View Application", tcp);
		tp.addTab("Support", support);

		// JTable
		viewApplicationTable = new JTable(new DefaultTableModel(new Object[] { "Field", "Response" }, 0));
		viewApplicationTable.setDefaultEditor(Object.class, null);

		// JScrollpane
		JScrollPane apptab = new JScrollPane(viewApplicationTable);

		// Track application Tab Layout
		tcp.setLayout(new MigLayout("", "[] [grow] [grow]", "[] [] [] [grow]"));
		tcp.add(tLab, "span,center,wrap");
		tcp.add(checkApply, "growx,split");
		tcp.add(applicationCombo, "wrap");
		tcp.add(status, "split");
		tcp.add(applicationStatus, "wrap");
		tcp.add(apptab);
		tcp.add(apptab, "growx,growy,span");

		// Apply Tab Layout
		appNew.setLayout(new MigLayout("", "[] 10 [grow] [grow] [grow]", "[] [] [] 100 [] 100 [] "));
		appNew.add(appLab, "span,center,wrap");

		appNew.add(applyForNewPaspportButton, "sg 1,span,center");

		// Support Tab Layout
		support.setLayout(new MigLayout("", "[] [] [] [grow]", "[] [] [] 10 [] 10 []"));
		support.add(supportLab, "span,center,wrap");
		support.add(supportClab, "span,left,wrap");
		support.add(supportClickLab, "split");
		support.add(redirectToAppstepsButton, "wrap");

		// Frame Layout
		setLayout(new MigLayout("", "[grow][grow][]", "[][grow]"));
		add(applyForNewPaspportButton, "left");
		add(emailAddressTextField, "right");
		add(logOutButton, "right,wrap");
		setIconImage((ResourceConstants.IMAGE_CITIZEN_DASHBOARD_ICON).getImage());
		add(tp, "growx,growy,span");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

	@Override
	public JFrame getFrame() {
		return this;
	}

	@Override
	public JButton getApplicationForNewPassportButton() {
		return applyForNewPaspportButton;
	}

	@Override
	public JButton getRedirectToAppStepsButton() {
		return redirectToAppstepsButton;
	}

	@Override
	public JButton getLogoutButton() {
		return logOutButton;
	}

	@Override
	public JTable getApplicationTable() {
		return viewApplicationTable;
	}

	@Override
	public JComboBox<Integer> getApplicationIdComboBox() {
		return applicationCombo;
	}

	@Override
	public JLabel getApplicationStatus() {
		return applicationStatus;

	}

	@Override
	public JLabel getEmailAddressTextField() {
		return emailAddressTextField;
	}

}
