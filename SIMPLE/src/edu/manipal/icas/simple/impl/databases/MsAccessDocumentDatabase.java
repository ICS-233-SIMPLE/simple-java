package edu.manipal.icas.simple.impl.databases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.healthmarketscience.jackcess.util.OleBlob;
import com.healthmarketscience.jackcess.util.OleBlob.Content;
import com.healthmarketscience.jackcess.util.OleBlob.LinkContent;
import com.healthmarketscience.jackcess.util.OleBlob.SimplePackageContent;

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
	public void createDocument(String name, String pathToDocument) throws IOException {
		if (documentExists(name)) {
			throw new IOException("Duplicate row for " + name);
		}
		OleBlob blob = null;
		try {
			blob = new OleBlob.Builder().setLink(new File(pathToDocument)).toBlob();
			// order of cols: content | d_name
			table.addRow(blob, name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (blob != null) {
				blob.close();
			}
		}
	}

	@Override
	public Boolean documentExists(String name) {
		return rowExists(name);
	}

	@Override
	public byte[] fetchContent(String name) throws IOException {
		Content content = getRow(name).getBlob(FIELD_CONTENT).getContent();
		byte[] bin = ((LinkContent) content).getLinkStream().readAllBytes();
		content.getBlob().close();
		System.out.println();
		return bin;
	}

	@Override
	public String fetchPath(String name) throws IOException {
		Content content = getRow(name).getBlob(FIELD_CONTENT).getContent();
		return ((LinkContent) content).getFilePath();
	}

}
