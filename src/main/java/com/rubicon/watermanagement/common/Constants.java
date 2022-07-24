package com.rubicon.watermanagement.common;

public class Constants {
    public static final String REQUESTED_STATUS="Requested";
    public static final String INPROGRESS_STATUS="Inprogress";
    public static final String CANCELED_STATUS="Cancled";
    public static final String DELIVERED_STATUS="Delivered";
    public static final String CANCELLATION_MSG_FOR_CANCELLED = "Request already cancelled.";
    public static final String CANCELLATION_MSG_FOR_IN_PROGRESS = "Request is in progess, cannot be cancelled.";
    public static final String CANCELLATION_MSG_FOR_DELIVERED = "Request delivered, cannot be cancelled.";
    public static final String CANCELLATION_SUCCESSFULL_MSG = "Request cancelled successfully.";
}
