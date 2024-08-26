package hospital.notification.services.clients;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;

@Configuration
public class AuthorizationServerClientConfig {

	public static final String SERVICE_NAME = "AUTHORIZATION-SERVER";

	@Bean
	@Primary
	CircuitBreakerConfig customCircuitBreakerConfig() {
		return CircuitBreakerConfig.custom().failureRateThreshold(30).waitDurationInOpenState(Duration.ofMillis(1000))
				.slidingWindowSize(5).build();
	}

	@Bean
	@Primary
	RetryConfig customRetryConfig() {
		return RetryConfig.custom().maxAttempts(3).waitDuration(Duration.ofMillis(500)).build();
	}

	@Bean
	CircuitBreakerRegistry circuitBreakerRegistry(CircuitBreakerConfig customCircuitBreakerConfig) {
		return CircuitBreakerRegistry.of(customCircuitBreakerConfig);
	}

	@Bean
	RetryRegistry retryRegistry(RetryConfig customRetryConfig) {
		return RetryRegistry.of(customRetryConfig);
	}
}
