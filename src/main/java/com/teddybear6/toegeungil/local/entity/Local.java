package com.teddybear6.toegeungil.local.entity;

import com.teddybear6.toegeungil.local.dto.LocalDTO;

import javax.persistence.*;

@Entity(name = "local")
@Table(name = "local")
public class Local {

    @Id
    @Column(name = "local_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int localCode;


    @Column(name = "local_name")
    private String localName;

    public Local() {
    }

    public Local(int localCode, String localName) {
        this.localCode = localCode;
        this.localName = localName;
    }
    public Local(LocalDTO localDTO) {
        this.localCode = localDTO.getLocalCode();
        this.localName = localDTO.getLocalName();
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
        return "Local{" +
                "localCode=" + localCode +
                ", localName='" + localName + '\'' +
                '}';
    }
}
