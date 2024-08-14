package hospital.notification.model;

import hospital.notification.dtos.NotificationDto;
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
public class Notification {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String source;

	private String to;

	private String subject;

	private String body;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private NotificationStatus status;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private NotificationType type;

	public Notification() {
	}

	public Notification(NotificationDto dto) {
		this.to = dto.getTo();
		this.source = dto.getSource();
		this.subject = dto.getSubject();
		this.body = dto.getBody();
		this.status = NotificationStatus.PENDING;
		this.type = NotificationType.EMAIL;
	}

}
