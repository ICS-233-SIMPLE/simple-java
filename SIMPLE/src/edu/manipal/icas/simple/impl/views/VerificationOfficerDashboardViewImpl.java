package edu.manipal.icas.simple.impl.views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;
import org.icepdf.ri.util.PropertiesManager;

import edu.manipal.icas.simple.utils.ImageUtils;
import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.views.VerificationOfficerDashboardView;

/**
 * Concrete class that defines the verification officer dashboard view.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class VerificationOfficerDashboardViewImpl extends JFrame implements VerificationOfficerDashboardView {
	private JScrollPane documentNameScrollPane;
	private JPanel containerPanel;
	private JPanel toolBarPanel;
	private JButton nextApplicationIdButton;
	private JButton previousApplicationIdButton;
	private JComboBox<Integer> applicationIdComboBox;
	private JLabel officerIdLabel;
	private JButton logoutButton;
	private JButton markAsInauthenticButton;
	private JButton markAsVerifiedButton;
	private SwingController pdfController;
	private JTable documentsList;

	/**
	 * Instantiates all the required buttons and panels that are to be a part of the
	 * verification officer's dashboard.
	 */
	public VerificationOfficerDashboardViewImpl() {
		super("Verification Officer Dashboard");
		documentNameScrollPane = new JScrollPane();
		containerPanel = new JPanel();
		toolBarPanel = new JPanel();
		nextApplicationIdButton = new JButton(
				ImageUtils.getScaledImage(ResourceConstants.IMAGE_NEXT_BUTTON_ICON, 15, 15));
		previousApplicationIdButton = new JButton(
				ImageUtils.getScaledImage(ResourceConstants.IMAGE_PREVIOUS_BUTTON_ICON, 15, 15));
		applicationIdComboBox = new JComboBox<Integer>();
		officerIdLabel = new JLabel();
		logoutButton = new JButton("Logout");
		markAsInauthenticButton = new JButton("Mark as inauthentic");
		markAsVerifiedButton = new JButton("Mark as verified");
		pdfController = new SwingController();
		initialiseUi();
	}

	/**
	 * Initialises the verification officer dashboard view and populates it with the
	 * required panels.
	 */
	private void initialiseUi() {
		containerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		c.gridwidth = 2;
		c.weightx = 0.5;
		c.weighty = 0;
		c.gridx = 0;
		c.ipady = 10;
		c.gridy = 0;
		c.anchor = GridBagConstraints.PAGE_START;
		containerPanel.add(initialiseToolBarPanel(), c);

		c.gridy = 1;
		c.weightx = 0.5;
		c.ipady = 0;
		c.gridwidth = 1;
		c.weighty = 1;
		c.anchor = GridBagConstraints.LINE_START;
		c.fill = GridBagConstraints.BOTH;
		containerPanel.add(initialiseDocumentThumbnailViewScrollPane(), c);

		c.weightx = 1;
		c.gridx = 1;
		c.gridwidth = 1;
		c.fill = GridBagConstraints.BOTH;
		containerPanel.add(initialiseDocumentPreviewPanel(), c);

		containerPanel.setBackground(new Color(255, 255, 255));
		setIconImage(ImageUtils.getScaledImage(ResourceConstants.IMAGE_VERIFICATION_ICON, 25, 25).getImage());
		add(containerPanel);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setResizable(false);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				System.exit(0);
			}
		});
	}

	/**
	 * Initialises the document preview panel that the verification officer uses to
	 * preview citizen documents.
	 * 
	 * @return the document preview panel
	 */
	private JPanel initialiseDocumentPreviewPanel() {
		PropertiesManager properties = new PropertiesManager(System.getProperties(),
				ResourceBundle.getBundle(PropertiesManager.DEFAULT_MESSAGE_BUNDLE));
		properties.setBoolean("application.viewerpreferences.hidetoolbar", Boolean.TRUE);
		SwingViewBuilder factory = new SwingViewBuilder(pdfController, properties);
		JPanel documentPreviewPanel = factory.buildViewerPanel();
		return documentPreviewPanel;
	}

	/**
	 * Initialises the tool bar panel which enables the verification officer to view
	 * all the documents of a citizen based on their application ID, mark the
	 * application as "verified" or "inauthentic", and logout.
	 * 
	 * @return the tool bar panel
	 */
	private JPanel initialiseToolBarPanel() {
		toolBarPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.LINE_START;
		c.gridheight = 1;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 5;
		toolBarPanel.add(new JLabel(), c);

		c.gridx = 1;
		c.weightx = 0;
		c.ipadx = 0;
		toolBarPanel.add(previousApplicationIdButton, c);

		c.gridx = 2;
		toolBarPanel.add(applicationIdComboBox, c);

		c.gridx = 3;
		toolBarPanel.add(nextApplicationIdButton, c);

		c.gridx = 4;
		c.ipadx = 20;
		toolBarPanel.add(new JLabel(), c);

		c.gridx = 5;
		c.ipadx = 0;
		markAsInauthenticButton.setBackground(new Color(204, 0, 0));
		markAsInauthenticButton.setForeground(Color.WHITE);
		toolBarPanel.add(markAsInauthenticButton, c);

		c.gridx = 6;
		markAsVerifiedButton.setBackground(new Color(0, 204, 0));
		markAsVerifiedButton.setForeground(Color.WHITE);
		toolBarPanel.add(markAsVerifiedButton, c);

		c.weightx = 1;
		c.anchor = GridBagConstraints.LINE_END;
		c.gridx = 7;
		toolBarPanel.add(new JLabel("Officer ID: "), c);

		c.weightx = 0;
		c.gridx = 8;
		toolBarPanel.add(officerIdLabel, c);

		c.gridx = 9;
		c.ipadx = 5;
		toolBarPanel.add(new JLabel(), c);

		c.gridx = 10;
		toolBarPanel.add(logoutButton, c);

		c.gridx = 11;
		c.ipadx = 5;
		toolBarPanel.add(new JLabel(), c);

		return toolBarPanel;
	}

	/**
	 * Initialises the scroll pane that displays the names of the citizen's
	 * documents.
	 * 
	 * @return the document name scroll pane
	 */
	private JScrollPane initialiseDocumentThumbnailViewScrollPane() {

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 1;

		documentsList = new JTable(new DefaultTableModel(new Object[] { "File name", "Authenticity" }, 0));
		panel.add(documentsList, c);
		documentNameScrollPane = new JScrollPane(panel);
		documentNameScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		return documentNameScrollPane;
	}

	@Override
	public JComboBox<Integer> getApplicationIdComboBox() {
		return applicationIdComboBox;
	}

	@Override
	public JButton getNextApplicationIdButton() {
		return nextApplicationIdButton;
	}

	@Override
	public JButton getPreviousApplicationIdButton() {
		return previousApplicationIdButton;
	}

	@Override
	public JButton getMarkAsInauthenticButton() {
		return markAsInauthenticButton;
	}

	@Override
	public JButton getMarkAsVerifiedButton() {
		return markAsVerifiedButton;
	}

	@Override
	public JLabel getOfficerIdLabel() {
		return officerIdLabel;
	}

	@Override
	public JButton getLogoutButton() {
		return logoutButton;
	}

	@Override
	public JTable getDocumentsListTable() {
		return documentsList;
	}

	@Override
	public JFrame getFrame() {
		return this;
	}

	@Override
	public SwingController getPdfController() {
		return pdfController;
	}

}
