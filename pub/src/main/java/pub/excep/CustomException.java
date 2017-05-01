package pub.excep;

public class CustomException extends Exception {

	private static final long serialVersionUID = -8617183739062649571L;
	private String msg;

	public CustomException(String msg) {
		this.msg = msg;
	}
}
