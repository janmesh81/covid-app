package com.covid.dashboard.covidapp.service.information.impl;

import com.covid.dashboard.covidapp.entity.CovidInformationEntity;
import com.covid.dashboard.covidapp.mapper.CovidInformationEntityContainmentZoneMapper;
import com.covid.dashboard.covidapp.mapper.CovidInformationEntityResponseMapper;
import com.covid.dashboard.covidapp.model.CovidContainmentZone;
import com.covid.dashboard.covidapp.model.CovidInformationResponse;
import com.covid.dashboard.covidapp.repository.CovidInformationRepository;
import com.covid.dashboard.covidapp.service.information.CovidInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * This is the service implementation for get methods.
 * It has implementation for 2 get methods.
 *  1. get for COVID information.
 *  2. get for containment zones.
 * **/
@Service
public class CovidInformationServiceImpl implements CovidInformationService {

    @Autowired
    private CovidInformationRepository repository;
    @Autowired
    private CovidInformationEntityResponseMapper mapper;
    @Autowired
    private CovidInformationEntityContainmentZoneMapper containmentZoneMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CovidInformationServiceImpl.class);

    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private static final String DEFAULT_VALUE_FOR_PARAMS = "%";

    @Value("${covidifo.containmentzone.threshold}")
    private String containmentZoneThreshold;

    @Override
    public Page<CovidInformationResponse> getFilteredCovidInformation(int pageNo,int pageSize,String country, String state, String startDate, String endDate, String frequency) throws ParseException {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<CovidInformationEntity> entities;
        if(DEFAULT_VALUE_FOR_PARAMS.equals(startDate))
        {
            entities = repository.findAllByCountryRegionAndProvinceState(country, state, pageable);
        }
        else {
            try {
                Date strDate = convertToFormattedDate(startDate);

                Date enDate;
                if(DEFAULT_VALUE_FOR_PARAMS.equals(endDate)){
                   enDate = convertToFormattedDate(dateFormatter.format(new Date()));
                }
                else {
                    enDate = convertToFormattedDate(endDate);
                }
                entities = repository.findAllByCountryRegionAndProvinceStateAndLastUpdateBetween(country, state, strDate, enDate, pageable);
            }
            catch (ParseException e) {
                LOGGER.error("Error Parsing the Date");
                throw e;
            }
        }
        List<CovidInformationResponse> responseList = mapper.covidInformationEntityToCovidInformationResponse(entities.getContent());
        return new PageImpl<>(responseList, pageable, entities.getTotalElements());
    }

    @Override
    public Page<CovidContainmentZone> getContainmentZones(int pageNo, int pageSize) {
        Page<CovidInformationEntity> entities;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        entities = repository.findAllByActiveGreaterThanEqual(Long.parseLong(containmentZoneThreshold), pageable);
        List<CovidContainmentZone> responseList = containmentZoneMapper.covidInformationEntityToCovidInformationResponse(entities.getContent());
        return new PageImpl<>(responseList, pageable, entities.getTotalElements());
    }

    private Date convertToFormattedDate(String dateValue) throws ParseException {
        Date date;
        try {
            LOGGER.info("Date to be formatted: "+dateValue);
            date = dateFormatter.parse(dateValue);
            LOGGER.info("Date formatted: "+date);
        } catch (ParseException e) {
            date = new Date();
            LOGGER.error("Error while parsing the date:: "+dateValue);
            throw e;
        }
        return date;
    }
}
