package hospital.notification.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hospital.notification.dtos.NotificationDto;
import hospital.notification.dtos.ResponseDto;
import hospital.notification.exceptions.TokenInValidateException;
import hospital.notification.services.EmailService;
import hospital.notification.services.URLTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notification")
public class NotificationController {

	private final EmailService emailService;

	private final URLTokenService urlTokenService;

	@PostMapping("send")
	@PreAuthorize("hasAuthority('SCOPE_system')")
	public ResponseDto<String> sendEmail(@Valid @RequestBody NotificationDto dto) throws Exception {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		return new ResponseDto<>("Success", emailService.sendEmail(dto, name));
	}

	@PostMapping("validate")
	@PreAuthorize("hasAuthority('SCOPE_system')")
	public ResponseDto<?> tokenDetails(@RequestParam String notificationToken) {
		try {
			return new ResponseDto<>("Success", urlTokenService.validateToken(notificationToken));
		} catch (TokenInValidateException e) {
			ResponseDto<String> responseDto = new ResponseDto<>("Failed", e.getLocalizedMessage());
			responseDto.setStatus(404);
			return responseDto;
		}
	}

	@PostMapping("deleteToken")
	@PreAuthorize("hasAuthority('SCOPE_system')")
	public ResponseDto<String> deleteToken(@RequestParam String notificationToken) {
		urlTokenService.deleteToken(notificationToken);
		return new ResponseDto<String>("Success", "Token Delete Success");
	}

}