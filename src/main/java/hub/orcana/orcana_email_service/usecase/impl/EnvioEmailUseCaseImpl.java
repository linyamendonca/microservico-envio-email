package hub.orcana.orcana_email_service.usecase.impl;

import hub.orcana.orcana_email_service.dto.EmailRequest;
import hub.orcana.orcana_email_service.dto.EmailTemplateRequest;
import hub.orcana.orcana_email_service.usecase.EnvioEmailUseCase;
import hub.orcana.orcana_email_service.usecase.ValidacaoEmailUseCase;
import hub.orcana.orcana_email_service.gateway.EmailGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EnvioEmailUseCaseImpl implements EnvioEmailUseCase {

    @Autowired
    private EmailGateway emailGateway;

    @Autowired
    private ValidacaoEmailUseCase validacaoEmailUseCase;

    @Override
    public void enviarEmailSimples(EmailRequest emailRequest) {
        if (!validacaoEmailUseCase.validarFormatoEmail(emailRequest.destinatario())) {
            throw new IllegalArgumentException("Email inválido");
        }

        if (!validacaoEmailUseCase.validarConteudoEmail(emailRequest)) {
            throw new IllegalArgumentException("Conteúdo do email inválido");
        }

        if (!validacaoEmailUseCase.verificarBlacklistEmail(emailRequest.destinatario())) {
            throw new IllegalArgumentException("Email está na blacklist");
        }
    }
    @Override
    public void enviarEmailEmLote(List<EmailRequest> emailRequests) {
        for (EmailRequest email : emailRequests) {
            enviarEmailSimples(email);
        }
    }
}
