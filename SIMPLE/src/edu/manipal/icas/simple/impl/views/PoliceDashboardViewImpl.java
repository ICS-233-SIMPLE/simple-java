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
	private JComboBox<String> applicationIDComboBox;
	private JButton nextButton;
	private JButton adverseButton;
	private JButton clearButton;
	private JButton incompleteButton;
	private JLabel officerIDLabel;
	private JLabel officerIDLabelHolder;
	private JButton logoutButton;
	private JButton viewDocumentButton;
	private String entries[][] = { { "", "", "" } };

	public PoliceDashboardViewImpl() {
		String appId[] = { "100013", "100343" };
		applicationIDComboBox = new JComboBox(appId);
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
		officerIDLabelHolder = new JLabel();

		officerIDLabel = new JLabel("Officer ID:");
		logoutButton = new JButton("Logout");

		initialisePoliceUI();
	}

	private void initialisePoliceUI() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Police Officer Dashboard");
		JPanel panel = new JPanel();

		JLabel officerIDLabel = new JLabel("Officer ID :");

		String column[] = { "Application ID", "Name", "Address" };

		JTable applicationTable = new JTable(entries, column);

		officerIDLabel.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		panel.setLayout(new MigLayout("", "[] [] [grow] 30 [] [] [] 20 [] 30 [] ", "[] [] 80 [] [grow]"));
		JScrollPane applicationScroll = new JScrollPane(applicationTable);
		panel.add(prevButton, "left");
		panel.add(applicationIDComboBox, "growx");
		panel.add(nextButton);
		panel.add(adverseButton, "growx");
		panel.add(clearButton, "growx");
		panel.add(incompleteButton, "growx");
		panel.add(viewDocumentButton, "growx");
		panel.add(officerIDLabel, "split");
		panel.add(officerIDLabelHolder);
		panel.add(applicationScroll);
		panel.add(logoutButton, "wrap");
		panel.add(applicationScroll, "growx, span");

		add(panel);
		setIconImage((ResourceConstants.IMAGE_POLICE_DASHBOARD_ICON).getImage());
		setSize(new Dimension(1500, 1000));
		setLocationRelativeTo(null);
		setResizable(true);
		setVisible(true);

	}

	@Override
	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public JButton getPreviousApplicationIdButton() {
		// TODO Auto-generated method stub
		return prevButton;
	}

	@Override
	public JButton getNextApplicationIdButton() {
		// TODO Auto-generated method stub
		return nextButton;
	}

	@Override
	public JComboBox<String> getApplicationIdCombo() {
		// TODO Auto-generated method stub
		return applicationIDComboBox;
	}

	@Override
	public JLabel getofficerIDLabel() {
		// TODO Auto-generated method stub
		return officerIDLabelHolder;
	}

	@Override
	public JButton getLogOutButton() {
		// TODO Auto-generated method stub
		return logoutButton;
	}

	@Override
	public JButton getviewDocumentButton() {
		// TODO Auto-generated method stub
		return viewDocumentButton;
	}

	@Override
	public JButton getAdverseButton() {
		// TODO Auto-generated method stub
		return adverseButton;
	}

	@Override
	public JButton getClearButton() {
		// TODO Auto-generated method stub
		return clearButton;
	}

	@Override
	public JButton getIncompleteButton() {
		// TODO Auto-generated method stub
		return incompleteButton;
	}

	@Override
	public String[][] getPendingVerificationTableEntries() {
		// TODO Auto-generated method stub
		return entries;
	}

}