package hub.orcana.orcana_email_service.gateway.impl;

import hub.orcana.orcana_email_service.dto.EmailRequest;
import hub.orcana.orcana_email_service.gateway.EmailProducerGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailProducerGatewayImpl implements EmailProducerGateway {

    private final RabbitTemplate rabbitTemplate;

    @Value("${broker.exchange.name}")
    private String exchangeName;

    @Value("${broker.routing.key}")
    private String routingKey;

    @Override
    public void publicarEmailParaFila(EmailRequest emailRequest) {
        try {
            rabbitTemplate.convertAndSend(exchangeName, routingKey, emailRequest);
            log.info("Email publicado na fila com sucesso. Destinatário: {}", emailRequest.destinatario());
        } catch (Exception e) {
            log.error("Erro ao publicar email na fila para destinatário: {}", emailRequest.destinatario(), e);
            throw new RuntimeException("Erro ao publicar email na fila", e);
        }
    }
}

