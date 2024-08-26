package hospital.notification.services;

import org.springframework.stereotype.Service;

@Service
public interface ClientTokenService {
	String getAccessToken();

}
