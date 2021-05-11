package com.covid.dashboard.covidapp.util;

import com.covid.dashboard.covidapp.entity.CovidInformationEntity;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
enum CSVHeader {FIPS, Admin2, Province_State, Country_Region, Last_Update, Lat, Long_, Confirmed, Deaths, Recovered, Active, Combined_Key, Incident_Rate, Case_Fatality_Ratio}

/**
 * This bean is responsible to for converting data present in the CSV file to
 * the entity, which represents the db table.
 * **/
@Component
public class CSVToEntityTransformer {

        private static final Logger LOGGER = LoggerFactory.getLogger(CSVToEntityTransformer.class);
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        public List<CovidInformationEntity> transform(String filePath) {
            LOGGER.info("Inside CSV to Entity transformer.");
            LOGGER.info("Incoming file to be transformed: "+filePath);
            List<CovidInformationEntity> covidInfoEntityList = new ArrayList<>();
            try (FileReader fileReader = new FileReader(filePath);
                    CSVParser csvParser = new CSVParser(fileReader,
                         CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
                Iterable<CSVRecord> csvRecords = csvParser.getRecords();

                for (CSVRecord csvRecord : csvRecords) {
                    LOGGER.info("Processing for row#: "+csvRecord.getRecordNumber()+" started");
                    CovidInformationEntity entity = CovidInformationEntity.CovidInformationEntityBuilder.newInstance()
                            .setFIPS(csvRecord.get(CSVHeader.FIPS.name()))
                            .withAdmin(csvRecord.get(CSVHeader.Admin2.name()))
                            .inCountryOrRegion(csvRecord.get(CSVHeader.Country_Region.name()))
                            .inProvinceOrState(csvRecord.get(CSVHeader.Province_State.name()))
                            .withLatitude(csvRecord.get(CSVHeader.Lat.name()))
                            .withLongitude(csvRecord.get(CSVHeader.Long_.name()))
                            .forCombinedKey(csvRecord.get(CSVHeader.Combined_Key.name()))
                            .havingConfirmedCases(parsedLongField(csvRecord.get(CSVHeader.Confirmed.name())))
                            .havingActiveCases(parsedLongField(csvRecord.get(CSVHeader.Active.name())))
                            .withDeathCount(parsedLongField(csvRecord.get(CSVHeader.Deaths.name())))
                            .withRecoveredCases(parsedLongField(csvRecord.get(CSVHeader.Recovered.name())))
                            .withCaseFatalityRate(csvRecord.get(CSVHeader.Case_Fatality_Ratio.name()))
                            .withIncidentRate(csvRecord.get(CSVHeader.Incident_Rate.name()))
                            .updatedOn(convertToFormattedDate(csvRecord.get(CSVHeader.Last_Update.name())))
                            .build();
                    covidInfoEntityList.add(entity);
                }
                LOGGER.info("Total size of Entities returned:: "+covidInfoEntityList.size());
            }
            catch (IOException e) {
                LOGGER.error("Error: " +e.getMessage()+" while processing File:: "+ filePath);
                LOGGER.error("Exception is:: ");
                e.printStackTrace();
            }
            return covidInfoEntityList;
        }

        private Long parsedLongField(String fieldValue){
            if (fieldValue == null | fieldValue.length()==0){
                return 0L;
            }
            else {
                return Long.parseLong(fieldValue);
            }

        }

        private Date convertToFormattedDate(String dateValue){
            Date date;
            try {
                LOGGER.info("Date to be formatted: "+dateValue);
                date = dateFormatter.parse(dateValue);
                LOGGER.info("Date formatted: "+date);
            } catch (ParseException e) {
                date = new Date();
                LOGGER.error("Error while parsing the date:: "+dateValue);
                LOGGER.error("Exception is :: ", e);
            }
            return date;
        }
}
