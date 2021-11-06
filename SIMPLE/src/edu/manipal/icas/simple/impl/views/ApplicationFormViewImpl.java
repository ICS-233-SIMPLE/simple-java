package edu.manipal.icas.simple.impl.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.github.lgooddatepicker.components.DatePicker;

import edu.manipal.icas.simple.models.application.AcceptedDocumentType;
import edu.manipal.icas.simple.models.application.ApplicationQuestion;
import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.utils.StringConstants;
import edu.manipal.icas.simple.views.ApplicationFormView;

public class ApplicationFormViewImpl extends JFrame implements ApplicationFormView {

	private JButton submitButton;
	private JButton cancelButton;
	private JTabbedPane tabbedPane;
	private GridBagConstraints gbc;

	// Personal Details Panel Object Declaration

	private ButtonGroup appType;
	private JTextField placeOfBirthTextField;
	private JTextArea permanentAddressTextField;
	private JTextArea presentAddressTextField;
	private JTextField mothersNameTextField;
	private JTextField fathersNameTextField;
	private JTextField emergencyNameTextField;
	private JTextField emergencyPhoneTextField;
	private JTextField emergencyEmailTextField;
	private JCheckBox selfDeclarationCheck;
	private JButton findCitizenButton;

	// Questions Panel Object Declaration

	private Map<ApplicationQuestion, ButtonGroup> questionButtonGroups;

	// Documents Panel Object Declaration

	private Map<AcceptedDocumentType, JButton> documentUploadButtons;

	// Appointment Panel Object Declaration

	private DatePicker appointmentDatePicker;
	private JButton bookSlotsButton;

	// Appointment Panel Object Declaration

	private JButton payButton;
	private JLabel payerNameLabel;
	private JLabel paymentAmountLabel;

	public ApplicationFormViewImpl() {
		super("Application Form");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1500, 1000);

		submitButton = new JButton("Submit details, questions, and documents");
		submitButton.setBackground(new Color(0, 204, 0));
		cancelButton = new JButton("Cancel");
		gbc = new GridBagConstraints();

		// Personal Details Panel Object Initialization

		placeOfBirthTextField = new JTextField(20);
		permanentAddressTextField = new JTextArea(3, 30);
		permanentAddressTextField.setLineWrap(true);
		permanentAddressTextField.setWrapStyleWord(true);
		presentAddressTextField = new JTextArea(3, 30);
		presentAddressTextField.setLineWrap(true);
		presentAddressTextField.setWrapStyleWord(true);
		mothersNameTextField = new JTextField(20);
		fathersNameTextField = new JTextField(20);
		emergencyNameTextField = new JTextField(20);
		emergencyPhoneTextField = new JTextField(20);
		emergencyEmailTextField = new JTextField(20);
		findCitizenButton = new JButton("Find citizen");

		selfDeclarationCheck = new JCheckBox("I Agree");

		questionButtonGroups = new HashMap<ApplicationQuestion, ButtonGroup>();

		// Documents Panel Object Initialization

		documentUploadButtons = new HashMap<AcceptedDocumentType, JButton>();

		// Appointment Panel Object Initializtion

		appointmentDatePicker = new DatePicker();
		bookSlotsButton = new JButton("Book Slot");

		// Payment Panel Object Initializtion

		payButton = new JButton("Proceed to Payment");
		payerNameLabel = new JLabel();
		paymentAmountLabel = new JLabel();

		tabbedPane = new JTabbedPane();
		tabbedPane.addTab("Personal Details", new JScrollPane(createPersonalDetailsPanel()));
		tabbedPane.addTab("Questions", createQuestionsPanel());
		tabbedPane.addTab("Documents", createDocumentsPanel());
		tabbedPane.addTab("Appointment", createAppointmentPanel());
		tabbedPane.addTab("Payment", createPaymentPanel());

