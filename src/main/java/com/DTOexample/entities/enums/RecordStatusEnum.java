package com.DTOexample.entities.enums;

public enum RecordStatusEnum {
    A("active"),
    D("deleted"),
    I("inactive");

    private String descrizione;

    RecordStatusEnum(String descrizione){
        this.descrizione = descrizione;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
