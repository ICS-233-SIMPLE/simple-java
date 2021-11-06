package edu.manipal.icas.simple.databases;

import java.io.IOException;

/**
 * Interface that defines operations to be implemented in an API that persists
 * document data.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface DocumentDatabase {
	/**
	 * Column that holds a unique name for each document row. To be used as the
	 * primary key.
	 */
	static final String FIELD_NAME = "D_NAME";

	/** Column that holds the binary/blob content of the document. */
	static final String FIELD_CONTENT = "CONTENT";

	/**
	 * Creates a new document entry in the database.
	 * 
	 * @param name           unique name corresponding to this document
	 * @param pathToDocument the path to the document that is to be linked with the
	 *                       database. Can be either a full path or a path relative
	 *                       to the CWD of the program
	 * @throws IOException if the passed name was not unique
	 */
	void createDocument(String name, String pathToDocument) throws IOException;

	/**
	 * Checks whether a document of a given name exists in the database.
	 * 
	 * @param name name of the document
	 * @return {@code true} if the document exists, {@code false} otherwise
	 */
	Boolean documentExists(String name);

	/**
	 * Fetches the content of a document from the database.
	 * 
	 * @param name name of the document to fetch
	 * @return binary content of the document
	 * @throws IOException if no document was found for the given name
	 */
	byte[] fetchContent(String name) throws IOException;

	/**
	 * Fetches the path to a document from the database.
	 * 
	 * @param name name of the document to fetch
	 * @return absolute path to the document in the filesystem as was linked to the
	 *         database
	 * @throws IOException if no document was found for the given name
	 */
	String fetchPath(String name) throws IOException;
}
