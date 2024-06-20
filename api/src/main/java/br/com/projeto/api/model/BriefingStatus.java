package br.com.projeto.api.model;

import lombok.Getter;

@Getter
public enum BriefingStatus {
    APPROVED(1, "APPROVED"),
    NEGOTIATION(2, "NEGOTIATION"),
    FINISHED(3, "FINISHED");

    private final int statusId;
    private final String statusString;

    BriefingStatus(int statusId, String statuString){
        this.statusId = statusId;
        this.statusString = statuString;
    }
}
