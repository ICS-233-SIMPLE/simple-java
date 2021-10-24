package edu.manipal.icas.simple;

import edu.manipal.icas.simple.impl.views.CitizenLoginViewImpl;
import edu.manipal.icas.simple.views.CitizenLoginView;

public class Main {

	public static void main(String[] args) {
		System.out.println("SIMPLE");

		CitizenLoginView view = new CitizenLoginViewImpl();
		view.getFrame().setVisible(true);
	}

}
