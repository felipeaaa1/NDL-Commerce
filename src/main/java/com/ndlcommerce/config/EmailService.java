package com.ndlcommerce.config;

import com.ndlcommerce.exception.EmailSendException;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import java.util.UUID;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Value("${spring.sendgrid.api-key}")
  private String sendGridApiKey;

  @Value("${app.domain}")
  private String dominio;

  private static final Logger LOGGER = LogManager.getLogger();

  public void sendValidationEmail(String recipent, String subject, UUID token) throws IOException {
    Email senderEmail = new Email("felipe.arnaud.alvves@gmail.com"); // Email do remetente
    Email recipentEmail = new Email(recipent); // Email do destinatário

    String validationLink = "http://" + dominio + "/auth/verifyEmail?token=" + token;

    String htmlContent =
        """
        <!DOCTYPE html>
        <html lang="pt-BR">
        <head>
            <meta charset="UTF-8">
            <title>Validação de e-mail</title>
        </head>
        <body style="font-family: Arial, Helvetica, sans-serif; background-color: #f6f6f6; padding: 20px;">
            <table width="100%%" cellpadding="0" cellspacing="0">
                <tr>
                    <td align="center">
                        <table width="600" cellpadding="20" cellspacing="0"
                               style="background-color: #ffffff; border-radius: 6px;">
                            <tr>
                                <td>
                                    <h2 style="color: #333;">Confirme seu endereço de e-mail</h2>

                                    <p style="color: #555; font-size: 14px;">
                                        Você precisa confirmar seu endereço de e-mail para continuar utilizando sua conta.
                                    </p>

                                    <p style="color: #555; font-size: 14px;">
                                        Clique no botão abaixo para validar seu e-mail:
                                    </p>

                                    <p style="text-align: center; margin: 30px 0;">
                                        <a href="%s"
                                           style="
                                               background-color: #4CAF50;
                                               color: #ffffff;
                                               padding: 12px 20px;
                                               text-decoration: none;
                                               border-radius: 4px;
                                               font-weight: bold;
                                               display: inline-block;
                                           ">
                                            Validar e-mail
                                        </a>
                                    </p>

                                    <p style="color: #555; font-size: 13px;">
                                        Se você não solicitou esta verificação, ignore este e-mail.
                                    </p>

                                    <p style="color: #999; font-size: 12px; margin-top: 30px;">
                                        Caso o botão não funcione, copie e cole o link abaixo no seu navegador:
                                        <br>
                                        <a>%s</a>
                                    </p>

                                    <hr style="border: none; border-top: 1px solid #eee; margin: 30px 0;">

                                    <p style="color: #999; font-size: 12px;">
                                        Este e-mail foi enviado automaticamente. Por favor, não responda.
                                    </p>
                                </td>
                            </tr>
                        </table>

                        <p style="color: #aaa; font-size: 11px; margin-top: 10px;">
                            © 2026 NDL Commerce
                        </p>
                    </td>
                </tr>
            </table>
        </body>
        </html>
        """
            .formatted(validationLink, validationLink);
    //        TODO: colocar o dominio aqui
    Content content = new Content("text/html", htmlContent);
    Mail mail = new Mail(senderEmail, subject, recipentEmail, content);

    SendGrid sendGrid = new SendGrid(sendGridApiKey);
    Request request = new Request();

    LOGGER.info("Preparing email validation for ");

    request.setMethod(Method.POST);
    request.setEndpoint("mail/send");
    request.setBody(mail.build());
    Response response = sendGrid.api(request);

    int statusCode = response.getStatusCode();
    if (response.getStatusCode() >= 300) {
      LOGGER.error(
          "Failed to send validation email. statusCode={}, body={}",
          response.getStatusCode(),
          response.getBody());
      throw new EmailSendException(
          "Failed to send validation email. SendGrid response: " + response.getBody());
    }

    LOGGER.warn(
        "Email validation sent successfully. statusCode={}, recipient={}",
        response.getStatusCode(),
        recipent);
  }
}
