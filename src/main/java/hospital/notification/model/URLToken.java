package hospital.notification.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class URLToken extends DateAudit {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String token;

	private LocalDateTime expiryDate;

	private Long userId;

	public URLToken() {
	}

	public URLToken(Long userId) {
		this.userId = userId;
		this.setToken(UUID.randomUUID().toString());
		this.setExpiryDate(LocalDateTime.now().plusMinutes(10));
	}
}
