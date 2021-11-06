package edu.manipal.icas.simple.models;

import java.io.IOException;

import edu.manipal.icas.simple.databases.PaymentDatabase;
import edu.manipal.icas.simple.impl.databases.MsAccessPaymentDatabase;

/**
 * Class that represents a payment for an {@link Application#}.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class Payment {
	private Integer paymentId;
	private Double amount;
	private Boolean paymentSuccessful;
	private PaymentDatabase db;

	/**
	 * Creates a new payment and stores it in the database.
	 * 
	 * @param amount
	 */
	public Payment(Double amount) {
		this.amount = amount;
		paymentSuccessful = false;
		db = MsAccessPaymentDatabase.getDatabase();
		try {
			this.paymentId = db.createPayment(amount);
			db.savePaymentSuccessful(paymentId, paymentSuccessful);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Constructs a payment object based on a pre existing database record.
	 * 
	 * @param paymentId
	 */
	public Payment(Integer paymentId) {
		this.paymentId = paymentId;
		db = MsAccessPaymentDatabase.getDatabase();
		try {
			this.amount = db.fetchPaymentAmount(paymentId);
			this.paymentSuccessful = db.fetchPaymentSuccessful(paymentId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fulfils the payment by communicating with the bank.
	 */
	public void fulfil() {
		paymentSuccessful = true;
		try {
			db.savePaymentSuccessful(paymentId, paymentSuccessful);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the amount that is being transacted in this payment.
	 * 
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * Gets the payment ID that uniquely identifies each payment.
	 * 
	 * @return the payment ID
	 */
	public Integer getPaymentId() {
		return paymentId;
	}

	/**
	 * Gets the payment status.
	 * 
	 * @return {@code true} if the payment is successful and {@code false} otherwise
	 */
	public Boolean getPaymentSuccessful() {
		return paymentSuccessful;
	}
}
