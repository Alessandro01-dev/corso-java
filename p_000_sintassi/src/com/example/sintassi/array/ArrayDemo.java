package com.example.sintassi.array;

import java.util.Arrays;
import java.util.Random;

public class ArrayDemo {

	public static void main(String[] args) {

		int daysOfTheMonth1[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		int daysOfTheMonth2[] = new int[100];
		
		int srcIndex = 0;
		int destIndex = 0;
		int len = daysOfTheMonth1.length;

		System.arraycopy(daysOfTheMonth1,srcIndex,daysOfTheMonth2,destIndex,len);
		System.out.println(Arrays.toString(daysOfTheMonth2));

		int[] arrayOfIntegers = new int[100];

		System.out.println(arrayOfIntegers);
		Random random = new Random();
		
		for (int i = 0; i < arrayOfIntegers.length; i++) {
			arrayOfIntegers[i] = random.nextInt(1000);
		}
		
		System.out.println(Arrays.toString(arrayOfIntegers));
		System.out.println(arrayOfIntegers);
		
		for (int el : arrayOfIntegers) {
			System.out.println(el);
		}
		
		for (int i = arrayOfIntegers.length -1; i >= 0; i--) {
			arrayOfIntegers[i] = random.nextInt(1000);
			System.out.println("i=" + i + "	" + arrayOfIntegers[i]);	
		}
		
		int sum = 0;
		
		for (int i = 0; i < arrayOfIntegers.length; i++) {
			sum += arrayOfIntegers[i];
		}
		
		System.out.println(sum);
		
		int partialSum = 0;
		
		for (int i =0; i < arrayOfIntegers.length; i++) {
			arrayOfIntegers[i] = i*2;
		}
		
		int lowerLimit = 55;
		int upperLimit = 82;
		
		for (int i = 55; i < 82; i++) {
			partialSum += arrayOfIntegers[i];
		}
		
		System.out.println(partialSum);
		
		int partialSumBackwards = 0;
		
		for (int i = upperLimit - 1; i >= lowerLimit; i--) {
			partialSumBackwards += arrayOfIntegers[i];
		}
		
		System.out.println(partialSumBackwards);
		
		int partialSumIncrement3 = 0;
		int increment = 3;
		lowerLimit = 12;
		upperLimit = 94;
		
		for (int i = lowerLimit; i < upperLimit; i += increment) {
			partialSumIncrement3 += arrayOfIntegers[i];
		}
		
		System.out.println(partialSumIncrement3);
		
		int totalSum1 = 0;
		increment = 5;
		
		for (int i = 0; i < arrayOfIntegers.length; i += increment) {
			totalSum1 += arrayOfIntegers[i];
		}
		
		System.out.println(totalSum1);
		
		int totalSum2 = 0;
		
		for (int i = 0; i < arrayOfIntegers.length; i++) {
			totalSum2 += arrayOfIntegers[i];
		}
		
		System.out.println(totalSum2);
	}

}
