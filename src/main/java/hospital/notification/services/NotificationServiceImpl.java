package hospital.notification.services;

import java.util.List;

import org.springframework.stereotype.Component;

import hospital.notification.dtos.NotificationDto;
import hospital.notification.enums.NotificationStatus;
import hospital.notification.enums.NotificationType;
import hospital.notification.exceptions.NotificationNotFoundException;
import hospital.notification.model.Notification;
import hospital.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final NotificationRepository notificationRepository;

	@Override
	public List<Notification> getAllNotification() {
		return notificationRepository.findAll();
	}

	@Override
	public Notification getNotification(Long id) throws NotificationNotFoundException {
		return notificationRepository.findById(id)
				.orElseThrow(() -> new NotificationNotFoundException("Notification with ID " + id + " not found"));
	}

	@Override
	public List<Notification> getNotificationByTo(String to) {
		return notificationRepository.findByTo(to);
	}

	@Override
	public List<Notification> getNotificationBySource(String source) {
		return notificationRepository.findBySource(source);
	}

	@Override
	public List<Notification> getNotificationByType(NotificationType type) {
		return notificationRepository.findByType(type);
	}

	@Override
	public List<Notification> getNotificationByStatus(NotificationStatus status) {
		return notificationRepository.findByStatus(status);
	}

	@Override
	public String sendNotification(NotificationDto dto) {
		return null;
	}

}
