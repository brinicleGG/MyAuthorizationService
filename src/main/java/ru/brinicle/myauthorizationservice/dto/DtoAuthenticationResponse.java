package ru.brinicle.myauthorizationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DtoAuthenticationResponse {

    @Schema(description = "email",
            example = "user@mail.com",
            type = "string")
    private String email;

    @Schema(description = "token",
            example = "sgvV1rCAKCKJBA.DAOJsdfCAjsncjnoaoJOIh.JasdOAIadSCancF4oaio",
            type = "string")
    private String token;
}
