package edu.manipal.icas.simple.impl.database;

import java.io.File;
import java.io.IOException;

import com.healthmarketscience.jackcess.CursorBuilder;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

public abstract class MsAccessDatabase {
	private static final String DB_NAME = "SimpleDB.accdb";

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

	protected Row getRow(Integer key) throws IOException {
		return CursorBuilder.findRowByPrimaryKey(table, key);
	}
}
