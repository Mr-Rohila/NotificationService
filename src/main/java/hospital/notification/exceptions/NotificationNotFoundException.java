package hospital.notification.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationNotFoundException extends Exception {

	private static final long serialVersionUID = 3574604464683435113L;
	private final String message;

	public NotificationNotFoundException(final String msg) {
		super(msg);
		this.message = msg;
	}

}
