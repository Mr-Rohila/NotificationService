package hospital.notification.services;

import org.springframework.stereotype.Service;

import hospital.notification.exceptions.TokenInValidateException;
import hospital.notification.model.URLToken;

@Service
public interface URLTokenService {

	URLToken createToken(Long userId);

	void deleteToken(String tokne, Long userId);

	URLToken validateToken(String token, Long userId) throws TokenInValidateException;
}
