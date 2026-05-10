package app.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class ContactDetailsRequest {

    @NotNull
    private UUID userId;

    private String contactInfo;
}
