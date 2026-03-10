package hub.orcana.orcana_email_service.usecase;

import hub.orcana.orcana_email_service.model.TemplateEmail;
import java.util.List;
import java.util.Optional;

public interface TemplateEmailUseCase {
    TemplateEmail criarTemplate(TemplateEmail templateEmail);
    Optional<TemplateEmail> buscarTemplatePorId(Integer id);
    List<TemplateEmail> listarTodosTemplates();
    TemplateEmail atualizarTemplate(Integer id, TemplateEmail templateEmail);
    void removerTemplate(Integer id);
    List<TemplateEmail> buscarTemplatesPorNome(String nome);
}
