package hospital.notification.dtos;

import java.util.Date;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.Data;

@Data
public class ResponseDto<T> {

	private Date timestamp = new Date();
	private int status;
	private String message;
	private String path;
	private T data;

	public ResponseDto(String message, T data) {
		this.timestamp = new Date();
		this.status = 200;
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes != null)
			this.path = attributes.getRequest().getRequestURI();

		this.message = message;
		this.data = data;
	}
}