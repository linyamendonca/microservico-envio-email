package hub.orcana.orcana_email_service.controller;

import hub.orcana.orcana_email_service.gateway.EmailGateway;
import hub.orcana.orcana_email_service.gateway.impl.EmailGatewayImpl;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailGateway emailGateway;

    public EmailController(EmailGateway emailGateway) {
        this.emailGateway = emailGateway;
    }

    @PostMapping("/simples")
    public String enviarEmailSimples(@RequestBody Map<String, String> emailRequest) {
        emailGateway.enviarEmailSimples(
                emailRequest.get("destinatario"),
                emailRequest.get("assunto"),
                emailRequest.get("texto")
        );

        return "E-mail enviado com sucesso";
    }
}
