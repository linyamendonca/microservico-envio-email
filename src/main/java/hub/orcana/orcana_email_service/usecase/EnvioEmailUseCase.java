package hub.orcana.orcana_email_service.usecase;

import hub.orcana.orcana_email_service.dto.EmailRequest;
import hub.orcana.orcana_email_service.dto.EmailTemplateRequest;

import java.util.List;

public interface EnvioEmailUseCase {
    void enviarEmailSimples(EmailRequest emailRequest);
    void enviarEmailEmLote(List<EmailRequest> emailRequests);
}
