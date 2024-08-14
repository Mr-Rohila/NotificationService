package hospital.notification.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private TemplateEngine templateEngine;

	public void sendVerificationEmail(String to, String verificationLink) {
		Context context = new Context();
		context.setVariable("verificationLink", verificationLink);
		context.setVariable("logoUrl", "YOUR_LOGO_URL");
		context.setVariable("companyName", "Your Company Name");

		String htmlContent = templateEngine.process("verification-email", context);

		MimeMessage message = emailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setTo(to);
			helper.setSubject("Please Verify Your Email Address");
			helper.setText(htmlContent, true);
			emailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
