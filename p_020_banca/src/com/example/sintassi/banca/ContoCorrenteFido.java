package com.example.sintassi.banca;

public class ContoCorrenteFido extends ContoCorrente {

	private double massimoFidoConcesso = 0.0;

	private double fidoDisponibile = 0.0;

	private String formato = "E' stato effettuato un %s di %.2f,\n il fido iniziale è %.2f,\n il fido finale è %.2f.\n";

	public ContoCorrenteFido(double fido) {
		this(0.0, fido);
	}

	public ContoCorrenteFido(double saldoConto, double fido) {
		super(saldoConto);
		this.massimoFidoConcesso = fido;
		this.fidoDisponibile = fido;
	}

	public void prelievi(double prelievo) {
		consentitoPrelevare(prelievo);
		double memoria = fidoDisponibile;
		// importo x
		// x < saldo => preleva normalmente
		if (prelievo <= saldo()) {
			super.prelievi(prelievo);
			stampaFido(formato, "Fido-", prelievo, memoria, fidoDisponibile);
			return;
		}

		double daPrelevareDalFido = prelievo - saldo();
		super.prelievi(saldo());

		fidoDisponibile -= daPrelevareDalFido;
		addStorico(new PrelievoFido(daPrelevareDalFido));
		stampaFido(formato, "Fido-", daPrelevareDalFido, memoria, fidoDisponibile);
	}

//<<<<<<< HEAD:p_020_banca/src/com/example/sintassi/banca/ContoCorrenteFido.java
	public void versamenti(double versamento) {
		if (versamento <= 0) {
			throw new IllegalArgumentException("Il versamento deve essere maggiore di 0");
		}

		// Calcolo quanto fido è stato usato
		double fidoUsato = massimoFidoConcesso - fidoDisponibile;

		// Se non è stato usato il fido, verso tutto normalmente sul conto
		if (fidoUsato == 0) {
			super.versamenti(versamento);
			return;
		}

		// Se il versamento basta solo a ricaricare parzialmente/tutto il fido
		if (versamento <= fidoUsato) {
			fidoDisponibile += versamento;

			addStorico(new VersamentoFido(versamento));
			return;
		}

		// Se il versamento supera il fido usato:
		// prima riempio il fido, poi il resto va nel saldo
		fidoDisponibile = massimoFidoConcesso;
		addStorico(new VersamentoFido(fidoUsato));
		double restoDaVersareSulConto = versamento - fidoUsato;

		super.versamenti(restoDaVersareSulConto);
//=======
//	public void versamenti (double versamento) {
//		double memoria=fidoDisponibile;
//		if (versamento<=(massimoFidoConcesso-fidoDisponibile)) {// se il versamento è <= massimoFidoConcesso - fidoDisponibile ==> riempire solo il fidoDisponibile (riempire sino al max il fido disponibile)
//			fidoDisponibile+=versamento;
//			stampaFido(formato, "Fido+", versamento, memoria, fidoDisponibile);
//			return;
//		}
//		// calcolo quota fido e quota cc ==> aggiorno
//			versamento-=(massimoFidoConcesso-fidoDisponibile);
//			double aumentoFido=massimoFidoConcesso-fidoDisponibile;
//			super.versamenti(versamento);
//		    fidoDisponibile=massimoFidoConcesso;
//		    stampaFido(formato, "Fido+", aumentoFido, memoria, fidoDisponibile);
//>>>>>>> aa56e604df29166a1af7c947f4f8f509d7091d59:i_020_banca/src/com/example/sintassi/banca/ContoCorrenteFido.java
	}

	private void consentitoPrelevare(double importo) {
		double importoPrelevabile = saldo() + fidoDisponibile;
		if (importo > importoPrelevabile) {
			System.out.println("L' importo del prelievo deve essere <= al saldo+fidoDisponibile ==> " + importo);
			throw new IllegalArgumentException(
					"L' importo del prelievo deve essere <= al saldo+fidoDisponibile ==> " + importo);
		}
	}

	public double fidoDisponibile() {
		return fidoDisponibile;
	}

	public double fido() {
		return massimoFidoConcesso;
	}

	public void stampaSaldo() {
		super.stampaSaldo();
		System.out.println("Fido disponibile: " + fidoDisponibile);
		System.out.println("Fido massimo: " + massimoFidoConcesso);
	}

	public void stampaFido(String formato, String operazione, double importo, double fidoPre, double fidoPost) {
		System.out.println(String.format(formato, operazione, importo, fidoPre, fidoPost));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getClass().getName());
		builder.append(" [massimoFidoConcesso=");
		builder.append(massimoFidoConcesso);
		builder.append(", fidoDisponibile=");
		builder.append(fidoDisponibile);
		builder.append(", saldo()=");
		builder.append(saldo());
		builder.append("]");
		return builder.toString();
	}
}
