package org.marathon.finalmatch;

public class ResponseLog {
    
    private String requestID;
    
    private Long requestTime;

    private Long responseTime;
    
    private Long delayTime;
    
    public ResponseLog(String requestId, Long responseTime) {
        this.requestID = requestId;
        this.responseTime = responseTime;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
        delayTime = responseTime - requestTime;
    }

    public Long getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(Long delayTime) {
        this.delayTime = delayTime;
    }

    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public Long getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Long responseTime) {
        this.responseTime = responseTime;
    }

}
