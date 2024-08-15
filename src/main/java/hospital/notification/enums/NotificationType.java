package hospital.notification.enums;

public enum NotificationType {

	VERIFY("Verify Mail Id"), PASSWORD_RESET("Password Reset");

	private final String value;

	NotificationType(final String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
