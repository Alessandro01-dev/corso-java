package com.example.sintassi.contenitori;

import java.util.ArrayList;
import java.util.Random;

public class ArrayListDemo {
	
	public static void main(String[] args) {
		
		Random rnd = new Random();
		
		int[] arrayOfIntegers = new int[10];
		
		ArrayList<Integer> listOfIntegers = new ArrayList<>();
	
		Integer x = 100;
		int y = x;
		
		for (int i = 0; i < arrayOfIntegers.length; i++) {
			int randomValue = rnd.nextInt(1000);
			arrayOfIntegers[i] += randomValue;
			listOfIntegers.add(randomValue);
			System.out.println("Dimensione dell'array " + arrayOfIntegers.length);
			System.out.println("Size della lista " + listOfIntegers.size());
		}
		
	}
	
}
