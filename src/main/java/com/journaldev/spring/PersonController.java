package com.journaldev.spring;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.apache.tika.parser.pkg.PackageParser;

@RestController
public class PersonController {
	
	@RequestMapping("/")
	public String welcome() throws Exception {
		readExcelFile();
		return "Welcome to Apache Tika Jar Extract...";
	}
	
	public void readExcelFile() throws Exception {
	//detecting the file type
      BodyContentHandler handler = new BodyContentHandler();
      Metadata metadata = new Metadata();
	  ClassLoader classLoader = this.getClass().getClassLoader();
      FileInputStream inputstream = new FileInputStream(new File(classLoader.getResource("Sample.jar").getFile()));
      ParseContext pcontext = new ParseContext();
      
      //Package parser
      PackageParser packageparser = new PackageParser();
      packageparser.parse(inputstream, handler, metadata,pcontext);
      System.out.println("Contents of the document: " + handler.toString());
      System.out.println("Metadata of the document:");
      String[] metadataNames = metadata.names();
      
      for(String name : metadataNames) {
         System.out.println(name + ":   " + metadata.get(name));
      }
	}
	
}
