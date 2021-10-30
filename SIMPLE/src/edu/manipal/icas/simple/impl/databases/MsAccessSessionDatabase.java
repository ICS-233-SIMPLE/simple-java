package edu.manipal.icas.simple.impl.databases;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import com.healthmarketscience.jackcess.Row;

import edu.manipal.icas.simple.databases.SessionDatabase;
import edu.manipal.icas.simple.models.Citizen;
import edu.manipal.icas.simple.session.CitizenSession;
import edu.manipal.icas.simple.session.Session;
import edu.manipal.icas.simple.session.SessionType;

public class MsAccessSessionDatabase extends MsAccessDatabase implements SessionDatabase {
	private static final MsAccessSessionDatabase DATABASE = new MsAccessSessionDatabase();

	private MsAccessSessionDatabase() {
		super("Sessions");
	}

	public static MsAccessSessionDatabase getDatabase() {
		return DATABASE;
	}

	@Override
	public void startSession(Session session) throws IOException {
		// column order: sessionid | type | timestamp | active
		if (getCurrentSession() != null) {
			throw new IOException("Cannot create new session while one is underway");
		}
		table.addRow(session.getSessionId(), session.getSessionType(), System.currentTimeMillis(), true);
	}

	@Override
	public void endCurrentSession() throws IOException {
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
		try {
			endExpiredSession();
			table.getDefaultCursor().reset();
			Row row = null;
			for (int i = 0; i <= table.getRowCount(); ++i) {
				row = table.getNextRow();
				if (row == null || row.getBoolean(FIELD_ACTIVE)) {
					break;
				}
			}

			if (row == null) {
				return null;
			}

			switch (SessionType.valueOf(row.getString(FIELD_SESSION_TYPE))) {
			case CITIZEN:
				return new CitizenSession(new Citizen(row.getString(FIELD_SESSION_ID)));
			default:
				break;
			}
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void endExpiredSession() throws IOException {
		table.getDefaultCursor().reset();
		Row row = null;
		for (int i = 0; i < table.getRowCount(); ++i) {
			row = table.getNextRow();
		}

		if (row == null) {
			return;
		}

		if (LocalDateTime.now().compareTo(row.getLocalDateTime(FIELD_TIMESTAMP).minus(1, ChronoUnit.HOURS)) > 0) {
			row.put(FIELD_ACTIVE, false);
			table.updateRow(row);
		}
	}

}
