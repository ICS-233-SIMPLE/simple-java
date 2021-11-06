package edu.manipal.icas.simple.utils;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Utility class that provides functions for image manipulation.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public final class ImageUtils {

	private ImageUtils() {
	}

	/**
	 * Gets the scaled version of the image icon passed to it.
	 * 
	 * @param icon   the image icon that is to be scaled to the desired size
	 * @param width  the width that the image should be scaled to
	 * @param height the height that the image should be scaled to
	 * @return the scaled image
	 */
	public static ImageIcon getScaledImage(ImageIcon icon, int width, int height) {
		int newWidth = icon.getIconWidth();
		int newHeight = icon.getIconHeight();

		if (newWidth > width) {
			newWidth = width;
			newHeight = (newWidth * icon.getIconHeight()) / icon.getIconWidth();
		}

		if (newHeight > height) {
			newHeight = height;
			newWidth = (icon.getIconWidth() * newHeight) / icon.getIconHeight();
		}

		return new ImageIcon(icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT));
	}

}
