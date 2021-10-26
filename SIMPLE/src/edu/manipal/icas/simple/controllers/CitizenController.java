package edu.manipal.icas.simple.controllers;

import edu.manipal.icas.simple.impl.views.CitizenLoginViewImpl;
import edu.manipal.icas.simple.views.CitizenLoginView;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import edu.manipal.icas.simple.impl.databases.MsAccessCitizenDatabase;



public class CitizenController  {
	private CitizenLoginView citizenView;
	
	public CitizenController(CitizenLoginView citizenView)
	{
		this.citizenView =citizenView;
		this.citizenView.addLoginListener(new LoginListener());
		
		
	}
	
	
	
	
	
	
	class LoginListener implements ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JTextField citizenEmail = citizenView.getEmailTextField();
			JPasswordField citizenPassword = citizenView.getPasswordPasswordField();
			
			String emialId = citizenEmail.getText();
			String password = citizenPassword.getText();
			System.out.println(emialId);
			System.out.println(password);
			
			
			
			
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	
	
	
	
	
	

}
