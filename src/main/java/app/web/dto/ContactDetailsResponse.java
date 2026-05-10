package app.web.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ContactDetailsResponse {

    private UUID id;

    private UUID userId;

    private String contactInfo;
}
