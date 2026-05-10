package app.web.mapper;

import app.model.ContactDetails;
import app.model.EmailNotification;
import app.web.dto.ContactDetailsResponse;
import app.web.dto.EmailNotificationResponse;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DtoMapper {

    public static EmailNotificationResponse fromEmailNotification(EmailNotification entity) {
        return EmailNotificationResponse.builder()
                .subject(entity.getSubject())
                .status(entity.getStatus())
                .createdOn(entity.getCreatedOn())
                .build();
    }

    public static ContactDetailsResponse fromContactDetails(ContactDetails entity) {
        return ContactDetailsResponse.builder()
                .id(entity.getId())
                .contactInfo(entity.getContactInfo())
                .userId(entity.getUserId())
                .build();
    }
}
