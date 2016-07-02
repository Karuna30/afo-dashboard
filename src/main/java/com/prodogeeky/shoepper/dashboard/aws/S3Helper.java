package com.prodogeeky.shoepper.dashboard.aws;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.transfer.TransferManager;

@Component
public class S3Helper {

	@Autowired
	private ResourcePatternResolver resourcePatternResolver;

	@Autowired
	private AmazonS3 amazonS3;

	public void downloadImages() throws IOException {
		Resource[] allNewImages =  this.resourcePatternResolver.getResources("s3://shopper.uploaded.images/**/*77*.jpeg");

		for (int i = 0; i < allNewImages.length; i++) {
			InputStream inputStream = allNewImages[i].getInputStream();
			Files.copy(inputStream, Paths.get("/Users/kaushalpanjwani/Study/prodogeeky/test-s3-local/1.jpeg"), StandardCopyOption.REPLACE_EXISTING);
		}
	}
	
	public void uploadImages() throws IOException {
		TransferManager transferManager = new TransferManager(this.amazonS3);
		transferManager.upload("shoepper.approved.images","1.jpeg",new File("/Users/kaushalpanjwani/Study/prodogeeky/test-s3-local/1.jpeg"));	
	}

}


