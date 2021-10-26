package edu.manipal.icas.simple;

  

import edu.manipal.icas.simple.impl.views.CitizenLoginViewImpl;
import edu.manipal.icas.simple.views.CitizenLoginView;
import edu.manipal.icas.simple.controllers.CitizenController;

public class Main  {

	
	public static void main(String[] args) {
		System.out.println("SIMPLE");

		CitizenLoginViewImpl view = new CitizenLoginViewImpl();
		view.getFrame().setVisible(true);
		CitizenController czControl = new CitizenController(view);
		
		
		
		
	}
	
	

}