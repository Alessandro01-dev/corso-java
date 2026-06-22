package com.example.sintassi.hello;

public class SommaString {

	public static String string1 = "abc";

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(addiziona("xyz"));
	}

	public static String addiziona(String string2) {
		String result = string1 + string2;
		return result;
	}

}