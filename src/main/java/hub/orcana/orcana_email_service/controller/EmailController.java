package hub.orcana.orcana_email_service.controller;

import hub.orcana.orcana_email_service.dto.EmailRequest;
import hub.orcana.orcana_email_service.usecase.EnvioEmailUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/email")
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final EnvioEmailUseCase envioEmailUseCase;

    @PostMapping("/simples")
    public ResponseEntity<String> enviarEmailSimples(@RequestBody Map<String, String> emailRequest) {
        try {
            EmailRequest email = new EmailRequest(
                    emailRequest.get("destinatario"),
                    emailRequest.get("assunto"),
                    emailRequest.get("mensagem"),
                    null
            );

            envioEmailUseCase.enviarEmailSimples(email);
            log.info("Email adicionado à fila com sucesso para: {}", email.destinatario());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Email adicionado à fila com sucesso");
        } catch (IllegalArgumentException e) {
            log.error("Erro de validação ao processar email: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro: " + e.getMessage());
        } catch (Exception e) {
            log.error("Erro ao processar email: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno ao processar email");
        }
    }
}
