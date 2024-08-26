package hospital.notification.services;

import org.springframework.stereotype.Service;

import hospital.notification.dtos.NotificationDto;

@Service
public interface EmailService {

	String sendEmail(NotificationDto dto, String serviceName) throws Exception;
}
