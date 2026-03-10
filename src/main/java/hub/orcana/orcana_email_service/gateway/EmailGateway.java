package hub.orcana.orcana_email_service.gateway;

import hub.orcana.orcana_email_service.dto.EmailTemplateRequest;

public interface EmailGateway {
    void enviarEmailSimples(String destinatario, String assunto, String texto);
}