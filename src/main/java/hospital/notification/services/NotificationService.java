package hospital.notification.services;

import java.util.List;

import org.springframework.stereotype.Service;

import hospital.notification.dtos.NotificationDto;
import hospital.notification.enums.NotificationStatus;
import hospital.notification.enums.NotificationType;
import hospital.notification.exceptions.NotificationNotFoundException;
import hospital.notification.model.Notification;

@Service
public interface NotificationService {

	List<Notification> getAllNotification();

	Notification getNotification(Long id) throws NotificationNotFoundException;

	List<Notification> getNotificationByTo(String to);

	List<Notification> getNotificationByServiceName(String serviceName);

	List<Notification> getNotificationByType(NotificationType type);

	List<Notification> getNotificationByStatus(NotificationStatus status);

	Notification saveNotification(NotificationDto dto, String serviceName);

	void saveNotification(Notification notification);


}
