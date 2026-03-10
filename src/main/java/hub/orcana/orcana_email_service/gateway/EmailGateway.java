package hub.orcana.orcana_email_service.gateway;

public interface EmailGateway {
    void enviarEmailSimples(String destinatario, String assunto, String texto);
}
