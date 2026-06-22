package com.example.sintassi.stringMethods;

public class StringMethods {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String myStr = "Io mi chiamo Mario Rossi";
		String mySubstr1 = "Mario";
		String mySubstr2 = "Giovanni";

		extractSubstring(myStr, mySubstr1);
		extractSubstring(myStr, mySubstr2);

	}

	public static void extractSubstring(String str, String substr) {

		if (str.contains(substr)) {
			int substrIndex = str.indexOf(substr);
			int subStringLength = substr.length();
			String extractedSubstring = str.substring(substrIndex, substrIndex + subStringLength);
			System.out.println("Sottostringa trovata: " + extractedSubstring);
		} else {
			System.out.println("Sottostringa non trovata");
		}
	}

	public boolean extractSubstringResult(String str, String substr) {
		boolean contiene = str.contains(substr);
		if (contiene) {
			int substrIndex = str.indexOf(substr);
			int subStringLength = substr.length();
			String extractedSubstring = str.substring(substrIndex, substrIndex + subStringLength);
			System.out.println("Sottostringa trovata: " + extractedSubstring);
		} else {
			System.out.println("Sottostringa non trovata");
		}
		return contiene;
	}
}
