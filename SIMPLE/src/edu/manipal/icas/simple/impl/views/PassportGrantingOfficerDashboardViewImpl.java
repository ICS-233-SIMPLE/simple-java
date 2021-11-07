package edu.manipal.icas.simple.impl.views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.util.PropertiesManager;

import edu.manipal.icas.simple.utils.ImageUtils;
import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.views.PassportGrantingOfficerDashboardView;

/**
 * Concrete class that defines the passport granting officer view.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */

public class PassportGrantingOfficerDashboardViewImpl extends JFrame implements PassportGrantingOfficerDashboardView {
	private JTabbedPane tabbedPane;
	private JButton nextApplicationIdButton;
	private JButton previousApplicationIdButton;
	private JComboBox<Integer> applicationIdComboBox;
	private JLabel officerIdLabel;
	private JButton logoutButton;
	private JButton approveButton;
	private JPanel navigationBarPanel;
	private JButton rejectButton;
	private JPanel containerPanel;
	private JButton requestPoliceVerificationButton;
	private JLabel nameLabel;
	private JLabel genderLabel;
	private JLabel emailAddressLabel;
	private JLabel dateOfBirthLabel;
	private JLabel nameOfMotherLabel;
	private JLabel nameOfFatherLabel;
	private JLabel birthAddressLabel;
	private JLabel permanentAddressLabel;
	private JLabel presentAddressLabel;
	private JLabel contactNumberLabel;
	private SwingController pdfBiometricsController;
	private SwingController pdfDocumentsController;
	private JPanel questionsPanel;
	private JComboBox<String> documentTypeComboBox;
	private JComboBox<String> biometricTypeComboBox;

	/**
	 * Initialises all the required components that are to be displayed in the PGO
	 * dashboard view.
	 */
	public PassportGrantingOfficerDashboardViewImpl() {
		super("Passport Granting Officer");
		previousApplicationIdButton = new JButton(
				ImageUtils.getScaledImage(ResourceConstants.IMAGE_PREVIOUS_BUTTON_ICON, 15, 15));
		applicationIdComboBox = new JComboBox<Integer>();
		nextApplicationIdButton = new JButton(
				ImageUtils.getScaledImage(ResourceConstants.IMAGE_NEXT_BUTTON_ICON, 15, 15));
		officerIdLabel = new JLabel();
		logoutButton = new JButton("Logout");
		approveButton = new JButton("Approve");
		rejectButton = new JButton("Reject");
		tabbedPane = new JTabbedPane();
		navigationBarPanel = new JPanel();
		containerPanel = new JPanel();
		requestPoliceVerificationButton = new JButton("Request police verification");
		nameLabel = new JLabel();
		genderLabel = new JLabel();
		emailAddressLabel = new JLabel();
		dateOfBirthLabel = new JLabel();
		nameOfMotherLabel = new JLabel();
		nameOfFatherLabel = new JLabel();
		contactNumberLabel = new JLabel();
		birthAddressLabel = new JLabel();
		permanentAddressLabel = new JLabel();
		presentAddressLabel = new JLabel();
		pdfDocumentsController = new SwingController();
		pdfBiometricsController = new SwingController();
		documentTypeComboBox = new JComboBox<String>();
		biometricTypeComboBox = new JComboBox<String>();

		initialiseUi();
	}

