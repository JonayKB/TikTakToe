package es.iespuertodelacruz.jcr.tiktaktoe.shared.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.jcr.tiktaktoe.shared.utils.HTMLTemplates;
import jakarta.mail.internet.MimeMessage;

@Service
public class MailService {
	@Autowired
	private JavaMailSender sender;

	@Value("${mail.from}")
	private String mailfrom;

	public void send(String[] destinatarios, String asunto, String contenidoHtml) {
		try {
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

			helper.setFrom(mailfrom);
			helper.setTo(destinatarios);
			helper.setSubject(asunto);
			helper.setText(contenidoHtml, true);

			sender.send(message);

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error al enviar el correo electrónico", e);
		}
	}

	public void sentVerifyToken(String destinatario, String asunto, String token) {

		String html = String.format(HTMLTemplates.VERIFICATION_EMAIL, destinatario, token);

		send(new String[] { destinatario }, asunto, html);
	}

}