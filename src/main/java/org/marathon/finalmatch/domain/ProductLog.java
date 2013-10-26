package org.marathon.finalmatch.domain;

public class ProductLog {
    private String code;
    private long quantity;

    public ProductLog(String code, long quantity) {
        this.code = code;
        this.quantity = quantity;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
