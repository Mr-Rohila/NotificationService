package hospital.notification.services;

import org.springframework.stereotype.Service;

import hospital.notification.exceptions.TokenInValidateException;
import hospital.notification.model.URLToken;

@Service
public interface URLTokenService {

	URLToken createToken(Long userId);

	void deleteToken(String tokne);

	URLToken validateToken(String token) throws TokenInValidateException;
}
