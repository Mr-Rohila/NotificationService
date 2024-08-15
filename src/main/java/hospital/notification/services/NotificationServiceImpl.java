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
	public List<Notification> getNotificationByServiceName(String serviceName) {
		return notificationRepository.findByServiceName(serviceName);
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
	public Notification saveNotification(NotificationDto dto, String serviceName) {

		switch (dto.getType()) {
		case VERIFY -> {
			Notification notification = new Notification();
			notification.setUserId(dto.getUserId());
			notification.setSubject("Please Verify Your Email Address");
			notification.setTemplateName("verification-email");
			notification.setRedirectUrl(dto.getRedirectUrl());
			notification.setStatus(NotificationStatus.PENDING);
			notification.setType(NotificationType.VERIFY);
			notification.setServiceName(serviceName);
			return notificationRepository.save(notification);
		}
		case PASSWORD_RESET -> {
			return null;
		}

		default -> throw new IllegalArgumentException("Unexpected type : " + dto.getType());
		}
	}

	@Override
	public void saveNotification(Notification notification) {
		notificationRepository.save(notification);
	}
}
