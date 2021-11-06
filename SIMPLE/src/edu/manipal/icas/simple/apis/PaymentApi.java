package edu.manipal.icas.simple.apis;

import edu.manipal.icas.simple.models.Payment;

/**
 * Interface that defines operations for processing a payment and creating a new
 * Payment.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public interface PaymentApi {

	/**
	 * Processes the payment and fulfils it.
	 * 
	 * @param payment the payment to be processed
	 * @return {@code true} if the payment is successful and {@code false} otherwise
	 */
	Boolean processPayment(Payment payment);

	/**
	 * Creates a new payment.
	 * 
	 * @param amount the amount that is being transacted in this payment
	 * @return the payment object
	 */
	Payment createPayment(Double amount);
}
