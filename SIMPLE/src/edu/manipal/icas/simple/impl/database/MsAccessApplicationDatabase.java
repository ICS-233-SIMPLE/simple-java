package edu.manipal.icas.simple.impl.database;

import java.io.IOException;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Row;

import edu.manipal.icas.simple.database.ApplicationDatabase;

public class MsAccessApplicationDatabase extends MsAccessDatabase implements ApplicationDatabase {

	private static final MsAccessApplicationDatabase INSTANCE = new MsAccessApplicationDatabase();
	private static final String PERMANENT_ADDRESS = "PERMANENT_ADDRESS";

	private MsAccessApplicationDatabase() {
		super("Applications");
	}

	public static MsAccessApplicationDatabase getInstance() {
		return INSTANCE;
	}

	@Override
	public void savePermanentAddress(Integer applicationId, String permanentAddress) throws IOException {
		Row row = getRow(applicationId);
		row.put(PERMANENT_ADDRESS, permanentAddress);
		table.updateRow(row);
	}

	@Override
	public String getPermanentAddress(Integer applicationId) throws IOException {
		return getRow(applicationId).getString(PERMANENT_ADDRESS);
	}

	@Override
	public Integer createApplication() throws IOException {
		return (Integer) table.addRow(Column.AUTO_NUMBER)[0];
	}

}
