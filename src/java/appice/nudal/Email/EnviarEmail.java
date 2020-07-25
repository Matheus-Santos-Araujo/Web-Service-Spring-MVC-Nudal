package appice.nudal.Email;
import java.util.Properties; 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class EnviarEmail{
        private String emailAppice;    
        private String mailSMTPserver;
        private String mailSMTPserverPort;
        private String mailSenha;
 
        public void enviar(String to, String subject,String message) {
 
                Properties props = new Properties();
 
                mailSMTPserverPort = "587";
                //Outlook
                //emailAppice = "contato.nudal@outlook.com";
                //mailSMTPserver = "smtp-mail.outlook.com";
                //mailSenha = "administrador_nudal_uece";
                //SendGrid
                 //sendgrid
                emailAppice = "apikey";
                mailSMTPserver = "smtp.sendgrid.net";
                mailSenha = "";
 
                props.put("mail.transport.protocol", "smtp"); // define protocolo de envio como SMTP
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", mailSMTPserver); // server SMTP do GMAIL
                props.put("mail.smtp.auth", "true"); // ativa autenticacao
                props.put("mail.smtp.user", emailAppice); // usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)
                //props.put("mail.smtp.ehlo", false);
                props.put("mail.debug", "true");
                props.put("mail.smtp.port", mailSMTPserverPort); // porta
                props.put("mail.smtp.socketFactory.port", mailSMTPserverPort); // mesma porta para o socket
                //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                props.put("mail.smtp.socketFactory.fallback", "false");
                // Cria um autenticador que sera usado a seguir
                SimpleAuth auth = null;
                auth = new SimpleAuth(emailAppice, mailSenha);
 
                // Session - objeto que ira realizar a conex�o com o servidor
                /*
                 * Como h� necessidade de autentica��o � criada uma autenticacao que �
                 * responsavel por solicitar e retornar o usu�rio e senha para
                 * autentica��o
                 */
                Session session = Session.getInstance(props, auth);
                session.setDebug(true); // Habilita o LOG das a��es executadas durante o envio do email
 
                // Objeto que cont�m a mensagem
                Message msg = new MimeMessage(session);
 
                try {
                        // Setando o destinat�rio
                        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
                        // Setando a origem do email
                        msg.setFrom(new InternetAddress("Nudal-Uece"));
                        // Setando o assunto
                        msg.setSubject(subject);
                        // Setando o conte�do/corpo do email
                        msg.setContent(message, "text/html;charset=UTF-8");
                        // Setando anexo
                        // FileDataSource fds = new
                        // FileDataSource("C:\\Users\\rbrasil\\Documents\\tre.pptx");
                        // msg.setDataHandler(new DataHandler(fds));
                        // msg.setFileName(fds.getName());
                       
 
                } catch (Exception e) {
                        System.out.println(">> Erro: Completar Mensagem");
                        e.printStackTrace();
                }
 
                // Objeto encarregado de enviar os dados para o email
                Transport tr;
                try {
                        tr = session.getTransport("smtp"); // define smtp para transporte
                        /*
                         * 1 - define o servidor smtp 2 - seu nome de usuario do gmail 3 -
                         * sua senha do gmail
                         */
                        tr.connect(mailSMTPserver, emailAppice, mailSenha);
                        msg.saveChanges(); // don't forget this
                        // envio da mensagem
                        tr.sendMessage(msg, msg.getAllRecipients());
                        tr.close();
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        System.out.println(">> Erro: Envio Mensagem");
                        e.printStackTrace();
                }
 
        }
}
 
// clase que retorna uma autenticacao para ser enviada e verificada pelo
// servidor smtp
class SimpleAuth extends Authenticator {
        public String username = null;
        public String password = null;
 
        public SimpleAuth(String user, String pwd) {
                username = user;
                password = pwd;
        }
 
        protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
        }
}
