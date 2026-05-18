package hub.orcana.orcana_email_service.usecase;

import hub.orcana.orcana_email_service.dto.EmailRequest;

public interface ValidacaoEmailUseCase {
    boolean validarFormatoEmail(String email);
    boolean validarConteudoEmail(EmailRequest emailRequest);
}
