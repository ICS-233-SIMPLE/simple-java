package edu.manipal.icas.simple.impl.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.views.PassportGrantingOfficerDashboardView;

public class PassportGrantingOfficerDashboardViewImpl extends JFrame implements PassportGrantingOfficerDashboardView {
	private JTabbedPane tabbedPane;
	private JButton nextApplicationIdButton;
	private JButton previousApplicationIdButton;
	private JComboBox<String> applicationIdComboBox;
	private JLabel officerIdLabel;
	private JButton logoutButton;
	private JButton approveButton;
	private JPanel navigationBarPanel;
	private JButton rejectButton;
	private JPanel containerPanel;
	private JButton requestPoliceVerificationButton;

	public PassportGrantingOfficerDashboardViewImpl() {
		super("Passport Granting Officer");
		previousApplicationIdButton = new JButton(getScaledImage(ResourceConstants.IMAGE_PREVIOUS_BUTTON_ICON, 15, 15));
		String testData[] = { "000000", "000001" };
		applicationIdComboBox = new JComboBox<>(testData);
		nextApplicationIdButton = new JButton(getScaledImage(ResourceConstants.IMAGE_NEXT_BUTTON_ICON, 15, 15));
		officerIdLabel = new JLabel();
		logoutButton = new JButton("Logout");
		approveButton = new JButton("Approve");
		rejectButton = new JButton("Reject");
		tabbedPane = new JTabbedPane();
		navigationBarPanel = new JPanel();
		containerPanel = new JPanel();
		requestPoliceVerificationButton = new JButton("Request police verification");

		initialiseUi();
	}

	/**
	 * Gets the scaled version of the image icon passed to it.
	 * 
	 * @param icon   the image icon that is to be scaled to the desired size
	 * @param width  the width that the image should be scaled to
	 * @param height the height that the image should be scaled to
	 * @return the scaled image
	 */
	private ImageIcon getScaledImage(ImageIcon icon, int width, int height) {
		int newWidth = icon.getIconWidth();
		int newHeight = icon.getIconHeight();

		if (newWidth > width) {
			newWidth = width;
			newHeight = (newWidth * icon.getIconHeight()) / icon.getIconWidth();
		}

		if (newHeight > height) {
			newHeight = height;
			newWidth = (icon.getIconWidth() * newHeight) / icon.getIconHeight();
		}

		return new ImageIcon(icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT));
	}

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

		ImageIcon scaledPassportGrantingIcon = getScaledImage(ResourceConstants.IMAGE_PASSPORT_GRANTING_ICON, 25, 25);
		setIconImage(scaledPassportGrantingIcon.getImage());
		containerPanel.setBackground(new Color(255, 255, 255));
		setResizable(false);
		setExtendedState(MAXIMIZED_BOTH);
		add(containerPanel);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
	}

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

	private JTabbedPane initialiseTabbedPane() {
		tabbedPane.addTab("Personal Details", initialisePersonalDetailsPanel());
		tabbedPane.addTab("Questions", initialiseQuestionsPanel());
		tabbedPane.addTab("Documents", initialiseDocumentsPanel());
		tabbedPane.addTab("Biometrics", initialiseBiometricsPanel());

		return tabbedPane;
	}

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
		c.insets = new Insets(9, 0, 0, 1200);
		personalDetailsPanel.add(new JLabel(":"), c);

		c.gridx = 1;
		c.gridy = 1;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Gender"), c);
		c.gridx = 2;
		c.insets = new Insets(9, 0, 0, 1200);
		personalDetailsPanel.add(new JLabel(":"), c);

		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Date Of Birth"), c);
		c.gridx = 2;
		c.insets = new Insets(9, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);

		c.gridx = 1;
		c.gridy = 3;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Email Address"), c);
		c.gridx = 2;
		c.insets = new Insets(9, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);

		c.gridx = 1;
		c.gridy = 4;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Contact Number"), c);
		c.gridx = 2;
		c.insets = new Insets(9, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);

		c.gridx = 1;
		c.gridy = 5;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Birth Address"), c);
		c.gridx = 2;
		c.insets = new Insets(9, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);

		c.gridx = 1;
		c.gridy = 6;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Permanent Address"), c);
		c.gridx = 2;
		c.insets = new Insets(9, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);

		c.gridx = 1;
		c.gridy = 7;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Present Address"), c);
		c.gridx = 2;
		c.insets = new Insets(9, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);

		c.gridx = 1;
		c.gridy = 8;
		c.weightx = 0;
		c.weighty = 0;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Father's Name"), c);
		c.gridx = 2;
		c.insets = new Insets(9, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);

		c.gridx = 1;
		c.gridy = 9;
		c.weightx = 1;
		c.weighty = 1;
		c.insets = new Insets(10, 10, 0, 0);
		personalDetailsPanel.add(new JLabel("Mother's Name"), c);
		c.gridx = 2;
		c.insets = new Insets(9, 0, 0, 10);
		personalDetailsPanel.add(new JLabel(":"), c);

		personalDetailsPanel.setBackground(Color.WHITE);
		return personalDetailsPanel;
	}

	private JPanel initialiseQuestionsPanel() {
		JPanel questionsPanel = new JPanel();

		return questionsPanel;
	}

	private JPanel initialiseDocumentsPanel() {
		JPanel documentsContainerPanel = new JPanel();
		documentsContainerPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 1;
		c.weightx = 1;
		c.ipady = 0;
		c.gridwidth = 4;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.VERTICAL;
		documentsContainerPanel.add(createDocumentNamePanel(), c);
		return documentsContainerPanel;
	}

	private JPanel createDocumentNamePanel() {
		JPanel documentNamePanel = new JPanel();
		documentNamePanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 200;
		c.anchor = GridBagConstraints.NORTHWEST;
		c.insets = new Insets(10, 10, 0, 0);
		c.gridx = 1;

		JRadioButton panCardRadioButton = new JRadioButton("PAN Card");
		panCardRadioButton.setForeground(Color.WHITE);
		panCardRadioButton.setBackground(new Color(107, 107, 107));

		JRadioButton driversLicenseRadioButton = new JRadioButton("Driving License");
		driversLicenseRadioButton.setForeground(Color.WHITE);
		driversLicenseRadioButton.setBackground(new Color(107, 107, 107));

		JRadioButton aadharRadioButton = new JRadioButton("Aadhar Card");
		aadharRadioButton.setForeground(Color.WHITE);
		aadharRadioButton.setBackground(new Color(107, 107, 107));

		ButtonGroup documents = new ButtonGroup();
		documents.add(panCardRadioButton);
		documents.add(driversLicenseRadioButton);
		documents.add(aadharRadioButton);

		c.gridy = 0;
		documentNamePanel.add(panCardRadioButton, c);

		c.gridy = 1;
		c.weightx = 0;
		c.weighty = 0;
		documentNamePanel.add(driversLicenseRadioButton, c);

		c.gridy = 2;
		c.weightx = 1;
		c.weighty = 1;
		documentNamePanel.add(aadharRadioButton, c);

		documentNamePanel.setBackground(new Color(107, 107, 107));

		return documentNamePanel;
	}

	private JPanel initialiseBiometricsPanel() {
		JPanel biometricsPanel = new JPanel();

		return biometricsPanel;
	}

	@Override
	public JFrame getFrame() {
		return this;
	}

	@Override
	public JComboBox<String> getApplicationIdComboBox() {
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
	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
