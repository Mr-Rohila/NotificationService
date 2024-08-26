package hospital.notification.services.clients;

import org.springframework.stereotype.Service;

import hospital.notification.dtos.ResponseDto;
import hospital.notification.dtos.UserDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorizationServerService {

	private final AuthorizationServerClient serverClient;

	@CircuitBreaker(name = AuthorizationServerClientConfig.SERVICE_NAME, fallbackMethod = "fallbackGetUser")
	@Retry(name = AuthorizationServerClientConfig.SERVICE_NAME)
	public ResponseDto<UserDto> getUser(Long userId) {
		return serverClient.getUser(userId);
	}

	// Fallback methods
	public ResponseDto<UserDto> fallbackGetUser(Long userId, Throwable ex) {
		return new ResponseDto<UserDto>(403, "Failed", null);
	}
}
