package hub.orcana.orcana_email_service.repository;

import hub.orcana.orcana_email_service.entity.TemplateEmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TemplateEmailRepository extends JpaRepository<TemplateEmailEntity, Long> {
    Optional<TemplateEmailEntity> findByNomeTemplate(String nomeTemplate);
}
