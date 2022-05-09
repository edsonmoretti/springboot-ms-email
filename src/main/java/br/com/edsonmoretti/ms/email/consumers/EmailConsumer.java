package br.com.edsonmoretti.ms.email.consumers;

import br.com.edsonmoretti.ms.email.dtos.EmailDto;
import br.com.edsonmoretti.ms.email.models.Email;
import br.com.edsonmoretti.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmailConsumer {
    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        email.setCreatedAt(LocalDateTime.now());
        emailService.send(email);
        System.out.println("Email status: " + email.getStatus());
    }
}
