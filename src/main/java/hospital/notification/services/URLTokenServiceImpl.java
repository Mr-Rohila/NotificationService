package hospital.notification.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import hospital.notification.exceptions.TokenInValidateException;
import hospital.notification.model.URLToken;
import hospital.notification.repository.URLTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class URLTokenServiceImpl implements URLTokenService {

	private final URLTokenRepository tokenRepository;

	@Override
	public URLToken createToken(Long userId) {
		return tokenRepository.save(new URLToken(userId));
	}

	@Override
	@Transactional
	public void deleteToken(String tokne) {
		this.tokenRepository.deleteByToken(tokne);
	}

	@Override
	public URLToken validateToken(String token) throws TokenInValidateException {
		URLToken resetToken = tokenRepository.findByToken(token).orElse(null);
		if (resetToken == null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			throw new TokenInValidateException("Link is invalid or expired");
		}
		return resetToken;
	}
}
