package app.web;

import app.model.ContactDetails;
import app.model.EmailNotification;
import app.service.EmailNotificationService;
import app.web.dto.ContactDetailsRequest;
import app.web.dto.ContactDetailsResponse;
import app.web.dto.EmailNotificationRequest;
import app.web.dto.EmailNotificationResponse;
import app.web.mapper.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/email-notifications")
public class EmailNotificationController {

    private final EmailNotificationService emailNotificationService;

    @Autowired
    public EmailNotificationController(EmailNotificationService emailNotificationService) {
        this.emailNotificationService = emailNotificationService;
    }

    @PostMapping
    public ResponseEntity<EmailNotificationResponse> sendMail(@RequestBody EmailNotificationRequest notificationRequest) {
        EmailNotification emailNotification = emailNotificationService.sendMail(notificationRequest);
        EmailNotificationResponse notificationResponse = DtoMapper.fromEmailNotification(emailNotification);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notificationResponse);
    }

    @PostMapping("/contact-details")
    public ResponseEntity<ContactDetailsResponse> modifyUserContactDetails(@RequestBody ContactDetailsRequest contactDetailsRequest) {
        ContactDetails contactDetails = emailNotificationService.createOrUpdateUserContactDetails(contactDetailsRequest);

        ContactDetailsResponse responseDto = DtoMapper.fromContactDetails(contactDetails);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseDto);
    }

    @GetMapping("/contact-details")
    public ResponseEntity<ContactDetailsResponse> getUserContactDetails(@RequestParam(name = "userId") UUID userId) {
        ContactDetails contactDetails = emailNotificationService.getContactDetailsByUserId(userId);

        ContactDetailsResponse responseDto = DtoMapper.fromContactDetails(contactDetails);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }

}
