package app.service;

import app.model.ContactDetails;
import app.model.EmailNotification;
import app.model.EmailNotificationStatus;
import app.repository.ContactDetailsRepository;
import app.repository.EmailNotificationRepository;
import app.web.dto.ContactDetailsRequest;
import app.web.dto.EmailNotificationRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class EmailNotificationService {

    private final EmailNotificationRepository notificationRepository;
    private final ContactDetailsRepository contactDetailsRepository;
    private final JavaMailSender mailSender;

    @Autowired
    public EmailNotificationService(EmailNotificationRepository notificationRepository,
                                    ContactDetailsRepository contactDetailsRepository,
                                    JavaMailSender mailSender) {
        this.notificationRepository = notificationRepository;
        this.contactDetailsRepository = contactDetailsRepository;
        this.mailSender = mailSender;
    }

    public EmailNotification sendMail(EmailNotificationRequest notificationRequest) {
        UUID userId = notificationRequest.getUserId();
        ContactDetails contactDetails = getContactDetailsByUserId(userId);

        if (contactDetails.getContactInfo() == null) {
            throw new IllegalArgumentException("User with id %s does not have valid contact details.".formatted(userId.toString()));
        }

        EmailNotification notification = EmailNotification.builder()
                .userId(userId)
                .subject(notificationRequest.getSubject())
                .body(notificationRequest.getBody())
                .createdOn(LocalDateTime.now())
                .build();

        try {
            MimeMessage mailMessage = getMailMessage(notificationRequest, contactDetails);
            mailSender.send(mailMessage);
            notification.setStatus(EmailNotificationStatus.SUCCESS);
        } catch (Exception e) {
            notification.setStatus(EmailNotificationStatus.FAILURE);
            log.warn("Failed to send mail to %s due to %s.".formatted(contactDetails.getContactInfo(), e.getMessage()));
        }

        return notificationRepository.save(notification);
    }

    private MimeMessage getMailMessage(EmailNotificationRequest notificationRequest, ContactDetails contactDetails) throws MessagingException {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage, "UTF-8");
        helper.setTo(contactDetails.getContactInfo());
        helper.setSubject(notificationRequest.getSubject());
        helper.setText(notificationRequest.getBody(), true);
        mailSender.send(mailMessage);
        return mailMessage;
    }

    public ContactDetails getContactDetailsByUserId(UUID userId) {
        return contactDetailsRepository.findByUserId(userId)
                .orElseThrow(() -> new NullPointerException("Contact details for user with id [%s] was not found.".formatted(userId.toString())));
    }

    public ContactDetails createOrUpdateUserContactDetails(ContactDetailsRequest contactDetailsRequest) {
        // Check if a user contact information exists in the DB
        Optional<ContactDetails> optionalUserDetails = contactDetailsRepository.findByUserId(contactDetailsRequest.getUserId());

        // If exists then update it
        if (optionalUserDetails.isPresent()) {
            ContactDetails contactDetails = optionalUserDetails.get();
            contactDetails.setContactInfo(contactDetailsRequest.getContactInfo());
            contactDetails.setUpdatedOn(LocalDateTime.now());

            return contactDetailsRepository.save(contactDetails);
        }

        // If it does not exist - create new one
        ContactDetails contactDetails = ContactDetails.builder()
                .userId(contactDetailsRequest.getUserId())
                .contactInfo(contactDetailsRequest.getContactInfo())
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();

        return contactDetailsRepository.save(contactDetails);

    }
}
