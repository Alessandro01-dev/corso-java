package com.example.sintassi.hello;

public class Sommatore {

	public static int somma = 0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println( addiziona(200) );
		System.out.println( addiziona(100) );
		System.out.println( addiziona(-30) );
		System.out.println( addiziona(500) );
		System.out.println( "-----------" );
		System.out.println( addiziona2(200) );
		System.out.println( addiziona2(100) );
		System.out.println( addiziona2(-30) );
		System.out.println( addiziona2(500) );
		
	}
	
	public static int addiziona(int valore) {
		somma = somma + valore;
		return somma;
	}
	
	public static int addiziona2(int valore) {
		int a = 0;
		a = a + valore;
		return a;
	}

}
