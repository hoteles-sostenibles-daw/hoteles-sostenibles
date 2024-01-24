package com.tfc.daw.emailHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    @Autowired
    private EmailService emailService;

    final String emailHotel = "hoteles.sostenibles.info@gmail.com";

    @PostMapping("/emailLanding")
    public ResponseEntity<String> sendEmailLanding(@RequestBody EmailContactUser user) {

        if (emailService.sendEmailLanding(createEmail(user))) {
            return new ResponseEntity<String>(HttpStatus.OK);
        }

        return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private EmailDetails createEmail(EmailContactUser user) {

        EmailDetails email = new EmailDetails();
        email.setRecipient(emailHotel);
        email.setSubject("nuevo contacto");
        email.setMsgBody("Persona de contacto: " + user.getNombre() + "\n" + "Email de contacto: " + user.getEmail());
        return email;
    }
}
