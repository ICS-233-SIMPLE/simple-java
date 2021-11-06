package edu.manipal.icas.simple.impl.views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;

import edu.manipal.icas.simple.utils.ResourceConstants;
import edu.manipal.icas.simple.views.PoliceDashboardView;

public class PoliceDashboardViewImpl extends JFrame implements PoliceDashboardView{

	private JButton prevButton;
	private JComboBox applicationIDComboBox;
	private JButton nextButton;
	private JButton adverseButton;
	private JButton clearButton;
	private JButton incompleteButton;
	private JLabel officerIDLabel;
	private JButton logoutButton;
	
	public PoliceDashboardImpl() {
		applicationIDComboBox = new JComboBox();
		prevButton = new JButton("Prev");
		nextButton = new JButton("Next");
		adverseButton = new JButton("Adverse");
		clearButton = new JButton("Clear");
		incompleteButton = new JButton("Incomplete");
		officerIDLabel = new JLabel("Officer ID:");		
		logoutButton = new JButton("Logout");
		
		initialisePoliceUI();
	}
	
	private void initialisePoliceUI() {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Police Officer Dashboard");
		JPanel panel = new JPanel();
		
        JLabel officerIDLabel = new JLabel("Officer ID :");
        String response [] [] = 
			{
					{"120970871", "Yodhin Agarwal", "xyz sdwdawg"}
			};
		
		String column [] =
			{
					"Application ID", "Name", "Address"	
			};
		
        JTable applicationTable = new JTable(response, column);
        
       
		
		officerIDLabel.setFont(ResourceConstants.FONT_SUBHEADING_PLAIN);
		panel.setLayout(new MigLayout("","[] [grow]","[] [] 80 [] [grow]"));	
		
		panel.add(prevButton, "left");
		panel.add(applicationIDComboBox,"growx");
		panel.add(nextButton);
		panel.add(adverseButton);
		panel.add(clearButton);
		panel.add(incompleteButton);
		panel.add(officerIDLabel);
		panel.add(logoutButton, "wrap");
		panel.add(applicationTable, "growx, span");
		
		JScrollPane applicationScroll = new JScrollPane(applicationTable);
		panel.add(applicationScroll, "growx, growy, span");
		
		
		
		
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



	public JFrame getFrame(){
		// TODO Auto-generated method stub
		return this;
	}
	
	
	
}