package hospital.notification.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hospital.notification.enums.NotificationStatus;
import hospital.notification.enums.NotificationType;
import hospital.notification.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

	List<Notification> findByTo(String to);

	List<Notification> findBySource(String source);

	List<Notification> findByType(NotificationType type);

	List<Notification> findByStatus(NotificationStatus status);

}
