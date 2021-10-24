package edu.manipal.icas.simple.utils;

import java.awt.Font;

import javax.swing.ImageIcon;

/**
 * A utility class that specifies all the images and fonts that are used to
 * create a rich user interface.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public final class ResourceConstants {
	private ResourceConstants() {
	}

	/**
	 * Stores the login icon that appears above the product name in the login view.
	 */
	public static final ImageIcon IMAGE_CITIZEN_LOGIN_ICON = new ImageIcon("res/images/citizen_login_icon.png");

	/** Stores the font style of headings that appear in the view. */
	public static final Font FONT_HEADING_BOLD = new Font("Roboto", Font.BOLD, 20);

	/** Holds the font style of sub headings in the view. */
	public static final Font FONT_TEXT_PLAIN = new Font("Roboto", Font.PLAIN, 12);

	/** Contains the font style of labels that appear in the view. */
	public static final Font FONT_TEXT_BOLD = new Font("Roboto", Font.BOLD, 13);
}
