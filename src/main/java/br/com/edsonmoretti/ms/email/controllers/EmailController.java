package br.com.edsonmoretti.ms.email.controllers;

import br.com.edsonmoretti.ms.email.dtos.EmailDto;
import br.com.edsonmoretti.ms.email.models.Email;
import br.com.edsonmoretti.ms.email.services.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<Email> send(@RequestBody @Valid EmailDto emailDto) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDto, email);
        emailService.send(email);
        return new ResponseEntity<>(email, HttpStatus.OK);
    }
}
