package org.marathon.finalmatch.domain;

public class ExceptionPeriod {

    private Long startTime = 0l;

    private Long endTime = 0l;

    private Long totalDelayTime = 0l;

    private Long responseCount = 0l;

    private Long periodTime;

    public ExceptionPeriod(Long requestTime, Long responseTime) {
        this.startTime = requestTime;
        this.endTime = responseTime;
    }

    public void addNewResponse(ResponseLog response) {
        this.totalDelayTime += response.getDelayTime();
        this.responseCount++;
        this.endTime = response.getResponseTime();
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public long getTotalDelayTime() {
        return totalDelayTime;
    }

    public void setTotalDelayTime(long totalDelayTime) {
        this.totalDelayTime = totalDelayTime;
    }

    public long getResponseCount() {
        return responseCount;
    }

    public void setResponseCount(long responseCount) {
        this.responseCount = responseCount;
    }

    public Long getPeriodTime() {
        if (this.periodTime == null) {
            this.periodTime = this.endTime - this.startTime;
        }
        return this.periodTime;
    }

}
