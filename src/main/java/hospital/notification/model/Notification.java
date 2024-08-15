package hospital.notification.model;

import hospital.notification.enums.NotificationStatus;
import hospital.notification.enums.NotificationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Notification extends DateAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String serviceName;

	@Column(name = "toEmail")
	private String to;

	private Long userId;

	private String userName;

	private String subject;

	private String redirectUrl;

	@Column(columnDefinition = "TEXT")
	private String templateName;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private NotificationStatus status;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private NotificationType type;

	private String error;
}