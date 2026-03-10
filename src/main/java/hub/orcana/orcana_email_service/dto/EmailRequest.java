package hub.orcana.orcana_email_service.dto;

public record EmailRequest(
        String destinatario,
        String assunto,
        String mensagem,
        String template

) {}
