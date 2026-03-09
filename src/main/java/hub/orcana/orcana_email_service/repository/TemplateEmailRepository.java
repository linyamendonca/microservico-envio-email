package hub.orcana.orcana_email_service.repository;

import hub.orcana.orcana_email_service.table.TemplateEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TemplateEmailRepository extends JpaRepository<TemplateEmail, Long> {
    Optional<TemplateEmail> findByNomeTemplate(String nomeTemplate);
}
