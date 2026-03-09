package hub.orcana.orcana_email_service.service;

import hub.orcana.orcana_email_service.table.TemplateEmail;
import hub.orcana.orcana_email_service.repository.TemplateEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TemplateEmailService {

    @Autowired
    private TemplateEmailRepository templateRepository;

    public TemplateEmail obterTemplate(String nomeTemplate) {
        return templateRepository.findByNomeTemplate(nomeTemplate)
                .orElseThrow(() -> new RuntimeException("Template não encontrado: " + nomeTemplate));
    }

    public String processarTemplate(String nomeTemplate, Map<String, String> variaveis) {
        TemplateEmail template = obterTemplate(nomeTemplate);
        String html = template.getCorpoEmail();

        // Substitui variáveis ${nomeVariavel}
        for (Map.Entry<String, String> entry : variaveis.entrySet()) {
            html = html.replace("${" + entry.getKey() + "}", entry.getValue());
        }

        return html;
    }
}
