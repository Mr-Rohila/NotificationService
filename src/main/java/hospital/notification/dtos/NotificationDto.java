package hospital.notification.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationDto {

	@NotBlank(message = "to is Empty")
	@NotNull(message = "to is null")
	@NotEmpty(message = "to is Empty")
	private String to;

	@NotBlank(message = "to is Empty")
	@NotNull(message = "to is null")
	@NotEmpty(message = "to is Empty")
	private String source;

	private String subject = "General - Notification";

	@NotBlank(message = "to is Empty")
	@NotNull(message = "to is null")
	@NotEmpty(message = "to is Empty")
	private String body;
}
