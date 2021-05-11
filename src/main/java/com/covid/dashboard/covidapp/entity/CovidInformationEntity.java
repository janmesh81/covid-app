package com.covid.dashboard.covidapp.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * This the entity class representing table from db, which has it's own builder class embedded.
 * **/

@Entity
@Table(name = "COVID_INFO")
public class CovidInformationEntity {
    @Id
    @GeneratedValue
    Long id ;
    @Column(name = "FIPS")
    String fips;
    @Column(name = "ADMIN")
    String admin;
    @Column(name = "PROVINCE_STATE")
    String provinceState;
    @Column(name = "COUNTRY_REGION")
    String countryRegion;
    @Column(name = "LAST_UPDATE")
    Date lastUpdate;
    @Column(name = "LATITUDE")
    String latitude;
    @Column(name = "LONGITUDE")
    String longitude;
    @Column(name = "CONFIRMED")
    Long confirmed;
    @Column(name = "DEATHS")
    Long deaths;
    @Column(name = "RECOVERED")
    Long recovered;
    @Column(name = "ACTIVE")
    Long active;
    @Column(name = "COMBINED_KEY")
    String combinedKey;
    @Column(name = "IR")
    String incidentRate;
    @Column(name = "CFR")
    String caseFatalityRatio;

    public CovidInformationEntity(CovidInformationEntityBuilder builder) {
        this.active = builder.active;
        this.admin = builder.admin;
        this.caseFatalityRatio = builder.caseFatalityRatio;
        this.combinedKey = builder.combinedKey;
        this.confirmed = builder.confirmed;
        this.countryRegion = builder.countryRegion;
        this.deaths = builder.deaths;
        this.fips = builder.fips;
        this.incidentRate = builder.incidentRate;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.lastUpdate = builder.lastUpdate;
        this.provinceState = builder.provinceState;
        this.recovered = builder.recovered;
    }
    public CovidInformationEntity(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFips() {
        return fips;
    }

    public void setFips(String fips) {
        this.fips = fips;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getProvinceState() {
        return provinceState;
    }

    public void setProvinceState(String provinceState) {
        this.provinceState = provinceState;
    }

    public String getCountryRegion() {
        return countryRegion;
    }

    public void setCountryRegion(String countryRegion) {
        this.countryRegion = countryRegion;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Long confirmed) {
        this.confirmed = confirmed;
    }

    public Long getDeaths() {
        return deaths;
    }

    public void setDeaths(Long deaths) {
        this.deaths = deaths;
    }

    public Long getRecovered() {
        return recovered;
    }

    public void setRecovered(Long recovered) {
        this.recovered = recovered;
    }

    public Long getActive() {
        return active;
    }

    public void setActive(Long active) {
        this.active = active;
    }

    public String getCombinedKey() {
        return combinedKey;
    }

    public void setCombinedKey(String combinedKey) {
        this.combinedKey = combinedKey;
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

    public static class CovidInformationEntityBuilder {
        String fips;
        String admin;
        String provinceState;
        String countryRegion;
        Date lastUpdate;
        String latitude;
        String longitude;
        Long confirmed;
        Long deaths;
        Long recovered;
        Long active;
        String combinedKey;
        String incidentRate;
        String caseFatalityRatio;

        public static CovidInformationEntityBuilder newInstance() {
            return new CovidInformationEntityBuilder();
        }
        private CovidInformationEntityBuilder() {}

        public CovidInformationEntityBuilder setFIPS(String fips)
        {
            this.fips = fips;
            return this;
        }
        public CovidInformationEntityBuilder withAdmin(String admin){
            this.admin = admin;
            return this;
        }
        public CovidInformationEntityBuilder inCountryOrRegion(String country_region){
            this.countryRegion = country_region;
            return this;
        }
        public CovidInformationEntityBuilder inProvinceOrState(String province_state){
            this.provinceState = province_state;
            return this;
        }
        public CovidInformationEntityBuilder updatedOn(Date last_update){
            this.lastUpdate = last_update;
            return this;
        }
        public CovidInformationEntityBuilder withLatitude(String latitude){
            this.latitude = latitude;
            return this;
        }
        public CovidInformationEntityBuilder withLongitude(String longitude){
            this.longitude = longitude;
            return this;
        }
        public CovidInformationEntityBuilder havingConfirmedCases(Long confirmed){
            this.confirmed = confirmed;
            return this;
        }
        public CovidInformationEntityBuilder withDeathCount(Long deaths){
            this.deaths = deaths;
            return this;
        }
        public CovidInformationEntityBuilder withRecoveredCases(Long recovered){
            this.recovered = recovered;
            return this;
        }
        public CovidInformationEntityBuilder havingActiveCases(Long active){
            this.active = active;
            return this;
        }
        public CovidInformationEntityBuilder forCombinedKey(String combined_key){
            this.combinedKey = combined_key;
            return this;
        }
        public CovidInformationEntityBuilder withIncidentRate(String incident_rate){
            this.incidentRate = incident_rate;
            return this;
        }
        public CovidInformationEntityBuilder withCaseFatalityRate(String case_fatality_ratio){
            this.caseFatalityRatio = case_fatality_ratio;
            return this;
        }
        public CovidInformationEntity build()
        {
            return new CovidInformationEntity(this);
        }
    }
}
