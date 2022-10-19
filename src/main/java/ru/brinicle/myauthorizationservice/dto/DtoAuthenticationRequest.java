package ru.brinicle.myauthorizationservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DtoAuthenticationRequest {


    @Schema()
    private String email;

    @Schema()
    private String password;
}