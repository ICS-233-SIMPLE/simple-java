package edu.manipal.icas.simple.impl.views;

import java.awt.Color;


import java.awt.Component;
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
import edu.manipal.icas.simple.views.BiometricOfficerView;

/**
 * Concrete class defining the biometric officer view
 * 
 * @author Yodhin Agarwal (yodhin.agarwal@learner.manipal.edu)
 */


public class BiometricOfficerViewImpl extends JFrame implements BiometricOfficerView {

	private JTextField applicationIDTextField;	
	private JButton scanButton;
	private JButton submitScanButton;
	private JComboBox biometricTypeComboBox;
	
	/**
	 * Instantiates all required components which need to be displayed
	 * in biometric officer view
	 */
	public BiometricOfficerViewImpl() {
		super("Biometric Officer Dashboard");
		applicationIDTextField = new JTextField();
		String[] biometricType = new String[] { "Fingerprint 1", "Fingerprint 2", "Fingerprint 3","Fingerprint 4", "Fingerprint 5", "Retinae"};
        biometricTypeComboBox = new JComboBox<>(biometricType);
		
		scanButton = new JButton("Click here to scan");
	    submitScanButton = new JButton("Submit scan");
		initialiseBiometricUi();
	}
	
	//Initialises the UI
	private void initialiseBiometricUi() {
	
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.HORIZONTAL;		
		g.gridwidth = 1;
		g.gridheight = 1;
		g.gridx = 0;
		g.gridy = 0;
		panel.add(new JLabel(), g);
		
		JLabel officerLabel = new JLabel("Officer name or session no.");
		officerLabel.setFont(ResourceConstants.FONT_HEADING_BOLD);
		g.gridx = 1;
		g.gridy = 1;
		g.gridwidth = 2;
		panel.add(officerLabel,g);
		
		g.gridy = 3;
		g.ipady = 30;
		panel.add(new JLabel(), g);

		JLabel applicationIDLabel = new JLabel("Enter application ID: ");
		applicationIDLabel.setFont(ResourceConstants.FONT_LABEL_BOLD);
		
		g.gridy = 4;
		g.ipady = 0;
		panel.add(applicationIDLabel, g);

		g.gridy = 5;
		panel.add(applicationIDTextField, g);
		
		g.gridy = 6;
		g.ipady = 10;
		panel.add(new JLabel(), g);
		
		JLabel biometricTypeLabel = new JLabel("Biometric Type: ");
		biometricTypeLabel.setFont(ResourceConstants.FONT_LABEL_BOLD);
		
		g.gridx = 0;
		g.gridy = 6;
		g.ipady = 0;
		panel.add(biometricTypeLabel,g);
		
		g.gridheight = 1; 
		g.gridwidth = 1;
		g.ipady = 0;
		g.gridx = 1;
		g.gridy = 8;
		biometricTypeComboBox.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		panel.add(biometricTypeComboBox,g);
		
		g.gridy = 9;
		g.gridx = 1;
		g.ipady = 30;
		panel.add(new JLabel(), g);

		g.ipady = 0;
		g.gridx = 1;
		g.gridy = 10;
		panel.add(scanButton, g);
		
		g.gridy = 9;
		g.gridx = 2;
		g.ipady = 30;
		panel.add(new JLabel(), g);

		g.ipady = 0;
		g.gridx = 2;
		g.gridy = 10;
		panel.add(submitScanButton, g);

		panel.setBackground(new Color(255, 255, 255));
		
		add(panel);
		setSize(new Dimension(300, 400));
		setLocationRelativeTo(null);
		setResizable(true);
		
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
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public JButton getScanButton() {
		// TODO Auto-generated method stub
		return scanButton;
	}

	@Override
	public JButton getSubmitScanButton() {
		// TODO Auto-generated method stub
		return submitScanButton;
	}

	@Override
	public JTextField getApplicationIDTextField() {
		// TODO Auto-generated method stub
		return applicationIDTextField;
	}
}