package hub.orcana.orcana_email_service.controller;

import hub.orcana.orcana_email_service.table.OrcamentoRequest;
import hub.orcana.orcana_email_service.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Time;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/orcamento/aprovado")
    public ResponseEntity<String> enviarEmailOrcamentoAprovado(@RequestBody OrcamentoRequest request) {
        try {
            Time sqlTime = Time.valueOf(request.getTempo());
            emailService.enviaEmailOrcamentoAprovado(request.getEmail(), request.getNome(),
                    request.getCodigo(), request.getValor(), sqlTime);
            return ResponseEntity.ok("E-mail de orçamento aprovado enviado");
        } catch (Exception e) {
            e.printStackTrace(); // Adicione esta linha para ver o erro completo
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

}
