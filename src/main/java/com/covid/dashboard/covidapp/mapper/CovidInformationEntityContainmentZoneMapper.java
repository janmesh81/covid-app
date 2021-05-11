package com.covid.dashboard.covidapp.mapper;

import com.covid.dashboard.covidapp.entity.CovidInformationEntity;
import com.covid.dashboard.covidapp.model.CovidContainmentZone;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/** This is the mapper class which maps data from entity to response object**/
@Mapper(componentModel = "spring")
public interface CovidInformationEntityContainmentZoneMapper {

        @Mapping(target="country", source="entity.countryRegion")
        @Mapping(target="state", source="entity.provinceState")
        @Mapping(target="latitude", source="entity.latitude")
        @Mapping(target="longitude", source="entity.longitude")
        CovidContainmentZone covidInformationEntityToCovidInformationResponse(CovidInformationEntity entity);
        List<CovidContainmentZone> covidInformationEntityToCovidInformationResponse(List<CovidInformationEntity> entity);

}
