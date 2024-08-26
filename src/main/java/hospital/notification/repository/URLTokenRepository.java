package hospital.notification.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hospital.notification.model.URLToken;

@Repository
public interface URLTokenRepository extends JpaRepository<URLToken, Long> {

	Optional<URLToken> findByToken(String token);

	void deleteByToken(String token);
}