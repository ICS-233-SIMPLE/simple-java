package edu.manipal.icas.simple.impl.views;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

public class BiometricDashboard {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BiometricDashboard window = new BiometricDashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BiometricDashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 812, 522);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		ImageIcon img = new ImageIcon(this.getClass().getResource("/img009.png"));
	    label = (JLabel) setIcon(img);
		label.setBounds(183, 195, 166, 132);
		frame.getContentPane().add(label);
	}

	private Object setIcon(ImageIcon img) {
		// TODO Auto-generated method stub
		return null;
	}
}
