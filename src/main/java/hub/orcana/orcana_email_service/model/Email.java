package hub.orcana.orcana_email_service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private String destinatario;
    private String assunto;
    private String corpoEmail;
    private String nomeTemplate;
}
