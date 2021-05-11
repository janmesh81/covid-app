package com.covid.dashboard.covidapp.service.loaddata.impl;

import com.covid.dashboard.covidapp.entity.CovidInformationEntity;
import com.covid.dashboard.covidapp.repository.CovidInformationRepository;
import com.covid.dashboard.covidapp.service.loaddata.LoadDataService;
import com.covid.dashboard.covidapp.util.CSVToEntityTransformer;
import com.covid.dashboard.covidapp.util.FileDownloader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This service is does below tasks.
 * It loads the remote file locally.
 * It loads the local CSV file into the database.
 * **/
@Service
public class LoadDataServiceImpl implements LoadDataService {
    @Autowired
    private FileDownloader fileDownloader;

    @Autowired
    private CovidInformationRepository repository;

    @Autowired
    private CSVToEntityTransformer transformer;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDataServiceImpl.class);
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy");

    public void execute(){
            LOGGER.info("Inside LoadDataServiceImpl: ");
            String currentDate = dateFormatter.format(new Date());
            LOGGER.info("Fetching for Date:: "+currentDate);
            String fileName = fileDownloader.downloadFile(currentDate);
            File fileObj = new File(fileName);
            if(fileObj.exists()) {
                List<CovidInformationEntity> entitiesToSave = transformer.transform(fileName);
                if(!entitiesToSave.isEmpty()){
                    repository.saveAll(entitiesToSave);
                }
            }
            LOGGER.info("Data Loading completed ");
    }

}

