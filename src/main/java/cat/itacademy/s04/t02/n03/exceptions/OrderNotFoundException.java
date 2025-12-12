package cat.itacademy.s04.t02.n03.exceptions;

public class OrderNotFoundException extends RuntimeException {
	public OrderNotFoundException(String message) {
		super(message);
	}
}
