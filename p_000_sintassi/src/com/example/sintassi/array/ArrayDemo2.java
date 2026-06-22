package com.example.sintassi.array;

public class ArrayDemo2 {

	public static void main(String[] args) {
		int[] arrayOfIntegers = new int[100];

		for (int i = 0; i < arrayOfIntegers.length; i++) {
			arrayOfIntegers[i] = i * 4 - 2;
		}

		int total = 0;
		int index = 0;
		while (index < arrayOfIntegers.length) {
			total += arrayOfIntegers[index];
			index++;
		}

		System.out.println("totale=" + total);

		for (int i = 0; i < arrayOfIntegers.length; i++) {
			arrayOfIntegers[i] = i * 2;
		}

		total = 0;
		index = 0;
		int increment = 5;
		while (index < arrayOfIntegers.length) {
			total += arrayOfIntegers[index];
			index += increment;
		}
		
		System.out.println(total);

	}
}
