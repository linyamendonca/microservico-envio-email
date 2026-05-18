package hub.orcana.orcana_email_service.usecase.impl;

import hub.orcana.orcana_email_service.dto.EmailRequest;
import hub.orcana.orcana_email_service.usecase.ValidacaoEmailUseCase;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;

@Service
public class ValidacaoEmailUseCaseImpl implements ValidacaoEmailUseCase {

    private static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public boolean validarFormatoEmail(String email) {
        return email != null && pattern.matcher(email).matches();
    }

    @Override
    public boolean validarConteudoEmail(EmailRequest emailRequest) {
        return emailRequest != null &&
                emailRequest.destinatario() != null &&
                emailRequest.assunto() != null &&
                !emailRequest.assunto().isEmpty() &&
                emailRequest.texto() != null &&
                !emailRequest.texto().isEmpty();
    }

}
