package com.covid.dashboard.covidapp.repository;

import com.covid.dashboard.covidapp.entity.CovidInformationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;

/**
 * This is the repository exposing APIs to services layer.
 * **/

@Repository
public interface CovidInformationRepository extends PagingAndSortingRepository<CovidInformationEntity, Long> {

    Page<CovidInformationEntity> findAllByCountryRegionAndProvinceState(String country, String state, Pageable pageable);
    Page<CovidInformationEntity> findAllByCountryRegionAndProvinceStateAndLastUpdateBetween(String country, String state,Date startDate, Date endDate,  Pageable pageable);
    Page<CovidInformationEntity> findAllByActiveGreaterThanEqual(Long activeCaseThreshold,  Pageable pageable);
}
