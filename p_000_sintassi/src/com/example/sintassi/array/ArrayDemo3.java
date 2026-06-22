package com.example.sintassi.array;

public class ArrayDemo3 {

	public static void main(String[] args) {
		int[] arrayOfIntegers = new int[100];

		for (int i = 0; i < arrayOfIntegers.length; i++) {
			arrayOfIntegers[i] = i * 2;
		}

		int totalEven = 0;
		int totalOdds = 0;

		for (int i = 0; i < arrayOfIntegers.length; i++) {
			
			if (i % 2 == 0) {
				totalEven += arrayOfIntegers[i];
				continue;
			}
			totalOdds += arrayOfIntegers[i];

		}

		System.out.println(totalEven);
		System.out.println(totalOdds);

	}

}