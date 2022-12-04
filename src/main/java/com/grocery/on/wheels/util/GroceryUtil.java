package com.grocery.on.wheels.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.amazonaws.services.connect.model.S3Config;
import com.amazonaws.services.s3.AmazonS3Client;
import com.grocery.on.wheels.s3.configuration.S3Configuration;
import com.grocery.on.wheels.s3.service.AmazonClient;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;  

public class GroceryUtil {
	private static final DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
	
	public static String getFormatDate(Date date) {
		return df.format(date);
	}
	
	public static String createAndUploadPDF() {
		return "";
	}
	
	
	
	/*******************************************PDF **************************************/
	public static File createAndUpload(List<String> texts, String invoiceId)  
	{  
		File pdfFile = new File(invoiceId + ".pdf");
		Document doc = new Document();  
		try  
		{  
			PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(pdfFile));  
			doc.open();  
			for(String text: texts) {
				doc.add(new Paragraph(text));
			}
			doc.close();  
			writer.close();  
		} catch (DocumentException e) {  
			e.printStackTrace();  
		} catch (FileNotFoundException e) {  
			e.printStackTrace();  
		}  
		return pdfFile;
	}  
	public static String uploadFile(File file, S3Configuration s3Config, AmazonClient s3Client) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Length", String.valueOf(file.length()));
        String path = String.format("%s/%s", s3Config.getBucketName(), UUID.randomUUID());
        String fileName = String.format("%s", file.getName());
        String savedFilePath = String.format("%s/%s", path, fileName);
        try {
        	s3Client.upload(path, fileName, Optional.of(metadata), new FileInputStream(file));
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        return savedFilePath;
	}
}