	/**
	 * Initialises the passport granting officer dashboard view and populates it
	 * with the required panels.
	 */
	private void initialiseUi() {
		containerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weightx = 0.5;
		c.weighty = 0;
		c.gridx = 0;
		c.ipady = 10;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		containerPanel.add(initialiseNavigationBarPanel(), c);

		c.gridy = 1;
		c.weightx = 0.5;
		c.ipady = 0;
		c.gridwidth = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		containerPanel.add(initialiseTabbedPane(), c);

		ImageIcon scaledPassportGrantingIcon = ImageUtils.getScaledImage(ResourceConstants.IMAGE_PASSPORT_GRANTING_ICON,
				25, 25);
		setIconImage(scaledPassportGrantingIcon.getImage());
		containerPanel.setBackground(new Color(255, 255, 255));
		setExtendedState(MAXIMIZED_BOTH);
		add(containerPanel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	/**
	 * Initialises the navigation bar panel which enables the passport granting
	 * officer to review all the documents of a citizen based on their application
	 * ID, approve or reject the application, request police verification, and
	 * logout.
	 * 
	 * @return the navigation bar panel
	 */
	private JPanel initialiseNavigationBarPanel() {
		navigationBarPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 5;
		navigationBarPanel.add(new JLabel(), c);

		c.gridx = 1;
		c.weightx = 0;
		c.ipadx = 0;
		navigationBarPanel.add(previousApplicationIdButton, c);

		c.gridx = 2;
		navigationBarPanel.add(applicationIdComboBox, c);

		c.gridx = 3;
		navigationBarPanel.add(nextApplicationIdButton, c);

		c.gridx = 4;
		c.ipadx = 20;
		navigationBarPanel.add(new JLabel(), c);

		c.gridx = 5;
		c.ipadx = 0;
		rejectButton.setBackground(new Color(204, 0, 0));
		rejectButton.setForeground(Color.WHITE);
		navigationBarPanel.add(rejectButton, c);

		c.gridx = 6;
		approveButton.setBackground(new Color(0, 204, 0));
		approveButton.setForeground(Color.WHITE);
		navigationBarPanel.add(approveButton, c);

		c.gridx = 7;
		c.ipadx = 15;
		navigationBarPanel.add(new JLabel(), c);

		c.weightx = 1;
		c.gridx = 8;
		c.ipadx = 0;
		requestPoliceVerificationButton.setBackground(new Color(5, 44, 238));
		requestPoliceVerificationButton.setForeground(Color.WHITE);
		navigationBarPanel.add(requestPoliceVerificationButton, c);

		c.weightx = 0;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 9;
		navigationBarPanel.add(new JLabel("Officer ID: "), c);

		c.gridx = 10;
		navigationBarPanel.add(officerIdLabel, c);

		c.gridx = 11;
		c.ipadx = 5;
		navigationBarPanel.add(new JLabel(), c);

		c.gridx = 12;
		navigationBarPanel.add(logoutButton, c);

		c.gridx = 13;
		c.ipadx = 5;
		navigationBarPanel.add(new JLabel(), c);

		return navigationBarPanel;

	}

	/**
	 * Initialises the tabbed pane which has tabs for personal details, questions,
	 * documents, and biometrics.
	 * 
	 * @return the tabbed pane
	 */
	private JTabbedPane initialiseTabbedPane() {
		tabbedPane.addTab("Personal Details", initialisePersonalDetailsPanel());
		tabbedPane.addTab("Questions", initialiseQuestionsPanel());
		tabbedPane.addTab("Documents", initialiseDocumentsPanel());
		tabbedPane.addTab("Biometrics", initialiseBiometricsPanel());

		return tabbedPane;
	}

	/**
	 * Initialises hte personal details panel which displays the personal details of
	 * the citizen.
	 * 
	 * @return the personal details panel
	 */
	private JPanel initialisePersonalDetailsPanel() {
		JPanel personalDetailsPanel = new JPanel();
		GridBagConstraints c = new GridBagConstraints();
		personalDetailsPanel.setLayout(new GridBagLayout());
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 1;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.insets = new Insets(10, 10, 0, 0);
		c.anchor = GridBagConstraints.NORTHWEST;
		personalDetailsPanel.add(new JLabel("Name"), c);
		c.gridx = 2;
		c.insets = new Insets(10, 0, 0, 0);
		personalDetailsPanel.add(new JLabel(":"), c);
		c.gridx = 3;
		c.insets = new Insets(10, 0, 0, 1150);
		personalDetailsPanel.add(nameLabel, c);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Gender"), c);
		c.gridx = 2;
		c.insets = new Insets(10, 0, 0, 0);
		personalDetailsPanel.add(new JLabel(":"), c);
		c.gridx = 3;
		c.insets = new Insets(10, 0, 0, 1150);
		personalDetailsPanel.add(genderLabel, c);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Date Of Birth"), c);
		c.gridx = 2;
		c.insets = new Insets(10, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);
		c.gridx = 3;
		c.insets = new Insets(10, 0, 0, 1150);
		personalDetailsPanel.add(dateOfBirthLabel, c);

		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Email Address"), c);
		c.gridx = 2;
		c.insets = new Insets(10, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);
		c.gridx = 3;
		c.insets = new Insets(10, 0, 0, 1150);
		personalDetailsPanel.add(emailAddressLabel, c);

		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Contact Number"), c);
		c.gridx = 2;
		c.insets = new Insets(10, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);
		c.gridx = 3;
		c.insets = new Insets(10, 0, 0, 1150);
		personalDetailsPanel.add(contactNumberLabel, c);

		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Birth Address"), c);
		c.gridx = 2;
		c.insets = new Insets(10, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);
		c.gridx = 3;
		c.insets = new Insets(10, 0, 0, 1150);
		personalDetailsPanel.add(birthAddressLabel, c);

		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Permanent Address"), c);
		c.gridx = 2;
		c.insets = new Insets(10, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);
		c.gridx = 3;
		c.insets = new Insets(10, 0, 0, 1150);
		personalDetailsPanel.add(permanentAddressLabel, c);

		c.gridx = 1;
		c.gridy = 7;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Present Address"), c);
		c.gridx = 2;
		c.insets = new Insets(10, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);
		c.gridx = 3;
		c.insets = new Insets(10, 0, 0, 1150);
		personalDetailsPanel.add(presentAddressLabel, c);

		c.gridx = 1;
		c.gridy = 8;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Father's Name"), c);
		c.gridx = 2;
		c.insets = new Insets(10, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);
		c.gridx = 3;
		c.insets = new Insets(10, 0, 0, 1150);
		personalDetailsPanel.add(nameOfFatherLabel, c);

		c.gridx = 1;
		c.gridy = 9;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Mother's Name"), c);
		c.gridx = 2;
		c.insets = new Insets(10, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);
		c.gridx = 3;
		c.insets = new Insets(10, 0, 0, 1150);
		personalDetailsPanel.add(nameOfMotherLabel, c);

		personalDetailsPanel.setBackground(Color.WHITE);
		return personalDetailsPanel;
	}

	private JPanel initialiseQuestionsPanel() {
		questionsPanel = new JPanel();
		questionsPanel.setLayout(new GridBagLayout());
		return questionsPanel;
	}

	/**
	 * Initialises the documents panel which displays the documents of the citizen.
	 * 
	 * @return documents panel
	 */
	private JPanel initialiseDocumentsPanel() {
		JPanel documentsPanel = new JPanel();
		documentsPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		documentsPanel.add(documentTypeComboBox, c);
		c.weighty = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		PropertiesManager properties = new PropertiesManager(System.getProperties(),
				ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
		properties.setBoolean("application.viewerpreferences.hidetoolbar", Boolean.TRUE);
		SwingViewBuilder factory = new SwingViewBuilder(pdfDocumentsController, properties);
		JPanel documentPreviewPanel = factory.buildViewerPanel();
		documentsPanel.add(documentPreviewPanel, c);

		return documentsPanel;
	}

	/**
	 * Initialises the biometrics panel which displays the biometrics of the
	 * citizen.
	 * 
	 * @return biometrics panel
	 */
	private JPanel initialiseBiometricsPanel() {
		JPanel biometricsPanel = new JPanel();
		biometricsPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weighty = 0;
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.LINE_START;
		biometricsPanel.add(biometricTypeComboBox, c);

		c.weighty = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.BOTH;
		PropertiesManager properties = new PropertiesManager(System.getProperties(),
				ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
		properties.setBoolean("application.viewerpreferences.hidetoolbar", Boolean.TRUE);
		SwingViewBuilder factory = new SwingViewBuilder(pdfBiometricsController, properties);
		JPanel documentPreviewPanel = factory.buildViewerPanel();
		biometricsPanel.add(documentPreviewPanel, c);
		return biometricsPanel;
	}

	@Override
	public JFrame getFrame() {
		return this;
	}

	@Override
	public JComboBox<Integer> getApplicationIdComboBox() {
		return applicationIdComboBox;
	}

	@Override
	public JButton getNextApplicationIdButton() {
		return nextApplicationIdButton;
	}

	@Override
	public JButton getPreviousApplicationIdButton() {
		return previousApplicationIdButton;
	}

	@Override
	public JButton getApproveButton() {
		return approveButton;
	}

	@Override
	public JButton getRejectButton() {
		return rejectButton;
	}

	@Override
	public JButton getRequestPoliceVerificationButton() {
		return requestPoliceVerificationButton;
	}

	@Override
	public JLabel getOfficerIdLabel() {
		return officerIdLabel;
	}

	@Override
	public JButton getLogoutButton() {
		return logoutButton;
	}

	@Override
	public JLabel getNameLabel() {
		return nameLabel;
	}

	@Override
	public JLabel getGenderLabel() {
		return genderLabel;
	}

	@Override
	public JLabel getEmailAddressLabel() {
		return emailAddressLabel;
	}

	@Override
	public JLabel getNameOfFatherLabel() {
		return nameOfFatherLabel;
	}

	@Override
	public JLabel getNameOfMotherLabel() {
		return nameOfMotherLabel;
	}

	@Override
	public JLabel getContactNumberLabel() {
		return contactNumberLabel;
	}

	@Override
	public JLabel getDateOfBirthLabel() {
		return dateOfBirthLabel;
	}

	@Override
	public JLabel getBirthAddressLabel() {
		return birthAddressLabel;
	}

	@Override
	public JLabel getPermanentAddressLabel() {
		return permanentAddressLabel;
	}

	@Override
	public JLabel getPresentAddressLabel() {
		return presentAddressLabel;
	}

	@Override
	public SwingController getPdfBiometricsController() {
		return pdfBiometricsController;
	}

	@Override
	public SwingController getPdfDocumentsController() {
		return pdfDocumentsController;
	}

	@Override
	public JPanel getQuestionsPanel() {
		return questionsPanel;
	}

	@Override
	public JComboBox<String> getDocumentTypeComboBox() {
		return documentTypeComboBox;
	}

	@Override
	public JComboBox<String> getBiometricTypeComboBox() {
		return biometricTypeComboBox;
	}
}
