package com.prodogeeky.shoepper.dashboard.jobs;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.prodogeeky.shoepper.dashboard.aws.S3Helper;

@Component
public class Scheduler {
	
	static final Logger LOG = LoggerFactory.getLogger(Scheduler.class);
	private static final Integer MAX_IMAGES_TO_PROCESS = 10;
	
	@Autowired
	private S3Helper s3Helper;

    @Scheduled(fixedDelay = 5000, initialDelay = 10000)
    public void downloadImages() {
    	try {
    		//GET from DB
			s3Helper.downloadImages();
		} catch (IOException e) {
			LOG.error("Exception in downloading images", e);
		}
    }

    
    @Scheduled(fixedDelay = 5000, initialDelay = 10000)
    public void approveImages() {
    	try {
			s3Helper.uploadImages();
			//PUT it in DB
		} catch (IOException e) {
			LOG.error("Exception in uploading images", e);
		}
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 10000)
    public void rejectImages() {
			//Update DB
    }

}
