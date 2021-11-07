package edu.manipal.icas.simple.models;

import java.io.IOException;
import java.util.Date;

import edu.manipal.icas.simple.databases.CitizenDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessCitizenDatabase;
import edu.manipal.icas.simple.utils.CryptographyUtils;

/**
 * Class that represents a citizen.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class Citizen {
	private static final CitizenDatabase db = MsAccessCitizenDatabase.getDatabase();

	private String emailAddress;
	private String password;
	private String name;
	private String gender;
	private Date dateOfBirth;
	private Long contactNumber;
	private PassportOffice passportOffice;

	/**
	 * Creates a new citizen. If the email address provided does not correspond to a
	 * previously existing citizen, a new record is created in the database.
	 * 
	 * @param emailAddress
	 */
	public Citizen(String emailAddress) {
		if (!db.citizenExists(emailAddress)) {
			try {
				db.createCitizen(emailAddress);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.emailAddress = emailAddress;
		try {
			password = db.fetchPassword(emailAddress);
			name = db.fetchName(emailAddress);
			gender = db.fetchGender(emailAddress);
			dateOfBirth = db.fetchDateOfBirth(emailAddress);
			contactNumber = db.fetchContactNumber(emailAddress);
			Integer officeId = db.fetchPassportOfficeId(emailAddress);
			if (officeId != null) {
				passportOffice = PassportOffice.getAllPassportOffices().get(officeId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Authenticates a citizen with an email address by means of verifying their
	 * password.
	 * 
	 * @param emailAddress email address of the citizen who is to be authenticated
	 * @param password     password that will be matched against a previously saved
	 *                     password
	 * @return {@code true} if the citizen's authentication request was deemed valid
	 *         and authentic, and {@code false} otherwise
	 * @throws IllegalArgumentException if this method is called on a citizen record
	 *                                  which does not exist
	 */
	public static Boolean authenticate(String emailAddress, String password) throws IllegalArgumentException {
		if (!db.citizenExists(emailAddress)) {
			throw new IllegalArgumentException("Cannot authenticate a citizen who does not exist");
		}
		try {
			return db.fetchPassword(emailAddress).equals(CryptographyUtils.hashString(password));
		} catch (IOException e) {
			return false;
		}
	}

	/**
	 * Gets the email address of the citizen.
	 * 
	 * @return email address of the citizen
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the password of the citizen.
	 * 
	 * @param password new password
	 */
	public void setPassword(String password) {
		this.password = CryptographyUtils.hashString(password);
		try {
			db.savePassword(emailAddress, this.password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the name of the citizen.
	 * 
	 * @return name of the citizen
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the citizen
	 * 
	 * @param name new name
	 * @throws IllegalArgumentException if the name is empty or null
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (name == null || "".equals(name)) {
			throw new IllegalArgumentException("Name cannot be empty!");
		}
		this.name = name;
		try {
			db.saveName(emailAddress, name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the gender of the citizen.
	 * 
	 * @return gender of the citizen
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the citizen.
	 * 
	 * @param gender new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
		try {
			db.saveGender(emailAddress, gender);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the date of birth of the citizen.
	 * 
	 * @return date of birth of the citizen
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth of the citizen.
	 * 
	 * @param dob new date of birth
	 * @throws IllegalArgumentException if the provided date is not before 18 years
	 *                                  of the current date; i.e., the citizen is
	 *                                  not a legal adult
	 */
	public void setDateOfBirth(Date dob) throws IllegalArgumentException {
		if (dob.after(new Date(System.currentTimeMillis() - 18 * 365 * 24 * 60 * 60 * 1000))) {
			throw new IllegalArgumentException("Invalid date; cannot be less than 18y");
		}
		dateOfBirth = dob;
		try {
			db.saveDateOfBirth(emailAddress, dob);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets the contact number of the citizen.
	 * 
	 * @return contact number of the citizen
	 */
	public Long getContactNumber() {
		return contactNumber;
	}

	/**
	 * Sets the contact number of the citizen.
	 * 
	 * @param contact new contact number
	 * @throws IllegalArgumentException if the provided contact number is not 10
	 *                                  digits long
	 */
	public void setContactNumber(Long contact) throws IllegalArgumentException {
		if (contact < ((long) 10e8) || contact > ((long) 10e9)) {
			throw new IllegalArgumentException("Contact number must be 10 digits long");
		}
		contactNumber = contact;
		try {
			db.saveContactNumber(emailAddress, contact);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PassportOffice getPassportOffice() {
		return passportOffice;
	}
	
	public void setPassportOffice(PassportOffice office) {
		this.passportOffice = office;
		try {
			db.savePassportOfficeId(emailAddress, office.getOfficeId());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
