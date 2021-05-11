package com.covid.dashboard.covidapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This is springboot application for covid dashboard.
 * This application has below components:
 * 	1. InitDataComponent.java which will be invoked once the application starts up.
 * 		This component reads the csv files from remote git repo, downloads it and populates
 * 		the data in the table.
 * 	2. LoadDataService.java this service is scheduled to run at 12:00:00 AM on daily bases.
 * 		This service will fetch the daily reports from remote git repo, downloads it and populates
 * 		the data in the table.
 * 	3. CovidAppRestController.jaa this the REST controller which exposes the services to get Covid
 * 		related information based on various filters.
 * 		It also exposes the service to get containment zones related information.
 * 	These services in turn uses many other components.
 **/
@EnableScheduling
@SpringBootApplication
public class CovidAppApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(CovidAppApplication.class);

	public static void main(String[] args) {
		LOGGER.info("Before run:: ");
		ApplicationContext applicationContext = SpringApplication.run(CovidAppApplication.class, args);
		LOGGER.info("After run:: ");

	}
}
