package hospital.notification.dtos;

import hospital.notification.enums.NotificationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationDto {

	@NotBlank(message = "Notification type not found")
	@NotEmpty(message = "Notification type not found")
	@NotNull(message = "Notification type not found")
	private NotificationType type;

	private String redirectUrl;

	@NotNull(message = "User Id not found")
	private Long userId;

}
