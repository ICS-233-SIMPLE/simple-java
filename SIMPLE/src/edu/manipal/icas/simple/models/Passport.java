package edu.manipal.icas.simple.models;

import java.io.IOException;
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

	public Passport(String passportId) {
		this.passportId = passportId;
		init();
	}

	public Passport(Document photo, String birthAddress, String permanentAddress, String fathersName,
			String mothersName, Date expiry, Citizen owner) {
		try {
			this.passportId = db.createPassport(photo.getName(), birthAddress, permanentAddress, fathersName, mothersName,
					expiry, owner.getEmailAddress());
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void init() {
		try {
			photograph = new Document(db.fetchPhotographName(passportId));
			birthAddress = db.fetchBirthAddress(passportId);
			permanentAddress = db.fetchPermanentAddress(passportId);
			nameOfFather = db.fetchNameOfFather(passportId);
			nameOfMother = db.fetchNameOfMother(passportId);
			expiryDate = db.fetchExpiryDate(passportId);
			status = db.fetchStatus(passportId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @return the passportId
	 */
	public String getPassportId() {
		return passportId;
	}

	/**
	 * @return the photograph
	 */
	public Document getPhotograph() {
		return photograph;
	}

	/**
	 * @return the birthAddress
	 */
	public String getBirthAddress() {
		return birthAddress;
	}

	/**
	 * @return the permanentAddress
	 */
	public String getPermanentAddress() {
		return permanentAddress;
	}

	/**
	 * @return the nameOfFather
	 */
	public String getNameOfFather() {
		return nameOfFather;
	}

	/**
	 * @return the nameOfMother
	 */
	public String getNameOfMother() {
		return nameOfMother;
	}

	/**
	 * @return the expiryDate
	 */
	public Date getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @return the status
	 */
	public PassportStatus getStatus() {
		return status;
	}

	/**
	 * @return the owner
	 */
	public Citizen getOwner() {
		return owner;
	}

}
