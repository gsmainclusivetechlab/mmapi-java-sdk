package com.mobilemoney.billpayment.model;

import java.io.Serializable;
import java.util.List;

import com.mobilemoney.common.model.CustomData;
import com.mobilemoney.common.model.MetaData;

/***
 * Class Bill
 *
 */
public class Bill implements Serializable {
	private static final long serialVersionUID = 8831388852350154575L;

	// Reference number for the Bill
    private String billReference;

    // Identifies the status of the Bill
    private String billStatus;

    // Amount outstanding on the bill to be paid
    private String amountDue;

    // Currency of the bill to be paid
    private String currency;

    // Description of the bill that is to be paid
    private String billdescription;

    // Date on which the Bill is due to be paid
    private String dueDate;

    // Minimum amount that is outstanding on the bill to be paid
    private String minimumAmountDue;

    // Date and time when the object was created
    private String creationDate;

    // Date and time when the object was modified
    private String modificationDate;

    // Collection of key/value pairs
    private List<CustomData> customData;

    // Collection of key/value pairs
    private List<MetaData> metadata;

    /***
     *
     * @return
     */
    public String getBillReference() {
        return billReference;
    }

    /***
     *
     * @param billReference
     */
    public void setBillReference(String billReference) {
        this.billReference = billReference;
    }

    /***
     *
     * @return
     */
    public String getBillStatus() {
        return billStatus;
    }

    /***
     *
     * @param billStatus
     */
    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    /***
     *
     * @return
     */
    public String getAmountDue() {
        return amountDue;
    }

    /***
     *
     * @param amountDue
     */
    public void setAmountDue(String amountDue) {
        this.amountDue = amountDue;
    }

    /***
     *
     * @return
     */
    public String getCurrency() {
        return currency;
    }

    /***
     *
     * @param currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /***
     *
     * @return
     */
    public String getBilldescription() {
        return billdescription;
    }

    /***
     *
     * @param billdescription
     */
    public void setBilldescription(String billdescription) {
        this.billdescription = billdescription;
    }

    /***
     *
     * @return
     */
    public String getDueDate() {
        return dueDate;
    }

    /***
     *
     * @param dueDate
     */
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    /***
     *
     * @return
     */
    public String getMinimumAmountDue() {
        return minimumAmountDue;
    }

    /***
     *
     * @param minimumAmountDue
     */
    public void setMinimumAmountDue(String minimumAmountDue) {
        this.minimumAmountDue = minimumAmountDue;
    }

    /***
     *
     * @return
     */
    public String getCreationDate() {
        return creationDate;
    }

    /***
     *
     * @param creationDate
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /***
     *
     * @return
     */
    public String getModificationDate() {
        return modificationDate;
    }

    /***
     *
     * @param modificationDate
     */
    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    /***
     *
     * @return
     */
    public List<CustomData> getCustomData() {
        return customData;
    }

    /***
     *
     * @param customData
     */
    public void setCustomData(List<CustomData> customData) {
        this.customData = customData;
    }

    /***
     *
     * @return
     */
    public List<MetaData> getMetadata() {
        return metadata;
    }

    /***
     *
     * @param metadata
     */
    public void setMetadata(List<MetaData> metadata) {
        this.metadata = metadata;
    }
}
