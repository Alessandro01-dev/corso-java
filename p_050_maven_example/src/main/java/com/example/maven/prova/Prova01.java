package com.example.maven.prova;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Prova01 {

	private static final Logger log = LoggerFactory.getLogger(Prova01.class);
	
	public static void main(String[] args) {
		String saluti = "Hello World";
		System.out.println(saluti);
		
		log.error(saluti);
	}

}
