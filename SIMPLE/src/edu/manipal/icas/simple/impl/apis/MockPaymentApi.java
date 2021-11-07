package edu.manipal.icas.simple.impl.apis;

import edu.manipal.icas.simple.apis.PaymentApi;
import edu.manipal.icas.simple.models.Payment;

/**
 * Dummy implementation of the Payment API.
 * 
 * @author Rea Mammen (rea.mammen@learner.manipal.edu)
 *
 */
public class MockPaymentApi implements PaymentApi {

	@Override
	public Boolean processPayment(Payment payment) {
		return true;
	}

	@Override
	public Payment createPayment(Double amount) {
		return new Payment(amount);
	}

}
