package hospital.notification.enums;

public enum NotificationType {

	EMAIL("EMAIL"), SMS("SMS");

	private final String value;

	NotificationType(final String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
