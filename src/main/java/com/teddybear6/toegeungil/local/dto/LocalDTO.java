package com.teddybear6.toegeungil.local.dto;

import com.teddybear6.toegeungil.local.entity.Local;

public class LocalDTO {

    private int localCode;


    private String localName;


    public LocalDTO() {
    }


    public LocalDTO(int localCode, String localName) {
        this.localCode = localCode;
        this.localName = localName;
    }

    public LocalDTO(Local local) {
        this.localCode = local.getLocalCode();
        this.localName = local.getLocalName();
    }

    public int getLocalCode() {
        return localCode;
    }

    public void setLocalCode(int localCode) {
        this.localCode = localCode;
    }

    public String getLocalName() {
        return localName;
    }

    public void setLocalName(String localName) {
        this.localName = localName;
    }

    @Override
    public String toString() {
        return "LocalDTO{" +
                "localCode=" + localCode +
                ", localName='" + localName + '\'' +
                '}';
    }
}
