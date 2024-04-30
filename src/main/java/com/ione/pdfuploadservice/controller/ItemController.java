package com.ione.pdfuploadservice.controller;

import com.ione.pdfuploadservice.scheduler.PdfUploadScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private PdfUploadScheduler pdfUploadScheduler;
	@GetMapping("/trigger/pdf")
	public void pushPdf() {
		try {
			pdfUploadScheduler.getPdfUploadAPI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
