package edu.manipal.icas.simple.impl.databases;

import java.io.File;
import java.io.IOException;

import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import edu.manipal.icas.simple.resources.Resources;

/**
 * Abstract class that defines common methods used by concrete implementations
 * that interact with Microsoft Access using Jackcess.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public abstract class MsAccessDatabase {
	private static final String DB_NAME = Resources.getResourceUri("db/SimpleDB.accdb");

	protected Database database;
	protected Table table;

	protected MsAccessDatabase(String tableName) {
		try {
			database = DatabaseBuilder.open(new File(DB_NAME));
			table = database.getTable(tableName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected Row getRow(Object key) throws IOException {
		Row row = CursorBuilder.findRowByPrimaryKey(table, key);
		if (row == null) {
			throw new IOException("Row does not exist");
		}
		return row;
	}

	protected Boolean rowExists(Object key) {
		try {
			if (getRow(key) == null) {
				return false;
			}
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
