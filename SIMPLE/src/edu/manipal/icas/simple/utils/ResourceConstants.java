package edu.manipal.icas.simple.utils;

import java.awt.Font;

import javax.swing.ImageIcon;

import edu.manipal.icas.simple.resources.Resources;

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
	public static final ImageIcon IMAGE_CITIZEN_LOGIN_ICON = new ImageIcon(
			Resources.getResourceUri("images/citizen_login_icon.png"));

	/**
	 * Holds the create citizen profile icon that appears above the product name in
	 * the create citizen profile view.
	 */
	public static final ImageIcon IMAGE_CREATE_CITIZEN_PROFILE_ICON = new ImageIcon(
			Resources.getResourceUri("images/create_citizen_profile_icon.png"));

	/**
	 * Stores the error icon that appears when an error occurs while filling in
	 * various details in the view.
	 */
	public static final ImageIcon IMAGE_ERROR_ICON = new ImageIcon(Resources.getResourceUri("images/error_icon.png"));

	/**
	 * Stores the login icon that appears above the product name in the officer
	 * login view.
	 */
	public static final ImageIcon IMAGE_OFFICER_LOGIN_ICON = new ImageIcon(
			Resources.getResourceUri("images/officer_login_icon.png"));

	/**
	 * Holds the verification icon that appears in the verification officer's
	 * dashboard.
	 */
	public static final ImageIcon IMAGE_VERIFICATION_ICON = new ImageIcon(
			Resources.getResourceUri("images/verification_icon.png"));

	/**
	 * Stores the icon that appears in the passport granting officer's dashboard.
	 */
	public static final ImageIcon IMAGE_PASSPORT_GRANTING_ICON = new ImageIcon(
			Resources.getResourceUri("images/passport_granting_icon.png"));
	/**
	 * Holds the button icon that is used for displaying an arrow pointing to the
	 * left, that may be selected in order to return to the previous page.
	 */
	public static final ImageIcon IMAGE_PREVIOUS_BUTTON_ICON = new ImageIcon(
			Resources.getResourceUri("images/previous_button_icon.png"));

	/**
	 * Holds the button icon that is used for displaying an arrow pointing to the
	 * right, that may be selected in order to go to the next page.
	 */
	public static final ImageIcon IMAGE_NEXT_BUTTON_ICON = new ImageIcon(
			Resources.getResourceUri("images/next_button_icon.png"));

	/** Stores the font style of headings that appear in the view. */
	public static final Font FONT_HEADING_BOLD = new Font("Roboto", Font.BOLD, 20);

	/** Holds the font style of sub headings in the view. */
	public static final Font FONT_SUBHEADING_PLAIN = new Font("Roboto", Font.PLAIN, 12);

	/** Contains the font style of labels that appear in the view. */
	public static final Font FONT_LABEL_BOLD = new Font("Roboto", Font.BOLD, 13);

	/** Holds the font style of buttons in the view. */
	public static final Font FONT_BUTTON_PLAIN = new Font("Roboto", Font.PLAIN, 12);
}
