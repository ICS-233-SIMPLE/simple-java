package edu.manipal.icas.simple.impl.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.utils.StringConstants;
import edu.manipal.icas.simple.views.CitizenLoginView;

/**
 * Concrete class that defines the citizen login view.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class CitizenLoginViewImpl extends JFrame implements CitizenLoginView {
	private JTextField emailTextField;
	private JPasswordField passwordPasswordField;
	private JButton loginButton;
	private JButton createProfileButton;
	private JButton redirectToStaffLoginPortalButton;

	/**
	 * Instantiates all the required fields and buttons that are to be displayed in
	 * the login view.
	 */
	public CitizenLoginViewImpl() {
		super("Citizen Login");
		emailTextField = new JTextField();
		passwordPasswordField = new JPasswordField();
		loginButton = new JButton("Login");
		createProfileButton = new JButton("Create Profile");
		redirectToStaffLoginPortalButton = new JButton("Redirect to staff login");
		initialiseUi();
	}

	/**
	 * Initialises the login UI and populates it with the required fields.
	 */
	private void initialiseUi() {
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		container.add(new JLabel(), c);

		c.gridwidth = 3;
		c.gridy = 0;
		c.gridx = 1;
		container.add(new JLabel(ResourceConstants.IMAGE_CITIZEN_LOGIN_ICON), c);

		c.gridx = 3;
		c.gridwidth = 1;
		container.add(new JLabel());

		JLabel productNameLabel = new JLabel(StringConstants.PRODUCT_NAME, JLabel.CENTER);
		productNameLabel.setFont(ResourceConstants.FONT_HEADING_BOLD);
		JLabel welcomeLabel = new JLabel("Welcome back! Login to continue", JLabel.CENTER);
		welcomeLabel.setFont(ResourceConstants.FONT_TEXT_PLAIN);
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 3;
		container.add(productNameLabel, c);
		c.gridy = 2;
		container.add(welcomeLabel, c);

		c.gridy = 3;
		c.ipady = 30;
		container.add(new JLabel(), c);

		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(ResourceConstants.FONT_TEXT_BOLD);
		;
		c.gridy = 4;
		c.ipady = 0;
		container.add(emailLabel, c);

		c.gridy = 5;
		container.add(emailTextField, c);

		c.gridy = 6;
		c.ipady = 10;
		container.add(new JLabel(), c);

		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(ResourceConstants.FONT_TEXT_BOLD);
		;
		c.gridy = 7;
		c.ipady = 0;
		container.add(passwordLabel, c);

		c.gridy = 8;
		container.add(passwordPasswordField, c);

		c.gridy = 9;
		c.ipady = 20;
		container.add(new JLabel(), c);

		c.gridy = 10;
		c.ipady = 0;
		container.add(loginButton, c);

		c.gridy = 11;
		c.ipady = 28;
		container.add(new JLabel(), c);

		JLabel signUpLabel = new JLabel("Don't have a profile? Sign up.");
		signUpLabel.setFont(ResourceConstants.FONT_TEXT_PLAIN);
		c.gridy = 11;
		c.ipady = 0;
		container.add(signUpLabel, c);

		c.gridy = 12;
		c.ipady = 0;
		container.add(createProfileButton, c);

		c.gridy = 12;
		c.gridx = 0;
		c.gridwidth = 5;
		c.ipady = 0;
		c.gridheight = 10;

		redirectToStaffLoginPortalButton.setBackground(new Color(240, 240, 240));
		redirectToStaffLoginPortalButton.setFont(ResourceConstants.FONT_TEXT_PLAIN);
		JPanel redirectToStaffLoginPortalPanel = new JPanel();
		redirectToStaffLoginPortalPanel.setBackground(new Color(255, 255, 255));
		redirectToStaffLoginPortalPanel.setLayout(new GridLayout(1, 3));

		c.fill = GridBagConstraints.HORIZONTAL;
		c.fill = GridBagConstraints.LAST_LINE_END;
		c.ipady = 0;
		c.weighty = 2.0;
		c.anchor = GridBagConstraints.PAGE_END;
		redirectToStaffLoginPortalPanel.add(redirectToStaffLoginPortalButton);
		c.insets = new Insets(1, 3, 3, 1);
		c.gridx = 3;
		c.gridwidth = 4;
		c.gridy = 13;
		container.add(redirectToStaffLoginPortalPanel, c);

		container.setBackground(new Color(255, 255, 255));

		setIconImage(ResourceConstants.IMAGE_CITIZEN_LOGIN_ICON.getImage());
		add(container);
		setSize(new Dimension(300, 400));
		setLocationRelativeTo(null);
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
		});

	}

	@Override
	public JButton getLoginButton() {
		return loginButton;
	}

	@Override
	public JButton getRedirectToStaffLoginPortalButton() {
		return redirectToStaffLoginPortalButton;
	}

	@Override
	public JTextField getEmailTextField() {
		return emailTextField;
	}

	@Override
	public JPasswordField getPasswordPasswordField() {
		return passwordPasswordField;
	}

	@Override
	public JFrame getFrame() {
		return this;
	}

}
