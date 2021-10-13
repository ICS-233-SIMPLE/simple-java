package edu.manipal.icas.simple.database;

import java.io.IOException;

public interface ApplicationDatabase {

	void savePermanentAddress(Integer applicationId, String permanentAddress) throws IOException;

	String getPermanentAddress(Integer applicationId) throws IOException;

	Integer createApplication() throws IOException;
}
