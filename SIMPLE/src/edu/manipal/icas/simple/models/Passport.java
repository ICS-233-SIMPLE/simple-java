package edu.manipal.icas.simple.models;

import java.util.Date;

import edu.manipal.icas.simple.databases.PassportDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessPassportDatabase;

/**
 * Class that represents a citizen's passport.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class Passport {
	private static final PassportDatabase db = MsAccessPassportDatabase.getDatabase();
	private String passportId;
	private Document photograph;
	private String birthAddress;
	private String permanentAddress;
	private String nameOfFather;
	private String nameOfMother;
	private Date expiryDate;
	private PassportStatus status;
	private Citizen owner;

}
