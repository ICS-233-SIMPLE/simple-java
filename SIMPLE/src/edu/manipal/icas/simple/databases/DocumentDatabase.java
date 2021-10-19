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
	static final String FIELD_NAME = "NAME";

	/** Column that holds the binary/blob content of the document. */
	static final String FIELD_CONTENT = "CONTENT";

	/**
	 * Creates a new document entry in the database.
	 * 
	 * @param name    unique name corresponding to this document
	 * @param content the binary content of the document that is to be saved
	 * @throws IOException if the passed name was not unique
	 */
	void createDocument(String name, byte[] content) throws IOException;

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
}
