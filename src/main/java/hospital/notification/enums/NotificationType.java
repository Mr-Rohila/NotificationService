package hospital.notification.enums;

public enum NotificationType {

	VERIFY_MAIL_ID("VERIFY MAIL ID"), PASSWORD_RESET("PASSWORD RESET");

	private final String value;

	NotificationType(final String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
