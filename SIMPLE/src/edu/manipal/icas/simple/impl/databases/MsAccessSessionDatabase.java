package edu.manipal.icas.simple.impl.databases;

import com.healthmarketscience.jackcess.Row;

import edu.manipal.icas.simple.databases.SessionDatabase;
import edu.manipal.icas.simple.session.Session;

public class MsAccessSessionDatabase extends MsAccessDatabase implements SessionDatabase {
	private static final MsAccessSessionDatabase DATABASE = new MsAccessSessionDatabase();

	private MsAccessSessionDatabase() {
		super("Sessions");
	}

	public static MsAccessSessionDatabase getDatabase() {
		return DATABASE;
	}

	@Override
	public void startSession(Session session) throws Exception {
		table.addRow(System.currentTimeMillis(), session.getSessionType(), session.getSessionId(), true);
	}

	@Override
	public void endCurrentSession() throws Exception {
		table.getDefaultCursor().reset();
		Row row = null;
		for (int i = 0; i < table.getRowCount(); ++i) {
			row = table.getNextRow();
		}
		row.put(FIELD_ACTIVE, false);
		table.updateRow(row);
	}

	@Override
	public Session getCurrentSession() {
		table.getDefaultCursor().reset();
		Row row = null;
		for (int i = 0; i < table.getRowCount(); ++i) {
			row = table.getNextRow();
			if (row.getBoolean(FIELD_ACTIVE)) {
				break;
			}
		}
		if (row == null) {
			return null;
		}
		// TODO: Reconstruct session
		return null;
	}

}
