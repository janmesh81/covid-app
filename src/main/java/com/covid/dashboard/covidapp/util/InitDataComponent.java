package com.covid.dashboard.covidapp.util;

import com.covid.dashboard.covidapp.entity.CovidInformationEntity;
import com.covid.dashboard.covidapp.repository.CovidInformationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * This Bean is responsible for fetching data and populating the table
 * on the ApplicationReadyEvent.
 * **/

@Component
public class InitDataComponent {

    @Autowired
    private FileDownloader fileDownloader;

    @Autowired
    private CovidInformationRepository repository;

    @Autowired
    private CSVToEntityTransformer transformer;

    private static final Logger LOGGER = LoggerFactory.getLogger("InitDataComponent");

    @EventListener(ApplicationReadyEvent.class)
    public void loadInitData() {
        LOGGER.info("Loading initial data");
        List<String> listOfFilePrefix = Arrays.asList("04-20-2021", "01-15-2021");
        for (String filePrefix : listOfFilePrefix) {
            String fileName = fileDownloader.downloadFile(filePrefix);
            File fileObj = new File(fileName);
            if (fileObj.exists()) {
                List<CovidInformationEntity> entitiesToSave = transformer.transform(fileName);
                if (!entitiesToSave.isEmpty()) {
                    repository.saveAll(entitiesToSave);
                }
            }
        }
        LOGGER.info("Initial loading completed");
    }
}
