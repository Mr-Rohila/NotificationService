package hospital.notification.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import hospital.notification.exceptions.TokenInValidateException;
import hospital.notification.model.URLToken;
import hospital.notification.repository.URLTokenRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class URLTokenServiceImpl implements URLTokenService {

	private URLTokenRepository tokenRepository;

	@Override
	public URLToken createToken(Long userId) {
		return tokenRepository.save(new URLToken(userId));
	}

	@Override
	public void deleteToken(String tokne, Long userId) {
		this.tokenRepository.deleteByTokenAndUserId(tokne, userId);
	}

	@Override
	public URLToken validateToken(String token, Long userId) throws TokenInValidateException {
		URLToken resetToken = tokenRepository.findByToken(token).orElse(null);
		if (resetToken == null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			throw new TokenInValidateException("Token is invalid or expired");
		}
		return resetToken;
	}
}
