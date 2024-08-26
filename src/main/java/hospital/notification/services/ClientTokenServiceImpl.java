package hospital.notification.services;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClientTokenServiceImpl implements ClientTokenService {

	private final RestTemplate restTemplate;

	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	private String issuerUri;

	final String clientId = "NotificationService";
	final String clientSecret = "12345";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public String getAccessToken() {
		String url = issuerUri + "/oauth2/token";
		String auth = clientId + ":" + clientSecret;
		String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes(StandardCharsets.UTF_8));
		String authHeader = "Basic " + encodedAuth;

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/x-www-form-urlencoded");
		headers.set("Authorization", authHeader);

		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "client_credentials");
		body.add("scope", "system");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

		ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);

		Map<String, Object> responseBody = response.getBody();
		if (responseBody != null && responseBody.containsKey("access_token")) {
			return (String) responseBody.get("access_token");
		} else {
			throw new RuntimeException("Failed to retrieve access token");
		}
	}

}
