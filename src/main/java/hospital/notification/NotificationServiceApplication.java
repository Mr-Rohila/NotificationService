package hospital.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import hospital.notification.dtos.NotificationDto;
import hospital.notification.enums.NotificationType;
import hospital.notification.services.EmailService;

@SpringBootApplication
public class NotificationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@Autowired
	private EmailService emailService;

	// @PostConstruct
	public void sendEmail() {
		NotificationDto dto = new NotificationDto();
		dto.setType(NotificationType.VERIFY);
		dto.setUserId(1L);
		dto.setRedirectUrl("https://google.com");
		emailService.sendEmail(dto, "Auth Server");
		System.out.println("Email Send Successfully");
	}
}
