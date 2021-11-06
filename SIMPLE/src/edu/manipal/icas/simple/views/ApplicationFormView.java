package edu.manipal.icas.simple.views;

import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.lgooddatepicker.components.DatePicker;

import edu.manipal.icas.simple.models.application.AcceptedDocumentType;
import edu.manipal.icas.simple.models.application.ApplicationQuestion;

/**
 * View that enables a citizen to apply for a new passport.
 * 
 * @author Priya Parashar (priya.parashar@learner.manipal.edu)
 *
 */
public interface ApplicationFormView extends View {
	public JButton getSubmitButton();

	public JButton getCancelButton();

	// Personal Details Panel Getters

	public ButtonGroup getApplicationTypeButtonGroup();

	public JTextField getPlaceOfBirthTextField();

	public JTextArea getPermanentAddressTextField();

	public JTextArea getPresentAddressTextField();

	public JTextField getMothersNameTextField();

	public JTextField getFathersNameTextField();

	public JTextField getEmergencyNameTextField();

	public JTextField getEmergencyPhoneTextField();

	public JTextField getEmergencyEmailTextField();

	public JCheckBox getSelfDeclarationCheckBox();
	
	public JButton getFindCitizenButton();

	// Questions Panel Getters

	public Map<ApplicationQuestion, ButtonGroup> getApplicationQuestionButtonGroups();

	// Document Panel Getters

	public Map<AcceptedDocumentType, JButton> getDocumentUploadButtons();
	
	// Appointment Panel Getters

	public DatePicker getAppointmentDatePicker();

	public JButton getBookSlotButton();

	// Payment Panel Getters & Setters

	public JButton getPayButton();

	public JLabel getPayerNameLabel();
	
	public JLabel getPaymentAmountLabel();
}
