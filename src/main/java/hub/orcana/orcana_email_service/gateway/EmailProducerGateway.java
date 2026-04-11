package hub.orcana.orcana_email_service.gateway;

import hub.orcana.orcana_email_service.dto.EmailRequest;

public interface EmailProducerGateway {
    void publicarEmailParaFila(EmailRequest emailRequest);
}

