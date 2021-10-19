package edu.manipal.icas.simple.impl.databases;

import java.io.IOException;

import edu.manipal.icas.simple.databases.DocumentDatabase;

/**
 * Concrete class that implements persistence API operations from
 * {@link DocumentDatabase}
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class MsAccessDocumentDatabase extends MsAccessDatabase implements DocumentDatabase {
	private static final MsAccessDocumentDatabase DATABASE = new MsAccessDocumentDatabase();

	private MsAccessDocumentDatabase() {
		super("Documents");
	}

	public static MsAccessDocumentDatabase getDatabase() {
		return DATABASE;
	}

	@Override
	public void createDocument(String name, byte[] content) throws IOException {
		if (documentExists(name)) {
			throw new IOException("Duplicate row for " + name);
		}
		table.addRow(name, content);
	}

	@Override
	public Boolean documentExists(String name) {
		return rowExists(name);
	}

	@Override
	public byte[] fetchContent(String name) throws IOException {
		return getRow(name).getBytes(FIELD_CONTENT);
	}

}
