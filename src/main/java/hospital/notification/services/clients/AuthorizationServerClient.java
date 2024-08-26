package hospital.notification.services.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import hospital.notification.dtos.ResponseDto;
import hospital.notification.dtos.UserDto;

@FeignClient(name = AuthorizationServerClientConfig.SERVICE_NAME, configuration = AuthorizationServerClientConfig.class)
public interface AuthorizationServerClient {

	@GetMapping("public/api/v1/user/{userId}")
	public ResponseDto<UserDto> getUser(@PathVariable Long userId);
}
