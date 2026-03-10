package hub.orcana.orcana_email_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateEmail {
    private Integer idTemplate;
    private String nomeTemplate;
    private String assunto;
    private String corpoEmail;
}
