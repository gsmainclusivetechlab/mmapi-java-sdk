package com.mobilemoney.billpayment.model;

import java.io.Serializable;

/***
 * Class BillReference
 *
 */
public class BillReference implements Serializable {
	private static final long serialVersionUID = 4280441198036114085L;

	// Type of the additional payment reference
    private String paymentReferenceType;

    // Value of the additional payment reference
    private String paymentReferenceValue;

    /***
     * Constructor with parameters
     *
     * @param paymentReferenceType
     * @param paymentReferenceValue
     */
    public BillReference(final String paymentReferenceType, final String paymentReferenceValue) {
        this.paymentReferenceType = paymentReferenceType;
        this.paymentReferenceValue = paymentReferenceValue;
    }

    /***
     *
     * @return
     */
    public String getPaymentReferenceType() {
        return paymentReferenceType;
    }

    /***
     *
     * @return
     */
    public String getPaymentReferenceValue() {
        return paymentReferenceValue;
    }
}
