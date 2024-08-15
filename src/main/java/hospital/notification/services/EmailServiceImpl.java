package hospital.notification.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import hospital.notification.dtos.NotificationDto;
import hospital.notification.dtos.UserDto;
import hospital.notification.enums.NotificationStatus;
import hospital.notification.model.Notification;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

	@Value("classpath:/static/public/images/logo.png")
	private Resource logoResource;

	private final JavaMailSender emailSender;

	private final TemplateEngine templateEngine;

	private final NotificationService notificationService;

	@Override
	public String sendEmail(NotificationDto dto, String serviceName) {
		// save notification first
		Notification notification = notificationService.saveNotification(dto, serviceName);
		try {
			UserDto user = getUser(dto.getUserId());
			notification.setTo(user.getEmailId());
			notification.setUserName(user.getFullName());
			MimeMessage message = createMail(notification);
			emailSender.send(message);
			notification.setStatus(NotificationStatus.DELIVERED);
			// update notification
			notificationService.saveNotification(notification);
			return "Mail Delivered";
		} catch (Exception e) {
			notification.setError(e.getMessage());
			notification.setStatus(NotificationStatus.FAILED);
			// update notification
			notificationService.saveNotification(notification);
			return "Mail Schedule";
		}
	}

	private MimeMessage createMail(Notification notification) throws MessagingException {
		Context context = new Context();
		String logoUrl = "cid:logo";
		context.setVariable("logoUrl", logoUrl);
		if (StringUtils.hasText(notification.getRedirectUrl()))
			context.setVariable("redirectUrl", notification.getRedirectUrl());
		if (StringUtils.hasText(notification.getUserName()))
			context.setVariable("name", notification.getUserName());

		String htmlContent = templateEngine.process(notification.getTemplateName(), context);
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		helper.setTo(notification.getTo());
		helper.setSubject(notification.getSubject());
		helper.setText(htmlContent, true);
		addLogo(helper);
		return message;
	}

	void addLogo(MimeMessageHelper helper) throws MessagingException {
		FileSystemResource logoFile;
		try {
			logoFile = new FileSystemResource(logoResource.getFile());
			helper.addInline("logo", logoFile);
		} catch (IOException e) {
		}
	}

	private UserDto getUser(Long userId) {
		// get the user from Authorization server using rest Template
		UserDto dto = new UserDto();
		dto.setEmailId("ankushjawla40@gmail.com");
		dto.setFullName("Ankuhs Rohila Rajput");
		return dto;
	}
	
	private 
}