package com.users.producers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.users.dtos.EmailDTO;
import com.users.models.UserModel;

import org.springframework.beans.factory.annotation.Value;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishMessageEmail(UserModel userModel) {

        try {
            var emailDTO = new EmailDTO();
            emailDTO.setUserId(userModel.getId());
            emailDTO.setEmailTo(userModel.getEmail());
            emailDTO.setSubject("CADASTRO EFETUADO!");
            emailDTO.setText("Seja bem-vindo(a) " + userModel.getName() + "!");

            rabbitTemplate.convertAndSend("", routingKey, emailDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
