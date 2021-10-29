package edu.manipal.icas.simple.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.manipal.icas.simple.impl.databases.MsAccessApplicationDatabase;
import edu.manipal.icas.simple.models.ApplicationType;

public class MsAccessApplicationDatabaseTest {
	
	static MsAccessApplicationDatabase db;
	static int appId;
	
	@BeforeClass
	public static void initDb() throws IOException {
		db = MsAccessApplicationDatabase.getDatabase();
		appId = db.createApplication();
	}

	@Test
	public void testApplicationExists() {
		assertTrue(db.applicationExists(appId));
	}

	@Test
	public void testPermanentAddress() throws IOException {
		db.savePermanentAddress(appId, "ADDR");
		assertEquals("ADDR", db.fetchPermanentAddress(appId));
	}

	@Test
	public void testDocumentsAuthentic() throws IOException {
		db.saveDocumentsAuthentic(appId, false);
		assertFalse(db.fetchDocumentsAuthentic(appId));
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testDateOfAppointment() throws IOException {
		Date date = new Date(System.currentTimeMillis());
		db.saveDateOfAppointment(appId, date);
		Date fetched = db.fetchDateOfAppointment(appId);
		assertEquals(date.getDate(), fetched.getDate());
		assertEquals(date.getMonth(), fetched.getMonth());
		assertEquals(date.getYear(), fetched.getYear());
	}

	@Test
	public void testPaymentId() throws IOException {
		db.savePaymentId(appId, 1234);
		assertEquals(1234 + "", db.fetchPaymentId(appId) + "");
	}

//	@Test
//	public void testAnswerForQuestion() throws IOException {
//		db.saveAnswerForQuestion(appId, 1.1, true);
//		db.saveAnswerForQuestion(appId, 1.2, false);
//		assertTrue(db.fetchAnswerForQuestion(appId, 1.1));
//		assertFalse(db.fetchAnswerForQuestion(appId, 1.2));
//	}

	@Test
	public void testApplicationType() throws IOException {
		db.saveApplicationType(appId, ApplicationType.FRESH);
		assertEquals(db.fetchApplicationType(appId), ApplicationType.FRESH);
	}
}
