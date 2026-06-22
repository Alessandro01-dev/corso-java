package com.example.sintassi.banca;

import java.util.ArrayList;

public class ContoCorrente extends Object {

	private double saldo = 0.0;
	private ArrayList<Movimento> storico = new ArrayList<>();

	//private String formato="%s: saldo precedente=%,.2f importo=%,.2f saldo finale=%,.2f";
	private String formato="E' stato effettuato un %s di %.2f,\n il saldo iniziale è %.2f,\n il saldo finale è %.2f.\n";

	public ContoCorrente() {
		this(0.0);
	}

	public ContoCorrente(double saldoConto) {
		super();
		checkImportoGreaterThanOrEqualZero(saldoConto);
		saldo = saldoConto;
	}

	public void prelievi(double prelievo) {
		checkImportoGreaterThanZero(prelievo);

		// condizione aggiuntiva prelievo <= saldo
		consentitoPrelevare(prelievo);
        double memoria=saldo;
		saldo -= prelievo;
		
		addStorico(new Prelievo(prelievo));
		
		
		stampaMovimento(formato,"prelievo",prelievo,memoria,saldo);
		//Quanto serve sapere è: l'operazione fatta, l'importo dell'operazione, il saldo prima e il saldo dopo tutto su una singola riga
	}

	public void versamenti(double versamento) {
		checkImportoGreaterThanZero(versamento);
		double memoria=saldo;
		saldo += versamento;
		
		addStorico(new Versamento(versamento));
		
		stampaMovimento(formato, "versamento", versamento, memoria, saldo);
	}

	private void checkImportoGreaterThanZero(double importo) {
		if (!(importo > 0)) {
			System.out.println("L'importo deve essere > 0 ==> " + importo);
			throw new IllegalArgumentException("L'importo deve essere > 0 ==> " + importo);
		}
	}

	private void checkImportoGreaterThanOrEqualZero(double importo) {
		if (!(importo >= 0)) {
			System.out.println("L'importo deve essere >= 0 ==> " + importo);
			throw new IllegalArgumentException("L'importo deve essere >= 0 ==> " + importo);
		}
	}

	private void consentitoPrelevare(double importo) {
		if (importo > saldo) {
			System.out.println("L' importo del prelievo deve essere <= al saldo ==> " + importo);
			throw new IllegalArgumentException("L' importo del prelievo deve essere <= al saldo ==> " + importo);
		}
	}
	
	public void addStorico(Movimento m) {
		storico.add(m);
		
	}
	
    public void allStorico(){
    	
		for (Movimento m:storico) {
    		System.out.println(m.getDescrizione() +" " + m.getValore());
    	} 
    }
    
	public void stampaMovimento (String formato, String operazione, double importo, double saldoPre, double saldoPost) {
    	System.out.println(String.format(formato,operazione, importo, saldoPre, saldoPost));
    }

	public void stampaSaldo() {
		System.out.println("Saldo del c/c: "+saldo);
		System.out.println("\n");
	}

	public double saldo() {
		return saldo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder( getClass().getName() );
		builder.append(" [saldo=");
		builder.append(saldo);
		builder.append("]");
		return builder.toString();
	}





}