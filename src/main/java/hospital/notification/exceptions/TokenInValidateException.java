package hospital.notification.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenInValidateException extends Exception {

	private static final long serialVersionUID = -3732523334872896893L;
	private final String message;

	public TokenInValidateException(final String msg) {
		super(msg);
		this.message = msg;
	}
}
