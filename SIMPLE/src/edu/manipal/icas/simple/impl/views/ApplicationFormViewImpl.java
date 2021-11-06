package edu.manipal.icas.simple.impl.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

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

import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.utils.StringConstants;
import edu.manipal.icas.simple.views.ApplicationFormView;

public class ApplicationFormViewImpl extends JFrame implements ApplicationFormView {

	private JButton submitButton;
	private JButton cancelButton;
	private JTabbedPane tabbedPane;
	private GridBagConstraints gbc;

	// Personal Details Panel Object Declaration

	private JRadioButton appTypeFresh;
	private JRadioButton appTypeReissue;
	private JRadioButton genderMale;
	private JRadioButton genderFemale;
	private JRadioButton genderTrans;
	private JTextField nameTextField;
	private DatePicker dateOfBirthPicker;
	private JTextField placeOfBirthTextField;
	private JTextArea permanentAddressTextField;
	private JTextArea presentAddressTextField;
	private JTextField mothersNameTextField;
	private JTextField fathersNameTextField;
	private JTextField emergencyNameTextField;
	private JTextArea emergencyAddressTextField;
	private JTextField emergencyPhoneTextField;
	private JTextField emergencyEmailTextField;
	private JCheckBox selfDeclarationCheck;

	// Questions Panel Object Declaration

	private JRadioButton quesOneAyes;
	private JRadioButton quesOneAno;
	private JRadioButton quesOneByes;
	private JRadioButton quesOneBno;
	private JRadioButton quesOneCyes;
	private JRadioButton quesOneCno;
	private JRadioButton quesOneDyes;
	private JRadioButton quesOneDno;

	private JRadioButton quesTwoAyes;
	private JRadioButton quesTwoAno;

	private JRadioButton quesThreeAyes;
	private JRadioButton quesThreeAno;
	private JRadioButton quesThreeByes;
	private JRadioButton quesThreeBno;
	private JRadioButton quesThreeCyes;
	private JRadioButton quesThreeCno;

	private JRadioButton quesFourAyes;
	private JRadioButton quesFourAno;
	private JRadioButton quesFourByes;
	private JRadioButton quesFourBno;
	private JRadioButton quesFourCyes;
	private JRadioButton quesFourCno;
	private JRadioButton quesFourDyes;
	private JRadioButton quesFourDno;

	private JRadioButton quesFiveAyes;
	private JRadioButton quesFiveAno;
	private JRadioButton quesFiveByes;
	private JRadioButton quesFiveBno;
	private JRadioButton quesFiveCyes;
	private JRadioButton quesFiveCno;

	// Documents Panel Object Declaration

	private JButton uploadAddressProof;
	private JButton uploadBirthProof;
	private JButton uploadOldPassport;
	private JFileChooser documentChooser;

	// Appointment Panel Object Declaration

	private JComboBox<String> passportOfficeComboBox;
	private DatePicker appointmentDatePicker;
	private JList<String> slotList;
	private JButton checkSlots;

	// Appointment Panel Object Declaration

	private JButton payButton;
	private String payerName;
	private String paymentAmount;

