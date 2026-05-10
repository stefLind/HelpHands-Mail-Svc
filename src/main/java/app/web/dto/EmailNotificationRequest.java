package app.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class EmailNotificationRequest {

    @NotNull
    UUID userId;

    @NotBlank
    private String subject;

    @NotBlank
    private String body;

}