		createNavigationBar();
	}

	private void createNavigationBar() {
		JPanel navBar = new JPanel(new GridBagLayout());
		navBar.setBackground(new Color(217, 217, 217));
		gbcReset();

		gbc.anchor = GridBagConstraints.NORTHEAST;

		gbc.insets = new Insets(10, 0, 0, 20);
		place(getCancelButton(), navBar, 0, 2, false);

		gbc.insets = new Insets(10, 1170, 0, 20);
		place(getSubmitButton(), navBar, 0, 1, false);

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;
		gbc.weighty = 1;
		gbc.insets = new Insets(20, 5, 5, 5);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridheight = GridBagConstraints.REMAINDER;

		place(getFormTabbedPane(), navBar, 0, 1, false);

		add(navBar);
	}

	private JPanel createPersonalDetailsPanel() {
		JPanel personalDetailsPanel = new JPanel(new GridBagLayout());
		gbcReset();

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(15, 0, 10, 0);

		JLabel serviceHeading = new JLabel("Service Required");
		serviceHeading.setFont(ResourceConstants.FONT_LABEL_BOLD);
		JLabel applicantHeading = new JLabel("Applicant Details");
		applicantHeading.setFont(ResourceConstants.FONT_LABEL_BOLD);
		JLabel familyHeading = new JLabel("Family Details");
		familyHeading.setFont(ResourceConstants.FONT_LABEL_BOLD);
		JLabel emergencyHeading = new JLabel("Emergency Contact Details");
		emergencyHeading.setFont(ResourceConstants.FONT_LABEL_BOLD);

		place(serviceHeading, personalDetailsPanel, 0, 0, false);
		place(applicantHeading, personalDetailsPanel, 2, 0, false);
		place(familyHeading, personalDetailsPanel, 9, 0, false);
		place(emergencyHeading, personalDetailsPanel, 12, 0, false);

		gbc.insets = new Insets(0, 0, 0, 0);

		place(new JLabel("Applying for:"), personalDetailsPanel, 1, 0, true);

		place(new JLabel("Place of Birth (Village/Town/City):"), personalDetailsPanel, 6, 0, true);
		place(new JLabel("Permanent Address:"), personalDetailsPanel, 7, 0, true);
		place(new JLabel("Present Residential Address:"), personalDetailsPanel, 8, 0, true);

		place(new JLabel("Mother's Name:"), personalDetailsPanel, 10, 0, true);
		place(new JLabel("Father's Name:"), personalDetailsPanel, 11, 0, true);

		place(new JLabel("Email address:"), personalDetailsPanel, 13, 0, true);
		place(new JLabel("Name:"), personalDetailsPanel, 14, 0, true);
		place(new JLabel("Contact Number:"), personalDetailsPanel, 15, 0, true);

		appType = new ButtonGroup();
		JRadioButton freshAppRadioButton = new JRadioButton("Fresh Passport");
		freshAppRadioButton.setMnemonic(0);
		
		JRadioButton reIssueAppRadioButton = new JRadioButton("Re-issue of Passport");
		reIssueAppRadioButton.setMnemonic(1);
		appType.add(freshAppRadioButton);
		appType.add(reIssueAppRadioButton);

		gbc.insets = new Insets(0, 50, 10, 0);

		place(freshAppRadioButton, personalDetailsPanel, 1, 1, false);
		place(reIssueAppRadioButton, personalDetailsPanel, 1, 2, false);

		gbc.gridwidth = 3;
		gbc.insets = new Insets(0, 100, 10, 0);

		place(getPlaceOfBirthTextField(), personalDetailsPanel, 6, 1, true);
		place(new JScrollPane(getPermanentAddressTextField()), personalDetailsPanel, 7, 1, true);
		place(new JScrollPane(getPresentAddressTextField()), personalDetailsPanel, 8, 1, true);
		place(getMothersNameTextField(), personalDetailsPanel, 10, 1, true);
		place(getFathersNameTextField(), personalDetailsPanel, 11, 1, true);
		place(getEmergencyEmailTextField(), personalDetailsPanel, 13, 1, true);
		place(getEmergencyNameTextField(), personalDetailsPanel, 14, 1, true);
		place(getEmergencyPhoneTextField(), personalDetailsPanel, 15, 1, true);
		
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridwidth = 1;
		place(findCitizenButton, personalDetailsPanel, 13, 3, false);

		gbc.gridwidth = 4;
		gbc.insets = new Insets(50, 0, 50, 0);

		JPanel selfDeclaration = new JPanel(new GridBagLayout());
		selfDeclaration.setBorder(BorderFactory
				.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "Self-Declaration"));
		place(selfDeclaration, personalDetailsPanel, 17, 0, false);

		gbcReset();
		gbc.anchor = GridBagConstraints.NORTHWEST;

		gbc.insets = new Insets(20, 50, 5, 50);
		place(new JLabel(
				"I owe allegiance to the sovereignty, unity & integrity of India, and have not voluntarily acquired citizenship or travel document of any"),
				selfDeclaration, 0, 0, true);
		gbc.insets = new Insets(0, 50, 5, 50);
		place(new JLabel(
				"other country. I have not lost, surrendered or been deprived of the citizenship of India. I have not contravened any of the conditions"),
				selfDeclaration, 1, 0, true);
		place(new JLabel("relating to the possession and use of an Indian passport."), selfDeclaration, 2, 0, true);
		place(new JLabel(
				"I affirm that the information and particulars given by me in this form are true and correct. I further state that I am not suppressing any"),
				selfDeclaration, 3, 0, true);
		place(new JLabel(
				"material information in this regard. I further affirm that the enclosures and documentary proof submitted in support of my application for"),
				selfDeclaration, 4, 0, true);
		place(new JLabel(
				"an Indian passport are authentic and solely pertain to me and I am fully responsible for the accuracy of the same. I am liable to be"),
				selfDeclaration, 5, 0, true);
		place(new JLabel(
				"penalized or prosecuted if found otherwise. I am aware that under the Passports Act, 1967 it is a criminal offence to furnish any false"),
				selfDeclaration, 6, 0, true);
		place(new JLabel(
				"information or to suppress any material information with a view to obtaining passport or travel document."),
				selfDeclaration, 7, 0, true);
		place(new JLabel(
				"I have read and understood the contents of the above and by submitting this form certify that all the information submitted by me in the"),
				selfDeclaration, 8, 0, true);
		gbc.insets = new Insets(0, 50, 20, 50);
		place(new JLabel("form is bonafide."), selfDeclaration, 9, 0, true);

		place(getSelfDeclarationCheckBox(), selfDeclaration, 10, 0, false);

		return personalDetailsPanel;
	}

	private JPanel createQuestionsPanel() {
		JPanel questionsPanel = new JPanel(new GridBagLayout());
		gbcReset();

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(25, 0, 10, 0);

		int gridy = 0;
		Double section = 0.0;
		for (ApplicationQuestion question : StringConstants.APPLICATION_QUESTIONS.keySet()) {
			if (question.number - section >= 1) {
				section = Math.floor(question.number);
				gbc.insets = new Insets(25, 0, 10, 0);
				JLabel questionLabel = new JLabel(StringConstants.APPLICATION_QUESTION_HEADINGS.get(section));
				questionLabel.setFont(ResourceConstants.FONT_LABEL_BOLD);
				place(questionLabel, questionsPanel, gridy++, 0, false);
			}
			gbc.insets = new Insets(3, 25, 3, 0);
			place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(question)), questionsPanel, gridy, 0, true);

			ButtonGroup questionButtonGroup = new ButtonGroup();
			JRadioButton yesButton = new JRadioButton("Yes");
			yesButton.setMnemonic(1);
			JRadioButton noButton = new JRadioButton("No");
			noButton.setMnemonic(0);
			
			questionButtonGroup.add(yesButton);
			questionButtonGroup.add(noButton);

			gbc.insets = new Insets(0, 220, 0, 0);
			place(yesButton, questionsPanel, gridy, 1, false);
			gbc.insets = new Insets(0, 60, 0, 0);
			place(noButton, questionsPanel, gridy, 2, false);
			gridy++;

			questionButtonGroups.put(question, questionButtonGroup);
		}
		return questionsPanel;
	}

	private JPanel createDocumentsPanel() {
		JPanel documentsPanel = new JPanel(new GridBagLayout());
		gbcReset();
		gbc.anchor = GridBagConstraints.NORTHWEST;

		gbc.insets = new Insets(0, 0, 70, 0);
		place(new JLabel("Please upload the applicable documents."), documentsPanel, 0, 0, false);

		int gridy = 1;
		for (AcceptedDocumentType documentType : AcceptedDocumentType.values()) {
			gbc.insets = new Insets(0, 0, 30, 0);
			place(new JLabel(StringConstants.ACCEPTED_DOCUMENT_TYPE_NAMES.get(documentType)), documentsPanel, gridy, 0, true);
			
			gbc.insets = new Insets(0, 200, 0, 0);
			JButton uploadButton = new JButton("Upload document");
			place(uploadButton, documentsPanel, gridy, 1, false);
			documentUploadButtons.put(documentType, uploadButton);
			gridy++;
		}

		return documentsPanel;
	}

	private JPanel createAppointmentPanel() {
		JPanel appointmentPanel = new JPanel(new GridBagLayout());
		gbcReset();
		gbc.anchor = GridBagConstraints.NORTHWEST;

		place(getAppointmentDatePicker(), appointmentPanel, 1, 2, false);

		gbc.insets = new Insets(0, 0, 30, 0);
		place(new JLabel("Select date of Appointment:"), appointmentPanel, 1, 0, true);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(30, 20, 0, 20);
		place(getBookSlotButton(), appointmentPanel, 2, 1, false);

		return appointmentPanel;
	}

	private JPanel createPaymentPanel() {
		JPanel paymentPanel = new JPanel(new GridBagLayout());
		gbcReset();
		gbc.anchor = GridBagConstraints.NORTHWEST;

		JLabel payWarning1 = new JLabel("Payment once made for availing passport services will not be refunded.",
				SwingConstants.LEFT);
		JLabel payWarning2 = new JLabel("If the payment is successfully done, please do not pay again.",
				SwingConstants.LEFT);
		JLabel payWarning3 = new JLabel("Please complete your transaction within 5 minutes.", SwingConstants.LEFT);
		payWarning1.setForeground(Color.red);
		payWarning2.setForeground(Color.red);
		payWarning3.setForeground(Color.red);

		gbc.insets = new Insets(0, 0, 10, 0);
		place(payWarning1, paymentPanel, 0, 0, false);
		place(payWarning2, paymentPanel, 1, 0, false);
		gbc.insets = new Insets(20, 0, 100, 0);
		place(payWarning3, paymentPanel, 2, 0, false);

		gbc.insets = new Insets(0, 0, 0, 0);
		place(new JLabel("Name:"), paymentPanel, 4, 0, true);
		gbc.insets = new Insets(20, 0, 0, 0);
		place(new JLabel("Amount to be paid:"), paymentPanel, 5, 0, true);

		gbc.insets = new Insets(0, -100, 0, 0);
		place(payerNameLabel, paymentPanel, 4, 1, true);
		place(paymentAmountLabel, paymentPanel, 5, 1, true);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(100, 0, 0, 0);
		place(getPayButton(), paymentPanel, 6, 0, false);

		return paymentPanel;
	}

	private void place(JComponent child, JComponent parent, int y, int x, boolean changeFont) {
		gbc.gridy = y;
		gbc.gridx = x;

		if (changeFont)
			child.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);

		parent.add(child, gbc);
	}

	private void gbcReset() {
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.ipadx = 0;
		gbc.ipady = 0;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
	}
	
	private JTabbedPane getFormTabbedPane() {
		return tabbedPane;
	}

	@Override
	public JButton getSubmitButton() {
		return submitButton;
	}

	@Override
	public JButton getCancelButton() {
		return cancelButton;
	}

	// Personal Details Panel Getters

	@Override
	public ButtonGroup getApplicationTypeButtonGroup() {
		return appType;
	}

	@Override
	public JTextField getPlaceOfBirthTextField() {
		return placeOfBirthTextField;
	}

	@Override
	public JTextArea getPermanentAddressTextField() {
		return permanentAddressTextField;
	}

	@Override
	public JTextArea getPresentAddressTextField() {
		return presentAddressTextField;
	}

	@Override
	public JTextField getMothersNameTextField() {
		return mothersNameTextField;
	}

	@Override
	public JTextField getFathersNameTextField() {
		return fathersNameTextField;
	}

	@Override
	public JTextField getEmergencyNameTextField() {
		return emergencyNameTextField;
	}

	@Override
	public JTextField getEmergencyPhoneTextField() {
		return emergencyPhoneTextField;
	}

	@Override
	public JTextField getEmergencyEmailTextField() {
		return emergencyEmailTextField;
	}

	@Override
	public JCheckBox getSelfDeclarationCheckBox() {
		return selfDeclarationCheck;
	}
	
	@Override
	public JButton getFindCitizenButton() {
		return findCitizenButton;
	}

	// Questions Panel Getters
	
	@Override
	public Map<ApplicationQuestion, ButtonGroup> getApplicationQuestionButtonGroups() {
		return questionButtonGroups;
	}

	// Document Panel Getters

	@Override
	public Map<AcceptedDocumentType, JButton> getDocumentUploadButtons() {
		return documentUploadButtons;
	}
	
	// Appointment Panel Getters
	
	@Override
	public DatePicker getAppointmentDatePicker() {
		return appointmentDatePicker;
	}

	@Override
	public JButton getBookSlotButton() {
		return bookSlotsButton;
	}

	// Payment Panel Getters & Setters

	@Override
	public JButton getPayButton() {
		return payButton;
	}
	
	@Override
	public JLabel getPayerNameLabel() {
		return payerNameLabel;
	}
	
	@Override
	public JLabel getPaymentAmountLabel() {
		return paymentAmountLabel;
	}

	@Override
	public JFrame getFrame() {
		return this;
	}
}