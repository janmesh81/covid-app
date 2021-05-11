package com.covid.dashboard.covidapp.mapper;

import com.covid.dashboard.covidapp.entity.CovidInformationEntity;
import com.covid.dashboard.covidapp.model.CovidInformationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/** This is the mapper class which maps data from entity to response object**/
@Mapper(componentModel = "spring")
public interface CovidInformationEntityResponseMapper {
            @Mapping(target="activeCaseNumber", source="entity.active")
            @Mapping(target="confirmedCaseNumber", source="entity.confirmed")
            @Mapping(target="deathNumber", source="entity.deaths")
            @Mapping(target="recoveryNumber", source="entity.recovered")
            @Mapping(target="incidentRate", source="entity.incidentRate")
            @Mapping(target="caseFatalityRatio", source="entity.caseFatalityRatio")
            CovidInformationResponse covidInformationEntityToCovidInformationResponse(CovidInformationEntity entity);


            List<CovidInformationResponse> covidInformationEntityToCovidInformationResponse(List<CovidInformationEntity> entity);
}
