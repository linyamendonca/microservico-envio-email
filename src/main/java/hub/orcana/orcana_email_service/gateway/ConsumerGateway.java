package hub.orcana.orcana_email_service.gateway;

import hub.orcana.orcana_email_service.dto.EmailRequest;
import hub.orcana.orcana_email_service.gateway.impl.EmailGatewayImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerGateway {

    private final EmailGatewayImpl emailGateway;

    @RabbitListener(queues = "${broker.queue.name}")
    public void receberEmailDaFila(EmailRequest emailRequest) {
        try {
            log.info("Recebendo email da fila para: {}", emailRequest.destinatario());

            // Enviar email através do gateway SMTP
            emailGateway.enviarEmailSimples(
                    emailRequest.destinatario(),
                    emailRequest.assunto(),
                    emailRequest.mensagem()
            );

            log.info("Email enviado com sucesso para: {}", emailRequest.destinatario());
        } catch (Exception e) {
            log.error("Erro ao processar email para: {}", emailRequest.destinatario(), e);
            throw new RuntimeException("Erro ao processar email da fila", e);
        }
    }
}
