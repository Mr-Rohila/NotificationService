package hospital.notification.enums;

public enum NotificationStatus {

	PENDING("PENDING"), SENT("SENT"), DELIVERED("DELIVERED"), FAILED("FAILED");

	private final String value;

	NotificationStatus(final String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}
}
