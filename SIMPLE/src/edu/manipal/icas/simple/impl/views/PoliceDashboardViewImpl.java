package edu.manipal.icas.simple.impl.views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.manipal.icas.simple.utils.ImageUtils;
import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.views.PoliceDashboardView;
import net.miginfocom.swing.MigLayout;

/**
 * Concrete class that defines the police officer dashboard view.
 *
 * @author Mehsheed(syed.ahmed2@learner.manipal.edu)
 *
 *
 */
public class PoliceDashboardViewImpl extends JFrame implements PoliceDashboardView {

	private JButton prevButton;
	private JComboBox<Integer> applicationIdComboBox;
	private JButton nextButton;
	private JButton adverseButton;
	private JButton clearButton;
	private JButton incompleteButton;
	private JLabel officerIdLabel;
	private JLabel officerIdLabelHolder;
	private JButton logoutButton;
	private JButton viewDocumentButton;
	private JTable applicationTable;

	public PoliceDashboardViewImpl() {
		super("Police Officer Dashboard");
		applicationIdComboBox = new JComboBox<Integer>();
		prevButton = new JButton(ImageUtils.getScaledImage(ResourceConstants.IMAGE_PREVIOUS_BUTTON_ICON, 20, 20));
		nextButton = new JButton(ImageUtils.getScaledImage(ResourceConstants.IMAGE_NEXT_BUTTON_ICON, 20, 20));
		adverseButton = new JButton("Adverse");
		adverseButton.setBackground(new Color(204, 0, 0));
		adverseButton.setForeground(Color.WHITE);
		clearButton = new JButton("Clear");
		clearButton.setBackground(new Color(0, 204, 0));
		clearButton.setForeground(Color.WHITE);

		incompleteButton = new JButton("Incomplete");
		incompleteButton.setBackground(new Color(174, 174, 0));
		incompleteButton.setForeground(Color.WHITE);

		viewDocumentButton = new JButton("View Adress Proof");
		officerIdLabelHolder = new JLabel();

		officerIdLabel = new JLabel("Officer badge ID:");
		logoutButton = new JButton("Logout");

		initialisePoliceUI();
	}

	private void initialisePoliceUI() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();

		JLabel officerIdLabel = new JLabel("Officer ID :");

		applicationTable = new JTable(new DefaultTableModel(new Object[] { "Application ID", "Name", "Address" }, 0));

		officerIdLabel.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		panel.setLayout(new MigLayout("", "[] [] [grow] 30 [] [] [] 20 [] 30 [] ", "[] [] 80 [] [grow]"));
		JScrollPane applicationScroll = new JScrollPane(applicationTable);
		panel.add(prevButton, "left");
		panel.add(applicationIdComboBox, "growx");
		panel.add(nextButton);
		panel.add(adverseButton, "growx");
		panel.add(clearButton, "growx");
		panel.add(incompleteButton, "growx");
		panel.add(viewDocumentButton, "growx");
		panel.add(officerIdLabel, "split");
		panel.add(officerIdLabelHolder);
		panel.add(applicationScroll);
		panel.add(logoutButton, "wrap");
		panel.add(applicationScroll, "growx, span");

		add(panel);
		setIconImage((ResourceConstants.IMAGE_POLICE_DASHBOARD_ICON).getImage());
		setSize(new Dimension(1500, 1000));
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(true);

	}

	@Override
	public JFrame getFrame() {
		return this;
	}

	@Override
	public JButton getPreviousApplicationIdButton() {
		return prevButton;
	}

	@Override
	public JButton getNextApplicationIdButton() {
		return nextButton;
	}

	@Override
	public JComboBox<Integer> getApplicationIdComboBox() {
		return applicationIdComboBox;
	}

	@Override
	public JButton getLogOutButton() {
		return logoutButton;
	}

	@Override
	public JButton getViewDocumentButton() {
		return viewDocumentButton;
	}

	@Override
	public JButton getAdverseButton() {
		return adverseButton;
	}

	@Override
	public JButton getClearButton() {
		return clearButton;
	}

	@Override
	public JButton getIncompleteButton() {
		return incompleteButton;
	}

	@Override
	public JTable getPendingVerificationTable() {
		return applicationTable;
	}

	@Override
	public JLabel getOfficerIdLabel() {
		return officerIdLabelHolder;
	}

}