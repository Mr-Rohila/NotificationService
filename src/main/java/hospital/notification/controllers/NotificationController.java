package hospital.notification.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hospital.notification.dtos.NotificationDto;
import hospital.notification.dtos.ResponseDto;
import hospital.notification.services.EmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/notification")
public class NotificationController {

	private final EmailService emailService;

	@PostMapping("send")
	public ResponseDto<String> sendEmail(@Valid @RequestBody NotificationDto dto) {
		return new ResponseDto<String>("Success", emailService.sendEmail(dto, "Service Name"));
	}
}