package com.covid.dashboard.covidapp.controller;

import com.covid.dashboard.covidapp.model.CovidContainmentZone;
import com.covid.dashboard.covidapp.model.CovidInformationResponse;
import com.covid.dashboard.covidapp.service.information.CovidInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.text.ParseException;

/**
 * This class is the REST controller which is listening to below URLs:
 * 1. /dashboard
 * 2. /dashboard/containment-zones
 * **/

@RestController
public class CovidAppRestController {

    @Autowired
private CovidInformationService covidInfoService;

private static final Logger LOGGER = LoggerFactory.getLogger(CovidAppRestController.class);
    //TODO: Exception handling and sending http error and response.
    @GetMapping("/dashboard")
    public Page<CovidInformationResponse> getCovidInformation(@RequestParam(defaultValue = "0") Integer pageNo,
                                                              @RequestParam(defaultValue = "10") Integer pageSize,
                                                              @RequestParam(value = "country", defaultValue = "%") String countryName,
                                                              @RequestParam(value = "state", defaultValue = "%") String stateName,
                                                              @RequestParam(value = "start_date", defaultValue = "%") String startDate,
                                                              @RequestParam(value = "end_date", defaultValue = "%") String endDate,
                                                              @RequestParam(value = "frequency", defaultValue = "daily") String frequency) throws IOException {
        LOGGER.info("Inside Controller, getCovidInformation Method:: ");
        try {
            return covidInfoService.getFilteredCovidInformation(pageNo,pageSize,countryName,stateName,startDate,endDate,frequency);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/dashboard/containment-zones")
    public Page<CovidContainmentZone> getContainmentZones(@RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam(defaultValue = "10") Integer pageSiz){
        LOGGER.info("Inside Controller, getContainmentZones Method:: ");
        return covidInfoService.getContainmentZones(pageNo, pageSiz);
    };
}
