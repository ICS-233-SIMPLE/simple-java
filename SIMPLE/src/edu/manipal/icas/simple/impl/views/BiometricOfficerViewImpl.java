package edu.manipal.icas.simple.impl.views;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.JTextField;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.util.PropertiesManager;

import edu.manipal.icas.simple.utils.ImageUtils;
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

	private JComboBox<Integer> applicationIDComboBox;
	private JButton scanButton;
	private JButton submitScanButton;
	private JComboBox<String> biometricTypeComboBox;
	private JButton logoutButton;
	private JButton prevButton;
	private JButton nextButton;
	private JLabel officerIDLabel;
	private SwingController pdfController;

	/**
	 * Instantiates all required components which need to be displayed in biometric
	 * officer view
	 */
	public BiometricOfficerViewImpl() {
		super("Biometric Officer Dashboard");
		applicationIDComboBox = new JComboBox<>();
		biometricTypeComboBox = new JComboBox<String>(StringConstants.ACCEPTED_BIOMETRIC_TYPE_NAMES.keySet().toArray(new String[0]));
		logoutButton = new JButton("Logout");
		scanButton = new JButton("Scan");
		submitScanButton = new JButton("Submit scan");
		prevButton = new JButton();
		prevButton.setIcon(ImageUtils.getScaledImage(ResourceConstants.IMAGE_PREVIOUS_BUTTON_ICON, 15, 15));
		nextButton = new JButton();
		nextButton.setIcon(ImageUtils.getScaledImage(ResourceConstants.IMAGE_NEXT_BUTTON_ICON, 15, 15));
		pdfController = new SwingController();
		initialiseBiometricUi();
	}

	// Initialises the UI
	private void initialiseBiometricUi() {
		JPanel panel = new JPanel();

		officerIDLabel = new JLabel("Officer ID :");

		officerIDLabel.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		panel.setLayout(new MigLayout("", "[] [grow]", "[] [grow]"));

		panel.add(prevButton, "left");
		panel.add(applicationIDComboBox, "growx");
		panel.add(nextButton);
		panel.add(biometricTypeComboBox, "growx");
		panel.add(scanButton, "cell 3 0");
		panel.add(submitScanButton, "cell 4 0");
		panel.add(officerIDLabel);
		panel.add(logoutButton, "right, wrap");
		
		PropertiesManager properties = new PropertiesManager(System.getProperties(),
				ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
		properties.setBoolean("application.viewerpreferences.hidetoolbar", Boolean.TRUE);
		SwingViewBuilder factory = new SwingViewBuilder(pdfController, properties);
		panel.add(factory.buildViewerPanel(), "span 10 1, growx, growy");

		add(panel);
		setSize(new Dimension(1500, 1000));
		setLocationRelativeTo(null);
		setIconImage(ResourceConstants.IMAGE_BIOMETRICS_OFFICER_DASHBOARD_ICON.getImage());
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);
	}

	@Override
	public JFrame getFrame() {
		return this;
	}

	@Override
	public JButton getScanButton() {
		return scanButton;
	}

	@Override
	public JButton getSubmitScanButton() {
		return submitScanButton;
	}

	@Override
	public JComboBox<Integer> getApplicationIdComboBox() {
		return applicationIDComboBox;
	}

	@Override
	public JButton getPrevAppIdButton() {
		return prevButton;
	}

	@Override
	public JButton getNextAppIdButton() {
		return nextButton;
	}

	@Override
	public JButton getLogoutButton() {
		return logoutButton;
	}

	@Override
	public JLabel getOfficerIdButton() {
		return officerIDLabel;
	}

	@Override
	public JComboBox<String> getBiometricTypeComboBox() {
		return biometricTypeComboBox;
	}

	@Override
	public SwingController getPdfController() {
		return pdfController;
	}

}