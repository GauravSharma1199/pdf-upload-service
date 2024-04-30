package com.ione.pdfuploadservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories(basePackages = {"com.ione.commondata.repo"})
@ComponentScan(basePackages = {"com.ione"})
//@ComponentScan(basePackages = {"com.ione.commondata","com.ione", "com.ione.commondata.dto.*"})
@EntityScan({"com.ione.commondata.entity"})
public class PdfUploadServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfUploadServiceApplication.class, args);
	}

}
