package com.covid.dashboard.covidapp.scheduler;

import com.covid.dashboard.covidapp.service.loaddata.LoadDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * This component is scheduler which executes LoadDataService
 * at 12:00:00 on daily bases.
 * **/
@Component
public class DailyReportFetchingScheduler {
    @Autowired
    private LoadDataService loadDataService;

    @Scheduled(cron = "0 0 0 * * *")
    public void runJob(){
        loadDataService.execute();
    }
}
