package com.email.consumers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.email.dtos.EmailRecordDTO;
import com.email.models.EmailModel;
import com.email.services.EmailService;

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}")
    public void listenEmailQueue(@Payload EmailRecordDTO dto) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(dto, emailModel);

        emailService.sendEmail(emailModel);

    }

}
