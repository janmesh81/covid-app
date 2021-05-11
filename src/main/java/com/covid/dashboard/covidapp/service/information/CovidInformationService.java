package com.covid.dashboard.covidapp.service.information;

import com.covid.dashboard.covidapp.model.CovidContainmentZone;
import com.covid.dashboard.covidapp.model.CovidInformationResponse;
import org.springframework.data.domain.Page;

import java.text.ParseException;

/**
 * Interface which is a contract for rest services**/

public interface CovidInformationService {

public Page<CovidInformationResponse> getFilteredCovidInformation(int pageNo,int pageSize,String country, String state, String startDate, String endDate, String frequency) throws ParseException;

public Page<CovidContainmentZone> getContainmentZones(int pageNo, int pageSize);
}
