package edu.manipal.icas.simple.models;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import edu.manipal.icas.simple.databases.CitizenDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessCitizenDatabase;
import edu.manipal.icas.simple.utils.CryptographyUtils;

public class Citizen {
	private static final CitizenDatabase db = MsAccessCitizenDatabase.getDatabase();

	private String emailAddress;
	private String password;
	private String name;
	private String gender;
	private Date dateOfBirth;
	private Long contactNumber;

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
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Boolean authenticate(String emailAddress, String password) throws IllegalArgumentException {
		if (!db.citizenExists(emailAddress)) {
			throw new IllegalArgumentException("Cannot authenticate a citizen who does not exist");
		}
		try {
			return db.fetchPassword(emailAddress).equals(CryptographyUtils.hashPassword(password));
		} catch (IOException e) {
			return false;
		}
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setPassword(String password) {
		this.password = CryptographyUtils.hashPassword(password);
		try {
			db.savePassword(emailAddress, this.password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		try {
			db.saveName(emailAddress, name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
		try {
			db.saveGender(emailAddress, gender);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dob) {
		dateOfBirth = dob;
		try {
			db.saveDateOfBirth(emailAddress, dob);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contact) {
		contactNumber = contact;
		try {
			db.saveContactNumber(emailAddress, contact);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
