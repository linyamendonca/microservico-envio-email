package hub.orcana.orcana_email_service.controller;

import hub.orcana.orcana_email_service.dto.EmailRequest;
import hub.orcana.orcana_email_service.gateway.EmailGateway;
import hub.orcana.orcana_email_service.usecase.EnvioEmailUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailGateway emailGateway;

    public EmailController(EmailGateway emailGateway) {
        this.emailGateway = emailGateway;
    }

    @PostMapping("/simples")
    public String enviarEmailSimples(@RequestBody Map<String, String> emailRequest) {
        try {
            emailGateway.enviarEmailSimples(
                    emailRequest.get("destinatario"),
                    emailRequest.get("assunto"),
                    emailRequest.get("texto")
            );
            return ResponseEntity.status(HttpStatus.OK).body("Email enviado com sucesso").toString();
        } catch (Exception IllegalArgumentException ) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + IllegalArgumentException.getMessage()).toString();
        }
    }
}
