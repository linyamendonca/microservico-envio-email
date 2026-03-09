package hub.orcana.orcana_email_service.service;

import hub.orcana.orcana_email_service.repository.TemplateEmailRepository;
import hub.orcana.orcana_email_service.table.TemplateEmail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;
import java.sql.Time;
import java.util.Map;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEmailRepository templateEmailRepository;

    public EmailService(JavaMailSender javaMailSender, TemplateEmailRepository templateEmailRepository) {
        this.javaMailSender = javaMailSender;
        this.templateEmailRepository = templateEmailRepository;
    }

    public void enviaEmailOrcamentoAprovado(String email, String nome, String codigoOrcamento, Double valor, Time tempo) {
        try {
            System.out.println("Iniciando envio de e-mail para: " + email);
            TemplateEmail templateEmail = templateEmailRepository.findByNomeTemplate("orcamento_aprovado")
                    .orElseThrow(() -> new RuntimeException("Template 'orcamento_aprovado' não encontrado"));

            System.out.println("Template encontrado: " + templateEmail.getNomeTemplate());

            String template = templateEmail.getCorpoEmail();
            String assunto = "Seu orçamento " + codigoOrcamento + " foi aprovado! - Júpiter Frito";

            String corpoEmail = template
                    .replace("${nomeCliente}", nome)
                    .replace("${codigoOrcamento}", codigoOrcamento)
                    .replace("${valor}", "R$ " + String.format("%.2f", valor))
                    .replace("${tempo}", tempo.toString());

            System.out.println("Enviando e-mail...");
            System.out.println("Para: " + email);
            System.out.println("Assunto: " + assunto);
            System.out.println("Corpo: " + corpoEmail.substring(0, Math.min(100, corpoEmail.length())) + "...");

            enviarEmail(email, assunto, corpoEmail);

            System.out.println("E-mail enviado com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro detalhado: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Erro ao enviar e-mail de orçamento aprovado", e);
        }
    }

    public void enviarEmail(String destinatario, String assunto, String texto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("orcanatechschool@gmail.com"); // e-mail da aplicação Brevo
        message.setTo(destinatario);
        message.setSubject(assunto);
        message.setText(texto);

        System.out.println("Enviando de: orcanatechschool@gmail.com para: " + destinatario);

        javaMailSender.send(message);
    } 

}
