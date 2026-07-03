package eccezioni;

public class MyRuntimeExceptionDemo {

	public static void main(String[] args) {
		MyRuntimeException me = new MyRuntimeException("Runtime Exception di prova");
		throw me;
	}

}
