package edu.manipal.icas.simple.models;

import java.io.IOException;

import edu.manipal.icas.simple.databases.DocumentDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessDocumentDatabase;

public class Document {
	private String name;
	private byte[] content;

	private static final DocumentDatabase db = MsAccessDocumentDatabase.getDatabase();

	public Document(String name, String path) {
		if (db.documentExists(name)) {
			throw new IllegalArgumentException("Document with the same name already exists!");
		}
		this.name = name;
		this.content = null;
		try {
			db.createDocument(name, path);
			this.content = db.fetchContent(name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Document(String name) {
		this.name = name;
		try {
			this.content = db.fetchContent(name);
		} catch (IOException e) {
			throw new IllegalArgumentException("Document does not exist!");
		}
	}

	public String getName() {
		return name;
	}

	public byte[] getContent() {
		return content;
	}
}
