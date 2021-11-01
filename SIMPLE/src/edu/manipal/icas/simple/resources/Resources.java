package edu.manipal.icas.simple.resources;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

/**
 * Utility class that handles resource files for this application. <br/>
 * All resource files are initially packed into the JAR and then copied over to
 * AppData as and when they are requested. Files are copied to AppData only once
 * to prevent wiping stateful resources like the database.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public final class Resources {
	private Resources() {
	}

	/**
	 * Cache of resources whose locations in AppData we already know. Helps avoid
	 * looking up the file each time it is requested.
	 */
	private static final Map<String, String> copiedFiles = new HashMap<>();

	/**
	 * Name of the directory in AppData/Roaming where resources are to be copied
	 * over to
	 */
	private static final String DIR_NAME = "ICAS/SIMPLE";

	/**
	 * Gets the absolute path to a resource from the user's filesystem.
	 * 
	 * @param resourceName the name of the resource relative to the
	 *                     {@link Resources} class
	 * @return absolute path of the resource in AppData/Roaming
	 */
	public static String getResourceUri(String resourceName) {
		try {
			if (copiedFiles.containsKey(resourceName)) {
				return copiedFiles.get(resourceName);
			}
			URL inputUrl = Resources.class.getResource(resourceName);
			File destination = new File(getPathToAppData() + "/" + DIR_NAME + "/" + resourceName);

			// Copy from the jar to AppData only if the file previously did not exist.
			if (!destination.exists())
				FileUtils.copyURLToFile(inputUrl, destination);
			copiedFiles.put(resourceName, destination.getAbsolutePath());
			return destination.getAbsolutePath();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * Gets the path to the AppData folder if on Windows, or the absolute path to
	 * the user/home directory on *NIX systems.
	 * 
	 * @return path to app data folder
	 */
	private static String getPathToAppData() {
		if (System.getProperty("os.name").toUpperCase().contains("WIN")) {
			return System.getenv("APPDATA");
		}
		return System.getProperty("user.home");
	}
}