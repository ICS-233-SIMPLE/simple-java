package edu.manipal.icas.simple.impl.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.DayOfWeek;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.utils.StringConstants;
import edu.manipal.icas.simple.views.ProfileCreationView;

/**
 * Concrete class that defines the profile creation view.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class ProfileCreationViewImpl extends JFrame implements ProfileCreationView {
	private JTextField emailAddressTextField;
	private JButton verifyAndContinueButton;
	private JPasswordField passwordPasswordField;
	private JPasswordField confirmPasswordPasswordField;
	private JButton continueButton;
	private JTextField nameTextField;
	private JTextField genderTextField;
	private JTextField contactNumberTextField;
	private DatePickerSettings dateSettings;
	private DatePicker datePicker;
	private JButton nextButton;
	private JComboBox<String> passportOfficeComboBox;
	private JButton finishButton;

	/**
	 * Instantiates all the required fields and buttons that are to be displayed in
	 * the profile creation view.
	 */
	public ProfileCreationViewImpl() {
		super("Profile Creation");
		emailAddressTextField = new JTextField();
		passwordPasswordField = new JPasswordField();
		verifyAndContinueButton = new JButton("Verify email and continue");
		confirmPasswordPasswordField = new JPasswordField();
		continueButton = new JButton("Continue");
		nameTextField = new JTextField();
		genderTextField = new JTextField();
		contactNumberTextField = new JTextField();
		dateSettings = new DatePickerSettings();
		dateSettings.setFirstDayOfWeek(DayOfWeek.SUNDAY);
		datePicker = new DatePicker(dateSettings);
		nextButton = new JButton("Next");
		passportOfficeComboBox = new JComboBox();
		finishButton = new JButton("Create citizen profile");
		initializeUi();
	}

	/**
	 * Initializes the profile creation UI and populates it with the create profile
	 * icon and the product name label.
	 */
	private void initializeUi() {
		JPanel container = new JPanel();
		container.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		container.add(new JLabel(), c);

		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 0;
		c.ipady = 15;
		container.add(new JLabel(ResourceConstants.IMAGE_CREATE_CITIZEN_PROFILE_ICON), c);

		c.gridheight = 1;
		c.gridwidth = 1;
		c.ipady = 0;
		c.gridx = 2;
		c.gridy = 0;
		container.add(new JLabel(), c);

		JLabel productNameLabel = new JLabel(StringConstants.PRODUCT_NAME, JLabel.CENTER);
		productNameLabel.setFont(ResourceConstants.FONT_HEADING_BOLD);
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		container.add(productNameLabel, c);

		JLabel newProfileLabel = new JLabel("Create a new profile", JLabel.CENTER);
		newProfileLabel.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		c.gridx = 1;
		c.gridy = 2;
		c.gridheight = 1;
		c.gridwidth = 1;
		container.add(newProfileLabel, c);

		c.gridy = 3;
		c.ipady = 15;
		container.add(new JLabel(), c);

		c.gridheight = 120;
		c.weighty = 1;
		c.gridy = 4;
		c.anchor = GridBagConstraints.PAGE_START;

		container.add(initialiseStepsContainerPanel(), c);

		setIconImage(ResourceConstants.IMAGE_CREATE_CITIZEN_PROFILE_ICON.getImage());
		container.setBackground(new Color(255, 255, 255));
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

	/**
	 * Initialises the steps container panel which contains only one panel at a
	 * time.
	 * 
	 * @return the steps conatiner panel
	 */
	private JPanel initialiseStepsContainerPanel() {
		JPanel stepsContainerPanel = new JPanel();
		stepsContainerPanel.setLayout(new BoxLayout(stepsContainerPanel, BoxLayout.LINE_AXIS));
		stepsContainerPanel.add(initialiseStep1Panel());
		return stepsContainerPanel;
	}

	/**
	 * Initialises the step 1 panel which contains the email address text field and
	 * a button which the citizen clicks on to proceed to the next step.
	 * 
	 * @return the step 1 panel
	 */
	private JPanel initialiseStep1Panel() {
		JPanel step1Panel = new JPanel();
		step1Panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		c.gridx = 0;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.weightx = 1;
		step1Panel.add(new JLabel("Email Address"), c);

		c.gridy = 1;
		emailAddressTextField.setSize(step1Panel.getWidth(), emailAddressTextField.getHeight());
		step1Panel.add(emailAddressTextField, c);

		c.gridy = 2;
		c.ipady = 15;
		step1Panel.add(new JLabel(), c);

		c.ipady = 0;
		c.gridy = 3;
		step1Panel.add(verifyAndContinueButton, c);

		step1Panel.setBackground(Color.WHITE);
		return step1Panel;
	}

	/**
	 * Initialises the step 2 panel which contains fields for the citizen to enter
	 * and confirm their password.
	 * 
	 * @return the step 2 panel
	 */
	private JPanel initialiseStep2Panel() {
		JPanel step2Panel = new JPanel();
		step2Panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		step2Panel.add(new JLabel("Password"), c);

		c.gridy = 1;
		step2Panel.add(passwordPasswordField, c);

		c.gridy = 2;
		c.ipady = 15;
		step2Panel.add(new JLabel(), c);

		c.gridy = 3;
		c.ipady = 0;
		step2Panel.add(new JLabel("Confirm Password"), c);

		c.gridy = 4;
		step2Panel.add(confirmPasswordPasswordField, c);

		c.ipady = 15;
		c.gridy = 5;
		step2Panel.add(new JLabel(), c);

		c.ipady = 0;
		c.gridy = 6;
		step2Panel.add(continueButton, c);

		step2Panel.setBackground(Color.WHITE);
		return step2Panel;
	}

	/**
	 * Initialises the step 3 panel which contains fields for entering the name,
	 * gender, DOB, and contact number.
	 * 
	 * @return the step 3 panel
	 */
	private JPanel initialiseStep3Panel() {
		step3Panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		step3Panel.add(new JLabel("Name"), c);

		c.gridx = 0;
		c.gridy = 1;
		step3Panel.add(nameTextField, c);

		c.ipady = 15;
		c.gridy = 2;
		step3Panel.add(new JLabel(), c);

		c.ipady = 0;
		c.gridy = 3;
		step3Panel.add(new JLabel("Gender"), c);

		c.gridy = 4;
		step3Panel.add(genderTextField, c);

		c.ipady = 15;
		c.gridy = 5;
		step3Panel.add(new JLabel(), c);

		c.ipady = 0;
		c.gridy = 6;
		step3Panel.add(new JLabel("Contact Number"), c);

		c.gridy = 7;
		step3Panel.add(contactNumberTextField, c);

		c.ipady = 15;
		c.gridy = 8;
		step3Panel.add(new JLabel(), c);

		c.ipady = 0;
		c.gridy = 9;
		step3Panel.add(new JLabel("Date Of Birth"), c);

		c.gridy = 10;
		step3Panel.add(datePicker, c);

		c.ipady = 15;
		c.gridy = 11;
		step3Panel.add(new JLabel(), c);

		c.ipady = 0;
		c.gridy = 12;
		step3Panel.add(nextButton, c);

		step3Panel.setBackground(Color.WHITE);
		return step3Panel;
	}

	/**
	 * Initialises the step 4 panel which contains a combo box from which the
	 * citizen can select the passport office branch.
	 * 
	 * @return the step 4 panel
	 */
	private JPanel initialiseStep4Panel() {
		step4Panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 1;
		step4Panel.add(new JLabel("Select Passport Office"), c);

		c.ipady = 15;
		c.gridy = 1;
		step4Panel.add(new JLabel(), c);

		c.ipady = 0;
		c.gridy = 2;
		passportOfficeComboBox.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		step4Panel.add(passportOfficeComboBox, c);

		c.gridy = 3;
		c.ipady = 15;
		step4Panel.add(new JLabel(), c);

		c.gridy = 4;
		c.ipady = 0;
		step4Panel.add(finishButton, c);

		step4Panel.setBackground(Color.WHITE);
		return step4Panel;
	}

	@Override
	public JPanel getStepsContainerPanel() {
		return stepsContainerPanel;
	}

	@Override
	public JPanel getStep1Panel() {
		return step1Panel;
	}

	@Override
	public JPanel getStep2Panel() {
		return step2Panel;
	}

	@Override
	public JPanel getStep3Panel() {
		return step3Panel;
	}

	@Override
	public JPanel getStep4Panel() {
		return step4Panel;
	}

	@Override
	public JTextField getEmailAddressTextField() {
		return emailAddressTextField;
	}

	@Override
	public JButton getVerifyAndContinueToStep2Button() {
		return verifyAndContinueButton;
	}

	@Override
	public JPasswordField getPasswordField() {
		return passwordPasswordField;
	}

	@Override
	public JPasswordField getConfirmPasswordField() {
		return confirmPasswordPasswordField;
	}

	@Override
	public JButton getContinueToStep3Button() {
		return continueButton;
	}

	@Override
	public JTextField getNameTextField() {
		return nameTextField;
	}

	@Override
	public JTextField getGenderTextField() {
		return genderTextField;
	}

	@Override
	public JTextField getContactNumberTextField() {
		return contactNumberTextField;
	}

	@Override
	public JButton getContinueToStep4Button() {
		return nextButton;
	}

	@Override
	public JComboBox<String> getPassportOfficeComboBox() {
		return passportOfficeComboBox;
	}

	@Override
	public JButton getFinishButton() {
		return finishButton;
	}

	@Override
	public JLabel getErrorMessageLabel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JFrame getFrame() {
		return this;
	}

	@Override
	public DatePicker getDateOfBirthDatePicker() {
		return datePicker;
	}

}
