package edu.manipal.icas.simple.models;

import java.io.IOException;

import edu.manipal.icas.simple.databases.DocumentDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessDocumentDatabase;

/**
 * Class that represents a file/document from the system's filesystem.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class Document {

	/** A unique name representing this document */
	private String name;

	/** Content of the file as a byte array */
	private byte[] content;

	/** Absolute path to the file */
	private String path;

	private static final DocumentDatabase db = MsAccessDocumentDatabase.getDatabase();

	/**
	 * Creates a new document and persists it by linking the path provided with the
	 * fields in the database.
	 * 
	 * @param name unique name of the file
	 * @param path absolute path to the file
	 */
	public Document(String name, String path) {
		if (db.documentExists(name)) {
			throw new IllegalArgumentException("Document with the same name already exists!");
		}
		this.name = name;
		this.content = null;
		this.path = path;
		try {
			db.createDocument(name, path);
			this.content = db.fetchContent(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new document object representing a document that is already in the
	 * database.
	 * 
	 * @param name
	 */
	public Document(String name) {
		this.name = name;
		try {
			this.content = db.fetchContent(name);
			this.path = db.fetchPath(name);
		} catch (IOException e) {
			throw new IllegalArgumentException("Document does not exist!");
		}
	}

	/**
	 * Gets the unique name of the document.
	 * 
	 * @return name of the document
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets a byte array output containing the unencoded contents of the document.
	 * 
	 * @return contents of the document
	 */
	public byte[] getContent() {
		return content;
	}

	/**
	 * Returns the absolute path to the underlying file that was linked to this
	 * document.
	 * 
	 * @return path to the file
	 */
	public String getPath() {
		return path;
	}
}
