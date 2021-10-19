package edu.manipal.icas.simple.databases;

import java.io.IOException;

/**
 * Interface that defines operations to be implemented in an API that persists
 * payment data.
 * 
 * @author Vishwas Adiga (vishwas.adiga@learner.manipal.edu)
 *
 */
public interface PaymentDatabase {
	/**
	 * Column that holds a unique ID for each payment. To be used as the primary
	 * key.
	 */
	static final String FIELD_ID = "ID";

	/**
	 * Column representing the amount of money being transacted in a given payment.
	 */
	static final String FIELD_AMOUNT = "AMOUNT";

	/** Column associated with the success state of the payment. */
	static final String FIELD_SUCCESSFUL = "SUCCESSFUL";

	/**
	 * Checks if a payment exists in the database.
	 * 
	 * @param paymentId ID of the payment to check
	 * @return {@code true} if the payment exists, {@code false} otherwise
	 */
	Boolean paymentExists(Integer paymentId);

	/**
	 * Creates a new payment entry in the database.
	 * 
	 * @param amount the amount of money being transacted
	 * @return a unique ID representing the payment
	 * @throws IOException
	 */
	Integer createPayment(Double amount) throws IOException;

	/**
	 * Saves the success state of a given payment in the database.
	 * 
	 * @param paymentId  ID of the payment to modify
	 * @param successful whether the payment was successful
	 * @throws IOException if the payment of the given ID was not found
	 */
	void savePaymentSuccessful(Integer paymentId, Boolean successful) throws IOException;

	/**
	 * Fetches the success state of a given payment from the database.
	 * 
	 * @param paymentId ID of the payment whose state is to be fetched
	 * @return success state of the payment
	 * @throws IOException if the payment of the given ID was not found
	 */
	Boolean fetchPaymentSuccessful(Integer paymentId) throws IOException;

	/**
	 * Fetches the amount of money associated with a given payment from the
	 * database.
	 * 
	 * @param paymentId ID of the payment whose attribute is to be fetched
	 * @return the amount being transacted in this payment
	 * @throws IOException if the payment of the given ID was not found
	 */
	Double fetchPaymentAmount(Integer paymentId) throws IOException;
}
