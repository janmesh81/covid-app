package com.covid.dashboard.covidapp.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * This bean is responsible to download file from remote url to local workspace
 * **/
@Component
public class FileDownloader {
    private static final Logger LOGGER = LoggerFactory.getLogger("FileDownloader");

    @Value("${covidinfo.file.location}")
    private String fileLocation;

    @Value("${covidinfo.file.format}")
    private String fileFormat;

    public String downloadFile(String filePreFix){
        String fileURL = String.format(fileLocation, filePreFix, fileFormat);
        String fileName = new StringBuilder().append(filePreFix).append(".").append(fileFormat).toString();
        LOGGER.info("Copying SourceFile: "+fileName+" from location: "+fileURL+" locally to file: "+fileName);
        File file = new File(fileName);
        LOGGER.info("Download Completed");
        try {
            FileUtils.copyURLToFile(new URL(fileURL), file);
        } catch (IOException e) {
           LOGGER.error("Error while copying the file:: "+e.getMessage());
           LOGGER.error("Exception:: ", e);
        }
        return file.getName();
    }
}
