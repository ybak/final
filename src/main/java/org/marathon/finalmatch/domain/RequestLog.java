package org.marathon.finalmatch.domain;

public class RequestLog {

    private String requestID;
    
    private Long requestTime;

    public RequestLog(String requestId, Long requestTime) {
        this.requestID = requestId;
        this.requestTime = requestTime;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }
    
}
