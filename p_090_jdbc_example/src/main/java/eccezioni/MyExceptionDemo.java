package eccezioni;

public class MyExceptionDemo {

	public static void main(String[] args) throws MyException {
		MyException me = new MyException("Runtime Exception di prova");
		throw me;
	}

}
