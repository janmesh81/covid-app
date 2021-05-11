package com.covid.dashboard.covidapp.model;

/**
 * Class representing the response of covidInformation service.
 * **/
public class CovidInformationResponse {
    private Long activeCaseNumber;
    private Long confirmedCaseNumber;
    private Long deathNumber;
    private Long recoveryNumber;
    private String incidentRate;
    private String caseFatalityRatio;

    public CovidInformationResponse(Long activeCaseNumber, Long confirmedCaseNumber, Long deathNumber, Long recoveryNumber, String incidentRate, String caseFatalityRatio) {
        this.activeCaseNumber = activeCaseNumber;
        this.confirmedCaseNumber = confirmedCaseNumber;
        this.deathNumber = deathNumber;
        this.recoveryNumber = recoveryNumber;
        this.incidentRate = incidentRate;
        this.caseFatalityRatio = caseFatalityRatio;
    }
    public CovidInformationResponse(){}

    public Long getActiveCaseNumber() {
        return activeCaseNumber;
    }

    public void setActiveCaseNumber(Long activeCaseNumber) {
        this.activeCaseNumber = activeCaseNumber;
    }

    public Long getConfirmedCaseNumber() {
        return confirmedCaseNumber;
    }

    public void setConfirmedCaseNumber(Long confirmedCaseNumber) {
        this.confirmedCaseNumber = confirmedCaseNumber;
    }

    public Long getDeathNumber() {
        return deathNumber;
    }

    public void setDeathNumber(Long deathNumber) {
        this.deathNumber = deathNumber;
    }

    public Long getRecoveryNumber() {
        return recoveryNumber;
    }

    public void setRecoveryNumber(Long recoveryNumber) {
        this.recoveryNumber = recoveryNumber;
    }

    public String getIncidentRate() {
        return incidentRate;
    }

    public void setIncidentRate(String incidentRate) {
        this.incidentRate = incidentRate;
    }

    public String getCaseFatalityRatio() {
        return caseFatalityRatio;
    }

    public void setCaseFatalityRatio(String caseFatalityRatio) {
        this.caseFatalityRatio = caseFatalityRatio;
    }
}
