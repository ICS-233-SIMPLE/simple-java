package edu.manipal.icas.simple.impl.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.utils.StringConstants;
import edu.manipal.icas.simple.views.OfficerLoginView;

/**
 * Concrete class that defines the officer login view.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class OfficerLoginViewImpl extends JFrame implements OfficerLoginView {
	private JTextField officerIdTextField;
	private JButton officerLoginButton;
	private JComboBox<String> officerTypeComboBox;

	/**
	 * Instantiates all the required components that are to be displayed in the
	 * officer login view.
	 */
	public OfficerLoginViewImpl() {
		super("Officer Login");
		officerIdTextField = new JTextField();
		officerLoginButton = new JButton("Login");
		String[] officerType = new String[] { "Biometrics Officer", "Verification Officer", "Passport Granting Officer",
				"Police Officer" };
		officerTypeComboBox = new JComboBox<>(officerType);
		intializeOfficerLoginUi();
	}

	/** Initializes the UI that the officer uses to login. */
	private void intializeOfficerLoginUi() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 0;
		c.gridy = 0;
		panel.add(new JLabel(), c);

		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 1;
		c.gridy = 0;
		panel.add(new JLabel(ResourceConstants.IMAGE_OFFICER_LOGIN_ICON), c);

		c.gridwidth = 1;
		c.gridheight = 1;
		c.gridx = 2;
		c.gridy = 0;
		panel.add(new JLabel(), c);

		JLabel productNameLabel = new JLabel(StringConstants.PRODUCT_NAME, JLabel.CENTER);
		productNameLabel.setFont(ResourceConstants.FONT_HEADING_BOLD);
		JLabel officerLoginLabel = new JLabel("Officer Login", JLabel.CENTER);
		officerLoginLabel.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		panel.add(productNameLabel, c);
		c.gridy = 2;
		panel.add(officerLoginLabel, c);

		c.ipady = 20;
		c.gridx = 1;
		c.gridy = 3;
		panel.add(new JLabel(), c);

		JLabel officerIdLabel = new JLabel("Officer ID");
		officerIdLabel.setFont(ResourceConstants.FONT_LABEL_BOLD);
		c.gridheight = 1;
		c.gridwidth = 1;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 4;
		panel.add(officerIdLabel, c);

		c.gridheight = 1;
		c.gridwidth = 1;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 5;
		panel.add(officerIdTextField, c);

		c.gridy = 6;
		c.gridx = 1;
		c.ipady = 10;
		panel.add(new JLabel(), c);

		JLabel officerTypeLabel = new JLabel("Officer Type");
		officerTypeLabel.setFont(ResourceConstants.FONT_LABEL_BOLD);
		c.gridy = 7;
		c.gridx = 1;
		c.ipady = 0;
		panel.add(officerTypeLabel, c);

		c.gridheight = 1;
		c.gridwidth = 1;
		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 8;
		officerTypeComboBox.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		panel.add(officerTypeComboBox, c);

		c.gridy = 9;
		c.gridx = 1;
		c.ipady = 30;
		panel.add(new JLabel(), c);

		c.ipady = 0;
		c.gridx = 1;
		c.gridy = 10;
		panel.add(officerLoginButton, c);

		panel.setBackground(new Color(255, 255, 255));
		setIconImage(ResourceConstants.IMAGE_OFFICER_LOGIN_ICON.getImage());
		add(panel);
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
	public JFrame getFrame() {
		return this;
	}

	@Override
	public JButton getOfficerLoginButton() {
		return officerLoginButton;
	}

	@Override
	public JTextField getOfficerIdTextField() {
		return officerIdTextField;
	}

	@Override
	public JComboBox<String> getOfficerTypeComboBox() {
		return officerTypeComboBox;
	}
}
