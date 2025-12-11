package cat.itacademy.s04.t02.n03.exceptions;

public class DuplicateProviderName extends RuntimeException {
	public DuplicateProviderName(String message) {
		super(message);
	}
}
