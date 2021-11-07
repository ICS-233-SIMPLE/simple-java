package edu.manipal.icas.simple.views;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

/**
 * View that enables a citizen to apply for a new passport.
 * 
 * @author Priya Parashar (priya.parashar@learner.manipal.edu)
 *
 */
public interface ApplicationFormView extends View {
	public JTabbedPane getFormTabbedPane();

	public JButton getSubmitButton();

	public JButton getCancelButton();

	// Personal Details Panel Getters

	public JRadioButton getAppTypeFreshRadioButton();

	public JRadioButton getAppTypeReissueRadioButton();

	public JRadioButton getGenderMaleRadioButton();

	public JRadioButton getGenderFemaleRadioButton();

	public JRadioButton getGenderTransRadioButton();

	public JTextField getNameTextField();

	public DatePicker getDateOfBirthPicker();

	public JTextField getPlaceOfBirthTextField();

	public JTextArea getPermanentAddressTextField();

	public JTextArea getPresentAddressTextField();

	public JTextField getMothersNameTextField();

	public JTextField getFathersNameTextField();

	public JTextField getEmergencyNameTextField();

	public JTextArea getEmergencyAddressTextField();

	public JTextField getEmergencyPhoneTextField();

	public JTextField getEmergencyEmailTextField();

	public JCheckBox getSelfDeclarationCheckBox();

	// Questions Panel Getters

	public JRadioButton getQuesOneAyesRadioButton();

	public JRadioButton getQuesOneAnoRadioButton();

	public JRadioButton getQuesOneByesRadioButton();

	public JRadioButton getQuesOneBnoRadioButton();

	public JRadioButton getQuesOneCyesRadioButton();

	public JRadioButton getQuesOneCnoRadioButton();

	public JRadioButton getQuesOneDyesRadioButton();

	public JRadioButton getQuesOneDnoRadioButton();

	public JRadioButton getQuesTwoAyesRadioButton();

	public JRadioButton getQuesTwoAnoRadioButton();

	public JRadioButton getQuesThreeAyesRadioButton();

	public JRadioButton getQuesThreeAnoRadioButton();

	public JRadioButton getQuesThreeByesRadioButton();

	public JRadioButton getQuesThreeBnoRadioButton();

	public JRadioButton getQuesThreeCyesRadioButton();

	public JRadioButton getQuesThreeCnoRadioButton();

	public JRadioButton getQuesFourAyesRadioButton();

	public JRadioButton getQuesFourAnoRadioButton();

	public JRadioButton getQuesFourByesRadioButton();

	public JRadioButton getQuesFourBnoRadioButton();

	public JRadioButton getQuesFourCyesRadioButton();

	public JRadioButton getQuesFourCnoRadioButton();

	public JRadioButton getQuesFourDyesRadioButton();

	public JRadioButton getQuesFourDnoRadioButton();

	public JRadioButton getQuesFiveAyesRadioButton();

	public JRadioButton getQuesFiveAnoRadioButton();

	public JRadioButton getQuesFiveByesRadioButton();

	public JRadioButton getQuesFiveBnoRadioButton();

	public JRadioButton getQuesFiveCyesRadioButton();

	public JRadioButton getQuesFiveCnoRadioButton();

	// Document Panel Getters

	public JButton getUploadAddressProofButton();

	public JButton getUploadBirthProofButton();

	public JButton getUploadOldPassportButton();

	public JFileChooser getDocumentChooser();

	// Appointment Panel Getters

	public JComboBox<String> getPassportOfficeComboBox();

	public DatePicker getAppointmentDatePicker();

	public JList<String> getSlotList();

	public JButton getCheckSlotsButton();

	// Payment Panel Getters & Setters

	public JButton getPayButton();

	public void setPayerName(String payerName);

	public void setPaymentAmount(String paymentAmount);
}
