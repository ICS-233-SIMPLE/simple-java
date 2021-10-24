package edu.manipal.icas.simple.views;

import javax.swing.JFrame;

/**
 * Interface that defines operations to be implemented in the view
 * implementation classes.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public interface View {

	/**
	 * Gets the frame that is to be displayed to the user.
	 * 
	 * @return the frame
	 */
	JFrame getFrame();
}
