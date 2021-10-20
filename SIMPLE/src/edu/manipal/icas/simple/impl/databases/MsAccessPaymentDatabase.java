package edu.manipal.icas.simple.impl.databases;

import java.io.IOException;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Row;

import edu.manipal.icas.simple.databases.PaymentDatabase;

/**
 * Concrete class that implements persistence API operations from
 * {@link PaymentDatabase}
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public class MsAccessPaymentDatabase extends MsAccessDatabase implements PaymentDatabase {
	private static final MsAccessPaymentDatabase DATABASE = new MsAccessPaymentDatabase();

	private MsAccessPaymentDatabase() {
		super("Payments");
	}

	public static MsAccessPaymentDatabase getDatabase() {
		return DATABASE;
	}

	@Override
	public Boolean paymentExists(Integer paymentId) {
		return rowExists(paymentId);
	}

	@Override
	public Integer createPayment(Double amount) throws IOException {
		return (Integer) table.addRow(Column.AUTO_NUMBER, amount, false)[0];
	}

	@Override
	public void savePaymentSuccessful(Integer paymentId, Boolean successful) throws IOException {
		Row row = getRow(paymentId);
		row.put(FIELD_SUCCESSFUL, successful);
		table.updateRow(row);
	}

	@Override
	public Boolean fetchPaymentSuccessful(Integer paymentId) throws IOException {
		return getRow(paymentId).getBoolean(FIELD_SUCCESSFUL);
	}

	@Override
	public Double fetchPaymentAmount(Integer paymentId) throws IOException {
		return getRow(paymentId).getDouble(FIELD_AMOUNT);
	}

}
