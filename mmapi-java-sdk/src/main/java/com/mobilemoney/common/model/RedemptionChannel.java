package com.mobilemoney.common.model;

import java.io.Serializable;

/***
 * Class RedemptionChannel
 */
public class RedemptionChannel implements Serializable {
    private static final long serialVersionUID = -4333786003548853463L;

    // Identifies the channel type
    private String channelType;

    /***
     *
     * @return
     */
    public String getChannelType() {
        return channelType;
    }

    /***
     *
     * @param channelType
     */
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
}
