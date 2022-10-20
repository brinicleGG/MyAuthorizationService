package ru.brinicle.myauthorizationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DtoAuthenticationRequest {


    @Schema(description = "email",
            example = "admin@mail.com",
            type = "string")
    private String email;

    @Schema(description = "password",
            example = "admin",
            type = "string")
    private String password;
}