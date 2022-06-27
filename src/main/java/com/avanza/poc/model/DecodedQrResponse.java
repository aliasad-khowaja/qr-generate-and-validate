package com.avanza.poc.model;

public class DecodedQrResponse {
    private String qrString;

    public DecodedQrResponse() {

    }

    public DecodedQrResponse(String qrString) {
        this.qrString = qrString;
    }

    public String getQrString() {
        return qrString;
    }

    public void setQrString(String qrString) {
        this.qrString = qrString;
    }
}
