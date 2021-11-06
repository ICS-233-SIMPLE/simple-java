package edu.manipal.icas.simple.impl.views;

import java.awt.Color;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.utils.StringConstants;
import edu.manipal.icas.simple.views.BiometricOfficerView;
import net.miginfocom.swing.MigLayout;

/**
 * Concrete class defining the biometric officer view
 * 
 * @author Yodhin Agarwal (yodhin.agarwal@learner.manipal.edu)
 */


public class BiometricOfficerViewImpl extends JFrame implements BiometricOfficerView {

	private JComboBox applicationIDComboBox;	
	private JButton scanButton;
	private JButton submitScanButton;
	private JComboBox biometricTypeComboBox;
	private JButton logoutButton;
	private JButton prevButton;
	private JButton nextButton;
	private ImageIcon fingerprintImg;
	
	
	
	/**
	 * Instantiates all required components which need to be displayed
	 * in biometric officer view
	 */
	public BiometricOfficerViewImpl() {
		String[] applicationID = new String[] {"Application ID"};
		applicationIDComboBox = new JComboBox<>(applicationID);
		String[] biometricType = new String[] { "Four fingers (left)", "Four fingers (right)", "Thumbs", "Photo"};
        biometricTypeComboBox = new JComboBox<>(biometricType);
		logoutButton = new JButton("Logout");
		scanButton = new JButton("Scan");
	    submitScanButton = new JButton("Submit scan");
	    prevButton = new JButton("Prev");
	    nextButton = new JButton("Next");
		initialiseBiometricUi();
	}
	
	//Initialises the UI
	private void initialiseBiometricUi() {
		JFrame frame = new JFrame("Biometric Officer Dashboard");
		JPanel panel = new JPanel();
		
		JLabel officerIDLabel = new JLabel("Officer ID :");
		
		JLabel fingerprintImg = new JLabel();
		

		
		officerIDLabel.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		panel.setLayout(new MigLayout("","[] [grow]","[] [] 80 [] [grow]"));		
	
		panel.add(prevButton, "left");
		panel.add(applicationIDComboBox,"growx");
		panel.add(nextButton);
		panel.add(biometricTypeComboBox, "growx");
		panel.add(scanButton, "cell 3 0");
		panel.add(submitScanButton, "cell 4 0");
		panel.add(officerIDLabel);
		panel.add(logoutButton, "right, wrap");		
		
		
		
		add(panel);
		setSize(new Dimension(1500,1000));
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
	
	public JComboBox getApplicationIDComboBox() {
		// TODO Auto-generated method stub
		return applicationIDComboBox;
	}
	@Override
	public JButton getPrevButton() {
		// TODO Auto-generated method stub
		return prevButton;
	}
	@Override
	public JButton getNextButton() {
		// TODO Auto-generated method stub
		return nextButton;
	}
	@Override
	public JButton getLogoutButton() {
		// TODO Auto-generated method stub
		return logoutButton;
	}

	@Override
	public JTextField getApplicationIDTextField() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}