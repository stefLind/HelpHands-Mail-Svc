package app.web.dto;

import app.model.EmailNotificationStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EmailNotificationResponse {

    private String subject;

    private EmailNotificationStatus status;

    private LocalDateTime createdOn;
}
