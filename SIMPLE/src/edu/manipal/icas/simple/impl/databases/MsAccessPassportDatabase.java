package edu.manipal.icas.simple.impl.databases;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Row;

import edu.manipal.icas.simple.databases.PassportDatabase;
import edu.manipal.icas.simple.models.PassportStatus;

/**
 * Concrete class that implements persistence API operations from
 * {@link PassportDatabase}
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class MsAccessPassportDatabase extends MsAccessDatabase implements PassportDatabase {
	private static final MsAccessPassportDatabase DATABASE = new MsAccessPassportDatabase();

	private MsAccessPassportDatabase() {
		super("Passports");
	}

	public static MsAccessPassportDatabase getDatabase() {
		return DATABASE;
	}

	@Override
	public Boolean passportExists(String passportId) {
		return rowExists(passportId);
	}

	@Override
	public Integer createPassport(String photographName, String birthAddress, String permanentAddress,
			String nameOfFather, String nameOfMother, Date expiryDate, String ownerId) throws IOException {
		return (Integer) table.addRow(Column.AUTO_NUMBER, photographName, birthAddress, permanentAddress, nameOfFather,
				nameOfMother, expiryDate, ownerId)[0];

	}

	@Override
	public String fetchPhotographName(String passportId) throws IOException {
		return getRow(passportId).getString(FIELD_PHOTOGRAPH);
	}

	@Override
	public String fetchBirthAddress(String passportId) throws IOException {
		return getRow(passportId).getString(FIELD_BIRTH_ADDRESS);
	}

	@Override
	public String fetchPermanentAddress(String passportId) throws IOException {
		return getRow(passportId).getString(FIELD_PERMANENT_ADDRESS);
	}

	@Override
	public String fetchNameOfFather(String passportId) throws IOException {
		return getRow(passportId).getString(FIELD_NAME_OF_FATHER);
	}

	@Override
	public String fetchNameOfMother(String passportId) throws IOException {
		return getRow(passportId).getString(FIELD_NAME_OF_MOTHER);
	}

	@Override
	public Date fetchExpiryDate(String passportId) throws IOException {
		LocalDateTime dateTime = getRow(passportId).getLocalDateTime(FIELD_EXPIRY_DATE);
		return Date.from(dateTime.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public void saveStatus(String passportId, PassportStatus status) throws IOException {
		Row row = getRow(passportId);
		row.put(FIELD_STATUS, status.toString());
		table.updateRow(row);

	}

	@Override
	public PassportStatus fetchStatus(String passportId) throws IOException {
		return PassportStatus.valueOf(getRow(passportId).getString(FIELD_STATUS));
	}

	@Override
	public String fetchOwnerId(String passportId) throws IOException {
		return getRow(passportId).getString(FIELD_OWNER_ID);
	}

}
