package hub.orcana.orcana_email_service.usecase.impl;

import hub.orcana.orcana_email_service.dto.EmailRequest;
import hub.orcana.orcana_email_service.usecase.EnvioEmailUseCase;
import hub.orcana.orcana_email_service.usecase.ValidacaoEmailUseCase;
import hub.orcana.orcana_email_service.gateway.EmailProducerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EnvioEmailUseCaseImpl implements EnvioEmailUseCase {

    private final EmailProducerGateway emailProducerGateway;
    private final ValidacaoEmailUseCase validacaoEmailUseCase;

    @Override
    public void enviarEmailSimples(EmailRequest emailRequest) {
        if (!validacaoEmailUseCase.validarFormatoEmail(emailRequest.destinatario())) {
            throw new IllegalArgumentException("Email inválido");
        }

        if (!validacaoEmailUseCase.validarConteudoEmail(emailRequest)) {
            throw new IllegalArgumentException("Conteúdo do email inválido");
        }
<<<<<<< HEAD

        if (!validacaoEmailUseCase.verificarBlacklistEmail(emailRequest.destinatario())) {
            throw new IllegalArgumentException("Email está na blacklist");
        }

        log.info("Validações passaram. Publicando email na fila para: {}", emailRequest.destinatario());
        emailProducerGateway.publicarEmailParaFila(emailRequest);
=======
>>>>>>> 7c061f4 (subindo teste)
    }

    @Override
    public void enviarEmailEmLote(List<EmailRequest> emailRequests) {
        log.info("Iniciando envio em lote de {} emails", emailRequests.size());
        for (EmailRequest email : emailRequests) {
            try {
                enviarEmailSimples(email);
            } catch (Exception e) {
                log.error("Erro ao processar email para: {}", email.destinatario(), e);
            }
        }
    }
}
