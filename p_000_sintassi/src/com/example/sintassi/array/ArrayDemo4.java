package com.example.sintassi.array;

import java.util.Arrays;

public class ArrayDemo4 {

	public static void main(String[] args) {
		int[] arrayOfIntegers1 = new int[100];
		int[] arrayOfIntegers2 = new int[100];
		int[] arrayOfIntegers3 = new int[arrayOfIntegers1.length + arrayOfIntegers2.length];

		for (int i = 0; i < arrayOfIntegers1.length; i++) {
			arrayOfIntegers1[i] = i * 2;
			arrayOfIntegers2[i] = i * -2;
		}

		int j = 0;

		for (int i = 0; i < arrayOfIntegers1.length; i++) {

			arrayOfIntegers3[j] = arrayOfIntegers1[i];
			j++;

			arrayOfIntegers3[j] = arrayOfIntegers2[i];
			j++;

		}

		System.out.println(Arrays.toString(arrayOfIntegers1));
		System.out.println(Arrays.toString(arrayOfIntegers2));
		System.out.println(Arrays.toString(arrayOfIntegers3));

	}

}
