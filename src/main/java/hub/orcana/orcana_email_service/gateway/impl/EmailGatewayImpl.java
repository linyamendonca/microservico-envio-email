package hub.orcana.orcana_email_service.gateway.impl;

import hub.orcana.orcana_email_service.gateway.EmailGateway;
import hub.orcana.orcana_email_service.usecase.EnvioEmailUseCase;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailGatewayImpl implements EmailGateway {

    private final JavaMailSender mailSender;

    public EmailGatewayImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarEmailSimples(String destinatario, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("orcanatechschool@gmail.com");
        message.setTo(destinatario);
        message.setSubject(assunto);
        message.setText(texto);
        mailSender.send(message);
    }
}
