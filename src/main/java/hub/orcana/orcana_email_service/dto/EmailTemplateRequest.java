package hub.orcana.orcana_email_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Map;

@Data
public class EmailTemplateRequest {

    @NotBlank(message = "Destinatário é obrigatório")
    @Email(message = "Email deve ter formato válido")
    private String destinatario;

    @NotBlank(message = "Nome do template é obrigatório")
    private String nomeTemplate;

    @NotNull(message = "Variáveis são obrigatórias")
    private Map<String, String> variaveis;
}