	public ApplicationFormViewImpl() {
		super("Application Form");
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		submitButton = new JButton("Submit");
		submitButton.setBackground(new Color(255, 80, 80, 200));
		cancelButton = new JButton("Cancel");
		gbc = new GridBagConstraints();

		// Personal Details Panel Object Initialization

		appTypeFresh = new JRadioButton("Fresh Passport");
		appTypeReissue = new JRadioButton("Re-issue of Passport");

		genderMale = new JRadioButton("Male");
		genderFemale = new JRadioButton("Female");
		genderTrans = new JRadioButton("Transgender");

		nameTextField = new JTextField(20);
		dateOfBirthPicker = new DatePicker();
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
		emergencyAddressTextField = new JTextArea(3, 30);
		emergencyAddressTextField.setLineWrap(true);
		emergencyAddressTextField.setWrapStyleWord(true);
		emergencyPhoneTextField = new JTextField(20);
		emergencyEmailTextField = new JTextField(20);

		selfDeclarationCheck = new JCheckBox("I Agree");

		// Questions Panel Object Initialization

		quesOneAyes = new JRadioButton("Yes");
		quesOneAno = new JRadioButton("No");
		quesOneByes = new JRadioButton("Yes");
		quesOneBno = new JRadioButton("No");
		quesOneCyes = new JRadioButton("Yes");
		quesOneCno = new JRadioButton("No");
		quesOneDyes = new JRadioButton("Yes");
		quesOneDno = new JRadioButton("No");

		quesTwoAyes = new JRadioButton("Yes");
		quesTwoAno = new JRadioButton("No");

		quesThreeAyes = new JRadioButton("Yes");
		quesThreeAno = new JRadioButton("No");
		quesThreeByes = new JRadioButton("Yes");
		quesThreeBno = new JRadioButton("No");
		quesThreeCyes = new JRadioButton("Yes");
		quesThreeCno = new JRadioButton("No");

		quesFourAyes = new JRadioButton("Yes");
		quesFourAno = new JRadioButton("No");
		quesFourByes = new JRadioButton("Yes");
		quesFourBno = new JRadioButton("No");
		quesFourCyes = new JRadioButton("Yes");
		quesFourCno = new JRadioButton("No");
		quesFourDyes = new JRadioButton("Yes");
		quesFourDno = new JRadioButton("No");

		quesFiveAyes = new JRadioButton("Yes");
		quesFiveAno = new JRadioButton("No");
		quesFiveByes = new JRadioButton("Yes");
		quesFiveBno = new JRadioButton("No");
		quesFiveCyes = new JRadioButton("Yes");
		quesFiveCno = new JRadioButton("No");

		// Documents Panel Object Initialization

		uploadAddressProof = new JButton("Upload from Device");
		uploadBirthProof = new JButton("Upload from Device");
		uploadOldPassport = new JButton("Upload from Device");
		documentChooser = new JFileChooser();
		documentChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));

		// Appointment Panel Object Initializtion

		passportOfficeComboBox = new JComboBox<>();
		passportOfficeComboBox
				.setPreferredSize(new Dimension(153, getPassportOfficeComboBox().getPreferredSize().height));
		passportOfficeComboBox.setMinimumSize(passportOfficeComboBox.getPreferredSize());
		appointmentDatePicker = new DatePicker();
		checkSlots = new JButton("Check Available Slots");
		slotList = new JList<>();
		slotList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		slotList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Payment Panel Object Initializtion

		payButton = new JButton("Proceed to Payment");

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

		place(new JLabel("Name:"), personalDetailsPanel, 3, 0, true);
		place(new JLabel("Gender:"), personalDetailsPanel, 4, 0, true);
		place(new JLabel("Date of Birth:"), personalDetailsPanel, 5, 0, true);
		place(new JLabel("Place of Birth (Village/Town/City):"), personalDetailsPanel, 6, 0, true);
		place(new JLabel("Permanent Address:"), personalDetailsPanel, 7, 0, true);
		place(new JLabel("Present Residential Address:"), personalDetailsPanel, 8, 0, true);

		place(new JLabel("Mother's Name:"), personalDetailsPanel, 10, 0, true);
		place(new JLabel("Father's Name:"), personalDetailsPanel, 11, 0, true);

		place(new JLabel("Name:"), personalDetailsPanel, 13, 0, true);
		place(new JLabel("Address:"), personalDetailsPanel, 14, 0, true);
		place(new JLabel("Phone Number:"), personalDetailsPanel, 15, 0, true);
		place(new JLabel("Email ID:"), personalDetailsPanel, 16, 0, true);

		ButtonGroup appType = new ButtonGroup();
		appType.add(getAppTypeFreshRadioButton());
		appType.add(getAppTypeReissueRadioButton());

		ButtonGroup genderType = new ButtonGroup();
		genderType.add(getGenderMaleRadioButton());
		genderType.add(getGenderFemaleRadioButton());
		genderType.add(getGenderTransRadioButton());

		gbc.insets = new Insets(0, 100, 10, 0);

		place(getAppTypeFreshRadioButton(), personalDetailsPanel, 1, 1, false);
		place(getGenderMaleRadioButton(), personalDetailsPanel, 4, 1, false);

		gbc.insets = new Insets(0, 50, 10, 0);

		place(getAppTypeReissueRadioButton(), personalDetailsPanel, 1, 2, false);
		place(getGenderFemaleRadioButton(), personalDetailsPanel, 4, 2, false);
		place(getGenderTransRadioButton(), personalDetailsPanel, 4, 3, false);

		gbc.gridwidth = 3;
		gbc.insets = new Insets(0, 100, 10, 0);

		place(getNameTextField(), personalDetailsPanel, 3, 1, true);
		place(getDateOfBirthPicker(), personalDetailsPanel, 5, 1, true);
		place(getPlaceOfBirthTextField(), personalDetailsPanel, 6, 1, true);
		place(new JScrollPane(getPermanentAddressTextField()), personalDetailsPanel, 7, 1, true);
		place(new JScrollPane(getPresentAddressTextField()), personalDetailsPanel, 8, 1, true);
		place(getMothersNameTextField(), personalDetailsPanel, 10, 1, true);
		place(getFathersNameTextField(), personalDetailsPanel, 11, 1, true);
		place(getEmergencyNameTextField(), personalDetailsPanel, 13, 1, true);
		place(new JScrollPane(getEmergencyAddressTextField()), personalDetailsPanel, 14, 1, true);
		place(getEmergencyPhoneTextField(), personalDetailsPanel, 15, 1, true);
		place(getEmergencyEmailTextField(), personalDetailsPanel, 16, 1, true);

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

		JLabel quesOne = new JLabel(StringConstants.APPLICATION_QUESTIONS.get(1.0));
		quesOne.setFont(ResourceConstants.FONT_LABEL_BOLD);
		JLabel quesTwo = new JLabel(StringConstants.APPLICATION_QUESTIONS.get(2.0));
		quesTwo.setFont(ResourceConstants.FONT_LABEL_BOLD);
		JLabel quesThree = new JLabel(StringConstants.APPLICATION_QUESTIONS.get(3.0));
		quesThree.setFont(ResourceConstants.FONT_LABEL_BOLD);
		JLabel quesFour = new JLabel(StringConstants.APPLICATION_QUESTIONS.get(4.0));
		quesFour.setFont(ResourceConstants.FONT_LABEL_BOLD);
		JLabel quesFive = new JLabel(StringConstants.APPLICATION_QUESTIONS.get(5.0));
		quesFive.setFont(ResourceConstants.FONT_LABEL_BOLD);

		place(quesOne, questionsPanel, 0, 0, false);
		place(quesTwo, questionsPanel, 5, 0, false);
		place(quesThree, questionsPanel, 8, 0, false);
		place(quesFour, questionsPanel, 12, 0, false);
		place(quesFive, questionsPanel, 17, 0, false);

		gbc.insets = new Insets(3, 25, 3, 0);

		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(1.1)), questionsPanel, 1, 0, true);
		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(1.2)), questionsPanel, 2, 0, true);
		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(1.3)), questionsPanel, 3, 0, true);
		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(1.4)), questionsPanel, 4, 0, true);

		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(2.1).substring(0, 140)), questionsPanel, 6, 0, true);
		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(2.1).substring(141)), questionsPanel, 7, 0, true);

		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(3.1)), questionsPanel, 9, 0, true);
		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(3.2)), questionsPanel, 10, 0, true);
		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(3.3)), questionsPanel, 11, 0, true);

		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(4.1)), questionsPanel, 13, 0, true);
		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(4.2)), questionsPanel, 14, 0, true);
		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(4.3)), questionsPanel, 15, 0, true);
		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(4.4)), questionsPanel, 16, 0, true);

		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(5.1)), questionsPanel, 18, 0, true);
		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(5.2)), questionsPanel, 19, 0, true);
		place(new JLabel(StringConstants.APPLICATION_QUESTIONS.get(5.3)), questionsPanel, 20, 0, true);

		ButtonGroup quesOneA = new ButtonGroup();
		quesOneA.add(getQuesOneAyesRadioButton());
		quesOneA.add(getQuesOneAnoRadioButton());
		ButtonGroup quesOneB = new ButtonGroup();
		quesOneB.add(getQuesOneByesRadioButton());
		quesOneB.add(getQuesOneBnoRadioButton());
		ButtonGroup quesOneC = new ButtonGroup();
		quesOneC.add(getQuesOneCyesRadioButton());
		quesOneC.add(getQuesOneCnoRadioButton());
		ButtonGroup quesOneD = new ButtonGroup();
		quesOneD.add(getQuesOneDyesRadioButton());
		quesOneD.add(getQuesOneDnoRadioButton());

		ButtonGroup quesTwoA = new ButtonGroup();
		quesTwoA.add(getQuesTwoAyesRadioButton());
		quesTwoA.add(getQuesTwoAnoRadioButton());

		ButtonGroup quesThreeA = new ButtonGroup();
		quesThreeA.add(getQuesThreeAyesRadioButton());
		quesThreeA.add(getQuesThreeAnoRadioButton());
		ButtonGroup quesThreeB = new ButtonGroup();
		quesThreeB.add(getQuesThreeByesRadioButton());
		quesThreeB.add(getQuesThreeBnoRadioButton());
		ButtonGroup quesThreeC = new ButtonGroup();
		quesThreeC.add(getQuesThreeCyesRadioButton());
		quesThreeC.add(getQuesThreeCnoRadioButton());

		ButtonGroup quesFourA = new ButtonGroup();
		quesFourA.add(getQuesFourAyesRadioButton());
		quesFourA.add(getQuesFourAnoRadioButton());
		ButtonGroup quesFourB = new ButtonGroup();
		quesFourB.add(getQuesFourByesRadioButton());
		quesFourB.add(getQuesFourBnoRadioButton());
		ButtonGroup quesFourC = new ButtonGroup();
		quesFourC.add(getQuesFourCyesRadioButton());
		quesFourC.add(getQuesFourCnoRadioButton());
		ButtonGroup quesFourD = new ButtonGroup();
		quesFourD.add(getQuesFourDyesRadioButton());
		quesFourD.add(getQuesFourDnoRadioButton());

		ButtonGroup quesFiveA = new ButtonGroup();
		quesFiveA.add(getQuesFiveAyesRadioButton());
		quesFiveA.add(getQuesFiveAnoRadioButton());
		ButtonGroup quesFiveB = new ButtonGroup();
		quesFiveB.add(getQuesFiveByesRadioButton());
		quesFiveB.add(getQuesFiveBnoRadioButton());
		ButtonGroup quesFiveC = new ButtonGroup();
		quesFiveC.add(getQuesFiveCyesRadioButton());
		quesFiveC.add(getQuesFiveCnoRadioButton());

		gbc.insets = new Insets(0, 220, 0, 0);

		place(getQuesOneAyesRadioButton(), questionsPanel, 1, 1, false);
		place(getQuesOneByesRadioButton(), questionsPanel, 2, 1, false);
		place(getQuesOneCyesRadioButton(), questionsPanel, 3, 1, false);
		place(getQuesOneDyesRadioButton(), questionsPanel, 4, 1, false);

		place(getQuesTwoAyesRadioButton(), questionsPanel, 7, 1, false);

		place(getQuesThreeAyesRadioButton(), questionsPanel, 9, 1, false);
		place(getQuesThreeByesRadioButton(), questionsPanel, 10, 1, false);
		place(getQuesThreeCyesRadioButton(), questionsPanel, 11, 1, false);

		place(getQuesFourAyesRadioButton(), questionsPanel, 13, 1, false);
		place(getQuesFourByesRadioButton(), questionsPanel, 14, 1, false);
		place(getQuesFourCyesRadioButton(), questionsPanel, 15, 1, false);
		place(getQuesFourDyesRadioButton(), questionsPanel, 16, 1, false);

		place(getQuesFiveAyesRadioButton(), questionsPanel, 18, 1, false);
		place(getQuesFiveByesRadioButton(), questionsPanel, 19, 1, false);
		place(getQuesFiveCyesRadioButton(), questionsPanel, 20, 1, false);

		gbc.insets = new Insets(0, 60, 0, 0);

		place(getQuesOneAnoRadioButton(), questionsPanel, 1, 2, false);
		place(getQuesOneBnoRadioButton(), questionsPanel, 2, 2, false);
		place(getQuesOneCnoRadioButton(), questionsPanel, 3, 2, false);
		place(getQuesOneDnoRadioButton(), questionsPanel, 4, 2, false);

		place(getQuesTwoAnoRadioButton(), questionsPanel, 7, 2, false);

		place(getQuesThreeAnoRadioButton(), questionsPanel, 9, 2, false);
		place(getQuesThreeBnoRadioButton(), questionsPanel, 10, 2, false);
		place(getQuesThreeCnoRadioButton(), questionsPanel, 11, 2, false);

		place(getQuesFourAnoRadioButton(), questionsPanel, 13, 2, false);
		place(getQuesFourBnoRadioButton(), questionsPanel, 14, 2, false);
		place(getQuesFourCnoRadioButton(), questionsPanel, 15, 2, false);
		place(getQuesFourDnoRadioButton(), questionsPanel, 16, 2, false);

		place(getQuesFiveAnoRadioButton(), questionsPanel, 18, 2, false);
		place(getQuesFiveBnoRadioButton(), questionsPanel, 19, 2, false);
		place(getQuesFiveCnoRadioButton(), questionsPanel, 20, 2, false);

		return questionsPanel;
	}

	private JPanel createDocumentsPanel() {
		JPanel documentsPanel = new JPanel(new GridBagLayout());
		gbcReset();
		gbc.anchor = GridBagConstraints.NORTHWEST;

		gbc.insets = new Insets(0, 0, 70, 0);
		place(new JLabel("Please upload the applicable documents."), documentsPanel, 0, 0, false);

		gbc.insets = new Insets(0, 0, 30, 0);
		place(new JLabel("Proof of Present Address:"), documentsPanel, 1, 0, true);
		place(new JLabel("Proof of Date of Birth:"), documentsPanel, 2, 0, true);
		place(new JLabel("Old Passport (first two and last two pages):"), documentsPanel, 3, 0, true);

		gbc.insets = new Insets(0, 200, 0, 0);
		place(getUploadAddressProofButton(), documentsPanel, 1, 1, false);
		place(getUploadBirthProofButton(), documentsPanel, 2, 1, false);
		place(getUploadOldPassportButton(), documentsPanel, 3, 1, false);

		return documentsPanel;
	}

	private JPanel createAppointmentPanel() {
		JPanel appointmentPanel = new JPanel(new GridBagLayout());
		gbcReset();
		gbc.anchor = GridBagConstraints.NORTHWEST;

		place(getPassportOfficeComboBox(), appointmentPanel, 0, 2, false);
		place(getAppointmentDatePicker(), appointmentPanel, 1, 2, false);

		gbc.insets = new Insets(0, 0, 30, 0);
		place(new JLabel("Select Passport Office:"), appointmentPanel, 0, 0, true);
		place(new JLabel("Select date of Appointment:"), appointmentPanel, 1, 0, true);

		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(30, 20, 0, 20);
		place(getCheckSlotsButton(), appointmentPanel, 2, 1, false);

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
		place(new JLabel(payerName), paymentPanel, 4, 1, true);
		place(new JLabel(paymentAmount), paymentPanel, 5, 1, true);

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

	@Override
	public JButton getSubmitButton() {
		return submitButton;
	}

	@Override
	public JButton getCancelButton() {
		return cancelButton;
	}

	@Override
	public JTabbedPane getFormTabbedPane() {
		return tabbedPane;
	}

	// Personal Details Panel Getters

	@Override
	public JRadioButton getAppTypeFreshRadioButton() {
		return appTypeFresh;
	}

	@Override
	public JRadioButton getAppTypeReissueRadioButton() {
		return appTypeReissue;
	}

	@Override
	public JRadioButton getGenderMaleRadioButton() {
		return genderMale;
	}

	@Override
	public JRadioButton getGenderFemaleRadioButton() {
		return genderFemale;
	}

	@Override
	public JRadioButton getGenderTransRadioButton() {
		return genderTrans;
	}

	@Override
	public JTextField getNameTextField() {
		return nameTextField;
	}

	@Override
	public DatePicker getDateOfBirthPicker() {
		return dateOfBirthPicker;
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
	public JTextArea getEmergencyAddressTextField() {
		return emergencyAddressTextField;
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

	// Questions Panel Getters

	@Override
	public JRadioButton getQuesOneAyesRadioButton() {
		return quesOneAyes;
	}

	@Override
	public JRadioButton getQuesOneAnoRadioButton() {
		return quesOneAno;
	}

	@Override
	public JRadioButton getQuesOneByesRadioButton() {
		return quesOneByes;
	}

	@Override
	public JRadioButton getQuesOneBnoRadioButton() {
		return quesOneBno;
	}

	@Override
	public JRadioButton getQuesOneCyesRadioButton() {
		return quesOneCyes;
	}

	@Override
	public JRadioButton getQuesOneCnoRadioButton() {
		return quesOneCno;
	}

	@Override
	public JRadioButton getQuesOneDyesRadioButton() {
		return quesOneDyes;
	}

	@Override
	public JRadioButton getQuesOneDnoRadioButton() {
		return quesOneDno;
	}

	@Override
	public JRadioButton getQuesTwoAyesRadioButton() {
		return quesTwoAyes;
	}

	@Override
	public JRadioButton getQuesTwoAnoRadioButton() {
		return quesTwoAno;
	}

	@Override
	public JRadioButton getQuesThreeAyesRadioButton() {
		return quesThreeAyes;
	}

	@Override
	public JRadioButton getQuesThreeAnoRadioButton() {
		return quesThreeAno;
	}

	@Override
	public JRadioButton getQuesThreeByesRadioButton() {
		return quesThreeByes;
	}

	@Override
	public JRadioButton getQuesThreeBnoRadioButton() {
		return quesThreeBno;
	}

	@Override
	public JRadioButton getQuesThreeCyesRadioButton() {
		return quesThreeCyes;
	}

	@Override
	public JRadioButton getQuesThreeCnoRadioButton() {
		return quesThreeCno;
	}

	@Override
	public JRadioButton getQuesFourAyesRadioButton() {
		return quesFourAyes;
	}

	@Override
	public JRadioButton getQuesFourAnoRadioButton() {
		return quesFourAno;
	}

	@Override
	public JRadioButton getQuesFourByesRadioButton() {
		return quesFourByes;
	}

	@Override
	public JRadioButton getQuesFourBnoRadioButton() {
		return quesFourBno;
	}

	@Override
	public JRadioButton getQuesFourCyesRadioButton() {
		return quesFourCyes;
	}

	@Override
	public JRadioButton getQuesFourCnoRadioButton() {
		return quesFourCno;
	}

	@Override
	public JRadioButton getQuesFourDyesRadioButton() {
		return quesFourDyes;
	}

	@Override
	public JRadioButton getQuesFourDnoRadioButton() {
		return quesFourDno;
	}

	@Override
	public JRadioButton getQuesFiveAyesRadioButton() {
		return quesFiveAyes;
	}

	@Override
	public JRadioButton getQuesFiveAnoRadioButton() {
		return quesFiveAno;
	}

	@Override
	public JRadioButton getQuesFiveByesRadioButton() {
		return quesFiveByes;
	}

	@Override
	public JRadioButton getQuesFiveBnoRadioButton() {
		return quesFiveBno;
	}

	@Override
	public JRadioButton getQuesFiveCyesRadioButton() {
		return quesFiveCyes;
	}

	@Override
	public JRadioButton getQuesFiveCnoRadioButton() {
		return quesFiveCno;
	}

	// Document Panel Getters

	@Override
	public JButton getUploadAddressProofButton() {
		return uploadAddressProof;
	}

	@Override
	public JButton getUploadBirthProofButton() {
		return uploadBirthProof;
	}

	@Override
	public JButton getUploadOldPassportButton() {
		return uploadOldPassport;
	}

	@Override
	public JFileChooser getDocumentChooser() {
		return documentChooser;
	}

	// Appointment Panel Getters

	@Override
	public JComboBox<String> getPassportOfficeComboBox() {
		return passportOfficeComboBox;
	}

	@Override
	public DatePicker getAppointmentDatePicker() {
		return appointmentDatePicker;
	}

	@Override
	public JList<String> getSlotList() {
		return slotList;
	}

	@Override
	public JButton getCheckSlotsButton() {
		return checkSlots;
	}

	// Payment Panel Getters & Setters

	@Override
	public JButton getPayButton() {
		return payButton;
	}

	@Override
	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	@Override
	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	@Override
	public JFrame getFrame() {
		return this;
	}
}